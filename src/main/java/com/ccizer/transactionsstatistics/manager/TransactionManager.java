package com.ccizer.transactionsstatistics.manager;

import com.ccizer.transactionsstatistics.converter.TransactionCreateRequestToVoConverter;
import com.ccizer.transactionsstatistics.model.request.TransactionCreateRequest;
import com.ccizer.transactionsstatistics.model.vo.TransactionVo;
import com.ccizer.transactionsstatistics.service.TransactionService;
import com.ccizer.transactionsstatistics.validator.TransactionCreateRequestValidator;
import org.springframework.stereotype.Service;

@Service
public class TransactionManager {

    private final TransactionService transactionService;
    private final TransactionCreateRequestValidator transactionCreateRequestValidator;
    private final TransactionCreateRequestToVoConverter transactionCreateRequestToVoConverter;

    public TransactionManager(TransactionService transactionService,
                              TransactionCreateRequestValidator transactionCreateRequestValidator,
                              TransactionCreateRequestToVoConverter transactionCreateRequestToVoConverter) {
        this.transactionService = transactionService;
        this.transactionCreateRequestValidator = transactionCreateRequestValidator;
        this.transactionCreateRequestToVoConverter = transactionCreateRequestToVoConverter;
    }

    public void storeTransactionRequest(TransactionCreateRequest transactionCreateRequest) {
        transactionCreateRequestValidator.validateRequest(transactionCreateRequest);
        TransactionVo transactionVo = transactionCreateRequestToVoConverter.apply(transactionCreateRequest);
        transactionService.storeTransaction(transactionVo);
    }
}