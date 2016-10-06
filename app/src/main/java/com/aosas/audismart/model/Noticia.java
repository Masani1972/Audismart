package com.aosas.audismart.model;

import java.io.Serializable;

/**
 * Created by Lmartinez on 19/09/2016.
 */
public class Noticia implements Serializable {
    public String id_noticia ="";
    public String titulo = "";
    public String contenido ="";
    public String archivo = "";
    public String rutaarchivo ="";
    public String fecha = "";
    public String hora ="";
    public String empresa = "";
    public String id_empresa = "";
    public String ACCION = "";

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
