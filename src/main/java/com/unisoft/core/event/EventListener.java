package com.unisoft.core.event;

/**
 * application event listener contract
 *
 * @author omar.H.Ajmi
 * @since 18/10/2020
 */
@FunctionalInterface
public interface EventListener {

    /**
     * handle {@code Event}
     *
     * @param event
     */
    void handle(Event event);
}
