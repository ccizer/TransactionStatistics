package com.ccizer.transactionsstatistics.manager;

import com.ccizer.transactionsstatistics.converter.StatisticsVoToResponseConverter;
import com.ccizer.transactionsstatistics.manager.impl.StatisticsManagerImpl;
import com.ccizer.transactionsstatistics.model.response.StatisticsResponse;
import com.ccizer.transactionsstatistics.model.vo.StatisticsVo;
import com.ccizer.transactionsstatistics.service.impl.StatisticsServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StatisticsManagerImplTest {

    @InjectMocks
    private StatisticsManagerImpl statisticsManager;

    @Mock
    private StatisticsServiceImpl statisticsService;

    @Mock
    private StatisticsVoToResponseConverter statisticsVoToResponseConverter;

    @Test
    public void should_retrieve_statistics() {
        //given
        StatisticsVo statisticsVo = new StatisticsVo();
        statisticsVo.setAvg(10.0);
        statisticsVo.setSum(10.0);
        statisticsVo.setMax(10.0);
        statisticsVo.setMin(10.0);
        statisticsVo.setCount(1L);

        StatisticsResponse statisticsResponse = new StatisticsResponse();
        statisticsResponse.setAvg(10.0);
        statisticsResponse.setSum(10.0);
        statisticsResponse.setMax(10.0);
        statisticsResponse.setMin(10.0);
        statisticsResponse.setCount(1L);

        when(statisticsService.retrieveStatistics()).thenReturn(statisticsVo);
        when(statisticsVoToResponseConverter.apply(statisticsVo)).thenReturn(statisticsResponse);

        //when
        StatisticsResponse response = statisticsManager.retrieveStatistics();

        //then
        assertThat(response).isNotNull();
        assertThat(response.getSum()).isEqualTo(10.0);
        assertThat(response.getAvg()).isEqualTo(10.0);
        assertThat(response.getMin()).isEqualTo(10.0);
        assertThat(response.getMax()).isEqualTo(10.0);
        assertThat(response.getCount()).isEqualTo(1L);

        InOrder inOrder = Mockito.inOrder(statisticsService, statisticsVoToResponseConverter);
        inOrder.verify(statisticsService).retrieveStatistics();
        inOrder.verify(statisticsVoToResponseConverter).apply(statisticsVo);
        inOrder.verifyNoMoreInteractions();
    }
}