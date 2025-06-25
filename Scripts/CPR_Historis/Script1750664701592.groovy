import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import internal.GlobalVariable as GlobalVariable


WebUI.openBrowser('')
WebUI.navigateToUrl('https://192.168.139.145:8081/')

// Login and navigation steps
WebUI.setText(findTestObject('Object Repository/Page_Halaman Login/input_Masuk ke Akun Anda_user'), '111655')
WebUI.setEncryptedText(findTestObject('Object Repository/Page_Halaman Login/input_Masuk ke Akun Anda_password'), 'uOEjhGckAKdbG0Jhq0RqTQ==')
WebUI.click(findTestObject('Object Repository/Page_Halaman Login/button_Masuk'))
WebUI.click(findTestObject('Object Repository/Page_BAMS CPR/i_Data Historis_metismenu-icon pe-7s-graph1'))
WebUI.click(findTestObject('Object Repository/Page_BAMS CPR/input_Tipe Debitur_gruptype'))
WebUI.click(findTestObject('Object Repository/Page_BAMS CPR/input_Periode Awal_PeriodeSearchAwal'))
WebUI.click(findTestObject('Object Repository/Page_BAMS CPR/div_Jan'))
WebUI.click(findTestObject('Object Repository/Page_BAMS CPR/input_Periode Akhir_PeriodeSearchAkhir'))
WebUI.click(findTestObject('Object Repository/Page_BAMS CPR/div_Mar'))
WebUI.setText(findTestObject('Object Repository/Page_BAMS CPR/input_Debitur_select2-search__field_1'), 'VANADIUM')
WebUI.click(findTestObject('Object Repository/Page_BAMS CPR/span_'))
WebUI.click(findTestObject('Object Repository/Page_BAMS CPR/p_Get All Value Chain'))
WebUI.click(findTestObject('Object Repository/Page_BAMS CPR/button_OK'))
WebUI.click(findTestObject('Object Repository/Page_BAMS CPR/p_Get All Konsumer'))
WebUI.click(findTestObject('Object Repository/Page_BAMS CPR/button_OK'))
WebUI.click(findTestObject('Object Repository/Page_BAMS CPR/a_Cari Data_1'))
WebUI.click(findTestObject('Object Repository/Page_BAMS CPR/button_OK'))
WebUI.click(findTestObject('Object Repository/Page_BAMS CPR/button_PROFIT AND LOSS'))

WebDriver driver = DriverFactory.getWebDriver()

// Locate table and find matching cells
WebElement table = driver.findElement(By.id("tableHistoris"))
List<WebElement> targetCells = table.findElements(By.xpath(".//tr[@class='bgRow rowHeaderPL rowHeader-12']/td[contains(@class, 'segment-Jan_2025_IDR')]"))

if (targetCells.isEmpty()) {
    println("No matching cells found in the table.")
} else {
    for (WebElement cell : targetCells) {
        String value = cell.getText().trim()
        if (!"0,00".equals(value)) {
            println("Found value: " + value)

            // Create dynamic test object
            String dynamicXPath = ".//tr[@class='bgRow rowHeaderPL rowHeader-12']/td[contains(@class, 'segment-Jan_2025_IDR') and text()='" + value + "']"
            TestObject dynamicCell = new TestObject("dynamicObject")
            dynamicCell.addProperty("xpath", ConditionType.EQUALS, dynamicXPath)

            // Right-click on the cell
            WebUI.rightClick(dynamicCell)
        }
    }
}

// Ensure GlobalVariable is defined
/*
 * if (!GlobalVariable.metaClass.hasProperty(GlobalVariable, "historisValues"))
 * { GlobalVariable.metaClass.historisValues = []
 * println("GlobalVariable.historisValues was dynamically created.") }
 * 
 * GlobalVariable.historisValues = targetCells.collect { it.getText().trim() }
 * println("Historis values stored in GlobalVariable: " +
 * GlobalVariable.historisValues)
 */

// Ensure GlobalVariable is defined
if (!GlobalVariable.metaClass.hasProperty(GlobalVariable, "historisValues")) {
	GlobalVariable.metaClass.historisValues = []
	println("GlobalVariable.historisValues was dynamically created.")
}

// Clean values: remove thousand separators and ",00"
GlobalVariable.historisValues = targetCells.collect {
	String rawValue = it.getText().trim()
	String cleanedValue = rawValue.replaceAll("\\.", "").replaceAll(",00", "")
	return cleanedValue
}

println("Cleaned Historis Values: " + GlobalVariable.historisValues)