package com.aosas.audismart.activitys;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aosas.audismart.R;
import com.aosas.audismart.comunication.proxy.AsyntaskFile;
import com.aosas.audismart.comunication.proxy.IRepository;
import com.aosas.audismart.comunication.proxy.Repository;
import com.aosas.audismart.model.BuscarTicket;
import com.aosas.audismart.model.RespuestaTicket;
import com.aosas.audismart.model.Ticket;
import com.aosas.audismart.repository.Preferences;
import com.aosas.audismart.util.Constantes;
import com.aosas.audismart.util.Util;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.RequestBody;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.makeText;

public class TicketActivity extends AppCompatActivity implements BaseActivity{
    private Ticket ticket;
    private IRepository repository = new Repository();
    private static final String TAG ="TicketActivity";
    private String path = null;
    private String nombreArchivo ="";

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

    @InjectView(R.id.fecha_respuesta)
    TextView fecha_respuesta;

    @InjectView(R.id.button_CargarArchivo)
    Button button_CargarArchivo;

    @InjectView(R.id.text_NombreArchivo)
    TextView text_NombreArchivo;

    @InjectView(R.id.button_CerraTicket)
    Button button_CerraTicket;

    @InjectView(R.id.button_AgregarRespuesta)
    Button button_AgregarRespuesta;

