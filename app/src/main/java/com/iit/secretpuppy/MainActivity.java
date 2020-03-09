package com.iit.secretpuppy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.iit.secretpuppy.utility.Config;

public class MainActivity extends AppCompatActivity {

    //MARK: UI Components
    private Button btnBreed;
    private Button btnDog;
    private Button btnDogSearch;
    private Button btnLeaderBorad;
    private Switch switchTime;

    //MARK: Life Cycle Method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupView();
    }

    //MARK: Setup main view
    private void setupView(){
        btnBreed       = findViewById(R.id.btnBreed);
        btnDog         = findViewById(R.id.btnDog);
        btnDogSearch   = findViewById(R.id.btnDogSearch);
        switchTime     = findViewById(R.id.switchTimeMode);
        btnLeaderBorad = findViewById(R.id.btnLeaderBoard);

        switchTime.setTypeface(ResourcesCompat.getFont(this, R.font.machinegunk_nyqg));

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

        btnDogSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),SearchBreed.class);
                startActivity(intent);
            }
        });

        switchTime.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                Config.IS_TIMER_MODE = b;
            }
        });

        btnLeaderBorad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),LeaderBoard.class);
                startActivity(intent);
            }
        });
    }

}
