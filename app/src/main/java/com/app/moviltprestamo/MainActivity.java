package com.app.moviltprestamo;

import android.content.Intent;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.app.moviltprestamo.adapters.PrestamosAdapter;
import com.app.moviltprestamo.classes.Respuesta;
import com.app.moviltprestamo.entities.PrestamoActivo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;

import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private RecyclerView mRecyclerView;
    private PrestamosAdapter mPrestamoAdapter;
    private ArrayList<PrestamoActivo> mPrestamoActivo;
    private RequestQueue mRequestQueue;
    private String ip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        FloatingActionButton settings = findViewById(R.id.btn_settings);
        setTitle("Resumen Prestamos Activos");
        //Filling the RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.rvPrestamos);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRequestQueue= Volley.newRequestQueue(this);

        Intent intent = getIntent();
        ip = intent.getStringExtra("ip");
        ip = (ip == null)?"192.168.1.5":ip;

        Log.d("IP sended",ip);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Cargando", Snackbar.LENGTH_INDEFINITE)
                        .setAction("Action", null).show();

                getData(); //That's it RecyclerView Filled

                Snackbar.make(view, "Cargando", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();

            }
        });

        settings.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                startActivity(new Intent(MainActivity.this, Pop.class));
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void getData(){
        String url = "http://"+ ip +":2323/get_prestamos_activos";
        JsonObjectRequest objRequest = new JsonObjectRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Response", response.toString());
                        mPrestamoActivo = new ArrayList<>();
                        Respuesta resp = new Respuesta().FromJson(response.toString());
                        Gson json = new Gson();
                        JsonElement jsonE = new JsonParser().parse(resp.getMensaje());
                        JsonArray array = jsonE.getAsJsonArray();
                        for (JsonElement j : array) {
                            PrestamoActivo pres = new PrestamoActivo();
                            pres = json.fromJson(j, PrestamoActivo.class);
                            mPrestamoActivo.add(pres);
                        }
                        mPrestamoAdapter = new PrestamosAdapter(mPrestamoActivo);
                        mRecyclerView.setAdapter(mPrestamoAdapter);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("ErrorRespo", error.toString());
                    }
                }
        );
        mRequestQueue.add(objRequest);
    }

}
