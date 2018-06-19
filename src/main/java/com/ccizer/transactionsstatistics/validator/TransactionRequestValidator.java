package com.ccizer.transactionsstatistics.validator;

import com.ccizer.transactionsstatistics.exception.TransactionValidationException;
import com.ccizer.transactionsstatistics.model.request.TransactionRequest;
import org.springframework.stereotype.Component;

import java.time.Instant;

import static com.ccizer.transactionsstatistics.constants.TransactionConstants.SIXTY_SECONDS;

@Component
public class TransactionRequestValidator {

    public void validateRequest(TransactionRequest transactionRequest) {
        long lastSixtySecondsInEpoch = Instant.now().minusSeconds(SIXTY_SECONDS).toEpochMilli();

        if (transactionRequest.getTimestamp() < lastSixtySecondsInEpoch) {
            throw new TransactionValidationException("Transaction timestamp is older than 60 seconds");
        }
    }
}