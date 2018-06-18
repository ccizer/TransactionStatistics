package com.ccizer.transactionsstatistics.converter;

import com.ccizer.transactionsstatistics.model.response.StatisticsRetrieveResponse;
import com.ccizer.transactionsstatistics.model.vo.StatisticsVo;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class StatisticsVoToRetrieveResponseConverter implements Function<StatisticsVo, StatisticsRetrieveResponse> {

    @Override
    public StatisticsRetrieveResponse apply(StatisticsVo statisticsVo) {
        StatisticsRetrieveResponse statisticsRetrieveResponse = new StatisticsRetrieveResponse();
        statisticsRetrieveResponse.setAvg(statisticsVo.getAvg());
        statisticsRetrieveResponse.setSum(statisticsVo.getSum());
        statisticsRetrieveResponse.setMax(statisticsVo.getMax());
        statisticsRetrieveResponse.setMin(statisticsVo.getMin());
        statisticsRetrieveResponse.setCount(statisticsVo.getCount());
        return statisticsRetrieveResponse;
    }
}
