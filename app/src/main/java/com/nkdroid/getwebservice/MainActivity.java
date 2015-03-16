package com.nkdroid.getwebservice;

import android.app.ProgressDialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;


public class MainActivity extends ActionBarActivity {
    private Toolbar toolbar;
    private ProgressDialog progressDialog;
    private UserData userData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setToolbar();

        progressDialog=new ProgressDialog(MainActivity.this);
        progressDialog.setCancelable(true);
        progressDialog.show();

        callGetService();
    }

    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setTitle("HOME");
            setSupportActionBar(toolbar);
        }
    }

    private void callGetService(){
        new CallWebService(AppConstants.URL,CallWebService.TYPE_JSONOBJECT) {

            @Override
            public void response(String response) {

                progressDialog.dismiss();

                userData =  new GsonBuilder().create().fromJson(response, UserData.class);
                Toast.makeText(MainActivity.this,userData.fname+" "+userData.lname,Toast.LENGTH_LONG).show();

            }

            @Override
            public void error(VolleyError error) {
                progressDialog.dismiss();
            }
        }.start();
    }
}
