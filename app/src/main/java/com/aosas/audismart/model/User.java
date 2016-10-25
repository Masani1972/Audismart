package com.aosas.audismart.model;


/**
 * The type User.
 * Modela toda la información del usuario
 */
public class User {
    /**
     * The Nombres.
     */
    public String nombres ="";
    /**
     * The Apellidos.
     */
    public String apellidos = "";
    /**
     * The Email.
     */
    public String email ="";
    /**
     * The Id departamento.
     */
    public String id_departamento ="";
    /**
     * The Id ciudad.
     */
    public String id_ciudad = "";
    /**
     * The Telefono.
     */
    public String telefono ="";
    /**
     * The Contrasena.
     */
    public String contrasena ="";
    /**
     * The Acepto terminos.
     */
    public String acepto_terminos = "";
    /**
     * The Acepto envio.
     */
    public String acepto_envio ="";
    /**
     * The Accion.
     */
    public String ACCION ="";

    /**
     * Instantiates a new User.
     *
     * @param nombres         the nombres
     * @param apellidos       the apellidos
     * @param email           the email
     * @param id_departamento the id departamento
     * @param id_ciudad       the id ciudad
     * @param telefono        the telefono
     * @param contrasena      the contrasena
     * @param acepto_terminos the acepto terminos
     * @param acepto_envio    the acepto envio
     * @param ACCION          the accion
     */
    public User(String nombres, String apellidos, String email, String id_departamento, String id_ciudad, String telefono, String contrasena, String acepto_terminos, String acepto_envio, String ACCION) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.email = email;
        this.id_departamento = id_departamento;
        this.id_ciudad = id_ciudad;
        this.telefono = telefono;
        this.contrasena = contrasena;
        this.acepto_terminos = acepto_terminos;
        this.acepto_envio = acepto_envio;
        this.ACCION = ACCION;
    }
}
