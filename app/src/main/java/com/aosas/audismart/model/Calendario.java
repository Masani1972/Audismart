package com.aosas.audismart.model;


/**
 * The type Calendario.
 * Clase creada para modelar los calendarios recibidos del servicio web
 * corresponde a la lista de impuestos del menú principal
 */
public class Calendario {
    /**
     * The Id calendario.
     */
    public String id_calendario;
    /**
     * The Nombre.
     */
    public String nombre;
    /**
     * The Nombrecorto.
     */
    public String nombrecorto;
    /**
     * The Ano.
     */
    public String ano;

    /**
     * Instantiates a new Calendario.
     *
     * @param id_calendario the id calendario
     * @param nombre        the nombre
     * @param nombrecorto   the nombrecorto
     * @param ano           the ano
     */
    public Calendario(String id_calendario, String nombre, String nombrecorto, String ano) {
        this.id_calendario = id_calendario;
        this.nombre = nombre;
        this.nombrecorto = nombrecorto;
        this.ano = ano;
    }
}
