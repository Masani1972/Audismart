package com.aosas.audismart.model;

/**
 * Created by Lmartinez on 21/01/2016.
 */
public class Empresa {

    public String id_cliente;
    public String nombre;
    public String id_departamento;
    public String id_ciudad;
    public String tipo_documento;
    public String documento;
    public String fecharegistromercantil;
    public String id_periodo;
    public String ingresos;
    public String categoria;
    public String impuesto_consumo;
    public String impuesto_riqueza;
    public String id_empresa;
    public String ACCION;

    public Empresa (String id_cliente,
           String nombre,
             String id_departamento,
             String id_ciudad,
            String tipo_documento,
             String documento,
             String ingresos,
             String categoria,
                    String impuesto_consumo,
                    String fecharegistromercantil,
                    String id_periodo,
            String impuesto_riqueza,String ACCION, String id_empresa){
        this.id_cliente= id_cliente;
        this.nombre= nombre;
        this.id_departamento=id_departamento;
                this.id_ciudad=id_ciudad;
                this.tipo_documento=tipo_documento;
                this.documento=documento;
                this.ingresos=ingresos;
                this.categoria=categoria;
                this.fecharegistromercantil=fecharegistromercantil;
                this.id_periodo=id_periodo;
                this.impuesto_consumo=impuesto_consumo;
                this.impuesto_riqueza=impuesto_riqueza;
        this.ACCION= ACCION;
        this.id_empresa = id_empresa;
    }
}
