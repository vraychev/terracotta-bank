package com.joshcummings.codeplay.terracotta.security;

import org.zaproxy.clientapi.core.ApiResponseSet;

public class ScanInfo implements Comparable<ScanInfo> {

    public int progress;
    public int id;
    public State state;

    @Override
    public int compareTo(ScanInfo o) {
        return id - o.getId();
    }

    enum State {
        NOT_STARTED,
        FINISHED,
        PAUSED,
        RUNNING
        ;
        static State parse(String s) {
            if ("NOT_STARTED".equalsIgnoreCase(s)) {
                return NOT_STARTED;
            }
            if ("FINISHED".equalsIgnoreCase(s)) {
                return FINISHED;
            }
            if ("PAUSED".equalsIgnoreCase(s)) {
                return PAUSED;
            }
            if ("RUNNING".equalsIgnoreCase(s)) {
                return RUNNING;
            }
            throw new IllegalArgumentException("Unknown state: " + s);
        }
        }

    public ScanInfo(ApiResponseSet response) {
        id = Integer.parseInt(response.getStringValue("id"));
        progress = Integer.parseInt(response.getStringValue("progress"));
        state = State.parse(response.getStringValue("state"));
    }

    int getProgress() {
        return progress;
    }

    void setProgress(int progress) {
        this.progress = progress;
    }

    int getId() {
        return id;
    }

    void setId(int id) {
        this.id = id;
    }

    State getState() {
        return state;
    }

    void setState(State state) {
        this.state = state;
    }
}
