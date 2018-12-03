package com.kiu.teletouch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ImageDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_details);

        TextView imageDetailsText = findViewById(R.id.imageDetailsTextView);

        String details = getIntent().getExtras().getString("imagedetails");

        imageDetailsText.setText(details);


    }
}
