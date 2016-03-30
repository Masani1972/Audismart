package com.aosas.audismart.util;

/**
 * Created by Lmartinez on 21/01/2016.
 */
public  class Constantes {

    //URL WebServices
    public static final  String URLSERVICES="http://aosmart.aosas.com/movil/";

    /*Metodos Servicios Web
    */
    public static final String REGISTRO_USUARIO = "REGISTRO CLIENTE";
    public static final String LOGIN = "LOGIN";
    public static final String REGISTRO_EMPRESA="REGISTRO EMPRESA";
    public static final String REGISTRO_DISPOSITIVO="REGISTRO DISPOSITIVO";
    public static final String CONSULTA_FECHASCLIENTE="FECHAS CLIENTE";


    /*
    Preferencias
     */
    public static final String SESION="sesion";
    public static final String IDCLIENT="idClient";
    public static final String IDCOMPANY="idCompany";

    /***
     * GCM preferences
     */
    public static final String SENT_TOKEN_TO_SERVER = "sentTokenToServer";
    public static final String REGISTRATION_COMPLETE = "registrationComplete";

    /**
     * Sistema operativo
     */
    public static final String SO = "Android";

    static String[] state = {"A","B","C"};
    static  String[][] parent = {
            {"aa","bb","cc","dd","ee"},
            {"ff","gg","hh","ii","jj"},
            {"kk","ll","mm","nn","oo"}
    };

    static  String[][][] child = {
            {
                    {"aaa","aab","aac","aad","aae"},
                    {"bba","bbb","bbc","bbd","bbe"},
                    {"cca","ccb","ccc","ccd","cce","ccf","ccg"},
                    {"dda","ddb","dddc","ddd","dde","ddf"},
                    {"eea","eeb","eec"}
            },
            {
                    {"ffa","ffb","ffc","ffd","ffe"},
                    {"gga","ggb","ggc","ggd","gge"},
                    {"hha","hhb","hhc","hhd","hhe","hhf","hhg"},
                    {"iia","iib","iic","iid","iie","ii"},
                    {"jja","jjb","jjc","jjd"}
            },
            {
                    {"kka","kkb","kkc","kkd","kke"},
                    {"lla","llb","llc","lld","lle"},
                    {"mma","mmb","mmc","mmd","mme","mmf","mmg"},
                    {"nna","nnb","nnc","nnd","nne","nnf"},
                    {"ooa","oob"}
            }
    };


}
