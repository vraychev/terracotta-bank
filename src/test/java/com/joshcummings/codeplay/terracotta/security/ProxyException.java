package com.joshcummings.codeplay.terracotta.security;

public class ProxyException extends RuntimeException {

    public ProxyException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProxyException(Throwable cause) {
        super(cause);
    }
}
