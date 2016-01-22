package com.aosas.audismart.comunication;

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
    Call<ResponseBody> createCompany(@Field("id_cliente") String id_cliente, @Field("nombre") String nombre, @Field("id_departamento") String id_departamento, @Field("id_ciudad") String id_ciudad,@Field("tipo_documento") String tipo_documento, @Field("documento") String documento, @Field("ingresos") String ingresos , @Field("categoria") String categoria, @Field("impuesto_consumo") String impuesto_consumo,@Field("impuesto_riqueza") String impuesto_riqueza,@Field("ACCION") String ACCION);
}
