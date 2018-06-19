package com.ccizer.transactionsstatistics.manager;

import com.ccizer.transactionsstatistics.converter.TransactionRequestToVoConverter;
import com.ccizer.transactionsstatistics.manager.impl.TransactionManagerImpl;
import com.ccizer.transactionsstatistics.model.request.TransactionRequest;
import com.ccizer.transactionsstatistics.model.vo.TransactionVo;
import com.ccizer.transactionsstatistics.service.impl.TransactionServiceImpl;
import com.ccizer.transactionsstatistics.validator.TransactionRequestValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.Instant;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TransactionManagerImplTest {

    @InjectMocks
    private TransactionManagerImpl transactionManager;

    @Mock
    private TransactionServiceImpl transactionService;

    @Mock
    private TransactionRequestValidator transactionRequestValidator;

    @Mock
    private TransactionRequestToVoConverter transactionRequestToVoConverter;

    @Test
    public void should_store_transaction_request() {
        //given
        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setAmount(12.3);
        transactionRequest.setTimestamp(Instant.now().toEpochMilli());

        TransactionVo transactionVo = new TransactionVo();
        transactionVo.setTime(transactionRequest.getTimestamp());
        transactionVo.setAmount(transactionRequest.getAmount());

        when(transactionRequestToVoConverter.apply(transactionRequest)).thenReturn(transactionVo);

        //when
        transactionManager.storeTransactionRequest(transactionRequest);

        //then
        InOrder inOrder = Mockito.inOrder(transactionRequestValidator, transactionRequestToVoConverter,
                transactionService);
        inOrder.verify(transactionRequestValidator).validateRequest(transactionRequest);
        inOrder.verify(transactionRequestToVoConverter).apply(transactionRequest);
        inOrder.verify(transactionService).storeTransaction(transactionVo);
        inOrder.verifyNoMoreInteractions();
    }
}