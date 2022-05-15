package com.biggergames.tbslapi.service.team.impl;

import com.biggergames.tbslapi.client.response.team.TeamResponse;
import com.biggergames.tbslapi.config.util.DataUtil;
import com.biggergames.tbslapi.model.TeamData;
import com.biggergames.tbslapi.service.ServiceResult;
import com.biggergames.tbslapi.service.team.ITeamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
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
        int rank = dataUtil.getTeamRank(teamName);
        if (rank < 1) {
            log.error("Takim rank bilgisi alinamadi. " + teamName);
        }

        return new ServiceResult<>(teamRes(teamData, rank));
    }

    private TeamResponse teamRes(TeamData teamData, int rank) {
        return new TeamResponse(teamData.getWins(), teamData.getLosses(), teamData.getDraws()
                , teamData.getPoints(), teamData.getScored(), teamData.getConceded(), rank, 0);
    }
}
