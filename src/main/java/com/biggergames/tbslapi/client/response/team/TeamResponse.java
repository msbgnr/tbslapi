package com.biggergames.tbslapi.client.response.team;

import com.biggergames.tbslapi.client.response.DefaultResponse;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class TeamResponse extends DefaultResponse {

    private int wins;

    private int losses;

    private int draws;

    private int points;

    private int scored;

    private int conceded;

    private int ranking;
    private int code;

    public TeamResponse(String message, int code) {
        super(message);
        this.code = code;
    }

}
