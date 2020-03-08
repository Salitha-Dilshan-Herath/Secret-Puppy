package com.iit.secretpuppy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.iit.secretpuppy.alerts.IdentifyBreedCorrectMessage;
import com.iit.secretpuppy.alerts.IdentifyBreedEmtyMessage;
import com.iit.secretpuppy.alerts.IdentifyBreedWrongWithDetailMessage;
import com.iit.secretpuppy.utility.Config;
import com.iit.secretpuppy.utility.DogCategories;
import com.iit.secretpuppy.utility.adapters.CustomPlaceholderSelectedSpinnerAdapter;
import com.iit.secretpuppy.utility.Utility;


public class IdentifyBreedActivity extends AppCompatActivity {

    private Spinner     spinnerBreed;
    private ImageView   imgDog;
    private Button      btnNext;
    private TextView    txtResult;
    private ProgressBar progressBarTimer ;
    private TextView    txtCountDown;
    private ConstraintLayout constraintLayoutTimer;

    private String randomBreed = "";
    private int stateOfBtnNext = 0;  //0 is user can submit the answer , 1 is user can get next images
    private int progress       = 10;
    private ArrayAdapter<String> adapter;
    private CountDownTimer progressCountDownTimer;
    private Boolean idRotated = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_breed);

        setupView();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (progressCountDownTimer != null)
             progressCountDownTimer.cancel();
    }

    //MARK: Custom Methods
    private void setupView() {

        if (idRotated) {
            idRotated = false;
            return;
        }

        //bind ui component to local variables
        spinnerBreed          = findViewById(R.id.spinner_breeds);
        imgDog                = findViewById(R.id.imgDog);
        btnNext               = findViewById(R.id.btnSubmit);
        txtResult             = findViewById(R.id.txtResult);
        progressBarTimer      = findViewById(R.id.view_progress_bar);
        txtCountDown          = findViewById(R.id.txtCountDown);
        constraintLayoutTimer = findViewById(R.id.constraintLayoutBreedTimer);

        txtResult.setVisibility(View.INVISIBLE);
        spinnerBreed.setVisibility(View.VISIBLE);
        progressBarTimer.setProgress(progress);

        //load image first time
        loadImage();


        if(Config.IS_TIMER_MODE) {
            constraintLayoutTimer.setVisibility(View.VISIBLE);
            setupTimer();
        }else {
            constraintLayoutTimer.setVisibility(View.GONE);
        }

        //button next click event
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                gameFunction(true);
            }
        });


    }

    private void loadImage () {

        setSpinerValues();

        //get random breed
        randomBreed  = DogCategories.getInstance().getRandomBreedName();

        //get random image according to breed
        String image_name  = DogCategories.getInstance().getRandomDogImageName(randomBreed);

        System.out.println(image_name);

        //set image to image view
        imgDog.setImageDrawable(Utility.getDrawable(this, image_name));


    }

    private void setSpinerValues() {

        //set data to spinner
        adapter = new ArrayAdapter (this, android.R.layout.simple_spinner_item,  DogCategories.getInstance().getShowBreeds());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerBreed.setAdapter(
                new CustomPlaceholderSelectedSpinnerAdapter(
                        adapter,
                        R.layout.contact_spinner_row_nothing_selected,
                        this));

    }

    private void gameFunction (Boolean isButtonTap) {
        if (stateOfBtnNext == 0){

            //validate user is select a breed or not
            if (spinnerBreed.getSelectedItem() == null){

                if  (Config.IS_TIMER_MODE && !isButtonTap){

                    IdentifyBreedEmtyMessage identifyBreedEmtyMessage = new IdentifyBreedEmtyMessage(IdentifyBreedActivity.this);
                    identifyBreedEmtyMessage.show();
                    restTimeUp();

                }else {
                    Toast.makeText(getApplicationContext(),"Please select a breed", Toast.LENGTH_SHORT).show();

                }
                return;
            }

            if (Config.IS_TIMER_MODE) {
                progressBarTimer.setVisibility(View.INVISIBLE);
                progressCountDownTimer.cancel();
                txtCountDown.setVisibility(View.INVISIBLE);
            }


            //selected breed convert to string
            String selected_name = (String) spinnerBreed.getSelectedItem();
            selected_name        = selected_name.replaceAll("\\s+","");

            txtResult.setVisibility(View.VISIBLE);
            spinnerBreed.setVisibility(View.INVISIBLE);

            //checked user answer is correct or wrong
            if (selected_name.toLowerCase().equals(randomBreed)) {
                IdentifyBreedCorrectMessage identifyBreedCorrectMessage = new IdentifyBreedCorrectMessage(IdentifyBreedActivity.this);
                identifyBreedCorrectMessage.show();

                txtResult.setText("Press Next Button");
                txtResult.setTextColor(Color.GREEN);

            }else {

                IdentifyBreedWrongWithDetailMessage identifyBreedWrongMessage = new IdentifyBreedWrongWithDetailMessage(IdentifyBreedActivity.this, Utility.getShowBreedName(randomBreed));
                identifyBreedWrongMessage.show();
                txtResult.setText("Press Next Button");
                txtResult.setTextColor(Color.RED);

            }

            btnNext.setText("Next");
            stateOfBtnNext = 1;

        }
        else if (stateOfBtnNext == 1){
            reloadActivity();

        }
    }

    //MARK: reload activity when press next button
    private void reloadActivity (){

        Intent intent = getIntent();
        finish();
        startActivity(intent);
        stateOfBtnNext = 0;

    }

    //MARK: Setup timer for when enable time mode
    private void setupTimer() {
        progressCountDownTimer = new CountDownTimer(10000,1000) {
            @Override
            public void onTick(long l) {

                progress--;
                System.out.println(progress);
                progressBarTimer.setProgress(progress*100/(10000/1000));
                txtCountDown.setText( progress  + "");
            }

            @Override
            public void onFinish() {

                progress++;
                progressBarTimer.setProgress(0);
                gameFunction(false);
            }
        };

        progressCountDownTimer.start();
    }

    //MARK: Rest method for when time's up
    private void restTimeUp(){
        txtResult.setVisibility(View.VISIBLE);
        spinnerBreed.setVisibility(View.INVISIBLE);
        txtResult.setTextColor(Color.GREEN);
        txtResult.setText("Press Next Button");
        txtCountDown.setVisibility(View.INVISIBLE);
        btnNext.setText("Next");
        stateOfBtnNext = 1;
    }


}
