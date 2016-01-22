package com.aosas.audismart.model;

/**
 * Created by Lmartinez on 21/01/2016.
 */
public class Empresa {

    private String id_cliente;
    private String nombre;
    private String id_departamento;
    private String id_ciudad;
    private String tipo_documento;
    private String documento;
    private String ingresos;
    private String categoria;
    private String impuesto_consumo;
    private String impuesto_riqueza;
    private String ACCION;

    public Empresa (String id_cliente,
           String nombre,
             String id_departamento,
             String id_ciudad,
            String tipo_documento,
             String documento,
             String ingresos,
             String categoria,
             String impuesto_consumo,
            String impuesto_riqueza,String ACCION){
        this.id_cliente= id_cliente;
        this.nombre= nombre;
        this.id_departamento=id_departamento;
                this.id_ciudad=id_ciudad;
                this.tipo_documento=tipo_documento;
                this.documento=documento;
                this.ingresos=ingresos;
                this.categoria=categoria;
                this.impuesto_consumo=impuesto_consumo;
                this.impuesto_riqueza=impuesto_riqueza;
        this.ACCION= ACCION;
    }
}
