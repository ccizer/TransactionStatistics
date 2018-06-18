package com.ccizer.transactionsstatistics.validator;

import com.ccizer.transactionsstatistics.exception.TransactionValidationException;
import com.ccizer.transactionsstatistics.model.request.TransactionCreateRequest;
import org.junit.Before;
import org.junit.Test;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class TransactionCreateRequestValidatorTest {

    private TransactionCreateRequestValidator transactionCreateRequestValidator;

    @Before
    public void setUp() {
        transactionCreateRequestValidator = new TransactionCreateRequestValidator();
    }

    @Test
    public void should_validate_transaction_create_request() {
        //given
        TransactionCreateRequest transactionCreateRequest = new TransactionCreateRequest();
        transactionCreateRequest.setAmount(12.3);
        transactionCreateRequest.setTimestamp(Instant.now().toEpochMilli());

        //when
        Throwable throwable = catchThrowable(() -> transactionCreateRequestValidator.validateRequest(transactionCreateRequest));

        //then
        assertThat(throwable).isNull();
    }

    @Test
    public void should_throw_exception_when_timestamp_is_older_than_sixty_seconds() {
        //given
        TransactionCreateRequest transactionCreateRequest = new TransactionCreateRequest();
        transactionCreateRequest.setAmount(12.3);
        transactionCreateRequest.setTimestamp(1478192204000L);

        //when
        Throwable throwable = catchThrowable(() -> transactionCreateRequestValidator.validateRequest(transactionCreateRequest));

        //then
        assertThat(throwable).isNotNull();
        assertThat(throwable).isInstanceOf(TransactionValidationException.class);
        TransactionValidationException transactionValidationException = (TransactionValidationException) throwable;
        assertThat(transactionValidationException.getMessage()).isEqualTo("Transaction timestamp is older than 60 seconds");
    }
}