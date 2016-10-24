package com.aosas.audismart.activitys;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.aosas.audismart.R;
import com.aosas.audismart.comunication.proxy.GetImages;
import com.aosas.audismart.model.Noticia;
import com.aosas.audismart.util.Constantes;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * The type Noticias detalle activity.
 * Muestra en detalle la información de una noticia
 * junto con la imagen de la noticia
 */
public class NoticiasDetalleActivity extends AppCompatActivity {
    private Noticia noticia;

    /**
     * The Text view empresa.
     */
    @InjectView(R.id.textView_Empresa)
    TextView textView_Empresa;

    /**
     * The Text view titulo.
     */
    @InjectView(R.id.textViewTitulo)
    TextView textViewTitulo;

    /**
     * The Image noticias.
     */
    @InjectView(R.id.imageNoticias)
    ImageView imageNoticias;


    /**
     * On create.
     *
     * @param savedInstanceState the saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticias_detalle);
        ButterKnife.inject(this);

        //Se adiciona el icono a la barra de la actividad
        ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.drawable.logoapp);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        noticia = (Noticia) getIntent().getSerializableExtra(Constantes.EXTRA_NOTICIA);
        cargarDatos();
    }

    private void cargarDatos() {
        textView_Empresa.setText(noticia.empresa);
        textViewTitulo.setText(noticia.titulo);
        if (noticia.archivo.length() > 0)
            new GetImages(imageNoticias, this).execute(Constantes.URLIMAGENES + noticia.rutaarchivo);
    }

}
