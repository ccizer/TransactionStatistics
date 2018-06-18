package com.ccizer.transactionsstatistics.controller;

import com.ccizer.transactionsstatistics.manager.StatisticsManager;
import com.ccizer.transactionsstatistics.model.response.StatisticsRetrieveResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatisticsController {

    private final StatisticsManager statisticsManager;

    public StatisticsController(StatisticsManager statisticsManager) {
        this.statisticsManager = statisticsManager;
    }

    @GetMapping("/statistics")
    @ResponseStatus(HttpStatus.OK)
    public StatisticsRetrieveResponse retrieveStatistics() {
        return statisticsManager.retrieveStatistics();
    }
}