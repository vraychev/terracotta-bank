package com.joshcummings.codeplay.terracotta.testng.zap

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.events.WebDriverEventListener
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.zaproxy.clientapi.core.Alert

class SecurityScannerEventListener implements WebDriverEventListener {

    private final static Logger LOG = LoggerFactory.getLogger(SecurityScannerEventListener)

    int currentScanID
    ZapProxyScanner zapScanner

    @Override
    void beforeAlertAccept(WebDriver driver) {

    }

    @Override
    void afterAlertAccept(WebDriver driver) {

    }

    @Override
    void afterAlertDismiss(WebDriver driver) {

    }

    @Override
    void beforeAlertDismiss(WebDriver driver) {

    }

    @Override
    void beforeNavigateTo(String url, WebDriver driver) {
        scan(url)
    }

    @Override
    void afterNavigateTo(String url, WebDriver driver) {
        scan(url)
    }

    @Override
    void beforeNavigateBack(WebDriver driver) {

    }

    @Override
    void afterNavigateBack(WebDriver driver) {

    }

    @Override
    void beforeNavigateForward(WebDriver driver) {

    }

    @Override
    void afterNavigateForward(WebDriver driver) {

    }

    @Override
    void beforeNavigateRefresh(WebDriver driver) {

    }

    @Override
    void afterNavigateRefresh(WebDriver driver) {

    }

    @Override
    void beforeFindBy(By by, WebElement element, WebDriver driver) {

    }

    @Override
    void afterFindBy(By by, WebElement element, WebDriver driver) {

    }

    @Override
    void beforeClickOn(WebElement element, WebDriver driver) {

    }

    @Override
    void afterClickOn(WebElement element, WebDriver driver) {

    }

    @Override
    void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {

    }

    @Override
    void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {

    }

    @Override
    void beforeScript(String script, WebDriver driver) {

    }

    @Override
    void afterScript(String script, WebDriver driver) {

    }

    @Override
    void onException(Throwable throwable, WebDriver driver) {

    }

    private void logAlerts(List<Alert> alerts) {
        for (Alert alert : alerts) {
            LOG.info("Alert: " + alert.getAlert() + " at URL: " + alert.getUrl() + " Parameter: " + alert.getParam() + " CWE ID: " + alert.getCweId())
        }
    }

    /*
        Remove false positives, filter based on risk and reliability
     */

    private List<Alert> filterAlerts(List<Alert> alerts) {
        List<Alert> filtered = []
        for (Alert alert : alerts) {
            if (alert.getRisk() == Alert.Risk.High && alert.getConfidence() != Alert.Confidence.Low) {
                filtered.add(alert)
            }
        }
        return filtered
    }

    void setAlertAndAttackStrength() {
        for (Policy policy : Policy.values()) {
            String ids = enableZapPolicy(policy)
            for (String id : ids.split(",")) {
                zapScanner.setScannerAlertThreshold(id, "LOW")
                zapScanner.setScannerAlertThreshold(id, "MEDIUM")
                zapScanner.setScannerAttackStrength(id, "HIGH")
            }
        }
    }

    private void scanWithZap(String url) {
        LOG.info("Scanning...")
        zapScanner.scan(url)
        currentScanID = zapScanner.getLastScannerScanId()
        int complete = 0
        while (complete < 100) {
            complete = zapScanner.getScanProgress(currentScanID)
            LOG.info("Scan is " + complete + "% complete.")
            try {
                Thread.sleep(1000)
            } catch (InterruptedException e) {
                LOG.error("Thread was interrupted {}", e.stackTrace)
            }
        }
        LOG.info("Scanning done.")
    }

    private String enableZapPolicy(Policy policy) {
        String scannerIds = policy.getScannerIds()
        zapScanner.setEnableScanners(scannerIds, true)
        scannerIds
    }

    private void spiderWithZap(String baseUrl) {
        zapScanner.setThreadCount(5)
        zapScanner.setMaxDepth(5)
        zapScanner.setPostForms(false)
        zapScanner.spider(baseUrl)
        int spiderID = zapScanner.getLastSpiderScanId()
        int complete = 0
        while (complete < 100) {
            complete = zapScanner.getSpiderProgress(spiderID)
            try {
                Thread.sleep(1000)
            } catch (InterruptedException e) {
                LOG.error("Thread was interrupted {}", e.stackTrace)
            }
        }
        for (String url : zapScanner.getSpiderResults(spiderID)) {
            LOG.info("Found URL: " + url)
        }
    }

    private void scan(String url) {
        LOG.info("Spidering...")
        spiderWithZap(url)

        LOG.info("Spider done.")

        setAlertAndAttackStrength()
        zapScanner.setEnablePassiveScan(true)
        scanWithZap(url)

        List<Alert> alerts = filterAlerts(zapScanner.getAlerts())
        logAlerts(alerts)
    }
}
