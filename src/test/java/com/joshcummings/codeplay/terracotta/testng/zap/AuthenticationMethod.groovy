package com.joshcummings.codeplay.terracotta.testng.zap

enum AuthenticationMethod {
    FORM_BASED_AUTHENTICATION("formBasedAuthentication"),
    HTTP_AUTHENTICATION("httpAuthentication"),
    MANUAL_AUTHENTICATION("manualAuthentication"),
    SCRIPT_BASED_AUTHENTICATION("scriptBasedAuthentication")

    private final String value

    String getValue() {
        value
    }

    AuthenticationMethod(String authenticationMethod) {
        this.value = authenticationMethod
    }

    static List<String> getValues() {
        List<String> values = []
        for (AuthenticationMethod authenticationMethod : AuthenticationMethod.values()) {
            values.add(authenticationMethod.getValue())
        }
        values
    }
}