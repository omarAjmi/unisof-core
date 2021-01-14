package com.unisoft.core.event.exception;

import com.unisoft.core.exception.UnisoftException;

/**
 * @author omar.H.Ajmi
 * @since 18/10/2020
 */
public class EventContextNotFound extends UnisoftException {
    private static final String DEFAULT_MESSAGE = "No Context Found";

    public EventContextNotFound() {
        super(DEFAULT_MESSAGE);
    }

    public EventContextNotFound(String context) {
        super(DEFAULT_MESSAGE + ": " + context);
    }
}
