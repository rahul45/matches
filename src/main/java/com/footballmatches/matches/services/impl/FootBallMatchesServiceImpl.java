package com.footballmatches.matches.services.impl;

import com.footballmatches.matches.entity.FootBallMatchDetail;
import com.footballmatches.matches.respository.FootBallMatchDetailsRepository;
import com.footballmatches.matches.services.FootballMatchesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FootBallMatchesServiceImpl implements FootballMatchesService {

    @Autowired
    FootBallMatchDetailsRepository repository;
    @Override
    public Long getNoOfMatchesByYear(Long year) {
        List<FootBallMatchDetail> data= repository.getMatchesDetailByYear(year);
      return Long.valueOf(data.size());
    }

    @Override
    public Long getNoOfDrawMatchesByYear(Long year) {
        List<FootBallMatchDetail> data= repository.getMatchesDetailByYear(year);

        int totalCount=0;
        if(data!= null){
             totalCount=data.stream().filter(e->(e.getTeam2goals() ==e.getTeam1goals()))
                    .collect(Collectors.toList()).size();
        }
        return Long.valueOf(totalCount) ;
    }
}
