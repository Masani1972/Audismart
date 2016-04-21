package com.aosas.audismart.model;

/**
 * Created by Lmartinez on 21/04/2016.
 */
public class NotificacionCumplio {
   public String id;
    public String cumplido;
    public String ACCION;

    public NotificacionCumplio(String id,String cumplido,String ACCION){
        this.id = id;
        this.cumplido = cumplido;
        this.ACCION = ACCION;
    }
}
