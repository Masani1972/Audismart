package com.aosas.audismart.model;

/**
 * Created by dayanamartinez on 12-08-16.
 */
public class BuscarTicket {

    public String id_ticket ="";
    public String id_cliente ="";
    public String grupo ="";
    public String ACCION ="";

    public BuscarTicket (String id_ticket,String id_cliente,String grupo,String ACCION){
        this.id_ticket = id_ticket;
        this.id_cliente = id_cliente;
        this.grupo = grupo;
        this.ACCION = ACCION;
    }

}

