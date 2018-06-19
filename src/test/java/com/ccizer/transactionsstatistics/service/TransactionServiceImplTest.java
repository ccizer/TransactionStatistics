package com.ccizer.transactionsstatistics.service;

import com.ccizer.transactionsstatistics.model.vo.TransactionVo;
import com.ccizer.transactionsstatistics.service.impl.TransactionServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.time.Instant;

import static com.ccizer.transactionsstatistics.constant.TransactionConstants.MAP_OF_TRANSACTIONS;
import static org.assertj.core.api.Assertions.assertThat;

public class TransactionServiceImplTest {

    private TransactionServiceImpl transactionService;

    @Before
    public void setUp() {
        transactionService = new TransactionServiceImpl();
        MAP_OF_TRANSACTIONS.clear();
    }

    @Test
    public void should_store_transaction() {
        //given
        long timeInEpochMilli = Instant.now().toEpochMilli();

        TransactionVo transactionVo = new TransactionVo();
        transactionVo.setAmount(10.0);
        transactionVo.setTime(timeInEpochMilli);

        //when
        transactionService.storeTransaction(transactionVo);

        //then
        assertThat(MAP_OF_TRANSACTIONS.containsEntry(timeInEpochMilli, 10.0)).isTrue();
    }
}