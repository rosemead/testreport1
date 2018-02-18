package foo

import geb.Page

class Wikipedia extends Page {
    static at = {true }

    static content = {
        bar { module(BarModule)}
    }
}
