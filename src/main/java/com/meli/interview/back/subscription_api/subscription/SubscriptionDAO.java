package com.meli.interview.back.subscription_api.subscription;

import com.meli.interview.back.subscription_api.exception.CollaboratorCallException;
import com.meli.interview.back.subscription_api.session.User;

import java.util.ArrayList;

public class SubscriptionDAO {

    public static ArrayList<Subscription> findSubscriptionByUser(User user) {
        throw new CollaboratorCallException(
            "TripDAO should not be invoked on an unit test.");
    }
}


/**
 * 1. Qué encontraste
 *  + Mét0d0 getLoggedUser() con exception
 *      - El mét0d0 está diseñado para fallar en pruebas unitarias, lo que impide probar para lo que fue creado
 */
