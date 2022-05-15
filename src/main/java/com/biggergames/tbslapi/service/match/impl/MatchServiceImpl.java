package com.biggergames.tbslapi.service.match.impl;


import com.biggergames.tbslapi.client.request.match.MatchScoreRequest;
import com.biggergames.tbslapi.client.response.match.MatchScoreResponse;
import com.biggergames.tbslapi.config.util.DataUtil;
import com.biggergames.tbslapi.enums.MatchResult;
import com.biggergames.tbslapi.model.TeamData;
import com.biggergames.tbslapi.service.ServiceResult;
import com.biggergames.tbslapi.service.match.IMatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MatchServiceImpl implements IMatchService {

    private final DataUtil dataUtil;

    @Override
    public ServiceResult<MatchScoreResponse> addMatchResult(MatchScoreRequest matchScoreRequest) {
        TeamData homeTeam = dataUtil.getTeamDataFromHash(matchScoreRequest.getHome());
        TeamData guestTeam = dataUtil.getTeamDataFromHash(matchScoreRequest.getGuest());
        if (homeTeam == null || guestTeam == null) {
            return new ServiceResult<>(new MatchScoreResponse("Takim isimlerini kontrol ediniz.", -2));
        }
        updateTeamData(homeTeam, guestTeam, matchScoreRequest);

        return new ServiceResult<>(new MatchScoreResponse("Giris basarili bir sekilde yapilmistir", 0));
    }

    private void updateTeamData(TeamData homeTeam, TeamData guestTeam, MatchScoreRequest matchScoreRequest) {
        if (matchScoreRequest.getHScore() > matchScoreRequest.getGScore()) {
            homeTeam.afterMatch(MatchResult.WIN, matchScoreRequest.getHScore(), matchScoreRequest.getGScore());
            guestTeam.afterMatch(MatchResult.LOSS, matchScoreRequest.getGScore(), matchScoreRequest.getHScore());
        } else if (matchScoreRequest.getHScore() < matchScoreRequest.getGScore()) {
            guestTeam.afterMatch(MatchResult.WIN, matchScoreRequest.getGScore(), matchScoreRequest.getHScore());
            homeTeam.afterMatch(MatchResult.LOSS, matchScoreRequest.getHScore(), matchScoreRequest.getGScore());
        } else {
            homeTeam.afterMatch(MatchResult.DRAW, matchScoreRequest.getHScore(), matchScoreRequest.getGScore());
            guestTeam.afterMatch(MatchResult.DRAW, matchScoreRequest.getGScore(), matchScoreRequest.getHScore());
        }
        dataUtil.updateTeamData(homeTeam);
        dataUtil.updateTeamData(guestTeam);
    }
}
