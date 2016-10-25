package com.aosas.audismart.model;


/**
 * The type Calendarios cliente.
 * Clase creada para modelar los calendarios recibidos del servicio web
 * corresponde a la lista de impuestos del menú princip
 */
public class CalendariosCliente {
    /**
     * The Id cliente.
     */
    public String id_cliente;
    /**
     * The Ano.
     */
    public String ano;
    /**
     * The Accion.
     */
    public String ACCION;

    /**
     * Instantiates a new Calendarios cliente.
     *
     * @param id_cliente the id cliente
     * @param ano        the ano
     * @param ACCION     the accion
     */
    public CalendariosCliente(String id_cliente, String ano, String ACCION) {
        this.id_cliente = id_cliente;
        this.ano = ano;
        this.ACCION = ACCION;
    }
}
