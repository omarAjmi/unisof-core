package com.unisoft.core.event;

import com.google.common.eventbus.Subscribe;
import com.unisoft.core.event.exception.EventContextNotFound;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

public class EventManagerTest {
    final static String TEST_CONTEXT = "test-context";
    final static TestEventListener TEST_EVENT_LISTENER = new TestEventListener();

    @Test
    void register() {
        assertDoesNotThrow(() -> EventManager.INSTANCE.register(TEST_CONTEXT, TEST_EVENT_LISTENER));
    }

    @Test
    void unregister() {
        EventManager.INSTANCE.register(TEST_CONTEXT, TEST_EVENT_LISTENER);
        assertDoesNotThrow(() -> EventManager.INSTANCE.unregister(TEST_CONTEXT, TEST_EVENT_LISTENER));
    }

    @Test
    void broadcast() {
        assertDoesNotThrow(() -> EventManager.INSTANCE.register(TEST_CONTEXT, TEST_EVENT_LISTENER));
        EventManager.INSTANCE.broadcast(TEST_CONTEXT, new ApplicationEvent("test-event", "test-payload"));
        assertTrue(TEST_EVENT_LISTENER.isInvoked());
    }

    @Test
    void broadcastFail() {
        EventManager.INSTANCE.clear();
        assertThrows(EventContextNotFound.class,
                () -> EventManager.INSTANCE.broadcast(TEST_CONTEXT,
                        new ApplicationEvent("test-event", "test-payload")));
    }

    public static class TestEventListener implements EventListener {
        private final Logger log = LoggerFactory.getLogger(TestEventListener.class);
        private boolean invoked;

        @Subscribe
        @Override
        public void handle(Event event) {
            log.info("handling {} event", event.identifier());
            this.invoked = true;
        }

        public boolean isInvoked() {
            return invoked;
        }
    }
}