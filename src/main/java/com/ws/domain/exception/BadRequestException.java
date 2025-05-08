package com.ws.domain.exception;


/**
 * Custom exception thrown when a requested resource is not found.
 *
 * <p>This exception is typically used to indicate that a certain resource,
 * such as a price or product, could not be found in the system. It extends
 * {@link RuntimeException} to allow for unchecked exceptions that do not
 * require explicit handling.</p>
 *
 * @see RuntimeException
 */

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
