package com.biggergames.tbslapi.enums;

public enum MatchResult {

    WIN(1, "WIN"),
    LOSS(2, "LOSS"),
    DRAW(3, "DRAW");

    private final Integer value;
    private final String desc;

    MatchResult(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public Integer getValue() {
        return value;
    }

}
