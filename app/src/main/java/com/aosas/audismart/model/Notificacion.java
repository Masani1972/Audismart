package com.aosas.audismart.model;

/**
 * Created by Lmartinez on 29/03/2016.
 */
import java.io.Serializable;

@SuppressWarnings("serial")
public class Notificacion implements Serializable {
    public String id,idFecha,idEmpresa,nombreEmpresa,idCalanedario,fecha,hora,antesDias,antesHora,antesFecha,nombre,nombreCorto,periodo,cumplido,fechaCumplido,ACCION;

    public Notificacion(String id, String idFecha, String idEmpresa, String nombreEmpresa, String idCalanedario, String fecha, String hora, String antesDias, String antesHora, String antesFecha, String nombre, String nombreCorto, String periodo, String cumplido, String fechaCumplido,String ACCION) {
        this.id = id;
        this.idFecha = idFecha;
        this.idEmpresa = idEmpresa;
        this.nombreEmpresa = nombreEmpresa;
        this.idCalanedario = idCalanedario;
        this.fecha = fecha;
        this.hora = hora;
        this.antesDias = antesDias;
        this.antesHora = antesHora;
        this.antesFecha = antesFecha;
        this.nombre = nombre;
        this.nombreCorto = nombreCorto;
        this.periodo = periodo;
        this.cumplido = cumplido;
        this.fechaCumplido = fechaCumplido;
        this.ACCION = ACCION;
    }
}
