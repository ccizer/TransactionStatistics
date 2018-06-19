package com.ccizer.transactionsstatistics.service.impl;

import com.ccizer.transactionsstatistics.model.vo.TransactionVo;
import com.ccizer.transactionsstatistics.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static com.ccizer.transactionsstatistics.constant.TransactionConstants.MAP_OF_TRANSACTIONS;

@Service
public class TransactionServiceImpl implements TransactionService {

    private static final Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);

    @Override
    public void storeTransaction(TransactionVo transactionVo) {
        MAP_OF_TRANSACTIONS.put(transactionVo.getTime(), transactionVo.getAmount());
        logger.info("A new transaction was stored with amount {} and time {}", transactionVo.getAmount(),
                transactionVo.getTime());
    }
}