package com.example.siddhartha.fundcollector;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class NewInvestment extends AppCompatActivity {

    SharedPreferences investmentPreferences;
    SharedPreferences.Editor editor;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_investment);
    }

    public void investment(View view){
//        Intent intent = getIntent();
//        Intent Intent = new Intent(this , _Investment.class);
        Intent Intent = new Intent(this , _Investment_cum_Status.class);
        investmentPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = investmentPreferences.edit();
        editor.putInt("reference" , 1);
        editor.commit();
//        societyIntent.putExtra("username" , intent.getExtras().getString("username"));
        startActivity(Intent);
    }

//    public void investmentGovernment(View view) {
////        Intent intent = getIntent();
//        Intent govtIntent = new Intent(this , _Investment.class);
////        govtIntent.putExtra("username" , intent.getExtras().getString("username"));
//        startActivity(govtIntent);
//    }
//
//    public void investmentIndividual(View view) {
////        Intent intent = getIntent();
//        Intent indiIntent = new Intent(this , _Investment.class);
////        indiIntent.putExtra("username" , intent.getExtras().getString("username"));
//        startActivity(indiIntent);
//    }
}
