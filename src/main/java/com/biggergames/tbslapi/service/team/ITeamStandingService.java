package com.biggergames.tbslapi.service.team;

import com.biggergames.tbslapi.client.response.team.TeamStandingsResponse;
import com.biggergames.tbslapi.service.ServiceResult;

public interface ITeamStandingService {

    ServiceResult<TeamStandingsResponse> getTeamStandings();

}
