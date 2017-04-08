package com.example.siddhartha.fundcollector;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


public class YourPayments extends AppCompatActivity {

    private TextView yourPayments;
    URL u=new URL();
    private String json_url_receive_data = u.address + "/check_previous_status/";
    private String status;
    private static final String TAG = "YourPayments";
    private SharedPreferences sharedPreferences;
    private String user_name;
    SharedPreferences investmentPreferences;
    SharedPreferences.Editor editor;
    int j;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.your_payments);
        yourPayments = (TextView)findViewById(R.id.your_payments);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, json_url_receive_data,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        status = response;
                        if (status.equals("false")) {
                            Log.v(TAG , status + " onResponse");
                            Toast.makeText(getApplicationContext(), "Something Went WRONG , Try Again", Toast.LENGTH_SHORT).show();
                        } else {
                            yourPayments.setText(status);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v(TAG, status + " " + "onError");
                Toast.makeText(getApplicationContext(), "Some Error Occured , Try Again", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
//                Intent intent = getIntent();
                j=sharedPreferences.getInt("user" , 2);
                if(j==0) {
                    user_name = sharedPreferences.getString("UserName", "username invalid");
                }
                else if(j==1){
                    user_name = sharedPreferences.getString("UserNameRegister" , "username invalid");
                }
                Log.v(TAG ,user_name);
                Map<String, String> params = new HashMap<String, String>();
                params.put("user" ,user_name);
                return params;
            }
        };
        MySingleton.getInstance(getApplicationContext()).addToRequestque(stringRequest);

    }

    public void enterCode(View view){
//        Intent intent = getIntent();
        Intent checkStatus = new Intent(this , _Investment_cum_Status.class);
        investmentPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = investmentPreferences.edit();
        editor.putInt("reference" , 0);
        editor.commit();
//        _check_.putExtra("username" , intent.getExtras().getString("username"));
        startActivity(checkStatus);
    }
}
