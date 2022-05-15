package com.biggergames.tbslapi.service.team;

import com.biggergames.tbslapi.client.response.team.TeamResponse;
import com.biggergames.tbslapi.service.ServiceResult;

public interface ITeamService {

    ServiceResult<TeamResponse> getTeamData(String teamName);

}
