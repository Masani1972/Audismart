package com.aosas.audismart.activitys;


import com.google.gson.JsonElement;

/**
 * Created by Lmartinez on 08/01/2016.
 */
public interface BaseActivity {
    public void succes(String succes,JsonElement jsonElement);
    public void error(String error);

}
