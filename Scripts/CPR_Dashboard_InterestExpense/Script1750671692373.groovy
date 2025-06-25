import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.WebElement as WebElement
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.openBrowser('')

WebUI.navigateToUrl('https://192.168.139.145:8081/')

WebUI.setText(findTestObject('Object Repository/Page_Halaman Login/input_Masuk ke Akun Anda_user'), '111655')

WebUI.setEncryptedText(findTestObject('Object Repository/Page_Halaman Login/input_Masuk ke Akun Anda_password'), 'uOEjhGckAKdbG0Jhq0RqTQ==')

WebUI.click(findTestObject('Object Repository/Page_Halaman Login/button_Masuk'))

WebUI.click(findTestObject('Object Repository/Page_BAMS CPR/span_-Silakan pilih-'))

WebUI.setText(findTestObject('Object Repository/Page_BAMS CPR/input__select2-search__field'), 'VANADIUM')

WebUI.click(findTestObject('Object Repository/Page_BAMS CPR/span_9014551301 - VANADIUM M E'))

WebUI.click(findTestObject('Object Repository/Page_BAMS CPR/input__PeriodeSearchAkhir'))

WebUI.click(findTestObject('Object Repository/Page_BAMS CPR/div_2025'))

WebUI.click(findTestObject('Object Repository/Page_BAMS CPR/span_-Silakan pilih-_1'))

WebUI.click(findTestObject('Object Repository/Page_BAMS CPR/span_Profit  Loss'))

WebUI.click(findTestObject('Object Repository/Page_BAMS CPR/span_-Silakan pilih-_1_2'))

WebUI.click(findTestObject('Object Repository/Page_BAMS CPR/span_Interest Expense'))

WebUI.click(findTestObject('Object Repository/Page_BAMS CPR/span_-Silakan pilih-_1_2_3'))

WebUI.click(findTestObject('Object Repository/Page_BAMS CPR/span_All'))

WebUI.click(findTestObject('Object Repository/Page_BAMS CPR/a_Cari Data'))

WebDriver driver = DriverFactory.getWebDriver()

// Locate all matching elements
List<WebElement> elements = driver.findElements(By.cssSelector('path.highcharts-point.highcharts-negative'))

// Filter elements with "2025" in aria-label
elements.each({ def element ->
        String ariaLabel = element.getAttribute('aria-label')

        if (ariaLabel.contains('2025')) {
            println('aria-label: ' + ariaLabel)
        }
    })

	/*
	 * GlobalVariable.dashboardValues = elements.collect({
	 * it.getAttribute('aria-label').trim() })
	 */

	/*
	 * GlobalVariable.dashboardValues = elements.collect({
	 * it.getAttribute('aria-label').trim() }) println('Dashboard values: ' +
	 * GlobalVariable.dashboardValues)
	 * 
	 * 
	 * println('Dashboard values stored in GlobalVariable.')
	 */

// Collect and clean values
GlobalVariable.dashboardValues = elements.collect {
	String rawValue = it.getAttribute('aria-label').trim()
	String cleanedValue = rawValue.replaceAll(",", "") // Remove commas
	return cleanedValue
}

println('Cleaned Dashboard Values: ' + GlobalVariable.dashboardValues)
println('Dashboard values stored in GlobalVariable.')
