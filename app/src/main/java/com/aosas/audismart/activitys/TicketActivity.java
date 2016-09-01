package com.aosas.audismart.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aosas.audismart.R;
import com.aosas.audismart.comunication.proxy.IRepository;
import com.aosas.audismart.comunication.proxy.Repository;
import com.aosas.audismart.model.BuscarTicket;
import com.aosas.audismart.model.Ticket;
import com.aosas.audismart.util.Constantes;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import butterknife.ButterKnife;
import butterknife.InjectView;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.makeText;

public class TicketActivity extends AppCompatActivity implements BaseActivity{
    private Ticket ticket;
    private IRepository repository = new Repository();

    @InjectView(R.id.text_empresa)
    TextView text_empresa;

    @InjectView(R.id.text_area)
    TextView text_area;

    @InjectView(R.id.text_estado)
    TextView text_estado;

    @InjectView(R.id.editText_asunto)
    EditText editText_asunto;

    @InjectView(R.id.layout_Respuestas)
    LinearLayout layout_Respuestas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);
        ButterKnife.inject(this);

        ticket = (Ticket) getIntent().getSerializableExtra("ticket");
        cargarDatos(ticket);
    }

    private void cargarDatos(Ticket ticket) {
        text_empresa.setText("Empresa: "+ticket.empresa);
        text_area.setText("Area: "+ticket.empresa);
        text_estado.setText("Estado: "+ticket.estado);
        editText_asunto.setText(ticket.asunto);
        BuscarTicket buscarTicket = new BuscarTicket(ticket.id_ticket,"","",Constantes.BUSCAR_TICKET_RESPUESTA);
        repository.createRequets(this,buscarTicket, Constantes.BUSCAR_TICKET_RESPUESTA);
    }



    @Override
    public void succes(String succes, JsonElement jsonElement) {
        if(succes.substring(0,22).equals(Constantes.BUSCAR_TICKET_RESPUESTA_RESPONSE)){
            int numeroRespuestas = Integer.parseInt(succes.substring(25,26));
            JsonArray jsonArray = jsonElement.getAsJsonArray();
            for (int index=0; index <numeroRespuestas;index++){
                EditText editText = new EditText(this);
                editText.setEnabled(false);
                editText.setText(((JsonObject) jsonArray.get(index)).get("asunto").getAsString());
                editText.setBackground(getResources().getDrawable(R.drawable.round_corner));
                if(index%2==0){
                    LayoutInflater factory = LayoutInflater.from(this);
                    View view = factory.inflate(R.layout.linear_header_persona, null);
                    layout_Respuestas.addView(view);
                    layout_Respuestas.addView(editText);
                    Log.i("index par",""+index);
                }else{
                    LayoutInflater factory = LayoutInflater.from(this);
                    View view = factory.inflate(R.layout.linear_header_soporte, null);
                    layout_Respuestas.addView(view);
                    layout_Respuestas.addView(editText);
                    Log.i("index impar",""+index);
                }
            }
        }
    }

    @Override
    public void error(String error) {
        makeText(TicketActivity.this, error, LENGTH_LONG).show();
    }
}
