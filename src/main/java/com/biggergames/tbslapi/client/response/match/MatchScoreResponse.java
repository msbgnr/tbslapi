package com.biggergames.tbslapi.client.response.match;

import com.biggergames.tbslapi.client.response.DefaultResponse;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class MatchScoreResponse extends DefaultResponse {

    private int code;

    public MatchScoreResponse(String message, int code) {
        super(message);
        this.code = code;
    }
}
