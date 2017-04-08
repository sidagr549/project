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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class Pay extends AppCompatActivity {
    private EditText editText;
    private static final String TAG = "Pay";
    private String payment;
    private String status;
    URL u=new URL();
    private String url = u.address + "/submit_amount/";
    private SharedPreferences sharedPreferences;
    private String user_name;
    int j;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay);
        editText = (EditText)findViewById(R.id.user_pay);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        j=sharedPreferences.getInt("user" , 2);
        if(j==0) {
            user_name = sharedPreferences.getString("UserName", "username invalid");
        }
        else if(j==1){
            user_name = sharedPreferences.getString("UserNameRegister" , "username invalid");
        }
    }

    public void pay(View view){
        payment = editText.getText().toString();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        status = response;
                        Log.v(TAG , status );
                        if(status.equals("cannot submit multiple")){
//                            Toast.makeText(getApplicationContext() , "You have submitted previously" , Toast.LENGTH_SHORT).show();
                            Toast.makeText(Pay.this , "You have submitted previously" , Toast.LENGTH_SHORT).show();
                        }
                        else if(status.equals("Success")){
                            Toast.makeText(getApplicationContext() , "Payment Successful" , Toast.LENGTH_SHORT).show();
//                            Intent intent = getIntent();
                            Intent goChnada = new Intent(getApplicationContext() , Chanda.class);
//                            goChnada.putExtra("username" , intent.getExtras().getString("username"));
                            startActivity(goChnada);
                        }
                        else {
                            Toast.makeText(getApplicationContext() , status , Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Intent intent = getIntent();
                Map<String , String>params = new HashMap<String, String>();
                params.put("user" , user_name);
                params.put("amount" , payment);
                params.put("Code" , intent.getExtras().getString("Code"));
                return params;
            }
        };
        MySingleton.getInstance(getApplicationContext()).addToRequestque(stringRequest);
//        Intent intent = getIntent();
//        Toast.makeText(getApplicationContext() , "PAYMENT SUCCESSFUL" , Toast.LENGTH_SHORT).show();
//        Intent goChnada = new Intent(this , Chanda.class););
//        goChnada.putExtra("username" , intent.getExtras().getString("username")
//        startActivity(goChnada);
    }
    //How to exit app from any activity
//    @Override
//    public void onBackPressed() {
//        Intent intent = new Intent(this , _InvestmentOverview.class);
//        intent.addCategory(Intent.CATEGORY_HOME);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(intent);
//    }
}
