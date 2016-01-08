package com.aosas.audismart.comunication;

/**
 * Created by Lmartinez on 07/01/2016.
 */
public class ResponseAPI {

    String error = "";
    String mensaje = "";

    public ResponseAPI(String error, String mensaje) {
        this.error = error;
        this.mensaje = mensaje;
    }

}
