import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
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
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys


WebUI.callTestCase(findTestCase('CPR_Dashboard_InterestExpense'), [:], FailureHandling.STOP_ON_FAILURE)
WebUI.callTestCase(findTestCase('CPR_Historis'), [:], FailureHandling.STOP_ON_FAILURE)

List<String> dashboardValues = GlobalVariable.dashboardValues ?: []
// Convert historisValues to a flat list of values for comparison
List<String> flatHistorisValues = GlobalVariable.historisValues.collectMany { it.values }
GlobalVariable.historisValues = flatHistorisValues // Now it holds only values

// Perform comparison
if (dashboardValues.equals(GlobalVariable.historisValues)) {
    println("✅ Values match!")
} else {
    println("❌ Mismatch detected!")
    println("Dashboard Values: " + dashboardValues)
    println("Historis Values: " + GlobalVariable.historisValues)
}


