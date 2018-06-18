package com.ccizer.transactionsstatistics.validator;

import com.ccizer.transactionsstatistics.exception.TransactionValidationException;
import com.ccizer.transactionsstatistics.model.request.TransactionCreateRequest;
import org.springframework.stereotype.Component;

import java.time.Instant;

import static com.ccizer.transactionsstatistics.constants.TransactionConstants.SIXTY_SECONDS;

@Component
public class TransactionCreateRequestValidator {

    public void validateRequest(TransactionCreateRequest transactionCreateRequest) {
        long lastSixtySecondsInEpoch = Instant.now().minusSeconds(SIXTY_SECONDS).toEpochMilli();

        if (transactionCreateRequest.getTimestamp() < lastSixtySecondsInEpoch) {
            throw new TransactionValidationException("Transaction timestamp is older than 60 seconds");
        }
    }
}