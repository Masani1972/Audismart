package com.aosas.audismart.model;

/**
 * Created by Lmartinez on 21/01/2016.
 */
public class Login {
    public String email ="";
    public String contrasena = "";
    public String ACCION ="";

    public Login(String email, String contrassena,String ACCION){
this.email = email;
        this.contrasena = contrassena;
        this.ACCION = ACCION;
    }
}
