package com.example.belajarandroidstudio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.belajarandroidstudio.App.CHANNEL_1_ID;

public class MainActivity extends AppCompatActivity {

    private NotificationManagerCompat notificationManager;


    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    final DatabaseReference tampil = myRef.child("DataAplikasi");

    DatabaseReference data_tampil;
    DatabaseReference data_tampil2;

    TextView nampil;
    TextView nampil2;
    Button btn_1, btn_3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_1 = findViewById(R.id.tombol);
        btn_3 = findViewById(R.id.tombolketiga);
        nampil = findViewById(R.id.belajarandroid);
        nampil2 = findViewById(R.id.data2);
        notificationManager = NotificationManagerCompat.from(this);

        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MainActivity.this, kedua.class);
                startActivity(intent);
            }
        });

        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tampil.setValue("ON");
            }
        });

        data_tampil = FirebaseDatabase.getInstance().getReference();
        data_tampil.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String nampilin_data = dataSnapshot.child("DataAplikasi2").getValue().toString();
                nampil.setText(nampilin_data);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        data_tampil2 = FirebaseDatabase.getInstance().getReference();
        data_tampil2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String nampilin_data2 = dataSnapshot.child("DataAplikasi3").getValue().toString();
                nampil2.setText(nampilin_data2);
                int nilai = Integer.parseInt(nampil2.getText().toString());
                if (nilai > 20){
                    Toast.makeText(getApplicationContext(),
                            "   WARNING! Melebihi Batas", Toast.LENGTH_LONG).show();
                    Notification notification = new NotificationCompat.Builder (MainActivity.this, CHANNEL_1_ID)
                            .setSmallIcon(R.drawable.ic_announcement_black_24dp)
                            .setContentTitle("Warning!")
                            .setContentText("Nilai Melebihi Batas")
                            .setPriority(NotificationCompat.PRIORITY_HIGH)
                            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                            .build();
                    notificationManager.notify(1, notification);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
