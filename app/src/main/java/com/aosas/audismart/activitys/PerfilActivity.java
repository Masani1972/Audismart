package com.aosas.audismart.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;

import com.aosas.audismart.R;
import com.aosas.audismart.adapters.ParentLevelNotificaciones;
import com.aosas.audismart.adapters.ParentLevelProfile;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.List;

public class PerfilActivity extends AppCompatActivity implements BaseActivity {
    private ExpandableListView explvlist;
    private List<String> listDataHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        // Adding tittle data
        listDataHeader = new ArrayList<String>();
        listDataHeader.add("Mis Datos Personales");
        listDataHeader.add("Mis empresas inscritas");

        explvlist = (ExpandableListView)findViewById(R.id.ParentLevelProfile);
        explvlist.setAdapter(new ParentLevelProfile(this,listDataHeader));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splash, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.action_perfil:
                Intent i = new Intent(this,PerfilActivity.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void succes(String succes, JsonElement jsonElement) {

    }

    @Override
    public void error(String error) {

    }
}
