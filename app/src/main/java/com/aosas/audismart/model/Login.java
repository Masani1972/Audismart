package com.aosas.audismart.model;


/**
 * The type Login.
 * Modelo que contiene las credenciales del login
 */
public class Login {
    /**
     * The Email.
     */
    public String email ="";
    /**
     * The Contrasena.
     */
    public String contrasena = "";
    /**
     * The Accion.
     */
    public String ACCION ="";

    /**
     * Instantiates a new Login.
     *
     * @param email       the email
     * @param contrassena the contrassena
     * @param ACCION      the accion
     */
    public Login(String email, String contrassena,String ACCION){
this.email = email;
        this.contrasena = contrassena;
        this.ACCION = ACCION;
    }
}
