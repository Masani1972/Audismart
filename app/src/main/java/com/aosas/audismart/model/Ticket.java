package com.aosas.audismart.model;

import java.io.Serializable;

/**
 * Created by dayanamartinez on 12-08-16.
 */
public class Ticket implements Serializable {

    public String id_ticket ="";
    public String id_cliente ="";
    public String titulo ="";
    public String asunto ="";
    public String archivo ="";
    public String rutaarchivo ="";
    public String responsable ="";
    public String id_estado ="";
    public String estado ="";
    public String empresa ="";
    public String fecha ="";
    public String fechacerrado ="";
    public String calificacion ="";
    public String ACCION ="";

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

