package com.meli.interview.back.subscription_api.subscription;

public class Subscription {
    private String partner;

    public float getPrice() {
        float price = 0;
        if (partner.equals("disney")) {
            price = 100;
        }

        if (partner.equals("netflix")) {
            price = 200;
        }

        if (partner.equals("spotify")) {
            price = 50;
        } else {
            price = 0;
        }

        return price;
     }
}

/**
 * 1. Qué encontraste
 *  * Codigo propenso a fallos:
 *      - No se recomienda quemar valores, se recomienda:
 *          + usar constantes
 *          + un archivo.properties para partners
 *          + crear un enum "PartnerEnum" que contenga cada partner con su respectivo valor

 *   * Rompimiento de Principio SOLID ("SRP", Principio de responsabilidad única)
 *      [line:8 to line:20]: si se agregan más servicios o se eliminan algunos servicios "partner"
 *                           esto obliga a modificarse el código del mét0d0 getprice() y eso estaría mal.
 *                  - Debería estar en otro lado de forma flexible

 *   * Manejo de valores desconocidos y fallo en condicionales de búsqueda
 *      - si "partner" es null, la comparación fallará con un NullPointerException
 *        SL: Usar Object.equals(partner, "disney") o verificar si partnet es null
 *   * If separado por partner hace que se ejecuten cada if de forma innecesaria
 *      - SL: usar if-else if, o usar un map
 *
 *   * El mét0d0 solo tendrá en cuenta el valor del partner "spotify", el problema esta en que si es un partner diferente
 *     a "spotify", siempre va a entrar por el else, retornando cero
 *
 *   * Se recomienda uso de logs o lanzar exepciones personalizadas para detectar errores más rápido


 _______________________________________________________________________________________________________________________
    _____________________________
    -> UNA PROPUESTA DE MEJORIA _
    _____________________________

 public class Subscription {
     private static final Map<String, Float> PRICES = Map.of(
     "disney", 100f,
     "netflix", 200f,
     "spotify", 50f
     );

     private String partner;

     public float getPrice() {
         if (partner == null) {
         throw new IllegalStateException("Partner name is null in Subscription");
         }

         Float price = PRICES.get(partner);
         if (price == null) {
         throw new IllegalArgumentException("Unknown partner: " + partner);
         }

        return PRICES.getOrDefault(partner, 0f);
     }
 }

 *
 */