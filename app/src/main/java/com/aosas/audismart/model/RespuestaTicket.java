package com.aosas.audismart.model;

import com.squareup.okhttp.RequestBody;

/**
 * Created by dayanamartinez on 08-09-16.
 */
public class RespuestaTicket {
    public String id_ticket ="";
    public String asunto="";
    public RequestBody archivo;
    public String ACCION;

    public RespuestaTicket(String id_ticket, String asunto, RequestBody archivo, String ACCION){
        this.id_ticket = id_ticket;
        this.asunto = asunto;
        this.archivo = archivo;
        this.ACCION = ACCION;

    }
}
