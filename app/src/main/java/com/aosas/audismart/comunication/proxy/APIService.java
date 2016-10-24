package com.aosas.audismart.comunication.proxy;

import com.aosas.audismart.util.Constantes;
import com.squareup.okhttp.ResponseBody;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;


/**
 * The interface Api service.
 * Contiene todo el api de los servicios web utilizados en la app
 * para cualquier servicio web nuevo, es necesario el registro en esta interface
 * Utilizado para la libreria retrofit
 */
public interface APIService {

    /**
     * Create user call.
     *
     * @param nombres         the nombres
     * @param apellidos       the apellidos
     * @param ACCION          the accion
     * @param email           the email
     * @param id_departamento the id departamento
     * @param id_ciudad       the id ciudad
     * @param telefono        the telefono
     * @param contrasena      the contrasena
     * @param acepto_terminos the acepto terminos
     * @param acepto_envio    the acepto envio
     * @return the call
     */
    @FormUrlEncoded
    @POST(Constantes.SERVICES)
    Call<ResponseBody> createUser(@Field("nombres") String nombres, @Field("apellidos") String apellidos, @Field("ACCION") String ACCION, @Field("email") String email, @Field("id_departamento") String id_departamento, @Field("id_ciudad") String id_ciudad, @Field("telefono") String telefono, @Field("contrasena") String contrasena, @Field("acepto_terminos") String acepto_terminos, @Field("acepto_envio") String acepto_envio);

    /**
     * Login user call.
     *
     * @param email      the email
     * @param contrasena the contrasena
     * @param ACCION     the accion
     * @return the call
     */
    @FormUrlEncoded
    @POST(Constantes.SERVICES)
    Call<ResponseBody> loginUser(@Field("email") String email, @Field("contrasena") String contrasena, @Field("ACCION") String ACCION);

    /**
     * Create company call.
     *
     * @param id_cliente             the id cliente
     * @param nombre                 the nombre
     * @param id_departamento        the id departamento
     * @param id_ciudad              the id ciudad
     * @param tipo_documento         the tipo documento
     * @param documento              the documento
     * @param fecharegistromercantil the fecharegistromercantil
     * @param ingresos               the ingresos
     * @param id_periodo             the id periodo
     * @param categoria              the categoria
     * @param impuesto_consumo       the impuesto consumo
     * @param impuesto_riqueza       the impuesto riqueza
     * @param ACCION                 the accion
     * @return the call
     */
    @FormUrlEncoded
    @POST(Constantes.SERVICES)
    Call<ResponseBody> createCompany(@Field("id_cliente") String id_cliente, @Field("nombre") String nombre, @Field("id_departamento") String id_departamento, @Field("id_ciudad") String id_ciudad, @Field("tipo_documento") String tipo_documento, @Field("documento") String documento, @Field("fecharegistromercantil") String fecharegistromercantil, @Field("ingresos") String ingresos, @Field("id_periodo") String id_periodo, @Field("categoria") String categoria, @Field("impuesto_consumo") String impuesto_consumo, @Field("impuesto_riqueza") String impuesto_riqueza, @Field("ACCION") String ACCION);

    /**
     * Forgot password call.
     *
     * @param email  the email
     * @param ACCION the accion
     * @return the call
     */
    @FormUrlEncoded
    @POST(Constantes.SERVICES)
    Call<ResponseBody> forgotPassword(@Field("email") String email, @Field("ACCION") String ACCION);

    /**
     * Register device call.
     *
     * @param id_cliente    the id cliente
     * @param so            the so
     * @param dispositivo   the dispositivo
     * @param identificador the identificador
     * @param ACCION        the accion
     * @return the call
     */
    @FormUrlEncoded
    @POST(Constantes.SERVICES)
    Call<ResponseBody> registerDevice(@Field("id_cliente") String id_cliente, @Field("so") String so, @Field("dispositivo") String dispositivo, @Field("identificador") String identificador, @Field("ACCION") String ACCION);

    /**
     * Consult date client call.
     *
     * @param id_cliente    the id cliente
     * @param id_calendario the id calendario
     * @param id_empresa    the id empresa
     * @param ACCION        the accion
     * @return the call
     */
    @FormUrlEncoded
    @POST(Constantes.SERVICES)
    Call<ResponseBody> consultDateClient(@Field("id_cliente") String id_cliente, @Field("id_calendario") String id_calendario, @Field("id_empresa") String id_empresa, @Field("ACCION") String ACCION);

    /**
     * Actualizar notificacion call.
     *
     * @param id_empresa    the id empresa
     * @param id_calendario the id calendario
     * @param hora          the hora
     * @param antesdias     the antesdias
     * @param anteshora     the anteshora
     * @param ACCION        the accion
     * @return the call
     */
    @FormUrlEncoded
    @POST(Constantes.SERVICES)
    Call<ResponseBody> actualizarNotificacion(@Field("id_empresa") String id_empresa, @Field("id_calendario") String id_calendario, @Field("hora") String hora, @Field("antesdias") String antesdias, @Field("anteshora") String anteshora, @Field("ACCION") String ACCION);

    /**
     * Empresas relacionadas call.
     *
     * @param id_empresa the id empresa
     * @param id_cliente the id cliente
     * @param ACCION     the accion
     * @return the call
     */
    @FormUrlEncoded
    @POST(Constantes.SERVICES)
    Call<ResponseBody> empresasRelacionadas(@Field("id_empresa") String id_empresa, @Field("id_cliente") String id_cliente, @Field("ACCION") String ACCION);

