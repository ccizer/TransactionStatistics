package com.ccizer.transactionsstatistics.service;

import com.ccizer.transactionsstatistics.model.vo.TransactionVo;

public interface TransactionService {

    void storeTransaction(TransactionVo transactionVo);
}