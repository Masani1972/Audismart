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

    public static final String API_BASE_URL = "http://aosmart.aosas.com/movil/";
    //public static final String API_BASE_URL = "http://www.mocky.io/v2/";


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
