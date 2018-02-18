package foo

class FooSpec extends MySpecBase {

    def setupSpec() {
        report "1 from setupSpec"
        report "2 from setupSpec"
    }

    def "test that passes"() {
        go 'https://www.wikipedia.com'
        report "at wikipedia"
        Wikipedia wikipedia = at Wikipedia
        wikipedia.bar.lookup('geb')
        expect:true
    }

    def "test that fails"() {
        go 'https://www.wikipedia.com'
        report "at wikipedia"
        Wikipedia wikipedia = at Wikipedia
        wikipedia.bar.lookup('spock')
        expect:false
    }

    def cleanupSpec() {
        report "1 from cleanupSpec"
        report "2 from cleanupSpec"
    }

}
