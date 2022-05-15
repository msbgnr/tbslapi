package com.biggergames.tbslapi.client.response.team;

import com.biggergames.tbslapi.client.response.DefaultResponse;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.core.io.InputStreamResource;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class TeamStandingsResponse extends DefaultResponse {
    private int code;

    private InputStreamResource inputStreamResource;

    public TeamStandingsResponse(String message, int code) {
        super(message);
        this.code = code;
    }

}
