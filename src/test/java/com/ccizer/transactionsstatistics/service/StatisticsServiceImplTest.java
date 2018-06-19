package com.ccizer.transactionsstatistics.service;

import com.ccizer.transactionsstatistics.model.vo.StatisticsVo;
import com.ccizer.transactionsstatistics.model.vo.TransactionVo;
import com.ccizer.transactionsstatistics.service.impl.StatisticsServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.time.Instant;

import static com.ccizer.transactionsstatistics.constant.TransactionConstants.MAP_OF_TRANSACTIONS;
import static org.assertj.core.api.Assertions.assertThat;

public class StatisticsServiceImplTest {

    private StatisticsServiceImpl statisticsService;

    @Before
    public void setUp() {
        statisticsService = new StatisticsServiceImpl();
        MAP_OF_TRANSACTIONS.clear();
    }

    @Test
    public void should_retrieve_statistics() {
        //given
        fillMapOfTransactions();

        //when
        StatisticsVo statisticsVo = statisticsService.retrieveStatistics();

        //then
        assertThat(statisticsVo).isNotNull();
        assertThat(statisticsVo.getAvg()).isEqualTo(20.0);
        assertThat(statisticsVo.getSum()).isEqualTo(60.0);
        assertThat(statisticsVo.getMax()).isEqualTo(30.0);
        assertThat(statisticsVo.getMin()).isEqualTo(10.0);
        assertThat(statisticsVo.getCount()).isEqualTo(3L);
    }

    private void fillMapOfTransactions() {
        TransactionVo transactionVo = new TransactionVo();
        transactionVo.setAmount(10.0);
        transactionVo.setTime(Instant.now().minusSeconds(2).toEpochMilli());

        TransactionVo transactionVo2 = new TransactionVo();
        transactionVo2.setAmount(20.0);
        transactionVo2.setTime(Instant.now().minusSeconds(1).toEpochMilli());

        TransactionVo transactionVo3 = new TransactionVo();
        transactionVo3.setAmount(30.0);
        transactionVo3.setTime(Instant.now().toEpochMilli());

        MAP_OF_TRANSACTIONS.put(transactionVo.getTime(), transactionVo.getAmount());
        MAP_OF_TRANSACTIONS.put(transactionVo2.getTime(), transactionVo2.getAmount());
        MAP_OF_TRANSACTIONS.put(transactionVo3.getTime(), transactionVo3.getAmount());
    }
}