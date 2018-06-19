package com.ccizer.transactionsstatistics.service.impl;

import com.ccizer.transactionsstatistics.model.vo.TransactionVo;
import com.ccizer.transactionsstatistics.service.TransactionService;
import org.springframework.stereotype.Service;

import static com.ccizer.transactionsstatistics.constants.TransactionConstants.MAP_OF_TRANSACTIONS;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Override
    public void storeTransaction(TransactionVo transactionVo) {
        MAP_OF_TRANSACTIONS.put(transactionVo.getTime(), transactionVo.getAmount());
    }
}