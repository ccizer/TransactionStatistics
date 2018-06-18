package com.ccizer.transactionsstatistics.service;

import com.ccizer.transactionsstatistics.model.vo.TransactionVo;
import org.junit.Before;
import org.junit.Test;

import static com.ccizer.transactionsstatistics.constants.TransactionConstants.MAP_OF_TRANSACTIONS;
import static org.assertj.core.api.Assertions.assertThat;

public class TransactionServiceTest {

    private TransactionService transactionService;

    @Before
    public void setUp() {
        transactionService = new TransactionService();
    }

    @Test
    public void should_store_transaction() {
        //given
        TransactionVo transactionVo = new TransactionVo();
        transactionVo.setAmount(10.0);
        transactionVo.setTimestamp(1478192204000L);

        //when
        transactionService.storeTransaction(transactionVo);

        //then
        assertThat(MAP_OF_TRANSACTIONS.containsEntry(1478192204000L, 10.0)).isTrue();
    }
}