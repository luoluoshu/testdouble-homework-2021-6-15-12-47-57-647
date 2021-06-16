package com.tw.banking;

import static com.tw.banking.Printer.STATEMENT_HEADER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class PrinterTest {
    @Test
    void should_print_statement_header_when_call_print_given_anyTransactions() {
        Console spyConsole = mock(Console.class);
        Printer printer = new Printer(spyConsole);

        printer.print(anyList());

        verify(spyConsole, times(1)).printLine(STATEMENT_HEADER);
        verify(spyConsole, times(1)).printLine(Printer.STATEMENT_HEADER);
    }

    @Test
    void should_not_change_given_transactions_when_call_print() {
        Console dummyConsole = mock(Console.class);
        Printer printer = new Printer(dummyConsole);
        Transaction transaction1 = new Transaction("23/02/2019", 1);
        Transaction transaction2 = new Transaction("23/02/2018", 1);
        List<Transaction> givenTransactions = Arrays.asList(transaction1, transaction2);

        printer.print(givenTransactions);

        List<Transaction> expectedTransactions = Arrays.asList(transaction1, transaction2);
        assertTransactionsEquals(expectedTransactions, givenTransactions);
    }

    private void assertTransactionsEquals(List<Transaction> expectedTransactions, List<Transaction> givenTransactions) {
        for (int i = 0; i < expectedTransactions.size(); i++) {
            assertEquals(expectedTransactions.get(i), givenTransactions.get(i));
        }
    }
}