package com.ccizer.transactionsstatistics.manager;

import com.ccizer.transactionsstatistics.model.request.TransactionRequest;

public interface TransactionManager {

    void storeTransactionRequest(TransactionRequest transactionRequest);
}
