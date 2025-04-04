package com.meli.interview.back.subscription_api.exception;

public class CollaboratorCallException extends RuntimeException {

    private static final long serialVersionUID = -4584041339906109902L;

    public CollaboratorCallException() {
        super();
    }

    public CollaboratorCallException(String message,
                                     Throwable cause) {
        super(message, cause);
    }

    public CollaboratorCallException(String message) {
        super(message);
    }

    public CollaboratorCallException(Throwable cause) {
        super(cause);
    }


}


/**
 * 1. Qué encontraste
 *  * Constructores redundantes
 *      - el primer constructor podria eliminarse

 *  * Falta de documentación:
 *      - No se especifica en que contexto se lanza cada exepción

 */