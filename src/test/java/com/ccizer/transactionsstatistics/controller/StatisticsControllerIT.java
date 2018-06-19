package com.ccizer.transactionsstatistics.controller;

import com.ccizer.transactionsstatistics.BaseWebIT;
import com.ccizer.transactionsstatistics.model.request.TransactionRequest;
import com.ccizer.transactionsstatistics.model.response.StatisticsResponse;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.Instant;

import static com.ccizer.transactionsstatistics.constants.TransactionConstants.MAP_OF_TRANSACTIONS;
import static org.assertj.core.api.Assertions.assertThat;

public class StatisticsControllerIT extends BaseWebIT {

    @Before
    public void setUp() {
        MAP_OF_TRANSACTIONS.clear();
    }

    @Test
    public void should_retrieve_transaction_statistics() {
        //given
        String transactionUrl = "http://localhost:" + serverPort + "/transactions";
        String statisticsUrl = "http://localhost:" + serverPort + "/statistics";

        createTransactions(transactionUrl);

        //when
        ResponseEntity<StatisticsResponse> responseEntity = testRestTemplate.getForEntity(statisticsUrl, StatisticsResponse.class);

        //then
        assertThat(responseEntity).isNotNull();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        StatisticsResponse statisticsResponse = responseEntity.getBody();
        assertThat(statisticsResponse.getAvg()).isEqualTo(20.0);
        assertThat(statisticsResponse.getSum()).isEqualTo(60.0);
        assertThat(statisticsResponse.getMax()).isEqualTo(30.0);
        assertThat(statisticsResponse.getMin()).isEqualTo(10.0);
        assertThat(statisticsResponse.getCount()).isEqualTo(3);
    }

    private void createTransactions(String transactionUrl) {
        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setTimestamp(Instant.now().minusSeconds(2).toEpochMilli());
        transactionRequest.setAmount(10.0);

        testRestTemplate.postForEntity(transactionUrl, transactionRequest, null);

        transactionRequest.setTimestamp(Instant.now().minusSeconds(1).toEpochMilli());
        transactionRequest.setAmount(20.0);

        testRestTemplate.postForEntity(transactionUrl, transactionRequest, null);

        transactionRequest.setTimestamp(Instant.now().toEpochMilli());
        transactionRequest.setAmount(30.0);

        testRestTemplate.postForEntity(transactionUrl, transactionRequest, null);
    }
}