package com.aosas.audismart.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aosas.audismart.R;
import com.aosas.audismart.activitys.NoticiasActivity;
import com.aosas.audismart.activitys.NoticiasDetalleActivity;
import com.aosas.audismart.activitys.NotificacionesActivity;
import com.aosas.audismart.model.Noticia;
import com.aosas.audismart.model.Notificacion;
import com.aosas.audismart.repository.Preferences;

import java.util.ArrayList;

/**
 * Created by dayanamartinez on 19-09-16.
 */
public class NoticiasListAdapter extends ArrayAdapter {
    private final Context context;
    ArrayList<Noticia> noticias;
    private TextView fechaDia, fechaMes, fechaYear, descripcion, nombreEmpresa, lblListId;


    public NoticiasListAdapter(Context context, ArrayList<Noticia> noticias) {
        super(context, R.layout.list_item_noticias, noticias);
        this.context = context;
        this.noticias = noticias;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(R.layout.list_item_noticias, null);

        Noticia noticia = noticias.get(position);
        fechaDia = (TextView) convertView.findViewById(R.id.lblListItemFechaDia);
        fechaMes = (TextView) convertView.findViewById(R.id.lblListItemFechaMes);
        fechaYear = (TextView) convertView.findViewById(R.id.lblListItemFechaYear);
        descripcion = (TextView) convertView.findViewById(R.id.lblListItemDescripcion);
        nombreEmpresa = (TextView) convertView.findViewById(R.id.lblListItemEmpresa);
        lblListId = (TextView) convertView.findViewById(R.id.lblListId);
        LinearLayout layout_noticias = (LinearLayout) convertView.findViewById(R.id.layout_noticias);

        fechaDia.setText(noticia.fecha.substring(8, 10));
        fechaMes.setText(noticia.fecha.substring(5, 7));
        fechaYear.setText(noticia.fecha.substring(0, 4));
        descripcion.setText(noticia.titulo);
        nombreEmpresa.setText(noticia.empresa);
        lblListId.setText(noticia.id_noticia);

        layout_noticias.setOnClickListener(new View.OnClickListener() {

         public void onClick(View v) {
                   TextView txtView = (TextView) v.findViewById(R.id.lblListId);
                 ArrayList<Noticia> noticias = Preferences.getNoticias(context);
                 for(int index =0;index<noticias.size();index++) {

                     if (noticias.get(index).id_noticia.equals(txtView.getText().toString())) {
                         Intent intentNoticias = new Intent(context, NoticiasDetalleActivity.class);
                         intentNoticias.putExtra("noticia", noticias.get(index));
                         context.startActivity(intentNoticias);
                     }
                 }
         }
           }
        );

        return convertView;
    }
}

