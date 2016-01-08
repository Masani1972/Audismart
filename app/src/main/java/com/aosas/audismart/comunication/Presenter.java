package com.aosas.audismart.comunication;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Lmartinez on 08/01/2016.
 */
public class Presenter implements IPresenter{



    @Override
    public void createRequets(Context context,Object object, String metodo,int fields) {


        new ServiceTaskAsyn(context,metodo,object).execute();
    }




    @Override
    public void createResponse() {

    }
}
