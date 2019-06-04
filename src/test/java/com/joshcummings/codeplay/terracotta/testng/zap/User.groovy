package com.joshcummings.codeplay.terracotta.testng.zap

import org.zaproxy.clientapi.core.ApiResponseSet

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper

class User {

    String id
    boolean enabled
    String contextId
    String name
    Map<String, String> credentials

    User(ApiResponseSet apiResponseSet) throws IOException {
        id = apiResponseSet.getStringValue("id")
        enabled = Boolean.valueOf(apiResponseSet.getStringValue("enabled"))
        contextId = apiResponseSet.getStringValue("contextId")
        name = apiResponseSet.getStringValue("name")
        ObjectMapper mapper = new ObjectMapper()
        credentials = mapper.readValue(apiResponseSet.getStringValue("credentials"), new TypeReference<HashMap<String, String>>() {
        })
    }

    String getId() {
        return id
    }

    void setId(String id) {
        this.id = id
    }

    boolean isEnabled() {
        return enabled
    }

    void setEnabled(boolean enabled) {
        this.enabled = enabled
    }

    String getContextId() {
        return contextId
    }

    void setContextId(String contextId) {
        this.contextId = contextId
    }

    String getName() {
        return name
    }

    void setName(String name) {
        this.name = name
    }

    Map<String, String> getCredentials() {
        return credentials
    }

    void setCredentials(Map<String, String> credentials) {
        this.credentials = credentials
    }
}
