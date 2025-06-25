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

// Initialize GlobalVariable BEFORE the loops
// Ensure GlobalVariable is defined and initialized as empty list
if (!GlobalVariable.metaClass.hasProperty(GlobalVariable, "historisValues")) {
    GlobalVariable.metaClass.historisValues = []
    println("GlobalVariable.historisValues was dynamically created.")
} else {
    GlobalVariable.historisValues = [] // Reset to empty list
    println("GlobalVariable.historisValues was reset.")
}

// Define segment types and row headers with descriptions
String[] segmentTypes = ["IDR", "VALAS"]
Map<String, String> rowHeaderDescriptions = [
    "rowHeader-12": "Total Interest Income IDR (MTD)",
    "rowHeader-15": "Total Interest Income VALAS (MTD)",
    "rowHeader-17": "Total Interest Income IDR dan VALAS (MTD)"
]

// Collect all values before assigning to GlobalVariable
List<Map<String, Object>> allHistorisValues = []

for (Map.Entry<String, String> entry : rowHeaderDescriptions.entrySet()) {
    String rowHeader = entry.getKey()
    String description = entry.getValue()

    for (String segmentType : segmentTypes) {
        // Locate table and find matching cells
        WebElement table = driver.findElement(By.id("tableHistoris"))
        List<WebElement> targetCells = table.findElements(By.xpath(
            String.format(".//tr[@class='bgRow rowHeaderPL %s']/td[contains(@class, 'segment-Jan_2025_%s')]", rowHeader, segmentType)
        ))

        if (targetCells.isEmpty()) {
            println("No matching cells found for: " + description + " and segment: " + segmentType)
        } else {
            for (WebElement cell : targetCells) {
                String value = cell.getText().trim()
                if (!"0,00".equals(value)) {
                    println("Found value in " + description + ", segment " + segmentType + ": " + value)

                    // Create dynamic test object
                    String dynamicXPath = String.format(
                        ".//tr[@class='bgRow rowHeaderPL %s']/td[contains(@class, 'segment-Jan_2025_%s') and text()='%s']",
                        rowHeader, segmentType, value
                    )
                    TestObject dynamicCell = new TestObject("dynamicObject")
                    dynamicCell.addProperty("xpath", ConditionType.EQUALS, dynamicXPath)

                    // Right-click on the cell
                    WebUI.rightClick(dynamicCell)
                }
            }
        }
		
		// Prepare historis values output in the desired format
		List<String> readableHistorisValues = allHistorisValues.collectMany { historisEntry ->
			historisEntry.values.collect { value ->
				"${historisEntry.description} (${historisEntry.segmentType}) $value."
			}
		}
		
		// Print historis values in the desired format
		println("=== HISTORIS VALUES ===")
		println("Historis Values: " + readableHistorisValues)

        // Collect values for this combination
        List<String> currentValues = targetCells.collect {
            String rawValue = it.getText().trim()
            // Uncomment if additional cleaning is required
            // String cleanedValue = rawValue.replaceAll("\\.", "").replaceAll(",00", "")
            return rawValue // Replace with `cleanedValue` if cleaning logic is uncommented
        }

        // Add to collection with metadata
        if (!currentValues.isEmpty()) {
            allHistorisValues.add([
                rowHeader: rowHeader,
                description: description,
                segmentType: segmentType,
                values: currentValues
            ])
        }

        println("Cleaned Historis Values for " + description + ", segment " + segmentType + ": " + currentValues)
    }
}

// NOW assign all collected values to GlobalVariable
GlobalVariable.historisValues = allHistorisValues

// Print final GlobalVariable content for verification
println("=== FINAL GLOBAL VARIABLE CONTENT ===")
println("GlobalVariable.historisValues size: " + GlobalVariable.historisValues.size())
for (int i = 0; i < GlobalVariable.historisValues.size(); i++) {
    Map<String, Object> entry = GlobalVariable.historisValues[i]
    println("Entry " + (i+1) + ": " + entry.description + " (" + entry.segmentType + ") = " + entry.values)
}

// Alternative: If you want just the values as a flat list
// Initialize a flat list to hold all values with descriptions
List<String> flatValues = []

// Print detailed entries and populate the flatValues
println("=== HISTORIS VALUES ===")
for (int i = 0; i < allHistorisValues.size(); i++) {
	Map<String, Object> historisEntry = allHistorisValues[i]
	String description = historisEntry.description
	String segmentType = historisEntry.segmentType
	List<String> values = historisEntry.values

	// Add each value to the flat list with description
	values.each { value ->
		flatValues << "${description} (${segmentType}) $value."
	}

	// Print detailed entries
	println("Entry ${i + 1}: ${description} (${segmentType}) = ${values}")
}

// Print the combined flat list
println("flatValues: " + flatValues)


