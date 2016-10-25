package com.aosas.audismart.model;


/**
 * The type Gcm.
 * Para realizar el registro del dispositivo
 * y recibir notificaciones push
 */
public class GCM {

    /**
     * The Id cliente.
     */
    public String id_cliente;
    /**
     * The So.
     */
    public String so;
    /**
     * The Dispositivo.
     */
    public String dispositivo;
    /**
     * The Identificador.
     */
    public String identificador;
    /**
     * The Accion.
     */
    public String ACCION;


    /**
     * Instantiates a new Gcm.
     *
     * @param id_cliente    the id cliente
     * @param so            the so
     * @param dispositivo   the dispositivo
     * @param identificador the identificador
     * @param ACCION        the accion
     */
    public  GCM(String id_cliente,String so,String dispositivo, String identificador,String ACCION){
        this.id_cliente = id_cliente;
        this.so = so;
        this.dispositivo = dispositivo;
        this.identificador = identificador;
        this.ACCION = ACCION;
    }
}
