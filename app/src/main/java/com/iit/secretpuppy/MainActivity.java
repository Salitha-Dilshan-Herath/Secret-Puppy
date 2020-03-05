package com.iit.secretpuppy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnBreed;
    private Button btnDog;
    private Button btnDogSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBreed     = findViewById(R.id.btnBreed);
        btnDog       = findViewById(R.id.btnDog);
        btnDogSearch = findViewById(R.id.btnDogSearch);

        btnBreed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),IdentifyBreedActivity.class);
                startActivity(intent);
            }
        });

        btnDog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),IdentifyDogActivity.class);
                startActivity(intent);
            }
        });
    }


}
