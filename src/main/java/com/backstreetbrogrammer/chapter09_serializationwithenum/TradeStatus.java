package com.backstreetbrogrammer.chapter09_serializationwithenum;

public enum TradeStatus {

    LIVE("Live"),
    STALE("Stale"),
    CLOSED("Closed"),
    UNKNOWN("Unknown status");

    private String descr;

    TradeStatus(final String desc) {
        this.descr = desc;
    }

    public String desc() {
        return descr;
    }

    @Override
    public String toString() {
        return "TradeStatus{" +
                "desc='" + descr + '\'' +
                '}';
    }
}
