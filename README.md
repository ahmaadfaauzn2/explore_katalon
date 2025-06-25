# Explore Katalon - CPR Testing Automation

Repository ini berisi eksplorasi dan pembelajaran tentang Katalon Studio dengan fokus pada otomasi testing aplikasi CPR (Credit Portfolio Risk/Corporate Performance Reporting). Project ini mencakup testing untuk dashboard, analisis historis, dan fitur perbandingan data.

## 📋 Deskripsi

Project ini dibuat untuk mengotomatisasi testing aplikasi CPR yang mencakup:
- Dashboard monitoring untuk Interest Income, Interest Expense, dan Net Interest Income
- Fitur analisis data historis
- Sistem perbandingan data antar periode
- Validasi akurasi kalkulasi finansial
- Testing performa dan reliability aplikasi CPR

## 🚀 Fitur yang Dieksplorasi

### CPR Dashboard Testing
- [x] Interest Income calculation validation
- [x] Interest Expense tracking verification  
- [x] Net Interest Income (NII) formula testing
- [x] Dashboard UI/UX testing
- [ ] Real-time data refresh testing
- [ ] Multi-user concurrent access testing

### CPR Data Analysis
- [x] Historical data analysis testing
- [x] Data comparison script validation
- [ ] Trend analysis verification
- [ ] Forecasting model testing

### CPR Reporting
- [x] Report generation testing
- [ ] Export functionality (PDF, Excel)
- [ ] Scheduled report testing
- [ ] Report accuracy validation

### Integration Testing
- [x] Database connectivity testing
- [ ] API integration testing
- [ ] Third-party system integration
- [ ] Real-time data synchronization

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
Test case untuk verifikasi fitur Interest Expense di dashboard CPR:
- Validasi perhitungan interest expense
- Verifikasi tampilan data expense
- Testing filter dan sorting data expense
- Validasi export/report expense data

#### 2. CPR_Dashboard_InterestIncome.tc  
Test case untuk verifikasi fitur Interest Income di dashboard CPR:
- Validasi perhitungan interest income
- Verifikasi tampilan data income
- Testing filter berdasarkan periode
- Validasi akurasi data income

#### 3. CPR_Dashboard_NetInterestIncome.tc
Test case untuk verifikasi Net Interest Income (NII):
- Validasi perhitungan NII (Income - Expense)
- Verifikasi formula kalkulasi
- Testing berbagai skenario data
- Validasi tampilan grafik/chart NII

### 🔍 CPR Analysis Test Cases

#### 4. CPR_Historis.tc
Test case untuk fitur historical data analysis:
- Testing akses data historis
- Verifikasi filtering berdasarkan periode
- Validasi trend analysis
- Testing export historical reports

#### 5. CPR_CompareScript.tc
Test case untuk fitur comparison/benchmark:
- Testing perbandingan data antar periode
- Validasi script comparison logic
- Verifikasi accuracy hasil perbandingan
- Testing various comparison scenarios

#### 6. New Test Case.tc
Template/placeholder untuk test case baru:
- Base structure untuk test case baru
- Common setup dan teardown
- Standard verification points

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

## 🔧 Custom Keywords

Custom keywords yang dibuat khusus untuk testing aplikasi CPR:

### CPRKeywords
- `loginToCPR(username, password)` - Login ke aplikasi CPR
- `navigateToDashboard()` - Navigasi ke dashboard utama
- `validateInterestCalculation(expected, actual)` - Validasi perhitungan interest
- `exportDashboardData(format)` - Export data dashboard
- `waitForDataLoad()` - Wait untuk loading data selesai

### DatabaseKeywords
- `connectToCPRDatabase()` - Koneksi ke database CPR
- `executeInterestQuery(query)` - Eksekusi query interest data
- `validateDatabaseConsistency()` - Validasi konsistensi data
- `getHistoricalData(startDate, endDate)` - Ambil data historis

### UtilityKeywords
- `generateTestData()` - Generate data test untuk CPR
- `calculateExpectedNII(income, expense)` - Kalkulasi expected NII
- `formatCurrency(amount)` - Format currency display
- `captureScreenshotWithTimestamp()` - Screenshot dengan timestamp

## 📊 Reporting

Project ini menggunakan beberapa jenis reporting:
- **Katalon Analytics** - Cloud-based reporting
- **Custom HTML Reports** - Report HTML custom
- **Allure Reports** - Detailed test reports
- **JUnit Reports** - Untuk integrasi CI/CD

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

### Useful Plugins
- **Katalon TestOps** - Test management platform
- **Smart Wait** - Intelligent waiting mechanism
- **Auto-healing** - Self-healing test objects

## 🤝 Contributing

Kontribusi sangat diterima! Silakan:

1. Fork repository ini
2. Buat feature branch (`git checkout -b feature/amazing-feature`)
3. Commit perubahan (`git commit -m 'Add amazing feature'`)
4. Push ke branch (`git push origin feature/amazing-feature`)
5. Buat Pull Request

## 📝 Changelog

### v1.0.0 (2024-06-XX)
- Initial project setup
- Basic web testing implementation
- API testing examples
- Git integration setup

### v0.1.0 (2024-06-XX)
- Project initialization
- Basic structure setup

## 📄 License

Distributed under the MIT License. See `LICENSE` for more information.

## 👤 Author

**Ahmad Fauzan**
- GitHub: [@ahmaadfaauzn2](https://github.com/ahmaadfaauzn2)
- LinkedIn: [Your LinkedIn Profile]

## 🙏 Acknowledgments

- Katalon Team untuk platform yang amazing
- Katalon Community untuk support dan knowledge sharing
- Contributors yang telah membantu project ini

---

## 📞 Support

Jika ada pertanyaan atau butuh bantuan:
- Buka issue di repository ini
- Diskusi di [Katalon Community Forum](https://forum.katalon.com/)
- Email: your.email@example.com

**Happy Testing! 🚀**
