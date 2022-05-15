package com.biggergames.tbslapi.config.util;

import com.biggergames.tbslapi.model.TeamData;
import com.biggergames.tbslapi.model.TeamStandingData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataUtil {

    private static final String FILE_NAME = "teamList.csv";
    private static final String ZSET_KEY = "teamStandings";
    private static final String HASH_KEY = "team";
    private final RedisTemplate<String, Object> redisTemplate;

    private List<TeamData> getTeamDataList() throws IOException {
        List<TeamData> teamDataList = new ArrayList<>();
        boolean header = true;
        String line = null;

        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                this.getClass().getClassLoader().getResourceAsStream(FILE_NAME)))) {
            while ((line = br.readLine()) != null) {
                if (header) {
                    header = false;
                } else {
                    teamDataList.add(new TeamData(line));
                }
            }
        } catch (Exception e) {
            log.error("Exception while reading the input file", e.getMessage());
            throw e;
        }
        return teamDataList;
    }

    public Boolean initTeamStandings() {
        List<TeamData> teamDataList = null;
        Boolean returnStatus = false;

        try {
            teamDataList = getTeamDataList();

            for (TeamData data : teamDataList) {
                redisTemplate.opsForZSet().add(ZSET_KEY, data.getName(), data.getPoints());
                redisTemplate.opsForHash().put(HASH_KEY, data.getName(), data);

                log.info("Data Inserted/Updated Successfully for " + data.getName());
            }
        } catch (Exception e) {
            log.error("Redis Data Initialize error " + e.getMessage());
            return false;
        }
        log.info("Redis Data Initialize Completed");
        return returnStatus.booleanValue();
    }

    public TeamData getTeamDataFromHash(String teamName) {
        if (StringUtils.isEmpty(teamName)) {
            return null;
        }
        return (TeamData) redisTemplate.opsForHash().get(HASH_KEY, teamName);
    }

    public void updateTeamData(TeamData teamData) {
        redisTemplate.opsForZSet().add(ZSET_KEY, teamData.getName(), teamData.getPoints());
        redisTemplate.opsForHash().put(HASH_KEY, teamData.getName(), teamData);
        log.info("Team Data :" + teamData.getName() + " updated with new score :" + teamData.getPoints());

    }

    public List<TeamStandingData> getTeamStandingsData() {
        List<TeamStandingData> resultList = new ArrayList<>();
        Set<ZSetOperations.TypedTuple<Object>> typedTuples = redisTemplate.opsForZSet().reverseRangeWithScores(ZSET_KEY, 0, -1);
        int index = 0;
        for (ZSetOperations.TypedTuple typle : typedTuples) {
            TeamStandingData teamStandingData = new TeamStandingData();

            teamStandingData.setTeamData(getTeamDataFromHash(typle.getValue().toString()));
            teamStandingData.setRank(++index);

            resultList.add(teamStandingData);
        }

        return resultList;
    }

    public int getTeamRank(String teamName) {
        Long rank = redisTemplate.opsForZSet().reverseRank(ZSET_KEY, teamName);
        return rank != null ? rank.intValue() + 1 : -1;
    }

}
