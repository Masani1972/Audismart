package com.aosas.audismart.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by lmartinez on 28/01/2016.
 */
public class AlertDialogCustom {
    private Context context;

    public AlertDialogCustom(AppCompatActivity activity) {
        context = activity.getBaseContext();
    }

    public void initAlert(String title,String message) {

    // Creating alert Dialog with one Button
    AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);

        //EditText email
        final EditText input = new EditText(context);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        alertDialog.setView(input);

        //Boton Aceptar
        alertDialog.setPositiveButton("YES",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int which) {
                        // Write your code here to execute after dialog
                        Toast.makeText(context, "Password Matched", Toast.LENGTH_SHORT).show();

                    }
                });

        alertDialog.show();
}
}
