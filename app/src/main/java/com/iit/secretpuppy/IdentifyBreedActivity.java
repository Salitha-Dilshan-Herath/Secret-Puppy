package com.iit.secretpuppy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.iit.secretpuppy.Alerts.IdentifyBreedCorrectMessage;
import com.iit.secretpuppy.Alerts.IdentifyBreedWrongWithDetailMessage;
import com.iit.secretpuppy.utility.DogCategories;
import com.iit.secretpuppy.utility.CustomPlaceholderSelectedSpinnerAdapter;
import com.iit.secretpuppy.utility.Utility;


public class IdentifyBreedActivity extends AppCompatActivity {

    private Spinner   spinnerBreed;
    private ImageView imgDog;
    private Button    btnNext;
    private TextView  txtResult;

    private String randomBreed = "";
    private ArrayAdapter<String> adapter;
    private int stateOfBtnNext = 0;  //0 is user can submit the answer , 1 is user can get next images

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
        txtResult    = findViewById(R.id.txtResult);

        txtResult.setVisibility(View.INVISIBLE);
        spinnerBreed.setVisibility(View.VISIBLE);

        //load image first time
        loadImage();


        //buttn next click event
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (stateOfBtnNext == 0){

                    //validate user is select a breed or not
                    if (spinnerBreed.getSelectedItem() == null){
                        Toast.makeText(getApplicationContext(),"Please select a breed", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    //selected breed convert to string
                    String selected_name = (String) spinnerBreed.getSelectedItem();

                    txtResult.setVisibility(View.VISIBLE);
                    spinnerBreed.setVisibility(View.INVISIBLE);

                    //checked user answer is correct or wrong
                    if (selected_name.toLowerCase().equals(randomBreed)) {
                        IdentifyBreedCorrectMessage identifyBreedCorrectMessage = new IdentifyBreedCorrectMessage(IdentifyBreedActivity.this);
                        identifyBreedCorrectMessage.show();
                        txtResult.setText("Your answer is correct");
                        txtResult.setTextColor(Color.GREEN);

                    }else {

                        IdentifyBreedWrongWithDetailMessage identifyBreedWrongMessage = new IdentifyBreedWrongWithDetailMessage(IdentifyBreedActivity.this, randomBreed);
                        identifyBreedWrongMessage.show();
                        txtResult.setText("Your answer is wrong");
                        txtResult.setTextColor(Color.RED);

                    }

                    btnNext.setText("Next");
                    stateOfBtnNext = 1;

                }else if (stateOfBtnNext == 1){

                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                    stateOfBtnNext = 0;
                }
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
