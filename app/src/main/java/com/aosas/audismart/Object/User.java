package com.aosas.audismart.Object;

/**
 * Created by Lmartinez on 06/01/2016.
 */
public class User {
    String nombres ="";
    String apellidos = "";
    String email ="";
    String id_departamento ="";
    String id_ciudad = "";
    String telefono ="";
    String contrasena ="";
    String acepto_terminos = "";
    String acepto_envio ="";
    String ACCION ="";

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
