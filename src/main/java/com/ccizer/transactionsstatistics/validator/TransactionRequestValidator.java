package com.ccizer.transactionsstatistics.validator;

import com.ccizer.transactionsstatistics.exception.TransactionValidationException;
import com.ccizer.transactionsstatistics.model.request.TransactionRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.time.Instant;

import static com.ccizer.transactionsstatistics.constant.TransactionConstants.SIXTY_SECONDS;

@Component
public class TransactionRequestValidator {

    public void validateRequest(TransactionRequest transactionRequest) {
        validateTimestamp(transactionRequest.getTimestamp());
        validateAmount(transactionRequest.getAmount());
    }

    private void validateTimestamp(Long timestamp) {
        long lastSixtySecondsInEpoch = Instant.now().minusSeconds(SIXTY_SECONDS).toEpochMilli();

        if (ObjectUtils.isEmpty(timestamp)) {
            throw new TransactionValidationException("Transaction timestamp cannot be empty");
        }

        if (timestamp < lastSixtySecondsInEpoch) {
            throw new TransactionValidationException("Transaction timestamp is older than 60 seconds");
        }
    }

    private void validateAmount(Double amount) {
        if (ObjectUtils.isEmpty(amount)) {
            throw new TransactionValidationException("Transaction amount cannot be empty");
        }
    }
}