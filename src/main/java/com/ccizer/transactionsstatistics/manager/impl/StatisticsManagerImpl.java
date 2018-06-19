package com.ccizer.transactionsstatistics.manager.impl;

import com.ccizer.transactionsstatistics.converter.StatisticsVoToResponseConverter;
import com.ccizer.transactionsstatistics.manager.StatisticsManager;
import com.ccizer.transactionsstatistics.model.response.StatisticsResponse;
import com.ccizer.transactionsstatistics.model.vo.StatisticsVo;
import com.ccizer.transactionsstatistics.service.StatisticsService;
import org.springframework.stereotype.Service;

@Service
public class StatisticsManagerImpl implements StatisticsManager {

    private final StatisticsService statisticsService;
    private final StatisticsVoToResponseConverter statisticsVoToResponseConverter;

    public StatisticsManagerImpl(StatisticsService statisticsService,
                                 StatisticsVoToResponseConverter statisticsVoToResponseConverter) {
        this.statisticsService = statisticsService;
        this.statisticsVoToResponseConverter = statisticsVoToResponseConverter;
    }

    @Override
    public StatisticsResponse retrieveStatistics() {
        StatisticsVo statisticsVo = statisticsService.retrieveStatistics();
        return statisticsVoToResponseConverter.apply(statisticsVo);
    }
}