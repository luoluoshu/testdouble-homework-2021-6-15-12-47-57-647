package com.tw.banking;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

class AccountTest {

    @Test
    void should_call_addDeposit_when_invoke_deposit() {
        //given
        TransactionRepository transactionRepository = mock(TransactionRepository.class);
        Printer printer = mock(Printer.class);
        Account account = new Account(transactionRepository, printer);
        int amount = 100;
        //when
        account.deposit(amount);
        //then
        verify(transactionRepository, times(1)).addDeposit(amount);
    }

    @Test
    void should_call_addWithdraw_when_invoke_withdraw() {
        //given
        TransactionRepository transactionRepository = mock(TransactionRepository.class);
        Printer printer = mock(Printer.class);
        Account account = new Account(transactionRepository, printer);
        int amount = 100;
        //when
        account.withdraw(amount);
        //then
        verify(transactionRepository, times(1)).addWithdraw(amount);
    }

    @Test
    void should_call_print_and_parameter_is_same_with_the_result_of_addTransactions_when_invoke_printStatement() {
        //given
        TransactionRepository transactionRepository = mock(TransactionRepository.class);
        Printer printer = mock(Printer.class);
        Account account = new Account(transactionRepository, printer);
        List<Transaction> transactions = Collections.singletonList(new Transaction("date", 100));
        given(transactionRepository.allTransactions()).willReturn(transactions);
        //when
        account.printStatement();
        //then
        verify(printer, times(1)).print(transactions);
    }
}