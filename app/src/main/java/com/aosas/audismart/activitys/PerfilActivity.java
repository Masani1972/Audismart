package com.aosas.audismart.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;

import com.aosas.audismart.R;
import com.aosas.audismart.adapters.SecondLevelProfile;
import com.google.gson.JsonElement;

import java.util.Arrays;
import java.util.List;

/**
 * The type Perfil activity.
 * Consulta la informacion de perfil del usuario
 */
public class PerfilActivity extends AppCompatActivity implements BaseActivity {
    private ExpandableListView explvlist;
    private List<String> listDataHeader;

    /**
     * On create.
     *
     * @param savedInstanceState the saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        //Se adiciona el icono a la barra de la actividad
        ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.drawable.logoapp);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        listDataHeader = Arrays.asList(getResources().getStringArray(R.array.title_perfil));
        explvlist = (ExpandableListView) findViewById(R.id.ParentLevelProfile);
        explvlist.setAdapter(new SecondLevelProfile(this, listDataHeader, false));

    }

    /**
     * On create options menu boolean.
     *
     * @param menu the menu
     * @return the boolean
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_splash, menu);
        return true;
    }

    /**
     * On options item selected boolean.
     *
     * @param item the item
     * @return the boolean
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_perfil:
                Intent i = new Intent(this, PerfilActivity.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Succes.
     *
     * @param succes      the succes
     * @param jsonElement the json element
     */
    @Override
    public void succes(String succes, JsonElement jsonElement) {

    }

    /**
     * Error.
     *
     * @param error the error
     */
    @Override
    public void error(String error) {

    }
}
