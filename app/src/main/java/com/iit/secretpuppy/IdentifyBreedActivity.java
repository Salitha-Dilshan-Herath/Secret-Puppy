package com.iit.secretpuppy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.iit.secretpuppy.utility.DogCategories;
import com.iit.secretpuppy.utility.Utility;

import java.util.List;


public class IdentifyBreedActivity extends AppCompatActivity {

    private Spinner spinnerBreed;
    private ImageView imgDog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_breed);

        setupView();
    }



    //MARK: Custom Methods
    private void setupView() {

        DogCategories.getInstance().shuffleBreedList();

        spinnerBreed = findViewById(R.id.spinner_breeds);
        imgDog       = findViewById(R.id.imgDog);

        ArrayAdapter<String> adapter = new ArrayAdapter (this, android.R.layout.simple_spinner_item,  DogCategories.getInstance().getShowBreeds());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBreed.setAdapter(adapter);

        DogCategories.getInstance().shuffleBreedList();
//
//        for (int i=1; i<= 30; i++) {
//
//            String name = DogCategories.getInstance().getRandomDogImageName(DogCategories.getInstance().getBreeds().get(0).toString());
//
//            System.out.println(name);
//            if (i == 10 || i ==20) {
//                System.out.println("=====================================");
//            }
//
//        }

         String name = DogCategories.getInstance().getRandomDogImageName(DogCategories.getInstance().getBreeds().get(0).toString());
         System.out.println(name);
         imgDog.setImageDrawable(Utility.getDrawable(this, name.toLowerCase().replaceAll("\\s","")));


    }


}
