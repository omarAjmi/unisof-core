package com.unisoft.core.event;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationEventTest {

    @Test
    void instantiationFails() {
        assertThrows(NullPointerException.class, () -> new ApplicationEvent(null, ""));
        assertThrows(IllegalArgumentException.class, () -> new ApplicationEvent("", ""));
    }

    @Test
    void instantiationSuccess() {
        final ApplicationEvent event = assertDoesNotThrow(() -> new ApplicationEvent("event-name", "event-load"));
        assertEquals("event-name", event.identifier());
        assertEquals("event-load", event.data());
    }
}