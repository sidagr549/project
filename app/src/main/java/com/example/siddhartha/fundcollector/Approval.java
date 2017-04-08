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

public class Approval extends AppCompatActivity {

    private TextView myCode;
    private String status;
    private static final String TAG = "Approval";
    SharedPreferences sharedPreferences;
    private String user_name;
    int j;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.approval);
        getSupportActionBar().hide();
        myCode = (TextView)findViewById(R.id.my_code);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        Intent intent = getIntent();
        status = intent.getExtras().getString("code");
        myCode.setText(status);

//        StringRequest stringRequest = new StringRequest(Request.Method.POST, json_url_receive_data,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        status = response;
//                        if (status.equals("false")) {
//                            Log.v(TAG , status + " onResponse");
//                            Toast.makeText(getApplicationContext(), "Something Went WRONG , Try Again", Toast.LENGTH_SHORT).show();
//                        } else {
//                            myCode.setText(status);
//                        }
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.v(TAG, status + " " + "onError");
//                Toast.makeText(getApplicationContext(), "Some Error Occured , Try Again", Toast.LENGTH_SHORT).show();
//                error.printStackTrace();
//            }
//        });
//        MySingleton.getInstance(getApplicationContext()).addToRequestque(stringRequest);

    }
    public void goToChanda(View view){
//        Intent intent123 = getIntent();
        j=sharedPreferences.getInt("user" , 2);
        if(j==0) {
            user_name = sharedPreferences.getString("UserName", "username invalid");
        }
        else if(j==1){
            user_name = sharedPreferences.getString("UserNameRegister" , "username invalid");
        }
        Intent goChanda = new Intent(this , Chanda.class);
        Log.v(TAG ,user_name);
//        goChanda.putExtra("username" , intent123.getExtras().getString("username"));
        startActivity(goChanda);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this , Chanda.class);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
