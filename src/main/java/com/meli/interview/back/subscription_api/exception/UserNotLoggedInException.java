package com.meli.interview.back.subscription_api.exception;

public class UserNotLoggedInException extends RuntimeException {
    private static final long serialVersionUID = 8959479918185637340L;
}

/**
 * 1. Qué encontraste
 *  * Ausencia de constructor
 *      - Para los mensajes que lanza la exepción, tocaría construirlo de forma manual

 *  * Ausencia de atributos:
 *      - Al lanzar una exepción no hay un mensaje que describa el error

 _______________________________________________________________________________________________________________________
 _____________________________
 -> UNA PROPUESTA DE MEJORIA _
 _____________________________

 public class UserNotLoggedInException extends RuntimeException {

     private static final long serialVersionUID = 8959479918185637340L;

     public UserNotLoggedInException() {
        super("User is not logged in");
     }

     public UserNotLoggedInException(String message) {
        super(message);
     }
 }


 */