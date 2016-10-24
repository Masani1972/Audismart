package com.aosas.audismart.comunication.proxy;

import com.aosas.audismart.util.Constantes;
import com.squareup.okhttp.OkHttpClient;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;


/**
 * The type Service generator.
 * Conexion para servics web
 * Solo aplica con retrofit
 */
public class ServiceGenerator {


    private static OkHttpClient httpClient = new OkHttpClient();

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(Constantes.URLSERVICES)
                    .addConverterFactory(GsonConverterFactory.create());

    /**
     * Create service s.
     *
     * @param <S>          the type parameter
     * @param serviceClass the service class
     * @return the s
     */
    public static <S> S createService(Class<S> serviceClass) {
        Retrofit retrofit = builder.client(httpClient).build();
        return retrofit.create(serviceClass);
    }
}
