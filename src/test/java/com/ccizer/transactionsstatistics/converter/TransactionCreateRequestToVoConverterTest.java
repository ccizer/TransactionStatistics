package com.ccizer.transactionsstatistics.converter;

import com.ccizer.transactionsstatistics.model.request.TransactionCreateRequest;
import com.ccizer.transactionsstatistics.model.vo.TransactionVo;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class TransactionCreateRequestToVoConverterTest {

    private TransactionCreateRequestToVoConverter transactionCreateRequestToVoConverter;

    @Before
    public void setUp() {
        transactionCreateRequestToVoConverter = new TransactionCreateRequestToVoConverter();
    }

    @Test
    public void should_convert_transaction_create_request_to_vo() {
        //given
        TransactionCreateRequest transactionCreateRequest = new TransactionCreateRequest();
        transactionCreateRequest.setAmount(12.3);
        transactionCreateRequest.setTimestamp(1478192204000L);

        //when
        TransactionVo transactionVo = transactionCreateRequestToVoConverter.apply(transactionCreateRequest);

        //then
        assertThat(transactionVo).isNotNull();
        assertThat(transactionVo.getAmount()).isEqualTo(12.3);
        assertThat(transactionVo.getTimestamp()).isEqualTo(1478192204000L);
    }
}