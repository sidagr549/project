package com.example.siddhartha.fundcollector;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    private static final String TAG = "Login";
    private EditText user_userName, user_userPassword;
    private TextView userNameReq, passwordReq, min8Characters;
    URL u=new URL();
    private String json_url_send_data = u.address + "/accounts/login/";
    private String username, password;
    private String status;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        getSupportActionBar().hide();
        user_userName = (EditText) findViewById(R.id.username);
        user_userPassword = (EditText) findViewById(R.id.user_password);
        userNameReq = (TextView) findViewById(R.id.username_required);
        userNameReq.setVisibility(View.INVISIBLE);
        passwordReq = (TextView) findViewById(R.id.password_required);
        passwordReq.setVisibility(View.INVISIBLE);
        min8Characters = (TextView) findViewById(R.id.min_characters);
        min8Characters.setVisibility(View.INVISIBLE);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
    }


    public void sign_in(View view) {

        username = user_userName.getText().toString();
        password = user_userPassword.getText().toString();
        editor = sharedPreferences.edit();
        editor.putString("UserName" , username);
        editor.putInt("user" , 0);
        editor.commit();
        Log.v(TAG, username);

        if ((!isEmptyUsername(username)) && (!isEmptyPassword(password)) && (minPassword(password) >= 8)) {
            userNameReq.setVisibility(View.INVISIBLE);
            passwordReq.setVisibility(View.INVISIBLE);
            min8Characters.setVisibility(View.INVISIBLE);

            StringRequest stringRequest = new StringRequest(Request.Method.POST, json_url_send_data,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.v(TAG, username + " " + password);
                            status = response;
                            if (status.equals("true")) {
                                Log.v(TAG, status + " " + "onResponse");
                                Intent intent = new Intent(getApplicationContext() , Chanda.class);
//                                Intent intent1 = getIntent();
                                Log.v(TAG , username);
//                                intent.putExtra("username" , intent1.getExtras().getString("username"));
                                startActivity(intent);
                            } else {
                                Toast.makeText(getApplicationContext(), "Wrong Username Password Combination", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), "Some Error Occured , Try Again", Toast.LENGTH_SHORT).show();
                }
            }) {

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    Log.v(TAG , "send successful");
                    params.put("username", username);
                    params.put("password", password);
                    return params;
                }
            };
            MySingleton.getInstance(getApplicationContext()).addToRequestque(stringRequest);
        } else {
            if (isEmptyUsername(username)) {
                userNameReq.setVisibility(View.VISIBLE);
            } else {
                userNameReq.setVisibility(View.INVISIBLE);
            }
            if (isEmptyPassword(password)) {
                passwordReq.setVisibility(View.VISIBLE);
            } else {
                passwordReq.setVisibility(View.INVISIBLE);
            }
            if (minPassword(password) < 8) {
                min8Characters.setVisibility(View.VISIBLE);
            } else {
                min8Characters.setVisibility(View.INVISIBLE);
            }
        }
//
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, json_url_receive_data, (String) null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try {
//                            status = response.getString("status").toString();
//                            Log.v(TAG, status + " " + "onResponse");
//                            if (status.equals("true")) {
//                                startActivity(new Intent(getApplicationContext(), MiddlePage.class));
//                            } else {
//                                Toast.makeText(getApplicationContext(), "Wrong Username Password Combination", Toast.LENGTH_SHORT).show();
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
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
//
//        MySingleton.getInstance(getApplicationContext()).addToRequestque(jsonObjectRequest);


//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, json_url_receive_data ,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try {
//                            status = response.getString("status").toString();
//                            Log.v(TAG, status + " " + "onResponse");
//                            if (status.equals("true")) {
//                                startActivity(new Intent(getApplicationContext(), MiddlePage.class));
//                            } else {
//                                Toast.makeText(getApplicationContext(), "Wrong Username Password Combination", Toast.LENGTH_SHORT).show();
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.v(TAG, status + " " + "onError");
//                Toast.makeText(getApplicationContext(), "Some Error Occured , Try Again", Toast.LENGTH_SHORT).show();
//                error.printStackTrace();
//            }
//        }){
//
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("username", username);
//                params.put("password", password);
//                return params;
//            }};
//
//        MySingleton.getInstance(getApplicationContext()).addToRequestque(jsonObjectRequest);

    }

    public void register(View view) {
        Intent registerIntent = new Intent(this, Register.class);
        startActivity(registerIntent);
    }

    public boolean isEmptyUsername(String a) {
        return a.trim().length() == 0;
    }

    public boolean isEmptyPassword(String a) {
        return a.trim().length() == 0;
    }

    public int minPassword(String a) {
        return a.trim().length();
    }


//    to create the menu
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

}
