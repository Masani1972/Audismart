package com.aosas.audismart.comunication;

import android.app.DownloadManager;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.SimpleXmlConverterFactory;


/**
 * Created by Lmartinez on 06/01/2016.
 */
public class ServiceGenerator {

    public static final String API_BASE_URL = "http://166.62.118.200/~aosas/aosmart/movil/";
    //public static final String API_BASE_URL = "http://www.mocky.io/v2/";

  /* private OkHttpClient client = new OkHttpClient();

    MediaType mediaType = MediaType.parse("multipart/form-data; boundary=---011000010111000001101001");
    RequestBody body = RequestBody.create(mediaType, "-----011000010111000001101001\r\nContent-Disposition: form-data; name=\"nombres\"\r\n\r\nffddf\r\n-----011000010111000001101001\r\nContent-Disposition: form-data; name=\"apellidos\"\r\n\r\nfdfdf\r\n-----011000010111000001101001\r\nContent-Disposition: form-data; name=\"ACCION\"\r\n\r\nREGISTRO CLIENTE\r\n-----011000010111000001101001\r\nContent-Disposition: form-data; name=\"email\"\r\n\r\ni@h.com\r\n-----011000010111000001101001\r\nContent-Disposition: form-data; name=\"id_departamento\"\r\n\r\n1\r\n-----011000010111000001101001\r\nContent-Disposition: form-data; name=\"id_ciudad\"\r\n\r\n1\r\n-----011000010111000001101001\r\nContent-Disposition: form-data; name=\"telefono\"\r\n\r\n24455\r\n-----011000010111000001101001\r\nContent-Disposition: form-data; name=\"contrasena\"\r\n\r\n2ffdfd\r\n-----011000010111000001101001\r\nContent-Disposition: form-data; name=\"acepto_terminos\"\r\n\r\n1\r\n-----011000010111000001101001\r\nContent-Disposition: form-data; name=\"acepto_envio\"\r\n\r\n1\r\n-----011000010111000001101001--");
    Request request = new Request.Builder()
            .url("http://166.62.118.200/~aosas/aosmart/movil//WS.php")
            .post(body)
            .addHeader("content-type", "multipart/form-data; boundary=---011000010111000001101001")
            .addHeader("cache-control", "no-cache")
            .addHeader("postman-token", "f9782eb9-ee20-1e9a-83af-779d77b0ae88")
            .build();
*/



   private static OkHttpClient httpClient = new OkHttpClient();


    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    ;

    public static <S> S createService(Class<S> serviceClass) {
        Retrofit retrofit = builder.client(httpClient).build();
        return retrofit.create(serviceClass);
    }


}
