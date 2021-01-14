package com.unisoft.core.event;

import com.unisoft.core.util.CoreUtil;

import java.util.Objects;

/**
 * base class for an application event
 *
 * @author omar.H.Ajmi
 * @since 18/10/2020
 */
public class ApplicationEvent implements Event {


    private final String identifier;
    private final Object load;

    public ApplicationEvent(String identifier, Object load) {
        CoreUtil.requireNonNullOrEmpty(identifier, "'identifier' cannot be null or empty");
        Objects.requireNonNull(load, "'load' cannot be null");
        this.identifier = identifier;
        this.load = load;
    }

    @Override
    public String identifier() {
        return this.identifier;
    }

    @Override
    public Object data() {
        return this.load;
    }
}
