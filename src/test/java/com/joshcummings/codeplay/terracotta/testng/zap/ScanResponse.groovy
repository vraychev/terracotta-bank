package com.joshcummings.codeplay.terracotta.testng.zap

import org.zaproxy.clientapi.core.ApiResponse
import org.zaproxy.clientapi.core.ApiResponseList
import org.zaproxy.clientapi.core.ApiResponseSet

class ScanResponse {
    List<ScanInfo> scans = []

    ScanResponse(ApiResponseList responseList) {
        for (ApiResponse rawResponse : responseList.getItems()) {
            scans.add(new ScanInfo((ApiResponseSet)rawResponse))
        }
        Collections.sort(scans)
    }

    List<ScanInfo> getScans() {
        return scans
    }

    ScanInfo getScanById(int scanId) {
        for (ScanInfo scan : scans) {
            if (scan.getId() == scanId) {
                return scan
            }
        }
        return null
    }

    ScanInfo getLastScan() {
        if (scans.size() == 0) {
            throw new IllegalArgumentException("No scans founds")
        }
        return scans[-1]
    }
}
