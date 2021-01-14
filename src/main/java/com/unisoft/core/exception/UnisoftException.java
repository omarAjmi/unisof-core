package com.unisoft.core.exception;

/**
 * The base Unisoft exception.
 *
 * @author omar.H.Ajmi
 * @since 19/10/2020
 */
public class UnisoftException extends RuntimeException {
    /**
     * Initializes a new instance of the UnisoftException class.
     */
    public UnisoftException() {
        super();
    }

    /**
     * Initializes a new instance of the UnisoftException class.
     *
     * @param message The exception message.
     */
    public UnisoftException(final String message) {
        super(message);
    }

    /**
     * Initializes a new instance of the UnisoftException class.
     *
     * @param cause The {@link Throwable} which caused the creation of this UnisoftException.
     */
    public UnisoftException(final Throwable cause) {
        super(cause);
    }

    /**
     * Initializes a new instance of the UnisoftException class.
     *
     * @param message The exception message.
     * @param cause   The {@link Throwable} which caused the creation of this UnisoftException.
     */
    public UnisoftException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Initializes a new instance of the UnisoftException class.
     *
     * @param message            The exception message.
     * @param cause              The {@link Throwable} which caused the creation of this UnisoftException.
     * @param enableSuppression  Whether suppression is enabled or disabled.
     * @param writableStackTrace Whether the exception stack trace will be filled in.
     */
    public UnisoftException(final String message, final Throwable cause, final boolean enableSuppression,
                            final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
