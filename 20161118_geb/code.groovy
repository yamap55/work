@Grapes([
    @Grab("org.gebish:geb-core:1.0"),
    @Grab("org.seleniumhq.selenium:selenium-firefox-driver:2.52.0"),
    @Grab("org.seleniumhq.selenium:selenium-support:2.52.0")
])
import geb.Browser

println "start"
Browser.drive {
    go "http://gebish.org"

    assert title == "Geb - Very Groovy Browser Automation" 

    $("#sidebar .sidemenu a", text: "jQuery-like API").click()

    assert $("#main h1")*.text() == ["Navigating Content", "Form Control Shortcuts"] 
    assert $("#sidebar .sidemenu a", text: "jQuery-like API").parent().hasClass("selected")
}