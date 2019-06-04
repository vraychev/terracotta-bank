package com.joshcummings.codeplay.terracotta.testng.zap

class ProxyException extends RuntimeException {

    ProxyException(String message, Throwable cause) {
        super(message, cause)
    }

    ProxyException(Throwable cause) {
        super(cause)
    }
}
