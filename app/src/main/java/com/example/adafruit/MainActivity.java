package com.example.adafruit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btnencender).setOnClickListener(this);
        findViewById(R.id.btnapagar).setOnClickListener(this);
        
    }

    @Override
    public void onClick(View view) {
        String url = "https://io.adafruit.com/api/v2/ramiro174/feeds/onoff/data";
        JSONObject jsonBody = new JSONObject();
        switch (view.getId()){

            case  R.id.btnencender:

                try {
                    jsonBody.put("value", "1");
                }catch (Exception e){
                }
                break;
            case R.id.btnapagar:

                try {
                    jsonBody.put("value", "0");
                }catch (Exception e){
                }
                break;


        }
        JsonObjectRequest carta1=new JsonObjectRequest(Request.Method.POST, url, jsonBody, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                Log.i("vol", error.toString());
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("X-AIO-Key", "aio_JtNT617naEdLXRWcv57DdijSQDaH");
                return headers;
            }
        };
 SingletonRequest.getInstance(this).addToRequestQue(carta1);
    }
}