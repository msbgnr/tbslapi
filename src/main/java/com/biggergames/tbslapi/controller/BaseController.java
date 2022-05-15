package com.biggergames.tbslapi.controller;

import com.biggergames.tbslapi.client.response.DefaultResponse;
import com.biggergames.tbslapi.client.response.team.TeamStandingsResponse;
import com.biggergames.tbslapi.service.ServiceResult;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class BaseController {

    protected <T extends DefaultResponse> ResponseEntity<T> createResponse(ServiceResult<T> serviceResult) {
        serviceResult.getValue().setError(HttpStatus.OK.equals(serviceResult.getStatus()) ? null : serviceResult.getStatus().getReasonPhrase());
        serviceResult.getValue().setStatus(serviceResult.getStatus().value());
        return ResponseEntity.status(serviceResult.getStatus()).body(serviceResult.getValue());
    }

    protected ResponseEntity<InputStreamResource> createCSVResponse(ServiceResult<TeamStandingsResponse> serviceResult) {
        HttpHeaders header = new HttpHeaders();
        header.set(HttpHeaders.CONTENT_TYPE, "text/csv");
        header.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=teamStandings.csv");
        return new ResponseEntity<>(serviceResult.getValue().getInputStreamResource(), header, serviceResult.getStatus());
    }

}
