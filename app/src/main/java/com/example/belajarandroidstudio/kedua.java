package com.example.belajarandroidstudio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;

public class kedua extends AppCompatActivity {

    private Button btn_kedua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kedua);
        btn_kedua = findViewById(R.id.tombolkedua);
        btn_kedua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(kedua.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
