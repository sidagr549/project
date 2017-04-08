package com.example.siddhartha.fundcollector;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


public class StatusOverview extends AppCompatActivity {

    private TextView status_name, status_description, status_amount_remaining, status_amount_collected, status_max_limit, status_contri;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.status_overview);

        status_name = (TextView) findViewById(R.id.status_name);
        status_description = (TextView) findViewById(R.id.status_description);
        status_amount_remaining = (TextView) findViewById(R.id.status_amount_remaining);
        status_amount_collected = (TextView) findViewById(R.id.status_amount_collected);
        status_max_limit = (TextView) findViewById(R.id.status_max_limit);
        status_contri = (TextView) findViewById(R.id.status_your_contri);

//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, json_url_receive_data, (String) null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try {
        Intent intent = getIntent();
        status_name.setText(intent.getExtras().getString("name"));
        status_description.setText(intent.getExtras().getString("description"));
        status_amount_remaining.setText(intent.getExtras().getString("amount_remaining"));
        status_amount_collected.setText(intent.getExtras().getString("amount_collected"));
        status_max_limit.setText(intent.getExtras().getString("max_limit_per_person"));
        status_contri.setText(intent.getExtras().getString("contribution"));
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
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
