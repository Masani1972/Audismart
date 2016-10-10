package com.aosas.audismart.activitys;

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

public class PasswordActivity extends AppCompatActivity implements BaseActivity{
    private IRepository repository = new Repository();

    @InjectView(R.id.editText_email)
    EditText editText_email;

    @InjectView(R.id.button_Recuperar)
    Button button_Recuperar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.button_Recuperar)
    public void button_Recuperar(View view) {
        repository.createRequets(this, editText_email.getText().toString(), Constantes.RECUPERAR_CONTRASENA);
    }


    @Override
    public void succes(String succes, JsonElement jsonElement) {
        Toast.makeText(this, succes, Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public void error(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }
}
