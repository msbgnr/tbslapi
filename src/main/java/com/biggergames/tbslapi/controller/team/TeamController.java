package com.biggergames.tbslapi.controller.team;

import com.biggergames.tbslapi.client.response.team.TeamResponse;
import com.biggergames.tbslapi.client.response.team.TeamStandingsResponse;
import com.biggergames.tbslapi.config.swagger.SwaggerConfiguration;
import com.biggergames.tbslapi.config.swagger.SwaggerDescription;
import com.biggergames.tbslapi.config.swagger.TbslApiResponse;
import com.biggergames.tbslapi.controller.BaseController;
import com.biggergames.tbslapi.service.ServiceResult;
import com.biggergames.tbslapi.service.team.ITeamService;
import com.biggergames.tbslapi.service.team.ITeamStandingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping(path = "/v1", produces = {"application/json"})
@RequiredArgsConstructor
@Api(tags = {SwaggerConfiguration.TEAM_V1_TAG})
@TbslApiResponse
public class TeamController extends BaseController {

    private final ITeamService teamService;

    private final ITeamStandingService teamStandingService;


    @GetMapping("team/{teamName}")
    @ApiOperation(value = "team", notes = SwaggerDescription.TEAM_V1_DESCRIPTION)
    public ResponseEntity<TeamResponse> getTeamData(@PathVariable("teamName") @ApiParam(value = SwaggerDescription.TEAM_NAME, required = true) String teamName) {
        ServiceResult<TeamResponse> serviceResult = teamService.getTeamData(teamName);
        return createResponse(serviceResult);
    }

    @GetMapping(value = "team-standings", produces = "text/csv")
    @ApiOperation(value = "teamStandings", notes = SwaggerDescription.TEAM_STANDINGS_V1_DESCRIPTION)
    public ResponseEntity<InputStreamResource> getTeamStandings() {
        ServiceResult<TeamStandingsResponse> serviceResult = teamStandingService.getTeamStandings();
        return createCSVResponse(serviceResult);
    }
}
