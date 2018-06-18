package com.ccizer.transactionsstatistics.service;

import com.ccizer.transactionsstatistics.model.vo.TransactionVo;
import org.springframework.stereotype.Service;

import static com.ccizer.transactionsstatistics.constants.TransactionConstants.MAP_OF_TRANSACTIONS;

@Service
public class TransactionService {

    public void storeTransaction(TransactionVo transactionVo) {
        MAP_OF_TRANSACTIONS.put(transactionVo.getTimestamp(), transactionVo.getAmount());
    }
}