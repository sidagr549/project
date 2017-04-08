package com.example.siddhartha.fundcollector;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class Chanda extends AppCompatActivity {

//    private Intent intent = getIntent();
    private static final String TAG = "Chanda";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chanda);
//        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//        String string = preferences.getString("UserName" , "username invalid");
//        Log.v(TAG , "Chanda " + string);
    }

    public void newProgram(View view){
//        Intent intent = getIntent();
        Intent new_program = new Intent(this , NewProgram.class);
//        Log.v("TAG" ,intent.getExtras().getString("username"));
//        new_program.putExtra("username" , intent.getExtras().getString("username"));
        startActivity(new_program);
    }

    public void newInvestment(View view){
//        Intent intent = getIntent();
//        Log.v("TAG" ,intent.getExtras().getString("username"));
        Intent new_investment = new Intent(this , NewInvestment.class);
//        new_investment.putExtra("username" , intent.getExtras().getString("username"));
        startActivity(new_investment);
    }

    public void previousStatus(View view){
//        Intent intent = getIntent();
//        Log.v("TAG" ,intent.getExtras().getString("username"));
        Intent previousStatus = new Intent(this ,YourPayments.class);
//        previousStatus.putExtra("username" , intent.getExtras().getString("username"));
        startActivity(previousStatus);
//        startActivity(new Intent(this , YourPayments.class));
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
