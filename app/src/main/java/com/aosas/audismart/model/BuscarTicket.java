package com.aosas.audismart.model;


/**
 * The type Buscar ticket.
 * Clase creada para el requets del servicio de busqueda de ticket
 */
public class BuscarTicket {

    /**
     * The Id ticket.
     */
    public String id_ticket = "";
    /**
     * The Id cliente.
     */
    public String id_cliente = "";
    /**
     * The Grupo.
     */
    public String grupo = "";
    /**
     * The Accion.
     */
    public String ACCION = "";

    /**
     * Instantiates a new Buscar ticket.
     *
     * @param id_ticket  the id ticket
     * @param id_cliente the id cliente
     * @param grupo      the grupo
     * @param ACCION     the accion
     */
    public BuscarTicket(String id_ticket, String id_cliente, String grupo, String ACCION) {
        this.id_ticket = id_ticket;
        this.id_cliente = id_cliente;
        this.grupo = grupo;
        this.ACCION = ACCION;
    }
}

