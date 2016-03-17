package com.aosas.audismart.model;

/**
 * Created by Lmartinez on 16/03/2016.
 */
public class GCM {

    public String id_cliente;
    public String so;
    public String dispositivo;
    public String identificador;
    public String ACCION;


    public  GCM(String id_cliente,String so,String dispositivo, String identificador,String ACCION){
        this.id_cliente = id_cliente;
        this.so = so;
        this.dispositivo = dispositivo;
        this.identificador = identificador;
        this.ACCION = ACCION;
    }
}
