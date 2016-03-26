package com.aosas.audismart.model;

/**
 * Created by Lmartinez on 25/03/2016.
 */
public class FechaCliente {
    public String idCliente;
    public String idCalendario;
    public String idEmpresa;
    public String ACCION;

    public FechaCliente(String idCliente, String idCalendario, String idEmpresa, String ACCION) {
        this.idCliente = idCliente;
        this.idCalendario = idCalendario;
        this.idEmpresa = idEmpresa;
        this.ACCION = ACCION;
    }
}
