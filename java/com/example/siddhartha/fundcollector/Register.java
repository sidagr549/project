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
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity {

    private static final String TAG = "Register";
    private EditText userName, eMail, password, confirmPassword;
    private TextView userNameAlreadyExists, userNameReq, eMailReq, passwordReq, passwordsNotMatch;
    URL u=new URL();
    private String json_url_receive_data = u.address + "/register/";
    public String user_username;
    private String user_email, user_password, user_confirm_password;
    private String status;
    private int flag = 1;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        getSupportActionBar().hide();
        userName = (EditText) findViewById(R.id.user_username);
        eMail = (EditText) findViewById(R.id.user_email);
        password = (EditText) findViewById(R.id.create_password);
        confirmPassword = (EditText) findViewById(R.id.confirm_password);
        userNameAlreadyExists = (TextView) findViewById(R.id.username_already_exists);
        userNameReq = (TextView) findViewById(R.id.user_name_required);
        userNameReq.setVisibility(View.INVISIBLE);
        eMailReq = (TextView) findViewById(R.id.email_required);
        eMailReq.setVisibility(View.INVISIBLE);
        passwordReq = (TextView) findViewById(R.id.pass_required);
        passwordReq.setVisibility(View.INVISIBLE);
        passwordsNotMatch = (TextView) findViewById(R.id.passwords_do_not_match);
        passwordsNotMatch.setVisibility(View.INVISIBLE);
        userNameAlreadyExists.setVisibility(View.INVISIBLE);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
    }
//How to exit app from any activity
//    @Override
//    public void onBackPressed() {
//        Intent intent = new Intent(Intent.ACTION_MAIN);
//        intent.addCategory(Intent.CATEGORY_HOME);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(intent);
//        finish();
//    }

    public void signUp(View view) {
        if (flag == 0) {
            userNameAlreadyExists.setVisibility(View.INVISIBLE);
        }
        user_username = userName.getText().toString();
        editor = sharedPreferences.edit();
        editor.putString("UserNameRegister" , user_username);
        editor.putInt("user" , 1);
        editor.commit();
//        startActivity(new Intent(getApplicationContext(), MiddlePage.class));

        user_email = eMail.getText().toString();
        user_password = password.getText().toString();
        user_confirm_password = confirmPassword.getText().toString();
//        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//        String string = preferences.getString("UserName" , "username invalid");
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, json_url_send_data,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        Log.v(TAG, user_username + " " + user_password + " "+user_email);
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(getApplicationContext(), "Some Error Occured , Try Again", Toast.LENGTH_SHORT).show();
//            }
//        }) {
//
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("username", user_username);
//                params.put("password", user_password);
//                params.put("email", user_email);
//                return params;
//            }
//        };
//        MySingleton.getInstance(getApplicationContext()).addToRequestque(stringRequest);
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
//                                userNameAlreadyExists.setVisibility(View.VISIBLE);
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
//

//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, json_url_receive_data, (String) null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try {
//                            Log.v(TAG , user_username);
//                            status = response.getString("status").toString();
//                            Log.v(TAG, status + " " + "onResponse");
//                            if (status.equals("true")) {
//                                startActivity(new Intent(getApplicationContext(), MiddlePage.class));
//                            } else {
//                                flag = 0;
//                                userNameAlreadyExists.setVisibility(View.VISIBLE);
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
//            protected Map<String, String> getParams(){
//                Log.v(TAG , user_username);
//                Log.v(TAG , "send successful");
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("username", user_username);
//                params.put("password", user_password);
//                params.put("email", user_email);
//                return params;
//            }};
//
//        MySingleton.getInstance(getApplicationContext()).addToRequestque(jsonObjectRequest);

        if ((!isEmptyUsername(user_username)) && (isEmailValid(user_email)) && (minPassword(user_password) >= 8) && (passwordMatching(user_password, user_confirm_password))) {
            userNameReq.setVisibility(View.INVISIBLE);
            eMailReq.setVisibility(View.INVISIBLE);
            passwordReq.setVisibility(View.INVISIBLE);
            passwordsNotMatch.setVisibility(View.INVISIBLE);

            StringRequest stringRequest = new StringRequest(Request.Method.POST, json_url_receive_data,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            status = response;
                            if (status.equals("true")) {
                                Log.v(TAG, status + " " + "onResponse");
                                Log.v(TAG, user_username);
                                Toast.makeText(getApplicationContext() , "Registration Successful" , Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), Chanda.class);
//                                intent.putExtra("username" , user_username);
                                startActivity(intent);
//                                startActivity(new Intent(getApplicationContext(), MiddlePage.class));
                            } else {
                                flag = 0;
                                userNameAlreadyExists.setVisibility(View.VISIBLE);
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
                    Log.v(TAG, user_username);
                    Log.v(TAG, "send successful");
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("username", user_username);
                    params.put("password", user_password);
                    params.put("email", user_email);
                    return params;

                }
            };
            MySingleton.getInstance(getApplicationContext()).addToRequestque(stringRequest);
        } else {
            if (isEmptyUsername(user_username)) {
                userNameReq.setVisibility(View.VISIBLE);
            } else {
                userNameReq.setVisibility(View.INVISIBLE);
            }
            if (isEmailValid(user_email) == false) {
                eMailReq.setVisibility(View.VISIBLE);
            } else {
                eMailReq.setVisibility(View.INVISIBLE);
            }
            if (minPassword(user_password) < 8) {
                passwordReq.setVisibility(View.VISIBLE);
            } else {
                passwordReq.setVisibility(View.VISIBLE);
            }
            if (passwordMatching(user_password, user_confirm_password) == false) {
                passwordsNotMatch.setVisibility(View.VISIBLE);
            } else {
                passwordsNotMatch.setVisibility(View.INVISIBLE);
            }
        }
    }

    public boolean isEmptyUsername(String a) {
        return a.trim().length() == 0;
    }

    public static boolean isEmailValid(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    public int minPassword(String a) {
        return a.trim().length();
    }

    public boolean passwordMatching(String firstPass, String secondPass) {
        return secondPass.equals(firstPass);
    }

}
