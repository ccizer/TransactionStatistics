package com.ccizer.transactionsstatistics.converter;

import com.ccizer.transactionsstatistics.model.response.StatisticsResponse;
import com.ccizer.transactionsstatistics.model.vo.StatisticsVo;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class StatisticsVoToResponseConverter implements Function<StatisticsVo, StatisticsResponse> {

    @Override
    public StatisticsResponse apply(StatisticsVo statisticsVo) {
        StatisticsResponse statisticsResponse = new StatisticsResponse();
        statisticsResponse.setAvg(statisticsVo.getAvg());
        statisticsResponse.setSum(statisticsVo.getSum());
        statisticsResponse.setMax(statisticsVo.getMax());
        statisticsResponse.setMin(statisticsVo.getMin());
        statisticsResponse.setCount(statisticsVo.getCount());
        return statisticsResponse;
    }
}