    @InjectView(R.id.edit_AsuntoRespuesta)
    EditText edit_AsuntoRespuesta;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);
        ButterKnife.inject(this);
        ticket = (Ticket) getIntent().getSerializableExtra("ticket");
        cargarDatos();
    }

    private void cargarDatos() {
        text_empresa.setText("Empresa: "+ticket.empresa);
        text_area.setText("Area: "+ticket.empresa);
        text_estado.setText("Estado: "+ticket.estado);
        editText_asunto.setText(ticket.asunto);
        fecha_respuesta.setText(ticket.fecha);
        BuscarTicket buscarTicket = new BuscarTicket(ticket.id_ticket,"","",Constantes.BUSCAR_TICKET_RESPUESTA);
        repository.createRequets(this,buscarTicket, Constantes.BUSCAR_TICKET_RESPUESTA);
    }

    @OnClick(R.id.button_CargarArchivo)
    public void button_CargarArchivo(View view) {
        cargarArchivo();
    }


    @OnClick(R.id.button_CerraTicket)
    public void button_CerraTicket(View view){ cerraTicket();}

    @OnClick(R.id.button_AgregarRespuesta)
    public void button_AgregarRespuesta(View view){ enviarRespuesta();}


    /*******************
     Presentador¡¡ Logica de la  vista
     *******************/
    private void enviarRespuesta() {
        RequestBody requestBody = null;
        String asuntoRespuesta = "";

        if(edit_AsuntoRespuesta.getText().toString().length()>0){
            asuntoRespuesta = edit_AsuntoRespuesta.getText().toString();
            if(path!=null) {
                Map<String, String> params = new HashMap<String, String>(3);
                params.put("id_ticket", ticket.id_ticket);
                params.put("asunto", asuntoRespuesta);
                params.put("ACCION", Constantes.RESPONDER_TICKET);

                new AsyntaskFile(path,nombreArchivo,params,this,Constantes.RESPONDER_TICKET).execute();
            }

        }
        else
            makeText(this, R.string.formularioIncompleto, LENGTH_LONG).show();

    }

    private void cargarArchivo() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try {
            startActivityForResult(
                   Intent.createChooser(intent, "Select a File to Upload"),
                    Constantes.FILE_SELECT_CODE);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "Please install a File Manager.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void cerraTicket(){
        ticket.ACCION = Constantes.CERRAR_TICKET;
        repository.createRequets(this,ticket, Constantes.CERRAR_TICKET);
    }

    @Override
    public void succes(String succes, JsonElement jsonElement) {
        if(succes.substring(0,22).equals(Constantes.BUSCAR_TICKET_RESPUESTA_RESPONSE)){
            int numeroRespuestas = Integer.parseInt(succes.substring(25,26));
            JsonArray jsonArray = jsonElement.getAsJsonArray();
            for (int index=0; index <numeroRespuestas;index++){
                crearViewRespuestas(index,jsonArray);

            }
        }else if (succes.equals(Constantes.CERRAR_TICKET_RESPONSE)){
           Intent intentCalificar = new Intent(this,CalificarTicketActivity.class);
            intentCalificar.putExtra("ticket",ticket);
            startActivity(intentCalificar);
            finish();
        }else{
            makeText(this, succes, LENGTH_LONG).show();
            finish();
        }
    }

    private void crearViewRespuestas(int index,JsonArray jsonArray) {
        //Creacion Layout
        LinearLayout layoutRespuesta = new LinearLayout(this);
        layoutRespuesta.setOrientation(LinearLayout.VERTICAL);
        layoutRespuesta.setBackground(getResources().getDrawable(R.drawable.round_corner));
        LinearLayout.LayoutParams linLayoutParam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        linLayoutParam.setMargins(0, 20, 0, 10);
        layoutRespuesta.setLayoutParams(linLayoutParam);

        //Creacion Texto respuesta
        TextView textView_Respuesta = new TextView(this);
        textView_Respuesta.setText("  "+((JsonObject) jsonArray.get(index)).get("asunto").getAsString());

        //Creacion Relative, para el icono y nombre archivo
        LinearLayout layout_Archivo = new LinearLayout(this);
        layout_Archivo.setOrientation(LinearLayout.HORIZONTAL);
        layout_Archivo.setLayoutParams(linLayoutParam);

        //Creacion icono Archivo
        ImageView imagen_Icon = new ImageView(this);
        imagen_Icon.setMaxHeight(android.app.ActionBar.LayoutParams.WRAP_CONTENT);
        imagen_Icon.setMaxWidth(android.app.ActionBar.LayoutParams.WRAP_CONTENT);
        imagen_Icon.setBackground(getResources().getDrawable(R.drawable.icon_file));

        //Creacion EditText archivo, con icono
        TextView text_View_Archivo = new TextView(this);
        String archivo = "";
        if(!((JsonObject) jsonArray.get(index)).get("archivo").isJsonNull())
            archivo = "  "+((JsonObject) jsonArray.get(index)).get("archivo").getAsString();
         text_View_Archivo.setText(archivo);

        layout_Archivo.addView(imagen_Icon);
        layout_Archivo.addView(text_View_Archivo);
        layoutRespuesta.addView(textView_Respuesta,0);
        layoutRespuesta.addView(layout_Archivo,1);

        if(index%2==0){
            LayoutInflater factory = LayoutInflater.from(this);
            View view = factory.inflate(R.layout.linear_header_soporte, null);
            TextView fecha = (TextView)view.findViewById(R.id.fecha_respuesta);
            fecha.setText(((JsonObject) jsonArray.get(index)).get("fecha").getAsString());
            layout_Respuestas.addView(view);
            layout_Respuestas.addView(layoutRespuesta);
        }else{
            LayoutInflater factory = LayoutInflater.from(this);
            View view = factory.inflate(R.layout.linear_header_persona, null);
            TextView fecha = (TextView)view.findViewById(R.id.fecha_respuesta);
            fecha.setText(((JsonObject) jsonArray.get(index)).get("fecha").getAsString());
            layout_Respuestas.addView(view);
            layout_Respuestas.addView(layoutRespuesta);
        }
    }

    @Override
    public void error(String error) {
        makeText(TicketActivity.this, error, LENGTH_LONG).show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case Constantes.FILE_SELECT_CODE:
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    Log.d(TAG, "File Uri: " + uri.toString());

                    try {
                        path = Util.getPath(this, uri);
                        Log.d(TAG, "File Path: " + path);
                        String[] parametrosPath = path.split("/");
                        nombreArchivo = parametrosPath[parametrosPath.length-1];
                        text_NombreArchivo.setText(nombreArchivo);
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }

                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
