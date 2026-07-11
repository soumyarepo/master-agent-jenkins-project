package com.devops;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTest {
    @Test
    void shouldAddTwoNumbers() {
        assertEquals(5, App.add(2, 3));
    }

    @Test
    void shouldReturnExpectedMessage() {
        assertEquals(
            "Hello from Jenkins Master-Agent Mini Project!",
            App.message()
        );
    }
}
