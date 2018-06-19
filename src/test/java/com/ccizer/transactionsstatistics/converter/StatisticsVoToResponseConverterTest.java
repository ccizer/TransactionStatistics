package com.ccizer.transactionsstatistics.converter;

import com.ccizer.transactionsstatistics.model.response.StatisticsResponse;
import com.ccizer.transactionsstatistics.model.vo.StatisticsVo;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StatisticsVoToResponseConverterTest {

    private StatisticsVoToResponseConverter statisticsVoToResponseConverter;

    @Before
    public void setUp() {
        statisticsVoToResponseConverter = new StatisticsVoToResponseConverter();
    }

    @Test
    public void should_convert_statistics_vo_to_statistics_retrieve_response() {
        //given
        StatisticsVo statisticsVo = new StatisticsVo();
        statisticsVo.setAvg(10.0);
        statisticsVo.setSum(20.0);
        statisticsVo.setMax(10.0);
        statisticsVo.setMin(10.0);
        statisticsVo.setCount(2L);

        //when
        StatisticsResponse statisticsResponse = statisticsVoToResponseConverter.apply(statisticsVo);

        //then
        assertThat(statisticsResponse).isNotNull();
        assertThat(statisticsResponse.getAvg()).isEqualTo(10.0);
        assertThat(statisticsResponse.getSum()).isEqualTo(20.0);
        assertThat(statisticsResponse.getMax()).isEqualTo(10.0);
        assertThat(statisticsResponse.getMin()).isEqualTo(10.0);
        assertThat(statisticsResponse.getCount()).isEqualTo(2L);
    }
}