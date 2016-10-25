package com.aosas.audismart.model;

/**
 * The type Cliente unico.
 * Modelo para almacenar el id del cliente
 */
public class ClienteUnico {
    /**
     * The Id cliente.
     */
    public String id_cliente;
    /**
     * The Accion.
     */
    public String ACCION;

    /**
     * Instantiates a new Cliente unico.
     *
     * @param id_cliente the id cliente
     * @param ACCION     the accion
     */
    public ClienteUnico(String id_cliente, String ACCION) {
        this.id_cliente = id_cliente;
        this.ACCION = ACCION;
    }
}
