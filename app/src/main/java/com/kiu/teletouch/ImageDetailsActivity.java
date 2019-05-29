package com.kiu.teletouch;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ImageDetailsActivity extends AppCompatActivity {

    DatabaseReference mDatabase;

    private String CHARGER = "charger";
    private String FAN = "fan";
    private String LIGHT = "light";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_details);

        TextView imageDetailsText = findViewById(R.id.imageDetailsTextView);

        String details = getIntent().getExtras().getString("imagedetails");

        imageDetailsText.setText(details);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        Button sendBtn = findViewById(R.id.btnSend);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateFirebaseData(details);
            }
        });

    }

    private void updateFirebaseData(String details) {


        if (details.contains("Light") || details.contains("Bulb") || details.contains("Tubelight")) {
            mDatabase.child(LIGHT).child("status").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String value = (String) dataSnapshot.getValue();
                    if (value.equals("1")) {
                        mDatabase.child(LIGHT).child("status").setValue("0");
                    } else {
                        mDatabase.child(LIGHT).child("status").setValue("1");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        } else if (details.contains("Fan") || details.contains("Ceiling fan")) {
            mDatabase.child(FAN).child("status").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String value = (String) dataSnapshot.getValue();
                    if (value.equals("1")) {
                        mDatabase.child(FAN).child("status").setValue("0");
                    } else {
                        mDatabase.child(FAN).child("status").setValue("1");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        } else if (details.contains("Charger") || details.contains("Socket") || details.contains("Switch") || details.contains("Electrical supply")) {
            mDatabase.child(CHARGER).child("status").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String value = (String) dataSnapshot.getValue();
                    if (value.equals("1")) {
                        mDatabase.child(CHARGER).child("status").setValue("0");
                    } else {
                        mDatabase.child(CHARGER).child("status").setValue("1");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        } else {
            Toast.makeText(this, "Nothing found, Please try again", Toast.LENGTH_SHORT).show();
        }


    }
}
