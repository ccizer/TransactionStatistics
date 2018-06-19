package com.ccizer.transactionsstatistics.converter;

import com.ccizer.transactionsstatistics.model.request.TransactionRequest;
import com.ccizer.transactionsstatistics.model.vo.TransactionVo;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class TransactionRequestToVoConverter implements Function<TransactionRequest, TransactionVo> {

    @Override
    public TransactionVo apply(TransactionRequest transactionRequest) {
        TransactionVo transactionVo = new TransactionVo();
        transactionVo.setAmount(transactionRequest.getAmount());
        transactionVo.setTime(transactionRequest.getTimestamp());
        return transactionVo;
    }
}