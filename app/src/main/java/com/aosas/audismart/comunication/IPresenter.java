package com.aosas.audismart.comunication;

import android.content.Context;

/**
 * Created by Lmartinez on 08/01/2016.
 */
public interface IPresenter {

    public void createRequets (Context context,Object object, String metodo);
    public void createResponse (String reponse);
}
