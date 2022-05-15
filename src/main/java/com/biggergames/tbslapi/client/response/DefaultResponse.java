package com.biggergames.tbslapi.client.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DefaultResponse {

    private final String timestamp = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ").format(ZonedDateTime.now().withZoneSameInstant(ZoneId.of("UTC")));

    private int status;

    private String error;
    private String message;

    public DefaultResponse(String message) {
        this.message = message;
    }
}
