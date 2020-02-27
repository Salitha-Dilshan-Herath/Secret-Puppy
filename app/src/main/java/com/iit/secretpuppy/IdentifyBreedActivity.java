package com.iit.secretpuppy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.iit.secretpuppy.utility.DogCategories;



public class IdentifyBreedActivity extends AppCompatActivity {

    Spinner spinnerBreed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_breed);

        spinnerBreed = findViewById(R.id.spinner_breeds);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, DogCategories.getInstance().getBreeds());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBreed.setAdapter(adapter);

        DogCategories.getInstance().shuffleBreedList();

        for (int i=1; i<= 30; i++) {

           String name = DogCategories.getInstance().getRandomDogImageName(DogCategories.getInstance().getBreeds().get(0).toString());

           System.out.println(name);
           if (i == 10 || i ==20) {
               System.out.println("=====================================");
           }

        }

    }
}
