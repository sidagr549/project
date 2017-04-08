package com.example.siddhartha.fundcollector;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class NewProgram extends AppCompatActivity {
    //    private Intent intent = getIntent();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_program);
    }

    public void programSociety(View view) {
//        Intent intent = getIntent();
        Intent societyIntent = new Intent(this, _Program.class);
        societyIntent.putExtra("flag" , 0);
//        societyIntent.putExtra("username", intent.getExtras().getString("username"));
        startActivity(societyIntent);
    }

    public void programGovernment(View view) {
//        Intent intent = getIntent();
        Intent govtIntent = new Intent(this, _Program.class);
        govtIntent.putExtra("flag" , 1);
//        govtIntent.putExtra("username", intent.getExtras().getString("username"));
        startActivity(govtIntent);
    }

    public void programIndividual(View view) {
//        Intent intent = getIntent();
        Intent programIntent = new Intent(this, _Program.class);
        programIntent.putExtra("flag" , 2);
//        programIntent.putExtra("username", intent.getExtras().getString("username"));
        startActivity(programIntent);
    }
}
