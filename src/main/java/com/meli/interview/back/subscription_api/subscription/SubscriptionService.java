package com.meli.interview.back.subscription_api.subscription;

import com.meli.interview.back.subscription_api.exception.UserNotLoggedInException;
import com.meli.interview.back.subscription_api.session.User;
import com.meli.interview.back.subscription_api.session.UserSession;

import java.util.ArrayList;

public class SubscriptionService {

    /**
     * Devuelve el costo total de las suscripciones de un usuario siempre que el usuario que esté logueado
     * se encuentre en su lista de amigos
     * @param user
     * @return costo total de la suscripciones del user
     * @throws UserNotLoggedInException si no hay un usuario logueado
     */
    public Float getUserSubscriptionsCost(User user) throws UserNotLoggedInException {
        ArrayList<Subscription> subscriptionList = new ArrayList<Subscription>();

        // get logged user
        User loggedUser = UserSession.getInstance().getLoggedUser();
        boolean isFriend = false;
        if (loggedUser != null) {
            for (User friend : user.getFriends()) {
                if (friend == loggedUser) {
                    isFriend = true;
                    break;
                }
            }
            if (isFriend) {
                subscriptionList = SubscriptionDAO.findSubscriptionByUser(user);
            }

            float totalPrice = 0;

            for (Subscription subscription : subscriptionList) {
                totalPrice += subscription.getPrice();
            }

            return totalPrice;
        } else {
            throw new UserNotLoggedInException();
        }
    }
}

/**
 * 1. Qué encontraste

 *  * Falta de cohesión:
 *      + El mét0d0 está haciendo demasiadas cosas, tiene muchas responsabilidades
 *          - Tiene instancias repetidas innecesarias para lista de suscripciones
 *          - Está haciendo cálculos de suscripciones
 *          - Está buscando la instancia del usuario
 *          - Está buscando en la lista de amigos que tengan sesión activa
 *          - por cada amigo busca que suscripciones tiene
 *          - Está validando sesiones de usuario
 *          - hace un sin fin de responsabilidades que no le corresponden.

 *   * Manejo incorrecto de excepciones
 *      + Manejo incorrecto de exepciones, para usuarios logeados

 *   * Acoplamiento directo:
 *      + Alto acoplamiento {UsserSession, SubscriptionDao}
 *          - Si se cambia la forma en que obtenemos usuarios ó suscripciones, hay que modificar
 *            el mét0d0 getUserSubscriptionsCost(User)
 *
 *      + Rompimiento de Principio SOLID ("DI", Inversión de Dependencia)
 *        [line:22 & line:32]: Se está dependiendo de clases concretas {UsserSession, SubscriptionDao}, lo que limita
 *                             la flexibilidad del código
 *              - En vez de depender de clases concretas, se debe depender de una abstracción(interfaces)


 _______________________________________________________________________________________________________________________
 * 2. Code Smell(Malos olores en el código)

 * A. Código duplicado
 *      [<(doble inicialización)>]
 *          [line:19]: subscriptionList = new ArrayList<Subscription>();
 *                      - crea instancia y lo inicializa en vacío
 *          [line:32]: subscriptionList = SubscriptionDAO.findSubscriptionByUser(user);
 *                      - el mismo mét0d0 devuelve una instancia de la lista vacía o con datos
 *      [<(codigo-sumar-precios-suscripciones)>]
 *          [line:37-39]: el for que suma los precios de las suscripciones, debería extraerse a un mét0d0 reutilizable

 * B. Comparacion incorrecta (igualdad)
 *      [line:26]: friend == loggedUser
 *                  - se está comparando referencias o direcciones de memoria
 *                  - como la intención es comparar valores, esto generaría error
 *                      * En la clase [User.class] debemos
 *                          + sobreescribir los mét0d0s {equals(), hascode()}
 *                          + implementar interfaces como Serializable, Comparable, Comparator

 * C. Mét0d0 multifuncional
 *  - Tiene muchas responsabilidades (verificar usuario, revisar amigos, obtener suscripciones, calcular total)
 *      + Debería dividirse en mét0d0s más pequeños que cumplan una única responsabilidad

 * D. Dependencias estáticas
 *  - Al estar acopladas directamente impide:
 *      + inyección de dependencias
 *      + dificultad para pruebas unitarias, porque no pueden ser mockeadas. A menos que se realice con una conexión
 *        real de base datos, pero viola el principio de aislamiento de pruebas unitarias


 _______________________________________________________________________________________________________________________
 * 3. Improvements (Mejoras)

 * A. Aplicar principio SOLID(Inyección de dependencias)
 *  - En lugar de usar {UsserSession, SubscriptionDao}, deberían pasarse como dependencias para reducir el acoplamiento

 * B. Refactorizar getUserSubscriptionsCost(User)
 *  - dividir en mét0d0s más pequeños y evitar validaciones innecesarias.


 _______________________________________________________________________________________________________________________
 4. Refactors (Refactorización) _
 ________________________________


 //    ***Definir interfaces***
 public interface UserSessionRepository {
    User getLoggedUser();
 }
 public interface SubscriptionRepository {
    List<Subscription> findSubscriptionsByUser(User user);
 }


 //    ***Definir implementaciones ***
 public class UserSessionAdapter implements UserSessionRepository {
    public User getLoggedUser() {
        return UserSession.getInstance().getLoggedUser();
    }
 }
 public class SubscriptionDAOAdapter implements SubscriptionRepository {
    public List<Subscription> findSubscriptionsByUser(User user) {
        return SubscriptionDAO.findSubscriptionByUser(user);
    }
 }


 //    ***Definir servicios por cada componente ***
 public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;

    methods(){...} ...
 }
 public class SubscriptionService {
    private final UserSessionRepository userSessionRepository;

    methods(){...} ...
 }

 ....
 CAPA-APLICACION:  UseCase

 ....
 public class SubscriptionUseCase {
    private final UserSessionService userSessionService;
    private final SubscriptionService subscriptionService;

    getSubscription() {
        // logica de que deberia hacer
        // .....
    }
 }


 */