package com.ccizer.transactionsstatistics.converter;

import com.ccizer.transactionsstatistics.model.request.TransactionRequest;
import com.ccizer.transactionsstatistics.model.vo.TransactionVo;
import org.junit.Before;
import org.junit.Test;

import java.time.Instant;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class TransactionRequestToVoConverterTest {

    private TransactionRequestToVoConverter transactionRequestToVoConverter;

    @Before
    public void setUp() {
        transactionRequestToVoConverter = new TransactionRequestToVoConverter();
    }

    @Test
    public void should_convert_transaction_create_request_to_vo() {
        //given
        long timeInEpochMilli = Instant.now().toEpochMilli();

        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setAmount(12.3);
        transactionRequest.setTimestamp(timeInEpochMilli);

        //when
        TransactionVo transactionVo = transactionRequestToVoConverter.apply(transactionRequest);

        //then
        assertThat(transactionVo).isNotNull();
        assertThat(transactionVo.getAmount()).isEqualTo(12.3);
        assertThat(transactionVo.getTime()).isEqualTo(timeInEpochMilli);
    }
}