package com.ccizer.transactionsstatistics.manager;

import com.ccizer.transactionsstatistics.converter.StatisticsVoToRetrieveResponseConverter;
import com.ccizer.transactionsstatistics.model.response.StatisticsRetrieveResponse;
import com.ccizer.transactionsstatistics.model.vo.StatisticsVo;
import com.ccizer.transactionsstatistics.service.StatisticsService;
import org.springframework.stereotype.Service;

@Service
public class StatisticsManager {

    private final StatisticsService statisticsService;
    private final StatisticsVoToRetrieveResponseConverter statisticsVoToRetrieveResponseConverter;

    public StatisticsManager(StatisticsService statisticsService,
                             StatisticsVoToRetrieveResponseConverter statisticsVoToRetrieveResponseConverter) {
        this.statisticsService = statisticsService;
        this.statisticsVoToRetrieveResponseConverter = statisticsVoToRetrieveResponseConverter;
    }

    public StatisticsRetrieveResponse retrieveStatistics() {
        StatisticsVo statisticsVo = statisticsService.retrieveStatistics();
        return statisticsVoToRetrieveResponseConverter.apply(statisticsVo);
    }
}