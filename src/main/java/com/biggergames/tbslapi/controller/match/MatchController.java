package com.biggergames.tbslapi.controller.match;

import com.biggergames.tbslapi.client.request.match.MatchScoreRequest;
import com.biggergames.tbslapi.client.response.match.MatchScoreResponse;
import com.biggergames.tbslapi.config.swagger.SwaggerConfiguration;
import com.biggergames.tbslapi.config.swagger.SwaggerDescription;
import com.biggergames.tbslapi.config.swagger.TbslApiResponse;
import com.biggergames.tbslapi.controller.BaseController;
import com.biggergames.tbslapi.service.ServiceResult;
import com.biggergames.tbslapi.service.match.IMatchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping(path = "/v1", produces = {"application/json"})
@RequiredArgsConstructor
@Api(tags = {SwaggerConfiguration.MATCH_SCORE_V1_TAG})
@TbslApiResponse
public class MatchController extends BaseController {

    private final IMatchService matchService;

    @PostMapping("match-score")
    @ApiOperation(value = "matchScore", notes = SwaggerDescription.MATCH_SCORE_V1_DESCRIPTION)
    public ResponseEntity<MatchScoreResponse> matchScore(@RequestBody @Valid
                                                         @ApiParam(name = "matchScoreRequest", value = "Takimlarin isim ve attiklari sayi bilgileri", required = true) MatchScoreRequest matchScoreRequest) {
        ServiceResult<MatchScoreResponse> serviceResult = matchService.addMatchResult(matchScoreRequest);
        return createResponse(serviceResult);
    }
}
