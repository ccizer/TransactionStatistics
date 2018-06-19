package com.ccizer.transactionsstatistics.handler;

import com.ccizer.transactionsstatistics.exception.TransactionValidationException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageNotReadableException;

import static org.assertj.core.api.Assertions.assertThat;

public class RestControllerExceptionHandlerTest {

    private RestControllerExceptionHandler restControllerExceptionHandler;

    @Before
    public void setUp() {
        restControllerExceptionHandler = new RestControllerExceptionHandler();
    }

    @Test
    public void should_handle_http_message_not_readable_exception() {
        //when
        ResponseEntity responseEntity = restControllerExceptionHandler.handleHttpMessageNotReadableException(
                new HttpMessageNotReadableException("exception"));

        //then
        assertThat(responseEntity.getBody()).isNull();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

    }

    @Test
    public void should_handle_http_message_conversion_exception() {
        //when
        ResponseEntity responseEntity = restControllerExceptionHandler.handleHttpMessageConversionException(
                new HttpMessageConversionException("exception"));

        //then
        assertThat(responseEntity.getBody()).isNull();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @Test
    public void should_handle_transaction_validation_exception() {
        //when
        ResponseEntity responseEntity = restControllerExceptionHandler.handleTransactionValidationException(
                new TransactionValidationException("exception"));

        //then
        assertThat(responseEntity.getBody()).isNull();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    public void should_handle_generic_exception() {
        //when
        ResponseEntity responseEntity = restControllerExceptionHandler.handleException(
                new Exception("exception"));

        //then
        assertThat(responseEntity.getBody()).isNull();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}