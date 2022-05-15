package com.biggergames.tbslapi.client.request.match;

import com.biggergames.tbslapi.validation.constraints.MatchScore;
import com.fasterxml.jackson.annotation.JsonProperty;

@MatchScore
public class MatchScoreRequest {

    private String home;

    private String guest;

    @JsonProperty("hScore")
    private int hScore;

    @JsonProperty("gScore")
    private int gScore;

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getGuest() {
        return guest;
    }

    public void setGuest(String guest) {
        this.guest = guest;
    }

    @JsonProperty("hScore")
    public int getHScore() {
        return hScore;
    }

    @JsonProperty("hScore")
    public void setHScore(int hScore) {
        this.hScore = hScore;
    }

    @JsonProperty("gScore")
    public int getGScore() {
        return gScore;
    }

    @JsonProperty("gScore")
    public void setGScore(int gScore) {
        this.gScore = gScore;
    }
}
