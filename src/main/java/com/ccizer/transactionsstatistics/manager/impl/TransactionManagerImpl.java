package com.ccizer.transactionsstatistics.manager.impl;

import com.ccizer.transactionsstatistics.converter.TransactionRequestToVoConverter;
import com.ccizer.transactionsstatistics.manager.TransactionManager;
import com.ccizer.transactionsstatistics.model.request.TransactionRequest;
import com.ccizer.transactionsstatistics.model.vo.TransactionVo;
import com.ccizer.transactionsstatistics.service.TransactionService;
import com.ccizer.transactionsstatistics.validator.TransactionRequestValidator;
import org.springframework.stereotype.Service;

@Service
public class TransactionManagerImpl implements TransactionManager {

    private final TransactionService transactionService;
    private final TransactionRequestValidator transactionRequestValidator;
    private final TransactionRequestToVoConverter transactionRequestToVoConverter;

    public TransactionManagerImpl(TransactionService transactionService,
                                  TransactionRequestValidator transactionRequestValidator,
                                  TransactionRequestToVoConverter transactionRequestToVoConverter) {
        this.transactionService = transactionService;
        this.transactionRequestValidator = transactionRequestValidator;
        this.transactionRequestToVoConverter = transactionRequestToVoConverter;
    }

    @Override
    public void storeTransactionRequest(TransactionRequest transactionRequest) {
        transactionRequestValidator.validateRequest(transactionRequest);
        TransactionVo transactionVo = transactionRequestToVoConverter.apply(transactionRequest);
        transactionService.storeTransaction(transactionVo);
    }
}