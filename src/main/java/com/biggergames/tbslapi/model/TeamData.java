package com.biggergames.tbslapi.model;

import com.biggergames.tbslapi.enums.MatchResult;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class TeamData implements Serializable {

    private static final long serialVersionUID = -5551383466251552147L;

    private static final int WIN_POINT = 3;
    private static final int DRAW_POINT = 1;
    private static final int LOSS_POINT = 0;

    private String name;
    private int wins = 0;
    private int losses = 0;
    private int draws = 0;
    private int points = 0;
    private int scored = 0;
    private int conceded = 0;

    public TeamData(String name) {
        this.name = name;
    }

    public void afterMatch(MatchResult resultType, int score, int concede) {
        switch (resultType) {
            case WIN:
                this.wins += 1;
                this.points += WIN_POINT;
                break;
            case LOSS:
                this.losses += 1;
                this.points += LOSS_POINT;
                break;
            case DRAW:
                this.draws += 1;
                this.points += DRAW_POINT;
                break;
        }
        this.scored += score;
        this.conceded += concede;
    }


}
