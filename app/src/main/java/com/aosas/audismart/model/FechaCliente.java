package com.aosas.audismart.model;


/**
 * The type Fecha cliente.
 * Modela las notificaciones recibidas en el servicio web
 */
public class FechaCliente {
    /**
     * The Id cliente.
     */
    public String idCliente;
    /**
     * The Id calendario.
     */
    public String idCalendario;
    /**
     * The Id empresa.
     */
    public String idEmpresa;
    /**
     * The Accion.
     */
    public String ACCION;

    /**
     * Instantiates a new Fecha cliente.
     *
     * @param idCliente    the id cliente
     * @param idCalendario the id calendario
     * @param idEmpresa    the id empresa
     * @param ACCION       the accion
     */
    public FechaCliente(String idCliente, String idCalendario, String idEmpresa, String ACCION) {
        this.idCliente = idCliente;
        this.idCalendario = idCalendario;
        this.idEmpresa = idEmpresa;
        this.ACCION = ACCION;
    }
}
