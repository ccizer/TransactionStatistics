package com.ccizer.transactionsstatistics.service;

import com.ccizer.transactionsstatistics.model.vo.StatisticsVo;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.DoubleSummaryStatistics;
import java.util.Map;

import static com.ccizer.transactionsstatistics.constants.TransactionConstants.MAP_OF_TRANSACTIONS;
import static com.ccizer.transactionsstatistics.constants.TransactionConstants.SIXTY_SECONDS;

@Service
public class StatisticsService {

    public StatisticsVo retrieveStatistics() {
        DoubleSummaryStatistics doubleSummaryStatistics = calculateSummaryStatistics();
        return mapToStatisticsVo(doubleSummaryStatistics);
    }

    private DoubleSummaryStatistics calculateSummaryStatistics() {
        Instant instantCurrent = Instant.now();
        long currentTimeInEpoch = instantCurrent.toEpochMilli();
        long lastSixtySecondsInEpoch = instantCurrent.minusSeconds(SIXTY_SECONDS).toEpochMilli();

        return MAP_OF_TRANSACTIONS.entries()
                .stream()
                .filter(entry -> entry.getKey() >= lastSixtySecondsInEpoch && entry.getKey() <= currentTimeInEpoch)
                .map(Map.Entry::getValue)
                .mapToDouble(value -> value)
                .summaryStatistics();
    }

    private StatisticsVo mapToStatisticsVo(DoubleSummaryStatistics stats) {
        StatisticsVo statisticsVo = new StatisticsVo();
        statisticsVo.setSum(stats.getSum());
        statisticsVo.setAvg(stats.getAverage());
        statisticsVo.setMax(stats.getMax());
        statisticsVo.setMin(stats.getMin());
        statisticsVo.setCount(stats.getCount());
        return statisticsVo;
    }
}