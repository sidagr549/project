package com.example.siddhartha.fundcollector;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class _Investment_cum_Status extends AppCompatActivity {

    private EditText checkStatus;
    URL u = new URL();
    private String json_url = u.address + "/find_group/";
    private String json_receive_url = u.address + "/try_group/";
    private String statusCheck;
    private String status;
    private static final String TAG = "_Investment_cum_Status";
    private SharedPreferences sharedPreferences;
    private String user_name;
    int j;
    private SharedPreferences investmentPreferences;
    private int i;
    private Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout._investment_cum_status);
        getSupportActionBar().hide();
        checkStatus = (EditText) findViewById(R.id.check___code);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        investmentPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        i = investmentPreferences.getInt("reference" , 2);
        Log.v(TAG , "hello" + String.valueOf(i));
        j=sharedPreferences.getInt("user" , 2);
        if(j==0) {
            user_name = sharedPreferences.getString("UserName", "username invalid");
        }
        else if(j==1){
            user_name = sharedPreferences.getString("UserNameRegister" , "username invalid");
        }
        Log.v(TAG , "hi" + String.valueOf(j));
    }

    public void _check_(View view) {

        statusCheck = checkStatus.getText().toString();
        if (isEmpty(statusCheck) > 0) {
//            StringRequest stringRequest = new StringRequest(Request.Method.POST, json_receive_url,
//                    new Response.Listener<String>() {
//                        @Override
//                        public void onResponse(String response) {
//                            status = response;
//                            if (status.equals("true")) {
//                                Log.v(TAG, status + " " + "onResponse");
//                                Log.v(TAG, statusCheck);
////                                startActivity(new Intent(getApplicationContext(), StatusOverview.class));
//                            } else {
//                                Toast.makeText(getApplicationContext(), "Wrong CODE , Try Again", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    Log.v(TAG, status + " " + "onError");
//                    Toast.makeText(getApplicationContext(), "Some Error Occured , Try Again", Toast.LENGTH_SHORT).show();
//                    error.printStackTrace();
//                }
//            }) {
//                @Override
//                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    Log.v(TAG, statusCheck);
                    Log.v(TAG, "send successful");
//                    Intent intent = getIntent();
                    Log.v(TAG, user_name);
                    params.put("Code", statusCheck);
                    params.put("user", user_name);
//                    return params;
//
//                }
//            };
//            MySingleton.getInstance(getApplicationContext()).addToRequestque(stringRequest);

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, json_url, new JSONObject(params),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            if(i==0) {
                                intent = new Intent(getApplicationContext(), StatusOverview.class);
                            }
                            else if(i==1){
                                intent = new Intent(getApplicationContext() , _InvestmentOverview.class);
                                intent.putExtra("Code",statusCheck);
                            }
                            try {
                                intent.putExtra("amount_collected", response.getString("amount_collected"));
                                intent.putExtra("max_limit_per_person", response.getString("max_limit_per_person"));
                                intent.putExtra("description", response.getString("description"));
                                intent.putExtra("amount_remaining", response.getString("amount_remaining"));
                                intent.putExtra("name", response.getString("name"));
                                Log.v(TAG , String.valueOf(i) + " hmmm...");
                                if(i==0) {
                                    intent.putExtra("contribution", response.getString("contribution"));
                                }
                                startActivity(intent);
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(getApplicationContext(), "Something Went Wrong , Try Again", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), "Some Error Occured , Try Again", Toast.LENGTH_SHORT).show();
                    error.printStackTrace();

                }
            });
            MySingleton.getInstance(getApplicationContext()).addToRequestque(jsonObjectRequest);


        } else {
            Toast.makeText(getApplicationContext(), "Enter The CODE", Toast.LENGTH_SHORT).show();
        }
    }

    public int isEmpty(String a) {
        return a.trim().length();
    }
}
