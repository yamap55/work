@Grapes([
    @Grab("org.gebish:geb-core:1.1.1"),
    @Grab("org.seleniumhq.selenium:selenium-support:3.4.0"),
])

import geb.Browser

println "start ${new Date().format('yyyy/MM/dd HH:mm:ss')}"


Browser.drive {
    go "http://gebish.org"

    assert title == "Geb - Very Groovy Browser Automation"
    assert $("h1")[0].text() == "What is it?"
}
println "end ${new Date().format('yyyy/MM/dd HH:mm:ss')}"
