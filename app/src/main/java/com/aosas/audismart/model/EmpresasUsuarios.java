package com.aosas.audismart.model;

/**
 * Created by Lmartinez on 17/04/2016.
 */
public class EmpresasUsuarios {
    public String id_cliente;
    public String id_empresa;
    public String ACCION;

    public EmpresasUsuarios(String id_cliente,
            String id_empresa, String ACCION){
        this.id_cliente = id_cliente;
        this.id_empresa = id_empresa;
        this.ACCION = ACCION;
    }
}
