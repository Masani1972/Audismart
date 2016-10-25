package com.aosas.audismart.model;


import java.io.Serializable;
import java.util.Calendar;


/**
 * The type Notificacion.
 * Modela las notificacines recibidas del servicio web
 * es serializable para enviar entre actividades
 */
public class Notificacion implements Serializable {
    /**
     * The Id.
     */
    public String id, /**
     * The Id fecha.
     */
    idFecha, /**
     * The Id empresa.
     */
    idEmpresa, /**
     * The Nombre empresa.
     */
    nombreEmpresa, /**
     * The Id calanedario.
     */
    idCalanedario, /**
     * The Fecha.
     */
    fecha, /**
     * The Hora.
     */
    hora, /**
     * The Antes dias.
     */
    antesDias, /**
     * The Antes hora.
     */
    antesHora, /**
     * The Antes fecha.
     */
    antesFecha, /**
     * The Nombre.
     */
    nombre, /**
     * The Nombre corto.
     */
    nombreCorto, /**
     * The Periodo.
     */
    periodo, /**
     * The Cumplido.
     */
    cumplido, /**
     * The Fecha cumplido.
     */
    fechaCumplido, /**
     * The Accion.
     */
    ACCION;
    /**
     * The Calendar.
     */
    public Calendar calendar;

    /**
     * Instantiates a new Notificacion.
     *
     * @param id            the id
     * @param idFecha       the id fecha
     * @param idEmpresa     the id empresa
     * @param nombreEmpresa the nombre empresa
     * @param idCalanedario the id calanedario
     * @param fecha         the fecha
     * @param hora          the hora
     * @param antesDias     the antes dias
     * @param antesHora     the antes hora
     * @param antesFecha    the antes fecha
     * @param nombre        the nombre
     * @param nombreCorto   the nombre corto
     * @param periodo       the periodo
     * @param cumplido      the cumplido
     * @param fechaCumplido the fecha cumplido
     * @param calendar      the calendar
     * @param ACCION        the accion
     */
    public Notificacion(String id, String idFecha, String idEmpresa, String nombreEmpresa, String idCalanedario, String fecha, String hora, String antesDias, String antesHora, String antesFecha, String nombre, String nombreCorto, String periodo, String cumplido, String fechaCumplido,Calendar calendar,String ACCION) {
        this.id = id;
        this.idFecha = idFecha;
        this.idEmpresa = idEmpresa;
        this.nombreEmpresa = nombreEmpresa;
        this.idCalanedario = idCalanedario;
        this.fecha = fecha;
        this.hora = hora;
        this.antesDias = antesDias;
        this.antesHora = antesHora;
        this.antesFecha = antesFecha;
        this.nombre = nombre;
        this.nombreCorto = nombreCorto;
        this.periodo = periodo;
        this.cumplido = cumplido;
        this.fechaCumplido = fechaCumplido;
        this.calendar = calendar;
        this.ACCION = ACCION;

    }
}
