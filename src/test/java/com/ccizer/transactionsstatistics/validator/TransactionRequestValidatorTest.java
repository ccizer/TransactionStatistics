package com.ccizer.transactionsstatistics.validator;

import com.ccizer.transactionsstatistics.exception.TransactionValidationException;
import com.ccizer.transactionsstatistics.model.request.TransactionRequest;
import org.junit.Before;
import org.junit.Test;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class TransactionRequestValidatorTest {

    private TransactionRequestValidator transactionRequestValidator;

    @Before
    public void setUp() {
        transactionRequestValidator = new TransactionRequestValidator();
    }

    @Test
    public void should_validate_transaction_create_request() {
        //given
        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setAmount(12.3);
        transactionRequest.setTimestamp(Instant.now().toEpochMilli());

        //when
        Throwable throwable = catchThrowable(() -> transactionRequestValidator.validateRequest(transactionRequest));

        //then
        assertThat(throwable).isNull();
    }

    @Test
    public void should_throw_exception_when_timestamp_is_older_than_sixty_seconds() {
        //given
        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setAmount(12.3);
        transactionRequest.setTimestamp(Instant.now().minusSeconds(100).toEpochMilli());

        //when
        Throwable throwable = catchThrowable(() -> transactionRequestValidator.validateRequest(transactionRequest));

        //then
        assertThat(throwable).isNotNull();
        assertThat(throwable).isInstanceOf(TransactionValidationException.class);
        TransactionValidationException transactionValidationException = (TransactionValidationException) throwable;
        assertThat(transactionValidationException.getMessage()).isEqualTo("Transaction timestamp is older than 60 seconds");
    }
}