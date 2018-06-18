package com.ccizer.transactionsstatistics.converter;

import com.ccizer.transactionsstatistics.model.response.StatisticsRetrieveResponse;
import com.ccizer.transactionsstatistics.model.vo.StatisticsVo;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StatisticsVoToRetrieveResponseConverterTest {

    private StatisticsVoToRetrieveResponseConverter statisticsVoToRetrieveResponseConverter;

    @Before
    public void setUp() {
        statisticsVoToRetrieveResponseConverter = new StatisticsVoToRetrieveResponseConverter();
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
        StatisticsRetrieveResponse statisticsRetrieveResponse = statisticsVoToRetrieveResponseConverter.apply(statisticsVo);

        //then
        assertThat(statisticsRetrieveResponse).isNotNull();
        assertThat(statisticsRetrieveResponse.getAvg()).isEqualTo(10.0);
        assertThat(statisticsRetrieveResponse.getSum()).isEqualTo(20.0);
        assertThat(statisticsRetrieveResponse.getMax()).isEqualTo(10.0);
        assertThat(statisticsRetrieveResponse.getMin()).isEqualTo(10.0);
        assertThat(statisticsRetrieveResponse.getCount()).isEqualTo(2L);
    }
}