package foo

import geb.Module

class BarModule extends Module {

    static content = {
        searchBox { $(id: 'searchInput')}
    }

    def lookup(str) {
        searchBox << str
        searchBox << org.openqa.selenium.Keys.ENTER
        browser.takeAReport "reporting from module"
    }
}
