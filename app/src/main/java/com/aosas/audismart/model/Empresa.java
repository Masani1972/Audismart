package com.aosas.audismart.model;

import java.io.Serializable;


/**
 * The type Empresa.
 * Clase para la creacion de empresa
 * y para las empresas recibidas del servicio web
 */
public class Empresa implements Serializable {

    /**
     * The Id cliente.
     */
    public String id_cliente;
    /**
     * The Nombre.
     */
    public String nombre;
    /**
     * The Id departamento.
     */
    public String id_departamento;
    /**
     * The Departamento.
     */
    public String departamento;
    /**
     * The Id ciudad.
     */
    public String id_ciudad;
    /**
     * The Ciudad.
     */
    public String ciudad;
    /**
     * The Tipo documento.
     */
    public String tipo_documento;
    /**
     * The Documento.
     */
    public String documento;
    /**
     * The Fecharegistromercantil.
     */
    public String fecharegistromercantil;
    /**
     * The Id periodo.
     */
    public String id_periodo;
    /**
     * The Ingresos.
     */
    public String ingresos;
    /**
     * The Categoria.
     */
    public String categoria;
    /**
     * The Impuesto consumo.
     */
    public String impuesto_consumo;
    /**
     * The Impuesto riqueza.
     */
    public String impuesto_riqueza;
    /**
     * The Id empresa.
     */
    public String id_empresa;
    /**
     * The Accion.
     */
    public String ACCION;

    /**
     * Instantiates a new Empresa.
     *
     * @param id_cliente             the id cliente
     * @param nombre                 the nombre
     * @param id_departamento        the id departamento
     * @param departamento           the departamento
     * @param id_ciudad              the id ciudad
     * @param ciudad                 the ciudad
     * @param tipo_documento         the tipo documento
     * @param documento              the documento
     * @param ingresos               the ingresos
     * @param categoria              the categoria
     * @param impuesto_consumo       the impuesto consumo
     * @param fecharegistromercantil the fecharegistromercantil
     * @param id_periodo             the id periodo
     * @param impuesto_riqueza       the impuesto riqueza
     * @param ACCION                 the accion
     * @param id_empresa             the id empresa
     */
    public Empresa(String id_cliente,
                   String nombre,
                   String id_departamento,
                   String departamento,
                   String id_ciudad,
                   String ciudad,
                   String tipo_documento,
                   String documento,
                   String ingresos,
                   String categoria,
                   String impuesto_consumo,
                   String fecharegistromercantil,
                   String id_periodo,
                   String impuesto_riqueza, String ACCION, String id_empresa) {
        this.id_cliente = id_cliente;
        this.nombre = nombre;
        this.id_departamento = id_departamento;
        this.departamento = departamento;
        this.ciudad = ciudad;
        this.id_ciudad = id_ciudad;
        this.tipo_documento = tipo_documento;
        this.documento = documento;
        this.ingresos = ingresos;
        this.categoria = categoria;
        this.fecharegistromercantil = fecharegistromercantil;
        this.id_periodo = id_periodo;
        this.impuesto_consumo = impuesto_consumo;
        this.impuesto_riqueza = impuesto_riqueza;
        this.ACCION = ACCION;
        this.id_empresa = id_empresa;
    }
}
