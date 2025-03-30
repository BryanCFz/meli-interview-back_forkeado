package com.meli.interview.back.subscription_api.session;

import com.meli.interview.back.subscription_api.exception.CollaboratorCallException;

public class UserSession {

    private static final UserSession userSession = new UserSession();

    private UserSession() {
    }

    public static UserSession getInstance() {
        return userSession;
    }

    public User getLoggedUser() {
        throw new CollaboratorCallException(
            "UserSession.getLoggedUser() should not be called in an unit test");
    }

}


/**
 * 1. Qué encontraste
 *  * Patron Singleton, con estado global:
 *      - tiene una única instancia global en la aplicación, todos los usuarios manejan una única instancia
 *        o misma referencia de memoria
 *   * Mét0d0 getLoggedUser() con exception
 *      - El mét0d0 está diseñado para fallar en pruebas unitarias, lo que impide probar para lo que fue creado

 _______________________________________________________________________________________________________________________
 * 2. Improvements (mejoras)
 * En lugar de usar el patrón singleton podemos reestructurar la clase "UserSession" para que sea una clase
 * con estado inyectable, permitiendo:
 *  + Facilidad en la gestión de sesiones por usuario (login, logout)
 *  + Poder hacer pruebas unitarias donde se pueden mockear los test

 _______________________________________________________________________________________________________________________
 4. Refactors (Refactorización) _
 ________________________________

 public class UserSession {
     private User loggedUser;

    public Optional<User> getLoggedUser() {
        return Optional.ofNullable(loggedUser);
    }

    public void login(User user) {
        if (user == null) throw new IllegalArgumentException("User cannot be null");
        this.loggedUser = user;
    }

    public void logout() {
        this.loggedUser = null;
    }
 }

 *
 */