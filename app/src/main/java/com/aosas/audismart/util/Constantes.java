package com.aosas.audismart.util;

/**
 * Created by Lmartinez on 21/01/2016.
 */
public  class Constantes {

    //URL WebServices
    public static final  String URLSERVICES="https://aosmart.aosas.com/movil/";

    /*Metodos Servicios Web
    */
    public static final String REGISTRO_USUARIO = "REGISTRO CLIENTE";
    public static final String LOGIN = "LOGIN";
    public static final String REGISTRO_EMPRESA="REGISTRO EMPRESA";
    public static final String REGISTRO_DISPOSITIVO="REGISTRO DISPOSITIVO";
    public static final String REGISTRO_DISPOSITIVO_REGISTRO="REGISTRO DISPOSITIVO REGISTRO";
    public static final String CONSULTA_FECHASCLIENTE="FECHAS CLIENTE";
    public static final String ACTUALIZA_NOTIFICACION="ACTUALIZA NOTIFICACION";
    public static final String EMPRESAS_RELACIONADA="EMPRESAS RELACIONADAS";
    public static final String CALENDARIOS="CALENDARIOS CLIENTE";
    public static final String NOTIFICACIONES_CUMPLIO="ACTUALIZA CUMPLIDO";
    public static final String BUSCAR_CLIENTE_UNICO="BUSCAR CLIENTE UNICO";
    public static final String ACTUALIZA_EMPRESA="ACTUALIZA EMPRESA";
    public static final String ACTUALIZA_CLIENTE="ACTUALIZA CLIENTE";


    /*
    Preferencias
     */
    public static final String SESION="sesion";
    public static final String IDCLIENT="idClient";
    public static final String IDCOMPANY="idCompany";
    public static final String NOTIFICACIONES="Notificaciones";
    public static final String CALENDARIOS_PREFERENCES="Calendarios";
    public static final String EMPRESAS="Empresas";
    public static final String USUSARIO="Usuario";

    /***
     * GCM preferences
     */
    public static final String SENT_TOKEN_TO_SERVER = "sentTokenToServer";
    public static final String REGISTRATION_COMPLETE = "registrationComplete";

    /**
     * Sistema operativo
     */
    public static final String SO = "Android";

   /*
   Formatos fechas
    */
    public static final String FORMATOFECHANOTIDICACIONJSON="yyyy-MM-dd";
    public static final String FORMATOFECHANOTIDICACIONJSONNOTIFICACION="yyyy-MM-dd hh:mm:ss";

    /*
    Activitys result
     */
    public static final int UPDATECOMPANY=1;

    /*
    Response
     */
    public static final String ACTUALIZA_EMPRESA_RESPONSE="Se actualizo con exito";
}
