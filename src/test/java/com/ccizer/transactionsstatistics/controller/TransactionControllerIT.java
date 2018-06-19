package com.ccizer.transactionsstatistics.controller;

import com.ccizer.transactionsstatistics.BaseWebIT;
import com.ccizer.transactionsstatistics.model.request.TransactionRequest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.Instant;

import static com.ccizer.transactionsstatistics.constants.TransactionConstants.MAP_OF_TRANSACTIONS;
import static org.assertj.core.api.Assertions.assertThat;

public class TransactionControllerIT extends BaseWebIT {

    @Before
    public void setUp() {
        MAP_OF_TRANSACTIONS.clear();
    }

    @Test
    public void should_create_transaction() {
        //given
        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setTimestamp(Instant.now().toEpochMilli());
        transactionRequest.setAmount(12.3);

        String url = "http://localhost:" + serverPort + "/transactions";

        //when
        ResponseEntity responseEntity = testRestTemplate.postForEntity(url, transactionRequest, null);

        //then
        assertThat(responseEntity).isNotNull();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void should_return_http_status_no_content_when_timestamp_is_older_than_sixty_seconds() {
        //given
        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setTimestamp(Instant.now().minusSeconds(100).toEpochMilli());
        transactionRequest.setAmount(12.3);

        String url = "http://localhost:" + serverPort + "/transactions";

        //when
        ResponseEntity responseEntity = testRestTemplate.postForEntity(url, transactionRequest, null);

        //then
        assertThat(responseEntity).isNotNull();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}