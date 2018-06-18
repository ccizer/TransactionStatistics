package com.ccizer.transactionsstatistics.controller;

import com.ccizer.transactionsstatistics.manager.TransactionManager;
import com.ccizer.transactionsstatistics.model.request.TransactionCreateRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

    private final TransactionManager transactionManager;

    public TransactionController(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    @PostMapping("/transactions")
    @ResponseStatus(HttpStatus.CREATED)
    public void createTransaction(@RequestBody TransactionCreateRequest transactionCreateRequest) {
        transactionManager.storeTransactionRequest(transactionCreateRequest);
    }
}