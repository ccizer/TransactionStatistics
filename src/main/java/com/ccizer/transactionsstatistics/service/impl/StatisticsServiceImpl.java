package com.ccizer.transactionsstatistics.service.impl;

import com.ccizer.transactionsstatistics.model.vo.StatisticsVo;
import com.ccizer.transactionsstatistics.service.StatisticsService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.DoubleSummaryStatistics;
import java.util.Map;

import static com.ccizer.transactionsstatistics.constants.TransactionConstants.MAP_OF_TRANSACTIONS;
import static com.ccizer.transactionsstatistics.constants.TransactionConstants.SIXTY_SECONDS;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Override
    public StatisticsVo retrieveStatistics() {
        DoubleSummaryStatistics summaryStatistics = calculateSummaryStatistics();
        return mapToStatisticsVo(summaryStatistics);
    }

    private DoubleSummaryStatistics calculateSummaryStatistics() {
        Instant instantCurrent = Instant.now();
        long currentTimeInEpochMilli = instantCurrent.toEpochMilli();
        long lastSixtySecondsInEpochMilli = instantCurrent.minusSeconds(SIXTY_SECONDS).toEpochMilli();

        return MAP_OF_TRANSACTIONS.entries()
                .stream()
                .filter(entry -> entry.getKey() >= lastSixtySecondsInEpochMilli && entry.getKey() <= currentTimeInEpochMilli)
                .map(Map.Entry::getValue)
                .mapToDouble(value -> value)
                .summaryStatistics();
    }

    private StatisticsVo mapToStatisticsVo(DoubleSummaryStatistics summaryStatistics) {
        StatisticsVo statisticsVo = new StatisticsVo();
        statisticsVo.setSum(summaryStatistics.getSum());
        statisticsVo.setAvg(summaryStatistics.getAverage());
        statisticsVo.setMax(summaryStatistics.getMax());
        statisticsVo.setMin(summaryStatistics.getMin());
        statisticsVo.setCount(summaryStatistics.getCount());
        return statisticsVo;
    }
}