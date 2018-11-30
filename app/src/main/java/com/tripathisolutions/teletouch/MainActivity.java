package com.tripathisolutions.teletouch;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnTakePicture;
    private ImageView placeHolder;
    private static final int requestCode = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();

        btnTakePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoCaptureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(photoCaptureIntent, requestCode);
            }
        });
    }

    private void findViews() {
        btnTakePicture = findViewById(R.id.btn_take_picture);
        placeHolder = findViewById(R.id.imagePlaceHolder);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(MainActivity.requestCode == requestCode && resultCode == RESULT_OK){

            try {
                Bitmap bitmap = (Bitmap)data.getExtras().get("data");
                placeHolder.setImageBitmap(bitmap);
            } catch (Exception e) {
                Toast.makeText(this, "Error in saving picture", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
