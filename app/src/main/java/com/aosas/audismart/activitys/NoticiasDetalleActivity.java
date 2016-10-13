package com.aosas.audismart.activitys;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.aosas.audismart.R;
import com.aosas.audismart.comunication.proxy.GetImages;
import com.aosas.audismart.model.Noticia;
import com.aosas.audismart.util.Constantes;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class NoticiasDetalleActivity extends AppCompatActivity {
    private Noticia noticia;

    @InjectView(R.id.textView_Empresa)
    TextView textView_Empresa;

    @InjectView(R.id.textViewTitulo)
    TextView textViewTitulo;

    @InjectView(R.id.imageNoticias)
    ImageView imageNoticias;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticias_detalle);
        ButterKnife.inject(this);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.drawable.logoapp);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        noticia = (Noticia) getIntent().getSerializableExtra("noticia");
        cargarDatos();
    }

    private void cargarDatos() {
        textView_Empresa.setText(noticia.empresa);
        textViewTitulo.setText(noticia.titulo);

        if(noticia.archivo.length()>0)
            new GetImages(imageNoticias,this).execute(Constantes.URLIMAGENES+noticia.rutaarchivo);
    }

}
