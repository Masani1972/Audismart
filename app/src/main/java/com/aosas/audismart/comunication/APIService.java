package com.aosas.audismart.comunication;

import com.aosas.audismart.Object.User;
import com.squareup.okhttp.ResponseBody;

import retrofit.Call;
import retrofit.http.Body;
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
    //Call<ResponseBody> createUser(@Body User user);
    Call<ResponseBody> createUser(@Field("nombres") String nombres, @Field("apellidos") String apellidos, @Field("ACCION") String ACCION, @Field("email") String email,@Field("id_departamento") String id_departamento, @Field("id_ciudad") String id_ciudad, @Field("telefono") String telefono , @Field("contrasena") String contrasena, @Field("acepto_terminos") String acepto_terminos,@Field("acepto_envio") String acepto_envio);


}
