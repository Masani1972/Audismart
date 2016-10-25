package com.aosas.audismart.model;


/**
 * The type Notificacion cumplio.
 * Modelo para marcar como cumplida una notificacion
 * y enviarla a un servicio web
 */
public class NotificacionCumplio {
    /**
     * The Id.
     */
    public String id;
    /**
     * The Cumplido.
     */
    public String cumplido;
    /**
     * The Accion.
     */
    public String ACCION;

    /**
     * Instantiates a new Notificacion cumplio.
     *
     * @param id       the id
     * @param cumplido the cumplido
     * @param ACCION   the accion
     */
    public NotificacionCumplio(String id,String cumplido,String ACCION){
        this.id = id;
        this.cumplido = cumplido;
        this.ACCION = ACCION;
    }
}
