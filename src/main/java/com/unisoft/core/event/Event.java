package com.unisoft.core.event;

/**
 * application event contract
 *
 * @author omar.H.Ajmi
 * @since 18/10/2020
 */
public interface Event {

    /**
     * gets event identifier
     *
     * @return
     */
    String identifier();

    /**
     * gets event data
     *
     * @return
     */
    Object data();
}
