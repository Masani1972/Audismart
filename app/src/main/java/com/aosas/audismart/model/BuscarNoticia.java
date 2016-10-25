package com.aosas.audismart.model;


/**
 * The type Buscar noticia.
 * Clase creada para el requets del servicio de busqueda de noticia
 */
public class BuscarNoticia {
    /**
     * The Id noticia.
     */
    public String id_noticia = "";
    /**
     * The Id cliente.
     */
    public String id_cliente = "";
    /**
     * The Accion.
     */
    public String ACCION = "";

    /**
     * Instantiates a new Buscar noticia.
     *
     * @param id_noticia the id noticia
     * @param id_cliente the id cliente
     * @param ACCION     the accion
     */
    public BuscarNoticia(String id_noticia, String id_cliente, String ACCION) {
        this.id_noticia = id_noticia;
        this.id_cliente = id_cliente;
        this.ACCION = ACCION;
    }
}
