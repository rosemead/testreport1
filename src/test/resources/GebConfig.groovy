import com.aoe.gebspockreports.GebReportingListener
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeDriverService

reportingListener = new GebReportingListener()

baseUrl = 'wikipedia.org'

driver = {
    def f = new File('drivers/chromedriver-win-32.exe')
    if (!f.exists())
        throw new RuntimeException()

    ChromeDriverService.Builder serviceBuilder = new ChromeDriverService.Builder()
            .usingAnyFreePort()
            .usingDriverExecutable(f)
    new ChromeDriver(serviceBuilder.build())}

reportsDir = "target/geb-reports"
reportOnTestFailureOnly = true
