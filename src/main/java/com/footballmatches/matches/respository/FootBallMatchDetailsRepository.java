package com.footballmatches.matches.respository;

import com.footballmatches.matches.entity.FootBallMatchDetail;

import java.util.List;

public interface FootBallMatchDetailsRepository {

    List<FootBallMatchDetail> getMatchesDetailByYear(Long year);
}
