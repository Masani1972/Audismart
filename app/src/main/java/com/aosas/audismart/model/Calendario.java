package com.aosas.audismart.model;

/**
 * Created by lmartinez on 20/04/2016.
 */
public class Calendario {
    public String id_calendario;
    public String nombre;
    public String nombrecorto;
    public String ano;

    public Calendario(String id_calendario,String nombre, String nombrecorto, String ano){
        this.id_calendario = id_calendario;
        this.nombre = nombre;
        this.nombrecorto = nombrecorto;
        this.ano = ano;
    }
}
