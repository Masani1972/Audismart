package com.aosas.audismart.model;

import java.io.Serializable;


/**
 * The type Noticia.
 * Modela las noticias recibidas del servicio web
 * es serializable para enviar entre actividades
 */
public class Noticia implements Serializable {
    /**
     * The Id noticia.
     */
    public String id_noticia ="";
    /**
     * The Titulo.
     */
    public String titulo = "";
    /**
     * The Contenido.
     */
    public String contenido ="";
    /**
     * The Archivo.
     */
    public String archivo = "";
    /**
     * The Rutaarchivo.
     */
    public String rutaarchivo ="";
    /**
     * The Fecha.
     */
    public String fecha = "";
    /**
     * The Hora.
     */
    public String hora ="";
    /**
     * The Empresa.
     */
    public String empresa = "";
    /**
     * The Id empresa.
     */
    public String id_empresa = "";
    /**
     * The Accion.
     */
    public String ACCION = "";

    /**
     * Instantiates a new Noticia.
     *
     * @param id_noticia  the id noticia
     * @param titulo      the titulo
     * @param contenido   the contenido
     * @param archivo     the archivo
     * @param rutaarchivo the rutaarchivo
     * @param fecha       the fecha
     * @param hora        the hora
     * @param empresa     the empresa
     * @param id_empresa  the id empresa
     * @param ACCION      the accion
     */
    public Noticia (String id_noticia, String titulo,String contenido, String archivo, String rutaarchivo, String fecha, String hora,String empresa, String id_empresa,String ACCION){
        this.id_noticia =id_noticia;
        this.titulo = titulo;
        this.contenido =contenido;
        this.archivo = archivo;
        this.rutaarchivo =rutaarchivo;
        this.fecha = fecha;
        this.hora =hora;
        this.empresa = empresa;
        this.id_empresa = id_empresa;
        this.ACCION = ACCION;
    }
}
