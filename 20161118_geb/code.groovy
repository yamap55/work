@Grapes([
    @Grab("org.gebish:geb-core:1.0"),
    @Grab("org.seleniumhq.selenium:selenium-support:3.0.1"),
])

import geb.Browser

println "start ${new Date().format('yyyy/MM/dd HH:mm:ss')}"


Browser.drive {
    go "http://gebish.org"

    assert title == "Geb - Very Groovy Browser Automation" 

    $("#sidebar .sidemenu a", text: "jQuery-like API").click()

    assert $("#main h1")*.text() == ["Navigating Content", "Form Control Shortcuts"] 
    assert $("#sidebar .sidemenu a", text: "jQuery-like API").parent().hasClass("selected")
}
println "end ${new Date().format('yyyy/MM/dd HH:mm:ss')}"