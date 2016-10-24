package com.aosas.audismart.activitys;


import com.google.gson.JsonElement;

/**
 * Created by Lmartinez on 08/01/2016.
 */
public interface BaseActivity  {
    /**
     * Succes.
     *
     * @param succes      the succes
     * @param jsonElement the json element
     */
    public void succes(String succes,JsonElement jsonElement);

    /**
     * Error.
     *
     * @param error the error
     */
    public void error(String error);

}
