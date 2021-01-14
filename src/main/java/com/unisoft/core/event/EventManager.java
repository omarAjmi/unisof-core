package com.unisoft.core.event;

import com.google.common.eventbus.EventBus;
import com.unisoft.core.event.exception.EventContextNotFound;
import com.unisoft.core.util.CoreUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @author omar.H.Ajmi
 * @since 18/10/2020
 */
public class EventManager {
    public static final EventManager INSTANCE = new EventManager();
    private static final Logger log = LoggerFactory.getLogger(EventManager.class);
    private static final String CONTEXT_ERROR = "'context' cannot be null";
    private final Set<EventBus> contexts = new HashSet<>();

    public void register(String on, final EventListener listener) {
        CoreUtil.requireNonNullOrEmpty(on, CONTEXT_ERROR);
        final EventBus bus = this.findContext(on).orElse(new EventBus(on));
        bus.register(listener);
        this.contexts.add(bus);
        log.debug("new listener has been registered: {}", listener.toString());
    }

    public void unregister(String on, EventListener listener) {
        CoreUtil.requireNonNullOrEmpty(on, CONTEXT_ERROR);
        this.findContext(on)
                .ifPresent(eventBus -> {
                    eventBus.unregister(listener);
                    log.debug("listener has been removed: {}", listener.toString());
                });
    }

    public void broadcast(String on, Event event) {
        CoreUtil.requireNonNullOrEmpty(on, CONTEXT_ERROR);
        final EventBus bus = this.contexts.stream()
                .filter(eventBus -> on.equals(eventBus.identifier()))
                .findFirst()
                .orElseThrow(() -> new EventContextNotFound(on));
        bus.post(event);
    }

    public void clear() {
        this.contexts.clear();
    }

    private Optional<EventBus> findContext(String context) {
        return this.contexts.stream()
                .filter(eventBus -> context.equalsIgnoreCase(eventBus.identifier()))
                .findFirst();
    }

    public void addContext(String context) {
        if (!this.findContext(context).isPresent()) {
            this.contexts.add(new EventBus(context.toUpperCase()));
        }
    }
}
