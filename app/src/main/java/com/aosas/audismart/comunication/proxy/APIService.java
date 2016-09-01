package com.aosas.audismart.comunication.proxy;

import com.squareup.okhttp.ResponseBody;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.Headers;
import retrofit.http.POST;


/**
 * Created by Lmartinez on 06/01/2016.
 */
public interface APIService {

    @FormUrlEncoded
    @POST("WS.php")
    Call<ResponseBody> createUser(@Field("nombres") String nombres, @Field("apellidos") String apellidos, @Field("ACCION") String ACCION, @Field("email") String email,@Field("id_departamento") String id_departamento, @Field("id_ciudad") String id_ciudad, @Field("telefono") String telefono , @Field("contrasena") String contrasena, @Field("acepto_terminos") String acepto_terminos,@Field("acepto_envio") String acepto_envio);

    @FormUrlEncoded
    @POST("WS.php")
    Call<ResponseBody> loginUser(@Field("email") String email, @Field("contrasena") String contrasena,@Field("ACCION") String ACCION);

    @FormUrlEncoded
    @POST("WS.php")
    Call<ResponseBody> createCompany(@Field("id_cliente") String id_cliente, @Field("nombre") String nombre, @Field("id_departamento") String id_departamento, @Field("id_ciudad") String id_ciudad,@Field("tipo_documento") String tipo_documento, @Field("documento") String documento,@Field("fecharegistromercantil") String fecharegistromercantil ,@Field("ingresos") String ingresos ,@Field("id_periodo") String id_periodo, @Field("categoria") String categoria, @Field("impuesto_consumo") String impuesto_consumo,@Field("impuesto_riqueza") String impuesto_riqueza,@Field("ACCION") String ACCION);

    @FormUrlEncoded
    @POST("WS.php")
    Call<ResponseBody> forgotPassword(@Field("email") String email, @Field("ACCION") String ACCION);

    @FormUrlEncoded
    @POST("WS.php")
    Call<ResponseBody> registerDevice(@Field("id_cliente") String id_cliente, @Field("so") String so,@Field("dispositivo") String dispositivo,@Field("identificador") String identificador,@Field("ACCION") String ACCION);

    @FormUrlEncoded
    @POST("WS.php")
    Call<ResponseBody> consultDateClient(@Field("id_cliente") String id_cliente, @Field("id_calendario") String id_calendario ,@Field("id_empresa") String id_empresa,@Field("ACCION") String ACCION);

    @FormUrlEncoded
    @POST("WS.php")
    Call<ResponseBody> actualizarNotificacion(@Field("id_empresa") String id_empresa, @Field("id_calendario") String id_calendario ,@Field("hora") String hora ,@Field("antesdias") String antesdias,@Field("anteshora") String anteshora,@Field("ACCION") String ACCION );

    @FormUrlEncoded
    @POST("WS.php")
    Call<ResponseBody> empresasRelacionadas(@Field("id_empresa") String id_empresa, @Field("id_cliente") String id_cliente ,@Field("ACCION") String ACCION );

    @FormUrlEncoded
    @POST("WS.php")
    Call<ResponseBody> calendarios(@Field("id_cliente") String id_cliente, @Field("ano") String ano ,@Field("ACCION") String ACCION );

    @FormUrlEncoded
    @POST("WS.php")
    Call<ResponseBody> notificacionCumplio(@Field("id") String id, @Field("cumplido") String cumplido ,@Field("ACCION") String ACCION );

    @FormUrlEncoded
    @POST("WS.php")
    Call<ResponseBody> clienteUnico(@Field("id_cliente") String id_cliente ,@Field("ACCION")String ACCION );

    @FormUrlEncoded
    @POST("WS.php")
    Call<ResponseBody> updateCompany(@Field("id_cliente") String id_cliente, @Field("nombre") String nombre, @Field("id_departamento") String id_departamento, @Field("id_ciudad") String id_ciudad,@Field("tipo_documento") String tipo_documento, @Field("documento") String documento,@Field("fecharegistromercantil") String fecharegistromercantil ,@Field("ingresos") String ingresos ,@Field("id_periodo") String id_periodo, @Field("categoria") String categoria, @Field("impuesto_consumo") String impuesto_consumo,@Field("impuesto_riqueza") String impuesto_riqueza,@Field("ACCION") String ACCION);

    @FormUrlEncoded
    @POST("WS.php")
    Call<ResponseBody> updateClient(@Field("nombres") String nombres, @Field("apellidos") String apellidos, @Field("email") String email, @Field("id_departamento") String id_departamento,@Field("id_ciudad") String id_ciudad, @Field("telefono") String telefono,@Field("ACCION") String ACCION);

    @FormUrlEncoded
    @POST("WS.php")
    Call<ResponseBody> deleteCompany(@Field("id_empresa") String id_empresa,@Field("ACCION") String ACCION);

    @FormUrlEncoded
    @POST("WS.php")
    Call<ResponseBody> searchTicket(@Field("id_ticket") String id_ticket,@Field("id_cliente") String id_cliente,@Field("grupo") String grupo,@Field("ACCION") String ACCION);

    @FormUrlEncoded
    @POST("WS.php")
    Call<ResponseBody> searchResponseTicket(@Field("id_ticket") String id_ticket,@Field("ACCION") String ACCION);
}
