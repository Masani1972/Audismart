package com.aosas.audismart.activitys;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.aosas.audismart.R;
import com.aosas.audismart.comunication.proxy.IRepository;
import com.aosas.audismart.comunication.proxy.Repository;
import com.aosas.audismart.model.BuscarTicket;
import com.aosas.audismart.model.Ticket;
import com.aosas.audismart.util.Constantes;
import com.google.gson.JsonElement;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.makeText;

public class CalificarTicketActivity extends AppCompatActivity implements BaseActivity {
    private String calificacion;
    private Ticket ticket;

    private IRepository repository = new Repository();

    @InjectView(R.id.text_empresaCal)
    TextView text_empresaCal;

    @InjectView(R.id.text_areaCal)
    TextView text_areaCal;

    @InjectView(R.id.text_estadoCal)
    TextView text_estadoCal;

    @InjectView(R.id.button_Calificar)
    Button button_Calificar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calificar_ticket);
        ButterKnife.inject(this);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.drawable.logoapp);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        cargarDatos((Ticket) getIntent().getSerializableExtra("ticket"));

        RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {

                calificacion = String.valueOf(rating);

            }
        });
    }

    private void cargarDatos(Ticket ticket) {
        this.ticket = ticket;
        text_empresaCal.setText("Empresa: "+ticket.empresa);
        text_areaCal.setText("Area: "+ticket.empresa);
        text_estadoCal.setText("Estado: "+ticket.estado);
    }

    @OnClick(R.id.button_Calificar)
    public void button_Calificar(View view){ calificar();}


    /*******************
     Presentador¡¡ Logica de la  vista
     *******************/

    private void calificar() {
        ticket.ACCION = Constantes.CALIFICAR_TICKET;
        ticket.calificacion = calificacion;
        repository.createRequets(this,ticket, Constantes.CALIFICAR_TICKET);
    }

    @Override
    public void succes(String succes, JsonElement jsonElement) {
        finish();
    }

    @Override
    public void error(String error) {
        makeText(this, error, LENGTH_LONG).show();
    }
}
