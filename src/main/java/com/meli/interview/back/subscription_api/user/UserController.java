package com.meli.interview.back.subscription_api.user;

public class UserController {

    public String createUser() {
        return "";
    }

}
/* [UserController.class]

  - De controlador solo tiene el nombre, porque en si es un POJO o clase normal
  - Para que este componente fuera un controlador gestionado por "IOC" Inyeccion-De-Control
    y fuera "DI" inyectado como una dependencia

        Carece de @Anotaciones:
            @RestController: controlador que devolvera objetos (JSON,XML,..etc)
            @RequestMapping("/users"): una ruta para acceder a los endpoints
        Carece de:
            - Inyeccion de dependencias hacia el servicio de usuarios
                @Autowired private UserService userService
            - Manejar una capa de aplicación, que gestione la lógica para caso de uso [UsuarioUsecase]

   --------------------------------------------------------------------------------------
    - Solo define un endpoint para "crear-usuario" y está bien!. Porque su única finalidad
          es (consultar/crear/modificar/eliminar) -> "USUARIOS" .. y no [["SUSCRIPCIONES"]]
        Pero...
        - Debería haber otro controlador nuevo, algo como ["SubscripcionController.class"]
          el cual tendrá como única responsabilidad:
          (consultar/crear/modificar/eliminar) -> "SUSCRIPCIONES"
            - Donde contendrá el endpoint:

              * Devuelve el costo total de las suscripciones de un usuario
                    - siempre que el usuario que esté logueado
                    - se encuentre en su lista de amigos

  --------------------------------------------------------------------------------------

    - El proyecto no tiene una estructura bien definida por capas,
    - No se ve patrones de arquitectura
    - Ausencia de patrones de diseño implementados
        - Hubo una clase que intento implementar el patron singleton pero veremos mas adelante...
 */