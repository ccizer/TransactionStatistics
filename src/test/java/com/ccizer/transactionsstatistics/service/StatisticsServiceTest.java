package com.ccizer.transactionsstatistics.service;

import com.ccizer.transactionsstatistics.model.vo.StatisticsVo;
import com.ccizer.transactionsstatistics.model.vo.TransactionVo;
import org.junit.Before;
import org.junit.Test;

import java.time.Instant;

import static com.ccizer.transactionsstatistics.constants.TransactionConstants.MAP_OF_TRANSACTIONS;
import static org.assertj.core.api.Assertions.assertThat;

public class StatisticsServiceTest {

    private StatisticsService statisticsService;

    @Before
    public void setUp() {
        statisticsService = new StatisticsService();
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
        transactionVo.setTimestamp(Instant.now().toEpochMilli());

        TransactionVo transactionVo2 = new TransactionVo();
        transactionVo2.setAmount(20.0);
        transactionVo2.setTimestamp(Instant.now().toEpochMilli());

        TransactionVo transactionVo3 = new TransactionVo();
        transactionVo3.setAmount(30.0);
        transactionVo3.setTimestamp(Instant.now().toEpochMilli());

        MAP_OF_TRANSACTIONS.put(transactionVo.getTimestamp(), transactionVo.getAmount());
        MAP_OF_TRANSACTIONS.put(transactionVo2.getTimestamp(), transactionVo2.getAmount());
        MAP_OF_TRANSACTIONS.put(transactionVo3.getTimestamp(), transactionVo3.getAmount());
    }
}