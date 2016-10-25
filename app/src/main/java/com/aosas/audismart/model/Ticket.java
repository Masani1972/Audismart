package com.aosas.audismart.model;

import java.io.Serializable;


/**
 * The type Ticket.
 * clase que modela los ticket recibidos del servicio web
 * y para el fomulario de nuevo ticket
 */
public class Ticket implements Serializable {

    /**
     * The Id ticket.
     */
    public String id_ticket ="";
    /**
     * The Id cliente.
     */
    public String id_cliente ="";
    /**
     * The Titulo.
     */
    public String titulo ="";
    /**
     * The Asunto.
     */
    public String asunto ="";
    /**
     * The Archivo.
     */
    public String archivo ="";
    /**
     * The Rutaarchivo.
     */
    public String rutaarchivo ="";
    /**
     * The Responsable.
     */
    public String responsable ="";
    /**
     * The Id estado.
     */
    public String id_estado ="";
    /**
     * The Estado.
     */
    public String estado ="";
    /**
     * The Empresa.
     */
    public String empresa ="";
    /**
     * The Fecha.
     */
    public String fecha ="";
    /**
     * The Fechacerrado.
     */
    public String fechacerrado ="";
    /**
     * The Calificacion.
     */
    public String calificacion ="";
    /**
     * The Accion.
     */
    public String ACCION ="";

    /**
     * Instantiates a new Ticket.
     *
     * @param id_ticket    the id ticket
     * @param id_cliente   the id cliente
     * @param titulo       the titulo
     * @param asunto       the asunto
     * @param archivo      the archivo
     * @param rutaarchivo  the rutaarchivo
     * @param responsable  the responsable
     * @param id_estado    the id estado
     * @param estado       the estado
     * @param empresa      the empresa
     * @param fecha        the fecha
     * @param fechacerrado the fechacerrado
     * @param calificacion the calificacion
     * @param ACCION       the accion
     */
    public Ticket(String id_ticket, String id_cliente, String titulo, String asunto, String archivo, String rutaarchivo, String responsable, String id_estado, String estado, String empresa, String fecha, String fechacerrado, String calificacion, String ACCION) {

        this.id_ticket = id_ticket;
        this.id_cliente = id_cliente;
        this.titulo = titulo;
        this.asunto = asunto;
        this.archivo = archivo;
        this.rutaarchivo = rutaarchivo;
        this.responsable = responsable;
        this.id_estado = id_estado;
        this.estado = estado;
        this.empresa = empresa;
        this.fecha = fecha;
        this.fechacerrado = fechacerrado;
        this.calificacion = calificacion;
        this.ACCION = ACCION;

    }




}

