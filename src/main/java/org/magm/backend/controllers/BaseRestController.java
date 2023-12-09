package org.magm.backend.controllers;
/*
*La clase BaseRestController es una clase base que proporciona un método para obtener el usuario autenticado en el contexto de seguridad. Aquí está una descripción de la clase y su método principal:

Método getUserLogged:
protected User getUserLogged(): Este método protegido devuelve el usuario autenticado actualmente.
SecurityContextHolder.getContext().getAuthentication(): Obtiene el objeto de autenticación del contexto de seguridad actual.
User user = (User) auth.getPrincipal(): Extrae el objeto principal del usuario del objeto de autenticación. En este caso, se espera que el principal sea un objeto de tipo User que representa al usuario autenticado.
La intención de esta clase es proporcionar a los controladores una forma fácil y consistente de acceder al usuario autenticado en el contexto de seguridad.

En resumen, BaseRestController es una clase base que encapsula la lógica común relacionada con la obtención del usuario autenticado en el contexto de seguridad y puede ser utilizada por otros controladores para acceder a esta información de manera uniforme.
* */
import org.magm.backend.auth.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class BaseRestController {

    protected User getUserLogged() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        return user;
    }
}
