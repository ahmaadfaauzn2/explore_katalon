# Explore Katalon - CPR Testing Automation

Repository ini berisi eksplorasi dan pembelajaran tentang Katalon Studio dengan fokus pada otomasi testing aplikasi CPR (Customer Profitability Report). Project ini mencakup testing untuk dashboard, analisis historis, dan fitur perbandingan data.

## 📋 Deskripsi

Project ini dibuat untuk mengotomatisasi testing aplikasi CPR yang mencakup:
- Sistem perbandingan data antar dashboard dan historis

## 🚀 Fitur yang Dieksplorasi

### CPR Dashboard Testing
- [x] Interest Income  
- [x] Interest Expense 
- [x] Dashboard UI/UX testing

### CPR Data Analysis
- [x] Perbandingan data Dashboard dan HIstoris

## 🛠️ Instalasi dan Setup

### Prerequisites
- Java 8 atau yang lebih baru
- Katalon Studio (versi terbaru)
- Web browsers (Chrome, Firefox, Safari, Edge)
- Mobile devices atau emulators (untuk mobile testing)

### Langkah Instalasi

1. **Download Katalon Studio**
   ```bash
   # Download dari website resmi
   https://www.katalon.com/download/
   ```

2. **Clone Repository**
   ```bash
   git clone https://github.com/ahmaadfaauzn2/explore_katalon.git
   cd explore_katalon
   ```

3. **Buka Project di Katalon Studio**
   - Buka Katalon Studio
   - File → Open Project
   - Pilih folder project ini
   - Buat global variable di profiles historisValues dan dashboardValues
   - Jalankan CPR_CompareScript

4. **Konfigurasi Browser Drivers**
   - Pastikan WebDriver sudah terinstall
   - Katalon Studio biasanya sudah include driver otomatis

## 📁 Struktur Project

```
explore_katalon/
├── Object Repository/          # CPR web elements dan objects
│   ├── Login/                 # Login page objects
│   ├── Dashboard/             # Dashboard elements
│   ├── Navigation/            # Menu dan navigasi
│   └── Reports/               # Report page objects
├── Test Cases/                # CPR test cases
│   ├── CPR_CompareScript.tc
│   ├── CPR_Dashboard_InterestExpense.tc
│   ├── CPR_Dashboard_InterestIncome.tc
│   ├── CPR_Dashboard_NetInterestIncome.tc
│   ├── CPR_Historis.tc
│   └── New Test Case.tc
├── Test Suites/               # Test suite collections
│   ├── CPR_Dashboard_Suite.ts
│   ├── CPR_Regression_Suite.ts
│   └── CPR_Smoke_Test.ts
├── Test Listeners/            # Event listeners
├── Keywords/                  # Custom keywords untuk CPR
│   ├── CPRKeywords.groovy
│   └── DatabaseKeywords.groovy
├── Profiles/                  # Environment profiles
│   ├── default.glbl
│   ├── staging.glbl
│   └── production.glbl
├── Data Files/                # Test data untuk CPR
│   ├── CPR_TestData.xlsx
│   └── InterestRates.csv
├── Reports/                   # Test execution reports
├── Libs/                      # External libraries
└── settings/                  # Project settings
```

## 🧪 Test Cases

Repository ini berisi test cases untuk aplikasi CPR (Credit Portfolio Risk/Corporate Performance Reporting) dengan fokus pada dashboard dan fitur historis.

### 📊 CPR Dashboard Test Cases

#### 1. CPR_Dashboard_InterestExpense.tc

#### 2. CPR_Dashboard_InterestIncome.tc  

#### 3. CPR_Dashboard_NetInterestIncome.tc

#### 4. CPR_Historis.tc

#### 5. CPR_CompareScript.tc


### 🔧 Test Case Structure Example

```groovy
// Example struktur untuk CPR Dashboard Test
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

// Setup
WebUI.openBrowser('')
WebUI.navigateToUrl('https://cpr-app.example.com')
WebUI.maximizeWindow()

// Login ke aplikasi CPR
WebUI.setText(findTestObject('Login/txt_username'), GlobalVariable.username)
WebUI.setText(findTestObject('Login/txt_password'), GlobalVariable.password)
WebUI.click(findTestObject('Login/btn_login'))

// Navigasi ke Dashboard
WebUI.click(findTestObject('Navigation/menu_dashboard'))
WebUI.waitForElementVisible(findTestObject('Dashboard/lbl_dashboard_title'), 10)

// Test Interest Income functionality
WebUI.click(findTestObject('Dashboard/tab_interest_income'))
WebUI.verifyElementPresent(findTestObject('Dashboard/tbl_interest_income'), 10)

// Verify data calculation
def incomeValue = WebUI.getText(findTestObject('Dashboard/txt_total_income'))
WebUI.verifyMatch(incomeValue, '\\d+\\.\\d+', true) // Verify numeric format

// Test filtering
WebUI.selectOptionByText(findTestObject('Dashboard/ddl_period'), '2024')
WebUI.click(findTestObject('Dashboard/btn_filter'))
WebUI.delay(2)

// Verify filtered results
WebUI.verifyElementPresent(findTestObject('Dashboard/tbl_filtered_data'), 10)

// Cleanup
WebUI.closeBrowser()
```



## Hasil
![image](https://github.com/user-attachments/assets/9327be41-5a11-46f7-8817-a517d33131a8)


## 🔗 Integrasi

### Git Integration
```bash
# Setup Git repository
git init
git remote add origin https://github.com/ahmaadfaauzn2/explore_katalon.git

# Commit changes
git add .
git commit -m "Initial commit"
git push origin main
```

### Jenkins Integration
```groovy
// Jenkinsfile example
pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/ahmaadfaauzn2/explore_katalon.git'
            }
        }
        stage('Test') {
            steps {
                sh 'katalon -noSplash -runMode=console -projectPath="." -retry=0 -testSuitePath="Test Suites/Regression Suite" -executionProfile="default"'
            }
        }
    }
}
```

## 📚 Resources & References

### Dokumentasi Resmi
- [Katalon Studio Documentation](https://docs.katalon.com/)
- [Katalon Academy](https://academy.katalon.com/)
- [Katalon Community](https://forum.katalon.com/)

### Tutorial & Guides
- [Getting Started with Katalon Studio](https://docs.katalon.com/katalon-studio/docs/getting-started.html)
- [Best Practices for Test Automation](https://docs.katalon.com/katalon-studio/docs/automation-best-practices.html)


## 📄 License

Distributed under the MIT License. See `LICENSE` for more information.

