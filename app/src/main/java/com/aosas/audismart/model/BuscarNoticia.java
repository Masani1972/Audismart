package com.aosas.audismart.model;

/**
 * Created by Lmartinez on 19/09/2016.
 */
public class BuscarNoticia {
    public String id_noticia  ="";
    public String id_cliente ="";
    public String ACCION ="";

    public BuscarNoticia (String id_noticia ,String id_cliente,String ACCION){
        this.id_noticia  = id_noticia ;
        this.id_cliente = id_cliente;
        this.ACCION = ACCION;
    }
}
