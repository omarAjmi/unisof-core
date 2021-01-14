package com.unisoft.core.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class UnisoftExceptionTest {

    @Test
    void doThrow() {
        assertThrows(UnisoftException.class, () -> {
            throw new UnisoftException();
        });
    }
}
