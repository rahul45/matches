package com.footballmatches.matches.respository.impl;

import com.footballmatches.matches.entity.FootBallMatchDetail;
import com.footballmatches.matches.respository.FootBallMatchDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import javax.xml.crypto.Data;
import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
public class FootBallMatchDetailsRepositoryImpl implements FootBallMatchDetailsRepository {
    private  final static String DATA_URL="https://jsonmock.hackerrank.com/api/football_matches";///?year=2011&page=10";

    @Override
    public List<FootBallMatchDetail> getMatchesDetailByYear(Long year) {
        RestTemplate template = new RestTemplate();
        List<FootBallMatchDetail> data = new ArrayList<>();
        String query = DATA_URL+"?year="+year+"&page=1";
        Map<String,Object> dataMap=template.getForObject(
                query, Map.class);
        if(dataMap.get("data")!=null) {
            for(LinkedHashMap<String,Object> map : (List<LinkedHashMap>) dataMap.get("data")){
                 FootBallMatchDetail matchDetail = new FootBallMatchDetail();
                 matchDetail.setYear((int)map.get("year"));
                 matchDetail.setCompetition((String)(map.get("competiton")));
                 matchDetail.setTeam1((String)(map.get("team1")));
                 matchDetail.setTeam1goals(Long.valueOf((String) map.get("team1goals")));
                 matchDetail.setTeam2goals(Long.valueOf((String) map.get("team2goals")));
                 data.add(matchDetail);
            }
        }
        return data;
    }

}