    /**
     * Calendarios call.
     *
     * @param id_cliente the id cliente
     * @param ano        the ano
     * @param ACCION     the accion
     * @return the call
     */
    @FormUrlEncoded
    @POST(Constantes.SERVICES)
    Call<ResponseBody> calendarios(@Field("id_cliente") String id_cliente, @Field("ano") String ano, @Field("ACCION") String ACCION);

    /**
     * Notificacion cumplio call.
     *
     * @param id       the id
     * @param cumplido the cumplido
     * @param ACCION   the accion
     * @return the call
     */
    @FormUrlEncoded
    @POST(Constantes.SERVICES)
    Call<ResponseBody> notificacionCumplio(@Field("id") String id, @Field("cumplido") String cumplido, @Field("ACCION") String ACCION);

    /**
     * Cliente unico call.
     *
     * @param id_cliente the id cliente
     * @param ACCION     the accion
     * @return the call
     */
    @FormUrlEncoded
    @POST(Constantes.SERVICES)
    Call<ResponseBody> clienteUnico(@Field("id_cliente") String id_cliente, @Field("ACCION") String ACCION);

    /**
     * Update company call.
     *
     * @param id_cliente             the id cliente
     * @param nombre                 the nombre
     * @param id_departamento        the id departamento
     * @param id_ciudad              the id ciudad
     * @param tipo_documento         the tipo documento
     * @param documento              the documento
     * @param fecharegistromercantil the fecharegistromercantil
     * @param ingresos               the ingresos
     * @param id_periodo             the id periodo
     * @param categoria              the categoria
     * @param impuesto_consumo       the impuesto consumo
     * @param impuesto_riqueza       the impuesto riqueza
     * @param ACCION                 the accion
     * @return the call
     */
    @FormUrlEncoded
    @POST(Constantes.SERVICES)
    Call<ResponseBody> updateCompany(@Field("id_cliente") String id_cliente, @Field("nombre") String nombre, @Field("id_departamento") String id_departamento, @Field("id_ciudad") String id_ciudad, @Field("tipo_documento") String tipo_documento, @Field("documento") String documento, @Field("fecharegistromercantil") String fecharegistromercantil, @Field("ingresos") String ingresos, @Field("id_periodo") String id_periodo, @Field("categoria") String categoria, @Field("impuesto_consumo") String impuesto_consumo, @Field("impuesto_riqueza") String impuesto_riqueza, @Field("ACCION") String ACCION);

    /**
     * Update client call.
     *
     * @param nombres         the nombres
     * @param apellidos       the apellidos
     * @param email           the email
     * @param id_departamento the id departamento
     * @param id_ciudad       the id ciudad
     * @param telefono        the telefono
     * @param ACCION          the accion
     * @return the call
     */
    @FormUrlEncoded
    @POST(Constantes.SERVICES)
    Call<ResponseBody> updateClient(@Field("nombres") String nombres, @Field("apellidos") String apellidos, @Field("email") String email, @Field("id_departamento") String id_departamento, @Field("id_ciudad") String id_ciudad, @Field("telefono") String telefono, @Field("ACCION") String ACCION);

    /**
     * Delete company call.
     *
     * @param id_empresa the id empresa
     * @param ACCION     the accion
     * @return the call
     */
    @FormUrlEncoded
    @POST(Constantes.SERVICES)
    Call<ResponseBody> deleteCompany(@Field("id_empresa") String id_empresa, @Field("ACCION") String ACCION);

    /**
     * Search ticket call.
     *
     * @param id_ticket  the id ticket
     * @param id_cliente the id cliente
     * @param grupo      the grupo
     * @param ACCION     the accion
     * @return the call
     */
    @FormUrlEncoded
    @POST(Constantes.SERVICES)
    Call<ResponseBody> searchTicket(@Field("id_ticket") String id_ticket, @Field("id_cliente") String id_cliente, @Field("grupo") String grupo, @Field("ACCION") String ACCION);

    /**
     * Search response ticket call.
     *
     * @param id_ticket the id ticket
     * @param ACCION    the accion
     * @return the call
     */
    @FormUrlEncoded
    @POST(Constantes.SERVICES)
    Call<ResponseBody> searchResponseTicket(@Field("id_ticket") String id_ticket, @Field("ACCION") String ACCION);

    /**
     * Close ticket call.
     *
     * @param id_ticket the id ticket
     * @param ACCION    the accion
     * @return the call
     */
    @FormUrlEncoded
    @POST(Constantes.SERVICES)
    Call<ResponseBody> closeTicket(@Field("id_ticket") String id_ticket, @Field("ACCION") String ACCION);

    /**
     * Value ticket call.
     *
     * @param id_ticket    the id ticket
     * @param calificacion the calificacion
     * @param ACCION       the accion
     * @return the call
     */
    @FormUrlEncoded
    @POST(Constantes.SERVICES)
    Call<ResponseBody> valueTicket(@Field("id_ticket") String id_ticket, @Field("calificacion") String calificacion, @Field("ACCION") String ACCION);

    /**
     * Search new call.
     *
     * @param id_noticia the id noticia
     * @param id_cliente the id cliente
     * @param ACCION     the accion
     * @return the call
     */
    @FormUrlEncoded
    @POST(Constantes.SERVICES)
    Call<ResponseBody> searchNew(@Field("id_noticia") String id_noticia, @Field("id_cliente") String id_cliente, @Field("ACCION") String ACCION);


}
