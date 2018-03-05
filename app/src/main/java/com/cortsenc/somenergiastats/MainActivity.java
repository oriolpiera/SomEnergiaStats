package com.cortsenc.somenergiastats;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.cortsenc.somenergiastats.model.ResponseJSON;
import com.google.gson.*;

import org.json.JSONObject;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Date;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RequestQueue queue = MySingleton.getInstance(this.getApplicationContext()).
                getRequestQueue();

        final Button butUpdate = findViewById(R.id.update);
        butUpdate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                updateData();
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();
        updateData();
    }

    private void updateData(){
        getMembers();
        getContracts();
        getMembersGkwh();
        getAmountGkwh();
    }

    private void getMembers(){
        final TextView mTxtDisplay;
        final TextView mTxtDisplayTime;
        ImageView mImageView;
        String url = "https://api.somenergia.coop/stats/socis";

        mTxtDisplay = (TextView) findViewById(R.id.valueMembers);
        mTxtDisplayTime = (TextView) findViewById(R.id.Time);

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Gson g = new Gson();
                        ResponseJSON resposta = g.fromJson(response.toString(),ResponseJSON.class);
                        if(resposta.status.equalsIgnoreCase("ONLINE")){
                            mTxtDisplay.setText(NumberFormat.getInstance().format(resposta.data.socis));
                            String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
                            mTxtDisplayTime.setText(currentDateTimeString);
                        }else{
                            mTxtDisplay.setText("Not avaiable");
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub

                    }
                });
        MySingleton.getInstance(this).addToRequestQueue(jsObjRequest);

    }

    private void getContracts(){
        final TextView mTxtDisplay;
        final TextView mTxtDisplayTime;
        ImageView mImageView;
        String url = "https://api.somenergia.coop/stats/contractes";

        mTxtDisplay = (TextView) findViewById(R.id.valueContracts);


        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Gson g = new Gson();
                        ResponseJSON resposta = g.fromJson(response.toString(),ResponseJSON.class);
                        if(resposta.status.equalsIgnoreCase("ONLINE")){
                            mTxtDisplay.setText(NumberFormat.getInstance().format(resposta.data.contractes));
                        }else{
                            mTxtDisplay.setText("Not avaiable");
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub

                    }
                });
        MySingleton.getInstance(this).addToRequestQueue(jsObjRequest);

    }


    private void getMembersGkwh(){
        final TextView mTxtDisplay;
        String url = "https://api.somenergia.coop/stats/generation_socis";

        mTxtDisplay = (TextView) findViewById(R.id.valueInversors);


        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Gson g = new Gson();
                        ResponseJSON resposta = g.fromJson(response.toString(),ResponseJSON.class);
                        if(resposta.status.equalsIgnoreCase("ONLINE")){

                            mTxtDisplay.setText(NumberFormat.getInstance().format(resposta.data.socis));
                        }else{
                            mTxtDisplay.setText("Not avaiable");
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub

                    }
                });
        MySingleton.getInstance(this).addToRequestQueue(jsObjRequest);

    }


    private void getAmountGkwh(){
        final TextView mTxtDisplay;
        String url = "https://api.somenergia.coop/stats/generation_amount";

        mTxtDisplay = (TextView) findViewById(R.id.valueInvestments);


        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Gson g = new Gson();
                        ResponseJSON resposta = g.fromJson(response.toString(),ResponseJSON.class);
                        if(resposta.status.equalsIgnoreCase("ONLINE")){
                            mTxtDisplay.setText(NumberFormat.getInstance().format(resposta.data.amount));
                        }else{
                            mTxtDisplay.setText("Not avaiable");
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub

                    }
                });
        MySingleton.getInstance(this).addToRequestQueue(jsObjRequest);

    }
}


