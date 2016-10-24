package com.aosas.audismart.activitys;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aosas.audismart.R;
import com.aosas.audismart.comunication.proxy.IRepository;
import com.aosas.audismart.comunication.proxy.Repository;
import com.aosas.audismart.util.Constantes;
import com.google.gson.JsonElement;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * The type Password activity.
 * Solicita el correo, para recuperar contraseña
 */
public class PasswordActivity extends AppCompatActivity implements BaseActivity {
    private IRepository repository = new Repository();

    /**
     * The Edit text email.
     */
    @InjectView(R.id.editText_email)
    EditText editText_email;

    /**
     * The Button recuperar.
     */
    @InjectView(R.id.button_Recuperar)
    Button button_Recuperar;

    /**
     * On create.
     *
     * @param savedInstanceState the saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        ButterKnife.inject(this);

        //Se adiciona el icono a la barra de la actividad
        ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.drawable.logoapp);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
    }

    /**
     * Button recuperar.
     *
     * @param view the view
     */
    @OnClick(R.id.button_Recuperar)
    public void button_Recuperar(View view) {
        repository.createRequets(this, editText_email.getText().toString(), Constantes.RECUPERAR_CONTRASENA);
    }


    /**
     * Succes.
     *
     * @param succes      the succes
     * @param jsonElement the json element
     */
    @Override
    public void succes(String succes, JsonElement jsonElement) {
        Toast.makeText(this, succes, Toast.LENGTH_LONG).show();
        finish();
    }

    /**
     * Error.
     *
     * @param error the error
     */
    @Override
    public void error(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }
}
