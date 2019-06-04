package com.joshcummings.codeplay.terracotta.security;

import java.util.ArrayList;
import java.util.List;

public enum AuthenticationMethod {

    FORM_BASED_AUTHENTICATION("formBasedAuthentication"),
    HTTP_AUTHENTICATION("httpAuthentication"),
    MANUAL_AUTHENTICATION("manualAuthentication"),
    SCRIPT_BASED_AUTHENTICATION("scriptBasedAuthentication")
    ;

    private final String value;

    public String getValue() {
        return value;
    }

    AuthenticationMethod(String authenticationMethod) {
        this.value = authenticationMethod;
    }

    static List<String> getValues() {
        List<String> values = new ArrayList<>();
        for (AuthenticationMethod authenticationMethod : AuthenticationMethod.values()) {
            values.add(authenticationMethod.getValue());
        }
        return values;
    }
}
