package com.aosas.audismart.comunication.proxy;

import android.app.Activity;
import android.content.Context;


/**
 * The interface Repository.
 */
public interface IRepository {

    /**
     * Create requets.
     *
     * @param context the context
     * @param object  the object
     * @param metodo  the metodo
     */
    public void createRequets(Context context, Object object, String metodo);

    /**
     * Create response.
     *
     * @param reponse  the reponse
     * @param metodo   the metodo
     * @param activity the activity
     */
    public void createResponse(String reponse, String metodo, Activity activity);
}
