package com.biggergames.tbslapi.service.team.impl;

import com.biggergames.tbslapi.client.response.team.TeamResponse;
import com.biggergames.tbslapi.client.response.team.TeamStandingsResponse;
import com.biggergames.tbslapi.config.util.DataUtil;
import com.biggergames.tbslapi.model.TeamData;
import com.biggergames.tbslapi.model.TeamStandingData;
import com.biggergames.tbslapi.service.ServiceResult;
import com.biggergames.tbslapi.service.team.ITeamService;
import com.biggergames.tbslapi.service.team.ITeamStandingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TeamStandingsService implements ITeamStandingService {

    private final DataUtil dataUtil;


    @Override
    public ServiceResult<TeamStandingsResponse> getTeamStandings() {
        List<TeamStandingData> teamStandingDataList = dataUtil.getTeamStandingsData();
        if (CollectionUtils.isEmpty(teamStandingDataList)) {
            return new ServiceResult<>(new TeamStandingsResponse("Takim listesi alinamadi", -4));
        }
        InputStreamResource inputStreamResource = getInputStreamResource(teamStandingDataList);
        if (inputStreamResource == null) {
            return new ServiceResult<>(new TeamStandingsResponse("Takim listesi olusturulamadi", -5));
        }

        return new ServiceResult<>(new TeamStandingsResponse(0, inputStreamResource));
    }

    private InputStreamResource getInputStreamResource(List<TeamStandingData> teamStandingDataList) {
        String[] csvHeader = {
                "team", "wins", "losses", "draws", "points", "scored", "conceded", "ranking"
        };

        List<List<String>> csvBody = new ArrayList<>();
        for (TeamStandingData teamStandingData : teamStandingDataList) {
            csvBody.add(Arrays.asList(teamStandingData.getTeamData().getName(),
                    teamStandingData.getTeamData().getWins() + "",
                    teamStandingData.getTeamData().getLosses() + "",
                    teamStandingData.getTeamData().getDraws() + "",
                    teamStandingData.getTeamData().getPoints() + "",
                    teamStandingData.getTeamData().getScored() + "",
                    teamStandingData.getTeamData().getConceded() + "",
                    teamStandingData.getRank() + ""));
        }

        ByteArrayInputStream byteArrayOutputStream;
        try (
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), CSVFormat.DEFAULT.withHeader(csvHeader));
        ) {
            for (List<String> rec : csvBody) {
                csvPrinter.printRecord(rec);
            }
            csvPrinter.flush();

            byteArrayOutputStream = new ByteArrayInputStream(out.toByteArray());
        } catch (Exception e) {
            log.error("Exception while preparing csv result for teamStandings", e);
            return null;
        }

        return new InputStreamResource(byteArrayOutputStream);
    }
}
