package com.ccizer.transactionsstatistics.converter;

import com.ccizer.transactionsstatistics.model.request.TransactionCreateRequest;
import com.ccizer.transactionsstatistics.model.vo.TransactionVo;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class TransactionCreateRequestToVoConverter implements Function<TransactionCreateRequest, TransactionVo> {

    @Override
    public TransactionVo apply(TransactionCreateRequest transactionCreateRequest) {
        TransactionVo transactionVo = new TransactionVo();
        transactionVo.setAmount(transactionCreateRequest.getAmount());
        transactionVo.setTimestamp(transactionCreateRequest.getTimestamp());
        return transactionVo;
    }
}