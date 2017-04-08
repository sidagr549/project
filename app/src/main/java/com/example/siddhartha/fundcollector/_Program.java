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
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class _Program extends AppCompatActivity {

    //    private static final String TAG = "_Program";
    private static final String TAG = "_Program";
//    private EditText societyName;
    private EditText _Name;
    private EditText generalDescription, amountReq, minAmountReq, maxAmountPerPerson;
//    private String society_name;
    private String _name;
    private String general_description, amount_req, minAmount_req, maxAmountPer_person;
    private String status;
    URL u = new URL();
    int j;
    private String json_url_receive_data = u.address + "/group_register/";
    private SharedPreferences sharedPreferences;
    private String user_name;
    private String type;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout._program);
//        societyName = (EditText) findViewById(R.id.name_society);
        _Name = (EditText) findViewById(R.id.name_);
        generalDescription = (EditText) findViewById(R.id.general__description);
        amountReq = (EditText) findViewById(R.id.amount_required);
        minAmountReq = (EditText) findViewById(R.id.min_amount_required);
        maxAmountPerPerson = (EditText) findViewById(R.id.max_amount_required_per_person);
    }

    public void startSocietyProgram(View view) {

//        society_name = societyName.getText().toString();
        _name = _Name.getText().toString();
        general_description = generalDescription.getText().toString();
        amount_req = amountReq.getText().toString();
        minAmount_req = minAmountReq.getText().toString();
        maxAmountPer_person = maxAmountPerPerson.getText().toString();

//        if ((isEmpty(society_name) > 0) && (isEmpty(general_description) > 0) && (isEmpty(amount_req) > 0) && (isEmpty(minAmount_req) > 0) && (isEmpty(maxAmountPer_person) > 0)) {
        if ((isEmpty(_name) > 0) && (isEmpty(general_description) > 0) && (isEmpty(amount_req) > 0) && (isEmpty(minAmount_req) > 0) && (isEmpty(maxAmountPer_person) > 0)) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, json_url_receive_data,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            status = response;
                            if (status.equals("false")) {
                                Toast.makeText(getApplicationContext(), "Something went Wrong , Try Again", Toast.LENGTH_SHORT).show();
                            } else {
                                Log.v(TAG, status + " " + "onResponse");
//                                Log.v(TAG, society_name);
                                Log.v(TAG, _name);
//                                Intent intent1 = getIntent();
                                Intent intent = new Intent(getApplicationContext(), Approval.class);
                                intent.putExtra("code", status);
//                                intent.putExtra("username" , intent1.getExtras().getString("username"));
                                startActivity(intent);
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.v(TAG, status + " " + "onError");
                    Toast.makeText(getApplicationContext(), "Some Error Occured , Try Again", Toast.LENGTH_SHORT).show();
                    error.printStackTrace();
                }
            }) {

                @Override
                protected Map<String, String> getParams() {
//                    Log.v(TAG, society_name);
                    Log.v(TAG, _name);
                    Log.v(TAG, "send successful");
                    Intent intent = getIntent();
                    int i = intent.getExtras().getInt("flag");
                    if (i == 0) {
                        type = "Society";
                    } else if (i == 1) {
                        type = "Government";
                    } else {
                        type = "Individual";
                    }
                    sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    j=sharedPreferences.getInt("user" , 2);
                    if(j==0) {
                        user_name = sharedPreferences.getString("UserName", "username invalid");
                    }
                    else if(j==1){
                        user_name = sharedPreferences.getString("UserNameRegister" , "username invalid");
                    }
                    Log.v(TAG, user_name);
                    Log.v(TAG, type);
                    Map<String, String> params = new HashMap<String, String>();
//                    params.put("name", society_name);
                    params.put("name", _name);
                    params.put("description", general_description);
                    params.put("amount_required", amount_req);
                    params.put("min_amount_req", minAmount_req);
                    params.put("max_deposit_per_person", maxAmountPer_person);
                    params.put("type", type);
                    params.put("user", user_name);
                    return params;

                }
            };
            MySingleton.getInstance(getApplicationContext()).addToRequestque(stringRequest);
        } else {
            Toast.makeText(getApplicationContext(), "All Fields Are MANDATORY", Toast.LENGTH_SHORT).show();
        }
    }

    public int isEmpty(String a) {
        return a.trim().length();
    }
}
