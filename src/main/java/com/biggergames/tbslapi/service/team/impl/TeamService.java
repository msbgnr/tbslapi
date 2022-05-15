package com.biggergames.tbslapi.service.team.impl;

import com.biggergames.tbslapi.client.response.team.TeamResponse;
import com.biggergames.tbslapi.config.util.DataUtil;
import com.biggergames.tbslapi.model.TeamData;
import com.biggergames.tbslapi.service.ServiceResult;
import com.biggergames.tbslapi.service.team.ITeamService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeamService implements ITeamService {

    private final DataUtil dataUtil;

    @Override
    public ServiceResult<TeamResponse> getTeamData(String teamName) {
        if (StringUtils.isEmpty(teamName)) {
            return new ServiceResult<>(new TeamResponse("Takim isim bilgisi bos olamaz.", -3));
        }
        TeamData teamData = dataUtil.getTeamDataFromHash(teamName);
        if (teamData == null) {
            return new ServiceResult<>(new TeamResponse("Takim isim bilgisini kontrol ediniz.", -2));
        }

        return new ServiceResult<>(teamRes(teamData));
    }

    private TeamResponse teamRes(TeamData teamData) {
        return new TeamResponse(teamData.getWins(), teamData.getLosses(), teamData.getDraws()
                , teamData.getPoints(), teamData.getScored(), teamData.getConceded(), 0);
    }
}
