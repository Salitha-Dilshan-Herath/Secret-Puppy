package com.iit.secretpuppy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.iit.secretpuppy.alerts.IdentifyBreedCorrectMessage;
import com.iit.secretpuppy.alerts.IdentifyBreedEmtyMessage;
import com.iit.secretpuppy.alerts.IdentifyBreedWrongMessage;
import com.iit.secretpuppy.utility.Config;
import com.iit.secretpuppy.utility.DogCategories;
import com.iit.secretpuppy.utility.Utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class IdentifyDogActivity extends AppCompatActivity implements View.OnClickListener {

    //MARK: UI Components
    private ImageView img1;
    private ImageView img2;
    private ImageView img3;
    private Button btnSubmit;
    private TextView txtBreedName;
    private TextView txtResult;
    private ProgressBar progressBarTimer;
    private TextView txtCountDown;
    private TextView txtScore;

    private ConstraintLayout viwImgBack1;
    private ConstraintLayout viwImgBack2;
    private ConstraintLayout viwImgBack3;

    //MARK: Instance Variable
    private List<String> randomBreedList = new ArrayList<>();
    private List<ImageView> imageViewsList = new ArrayList<>();
    private List<ConstraintLayout> imageViewsBackgroundList = new ArrayList<>();

    private int randomIndex = 0;
    private int selectedIndex = -1;
    private int progress = 10;
    private CountDownTimer progressCountDownTimer;


    //MARK: Life Cycle Methods
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_dog);

        setupView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (progressCountDownTimer != null)
            progressCountDownTimer.cancel();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        //Save score in sharedPref
        Utility.saveLevel2Score(this, Config.CURRENT_SCORE_DOG_ACTIVITY);
        Config.CURRENT_SCORE_DOG_ACTIVITY = 0;
    }

    //MARK: Initial view setup
    private void setupView() {

        //bind ui components
        img1 = findViewById(R.id.imgDog1);
        img2 = findViewById(R.id.imgDog2);
        img3 = findViewById(R.id.imgDog3);
        btnSubmit = findViewById(R.id.btnSubmit);
        txtBreedName = findViewById(R.id.txtBreedName);
        txtResult = findViewById(R.id.txtLevel2Result);
        viwImgBack1 = findViewById(R.id.constriantImgBack1);
        viwImgBack2 = findViewById(R.id.constriantImgBack2);
        viwImgBack3 = findViewById(R.id.constriantImgBack3);
        txtScore = findViewById(R.id.txtScoreDog);

        progressBarTimer = findViewById(R.id.view_dog_progress_bar);
        txtCountDown = findViewById(R.id.txtCountDownDog);

        txtScore.setText("  Score \n " + Config.CURRENT_SCORE_DOG_ACTIVITY);

        btnSubmit.setOnClickListener(this);
        btnSubmit.setEnabled(false);

        //clear image view and random images list
        imageViewsList.clear();
        randomBreedList.clear();

        imageViewsList.add(img1);
        imageViewsList.add(img2);
        imageViewsList.add(img3);

        imageViewsBackgroundList.add(viwImgBack1);
        imageViewsBackgroundList.add(viwImgBack2);
        imageViewsBackgroundList.add(viwImgBack3);

        txtResult.setVisibility(View.INVISIBLE);

        //check current mode
        if (Config.IS_TIMER_MODE) {
            progressBarTimer.setVisibility(View.VISIBLE);
            txtCountDown.setVisibility(View.VISIBLE);
            setupTimer();
        } else {
            progressBarTimer.setVisibility(View.GONE);
            txtCountDown.setVisibility(View.GONE);
        }

        //set images to image view
        for (int i = 0; i < 3; i++) {
            imageViewsList.get(i).setOnClickListener(this);
            setImage(imageViewsList.get(i), i);
        }

        //select random breed to user
        Random ran = new Random();
        randomIndex = ran.nextInt(3);
        String name = randomBreedList.get(randomIndex);
        String show_name = Utility.getShowBreedName(name);
        txtBreedName.setText("Find a " + show_name.toLowerCase() + " breed image");

    }

    //MARK: set image for image view
    private void setImage(ImageView viw, int index) {

        //get random breed
        String randomBreed = DogCategories.getInstance().getRandomBreedName();

        randomBreedList.add(randomBreed);

        System.out.println(randomBreed);

        //get random image according to breed
        String image_name = DogCategories.getInstance().getRandomDogImageName(randomBreed);


        //set image to image view
        viw.setImageDrawable(Utility.getDrawable(this, image_name));

        viw.setTag(index);
    }

    //MARK: submit button click event
    private void gameFunction(Boolean isButtonTap) {


        if (Config.IS_TIMER_MODE && !isButtonTap) {

            IdentifyBreedEmtyMessage identifyBreedEmtyMessage = new IdentifyBreedEmtyMessage(IdentifyDogActivity.this);
            identifyBreedEmtyMessage.show();
            restTimeUp();

            return;
        }

        if (Config.IS_TIMER_MODE) {
            progressBarTimer.setVisibility(View.INVISIBLE);
            progressCountDownTimer.cancel();
            txtCountDown.setVisibility(View.INVISIBLE);
        }

        txtResult.setVisibility(View.VISIBLE);
        txtBreedName.setVisibility(View.INVISIBLE);

        System.out.println(selectedIndex);
        System.out.println(randomIndex);

        if (selectedIndex == randomIndex) {

            IdentifyBreedCorrectMessage identifyBreedCorrectMessage = new IdentifyBreedCorrectMessage(this);
            identifyBreedCorrectMessage.show();

            Config.CURRENT_SCORE_DOG_ACTIVITY += 10;
            txtScore.setText("  Score \n " + Config.CURRENT_SCORE_DOG_ACTIVITY);


            imageViewsBackgroundList.get(randomIndex).setBackgroundColor(Color.GREEN);
            imageViewsBackgroundList.get(randomIndex).startAnimation(Utility.flashingAnimation());

            txtResult.setText("Press Next Button");
            txtResult.setTextColor(Color.WHITE);

        } else {


            IdentifyBreedWrongMessage identifyBreedWrongMessage = new IdentifyBreedWrongMessage(this, imageViewsList.get(randomIndex).getDrawable());
            identifyBreedWrongMessage.show();

            imageViewsBackgroundList.get(selectedIndex).setBackgroundColor(Color.RED);
            imageViewsBackgroundList.get(selectedIndex).startAnimation(Utility.flashingAnimation());

            txtResult.setText("Press Next Button");
            txtResult.setTextColor(Color.WHITE);

            //Vibrate phone when give wrong answer
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(400);

        }


        img1.setClickable(false);
        img2.setClickable(false);
        img3.setClickable(false);

        btnSubmit.setEnabled(true);

    }



    //MARK: reload activity when press next button
    private void reloadActivity() {

        Intent intent = getIntent();
        finish();
        startActivity(intent);

    }

    //MARK: Setup timer for when enable time mode
    private void setupTimer() {
        progressCountDownTimer = new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long l) {

                progress--;
                System.out.println(progress);
                progressBarTimer.setProgress(progress * 100 / (10000 / 1000));
                txtCountDown.setText(progress + "");
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
    private void restTimeUp() {
        txtResult.setVisibility(View.VISIBLE);
        txtBreedName.setVisibility(View.INVISIBLE);
        txtResult.setTextColor(Color.WHITE);
        txtResult.setText("Press Next Button");
        txtCountDown.setVisibility(View.INVISIBLE);
        btnSubmit.setEnabled(true);
    }

    //MARK: click event function
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.imgDog1:

                viwImgBack3.setBackgroundColor(Color.TRANSPARENT);
                viwImgBack2.setBackgroundColor(Color.TRANSPARENT);
                viwImgBack1.setBackgroundColor(Color.YELLOW);
                selectedIndex = (int) img1.getTag();
                gameFunction(true);
                break;
            case R.id.imgDog2:

                viwImgBack1.setBackgroundColor(Color.TRANSPARENT);
                viwImgBack3.setBackgroundColor(Color.TRANSPARENT);
                viwImgBack2.setBackgroundColor(Color.YELLOW);
                selectedIndex = (int) img2.getTag();
                gameFunction(true);
                break;
            case R.id.imgDog3:

                viwImgBack1.setBackgroundColor(Color.TRANSPARENT);
                viwImgBack2.setBackgroundColor(Color.TRANSPARENT);
                viwImgBack3.setBackgroundColor(Color.YELLOW);
                selectedIndex = (int) img3.getTag();
                gameFunction(true);
                break;

            case R.id.btnSubmit:


                reloadActivity();
                break;
        }
    }
}
