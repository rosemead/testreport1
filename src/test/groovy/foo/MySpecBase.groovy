package foo

import geb.Browser
import geb.report.ReporterSupport
import geb.spock.GebReportingSpec
import spock.lang.Shared

class MySpecBase extends GebReportingSpec {
    @Shared
    boolean inSetupSpec, inCleanupSpec

    private int gebReportingPerTestCounter = 1
    @Shared
    private int _gebReportingSpecTestCounter = 0

    def setupSpec() {
        inCleanupSpec = false
        inSetupSpec = true
    }
        def setup() {
        inSetupSpec=false
        storeSpecLabel()
    }

    def storeSpecLabel() {
        Browser.metaClass.takeAReport = { owner.report(it) }
        browser.metaClass = null
    }

    @Override
    void report(String label) {
        String reportLabel
        if (gebReportingSpecTestName) {
            /**
             * reporting from a feature. Just update indice
             */
            reportLabel = createReportLabel(label)
            browser.report(reportLabel)
            def (featureNum, reportNum) = reportLabel.split('-', 3)
            _gebReportingSpecTestCounter = featureNum.toInteger()
            gebReportingPerTestCounter = reportNum.toInteger()
        }
        else {
            /**
             * assume we are in setupSpec
             */
            def methodName = 'setupSpec'
            int featureNum=0
            if (!inSetupSpec) {
                /**
                 * unless we are not is setupSpec: Then, assume we are in cleanupSpec
                 */
                methodName = 'cleanupSpec'
                reportGroup getClass() //reportGroup isn't set at cleanupSpec
                if (!inCleanupSpec) {
                    /**
                     * first cleanupSpec report. Init indice
                     */
                    inCleanupSpec = true
                    ++_gebReportingSpecTestCounter
                    gebReportingPerTestCounter = 1
                }
            }
            reportLabel = ReporterSupport.toTestReportLabel(_gebReportingSpecTestCounter, gebReportingPerTestCounter++, methodName, label)
            browser.report(reportLabel)
        }
    }
}
