package com.example.controlepastorais;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class AppAuthorshipActivity  extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_authorship);
    }

    public void callPastoralListActivity(View view){
        Intent intent = new Intent(this, PastoralListActivity.class);
        startActivity(intent);
    }
}
