package com.footballmatches.matches.controller;

import com.footballmatches.matches.services.FootballMatchesService;
import com.footballmatches.matches.services.UserService;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/matches")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class FootballMatchDetailController {

    @Autowired
    FootballMatchesService service;

    @Autowired
    UserService userService;

    @GetMapping("/totalmatches/{year}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Long> getNoOfMatchesByYear(@PathVariable("year") Long year){
        Long totalMatches=service.getNoOfMatchesByYear(year);
        return ResponseEntity.ok(totalMatches);
    }

    @GetMapping("/drawmatches/{year}")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<Long> getNoOfDrawMatchesByYear(@PathVariable("year") Long year){
        return ResponseEntity.ok(service.getNoOfDrawMatchesByYear(year));
    }

}
