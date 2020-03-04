package com.iit.secretpuppy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.iit.secretpuppy.utility.DogCategories;
import com.iit.secretpuppy.utility.CustomPlaceholderSelectedSpinnerAdapter;
import com.iit.secretpuppy.utility.Utility;


public class IdentifyBreedActivity extends AppCompatActivity {

    private Spinner   spinnerBreed;
    private ImageView imgDog;
    private Button    btnNext;

    private String randomBreed = "";
    private ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_breed);

        setupView();
    }



    //MARK: Custom Methods
    private void setupView() {

        //bind ui component to local varible
        spinnerBreed = findViewById(R.id.spinner_breeds);
        imgDog       = findViewById(R.id.imgDog);
        btnNext      = findViewById(R.id.btnSubmit);



        //load image first time
        loadImage();


        //buttn next click event
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loadImage();
            }
        });


    }


    private void loadImage () {

        setspinnerValues();

        //get random breed
        randomBreed  = DogCategories.getInstance().getRandomBreedName();

        //get random image according to breed
        String image_name  = DogCategories.getInstance().getRandomDogImageName(randomBreed);

        System.out.println(image_name);

        //set image to image view
        imgDog.setImageDrawable(Utility.getDrawable(this, image_name));
    }

    private void setspinnerValues() {

        //set data to spinner
        adapter = new ArrayAdapter (this, android.R.layout.simple_spinner_item,  DogCategories.getInstance().getShowBreeds());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerBreed.setAdapter(
                new CustomPlaceholderSelectedSpinnerAdapter(
                        adapter,
                        R.layout.contact_spinner_row_nothing_selected,
                        this));

    }

}
