package com.aosas.audismart.comunication.proxy;

import com.squareup.okhttp.ResponseBody;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by dayanamartinez on 10-09-16.
 */
public interface FileUploadService {

    @Multipart
    @POST("WS.php")
    Call<ResponseBody> respondTicket(@Part("id_ticket") RequestBody id_ticket,
                                     @Part("asunto") RequestBody asunto,
                                     @Part ("archivo")MultipartBody.Part file,
                                     @Part("ACCION") RequestBody ACCION);




}

