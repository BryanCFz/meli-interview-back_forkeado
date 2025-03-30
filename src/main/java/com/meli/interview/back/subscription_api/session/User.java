package com.meli.interview.back.subscription_api.session;

import com.meli.interview.back.subscription_api.subscription.Subscription;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String id;
    private String name;

    private List<Subscription> subscriptions = new ArrayList<Subscription>();
    private List<User> friends = new ArrayList<User>();

    public List<User> getFriends() {
        return friends;
    }

    public void addFriend(User user) {
        friends.add(user);
    }

    public void addSubscription(Subscription trip) {
        subscriptions.add(trip);
    }

    public List<Subscription> subscriptions() {
        return subscriptions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

/*

   // CODE SMELL

1. Falta de encapsulamiento
    - Los métodos getFriends() y getSubscriptions() devuelven la referencia directa de las listas.
    - Esto permite modificaciones externas sin control.

2. Nombres poco claros
    - subscriptions() como nombre de mét0d0 es confuso. Debería ser getSubscriptions().

3. Falta de validaciones
    - addFriend(User user): No evita agregar null o duplicados.
    - addSubscription(Subscription subscription): No evita null.

4. Uso de ArrayList en la inicialización
    - Mejor usar List<> en las propiedades, pero inicializarlo en el constructor para evitar listas null.

________________________________________________________________________________________________________________________
 */

/**
 _______________________________________________________________________________________________________________________
  Refactors (Refactorización) _
 ________________________________

 @Getter
 @AllArgConstuctor
 public class User {

     private String id;
     private String name;
     private final Set<Subscription> subscriptions = new HashSet<>();
     private final Set<User> friends = new HashSet<>();

     public List<User> getFriends() {
        return Collections.unmodifiableList(friends.stream().toList());
     }

     public void addFriend(User user) {
         if (user == null) throw new IllegalArgumentException("Friend cannot be null");
         if (user.equals(this)) throw new IllegalArgumentException("Cannot befriend yourself");
         friends.add(user);
     }

     public List<Subscription> getSubscriptions() {
        return Collections.unmodifiableList(subscriptions.stream().toList());
     }

     public void addSubscription(Subscription subscription) {
         if (subscription == null) throw new IllegalArgumentException("Subscription cannot be null");
         subscriptions.add(subscription);
     }

     public void setName(String name) {
         if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
         }
         this.name = name;
     }
 }
 

 *
 */

/*
   // IMPROVEMENTS (MEJORAS)

+ Encapsulación
    - Las listas {friends, subscriptions} ya no podrán modificarse desde a fuera
+ Validaciones
    - se evitan valores "null" o comportamientos inesperados
    - Uso Set<>
        * Evita valores duplicados en las listas (friends, subscriptions)
        * Evita que se modifiquen las listas desde afueraCollections.unmodificableList()
+
+
 */