package com.biggergames.tbslapi.service.match;

import com.biggergames.tbslapi.client.request.match.MatchScoreRequest;
import com.biggergames.tbslapi.client.response.match.MatchScoreResponse;
import com.biggergames.tbslapi.service.ServiceResult;

public interface IMatchService {
    ServiceResult<MatchScoreResponse> addMatchResult(MatchScoreRequest matchScoreRequest);
}
