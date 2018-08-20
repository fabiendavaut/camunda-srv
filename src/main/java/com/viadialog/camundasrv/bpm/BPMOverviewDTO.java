package com.viadialog.camundasrv.bpm;

import java.util.HashMap;

public class BPMOverviewDTO {

    private HashMap<String, String> map = new HashMap<String, String>();

    public HashMap<String, String> getMap() {
        return this.map;
    }

    public BPMOverviewDTO input(String key, String value) {
        this.map.put(key,value);
        return this;
    }

    public BPMOverviewDTO input(String key, Long value) {
        this.map.put(key,String.valueOf(value));
        return this;
    }

}
