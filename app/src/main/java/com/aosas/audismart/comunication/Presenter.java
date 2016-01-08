package com.aosas.audismart.comunication;

import android.content.Context;

import org.json.JSONArray;

import java.util.ArrayList;
import com.google.gson.*;

/**
 * Created by Lmartinez on 08/01/2016.
 */
public class Presenter implements IPresenter{



    @Override
    public void createRequets(Context context,Object object, String metodo) {
        new ServiceTaskAsyn(context,metodo,object).execute();
    }

    @Override
    public void createResponse(String response) {


        JsonObject jsonObject = new JsonParser().parse(response).getAsJsonObject();
        JsonElement error = jsonObject.get("error");
        String reult =error.getAsString();


    }
}
