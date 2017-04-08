package com.example.siddhartha.fundcollector;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class _InvestmentOverview extends AppCompatActivity {

//    private TextView society_name, society_description;
    private TextView _name, _description;
    private TextView amount_remaining, amount_collected, max_limit;
    private SharedPreferences sharedPreferences;
    private String user_name;
    int j;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout._investment_overview);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        society_name = (TextView) findViewById(R.id.society_name);
        _name = (TextView) findViewById(R.id._name);
//        society_description = (TextView) findViewById(R.id.society_description);
        _description = (TextView) findViewById(R.id._description);
        amount_remaining = (TextView) findViewById(R.id.amount_remaining);
        amount_collected = (TextView) findViewById(R.id.amount_collected);
        max_limit = (TextView) findViewById(R.id.max_limit);
        j=sharedPreferences.getInt("user" , 2);
        if(j==0) {
            user_name = sharedPreferences.getString("UserName", "username invalid");
        }
        else if(j==1){
            user_name = sharedPreferences.getString("UserNameRegister" , "username invalid");
        }

//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, json_url_receive_data, (String) null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try {
        Intent intent = getIntent();
        _name.setText(intent.getExtras().getString("name"));
        _description.setText(intent.getExtras().getString("description"));
        amount_remaining.setText(intent.getExtras().getString("amount_remaining"));
        amount_collected.setText(intent.getExtras().getString("amount_collected"));
        max_limit.setText(intent.getExtras().getString("max_limit_per_person"));
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                            Toast.makeText(getApplicationContext(), "Something Went Wrong , Try Again", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(getApplicationContext(), "Some Error Occured , Try Again", Toast.LENGTH_SHORT).show();
//                error.printStackTrace();
//
//            }
//        });
//        MySingleton.getInstance(getApplicationContext()).addToRequestque(jsonObjectRequest);
    }

    public void proceedToPayment(View view) {
        Intent intent = getIntent();
        Intent payment = new Intent(this, Pay.class);
        payment.putExtra("username" , user_name);
        payment.putExtra("Code" , intent.getExtras().getString("Code"));
        startActivity(payment);
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
