package com.aosas.audismart.model;


/**
 * The type Contrasena.
 * Clase para enviar el mail y recuperar contraeña
 */
public class Contrasena {
    /**
     * The Email.
     */
    public String email;
    /**
     * The Accion.
     */
    public String ACCION;

    /**
     * Instantiates a new Contrasena.
     *
     * @param email  the email
     * @param ACCION the accion
     */
    public Contrasena (String email, String ACCION){
        this.email = email;
        this.ACCION = ACCION;
    }
}

