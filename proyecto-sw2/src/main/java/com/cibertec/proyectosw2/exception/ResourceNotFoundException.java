package com.cibertec.proyectosw2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Excepción personalizada para indicar que un recurso (entidad)
 * solicitado por ID no existe en la base de datos.
 *
 * Al anotarla con @ResponseStatus(HttpStatus.NOT_FOUND),
 * Spring Boot responderá automáticamente con código 404
 * cuando esta excepción se lance y no sea capturada.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    /**
     * @param recurso  Nombre lógico del recurso (e.g. "Usuario", "Producto").
     * @param id       Valor del identificador buscado.
     */
    public ResourceNotFoundException(String recurso, Object id) {
        super(String.format("%s no encontrado con id = %s", recurso, id));
    }
}
