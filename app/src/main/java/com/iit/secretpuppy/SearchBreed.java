package com.iit.secretpuppy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.iit.secretpuppy.utility.DogCategories;
import com.iit.secretpuppy.utility.adapters.ImageSlideAdapter;
import com.iit.secretpuppy.utility.Utility;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class SearchBreed extends AppCompatActivity {

    //MARK: UI Components
    private AutoCompleteTextView txtSearch;
    private ViewPager viewPagerSlider;
    private Button btnSearch;
    private Button btnStop;

    //MARK: Instance variables
    private ArrayList<Drawable> imgArray = new ArrayList<Drawable>();
    private int currentPage              = 0;
    private final Handler handler        = new Handler();
    private Timer swipeTimer             = new Timer();
    private ImageSlideAdapter imageSlideAdapter;

    //MARK: Life cycle methods
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_breed);

        setupView();
    }

    //MARK: Initial View
    private void setupView(){

        txtSearch       =  findViewById(R.id.txtSearch);
        btnSearch       = findViewById(R.id.btnBreedSearch);
        viewPagerSlider = findViewById(R.id.viewPagerSlider);
        btnStop         = findViewById(R.id.btnStop);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, DogCategories.getInstance().getShowBreeds());

        txtSearch.setThreshold(0);
        txtSearch.setAdapter(adapter);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Utility.hideSoftKeyboard(SearchBreed.this);
                setupSlider();
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btnStop.setEnabled(false);
                stopTimmer();

            }
        });

        btnStop.setEnabled(false);
    }

    //MARK: Initial the slider
    private void setupSlider(){

        //call reset function
        resetView();

        //get type text
        String searchText = txtSearch.getText().toString();

        //validate user input empty or null
        if (searchText == null || searchText.isEmpty()) {
            viewPagerSlider.setVisibility(View.INVISIBLE);
            Toast.makeText(getApplicationContext(),"Please insert search text", Toast.LENGTH_SHORT).show();
            return;
        }

        //formatting the search string
        searchText  = searchText.replaceAll("\\s+","").toLowerCase();

        //check search text is available breed or not
        if (!DogCategories.getInstance().getBreeds().contains(searchText)) {

            viewPagerSlider.setVisibility(View.INVISIBLE);
            Toast.makeText(getApplicationContext(),"Invalid breed name", Toast.LENGTH_SHORT).show();

            if (imageSlideAdapter != null) {
                imageSlideAdapter.notifyDataSetChanged();
            }

            return;
        }

        btnStop.setEnabled(true);
        viewPagerSlider.setVisibility(View.VISIBLE);

        //create image slider array
        for (int i=0; i<10; i++) {
            String imageName = DogCategories.getInstance().getRandomDogImageName(searchText);
            imgArray.add(Utility.getDrawable(SearchBreed.this,imageName));
        }


        imageSlideAdapter = new ImageSlideAdapter(SearchBreed.this, imgArray);

        //create adapter to slider
        viewPagerSlider.setAdapter(imageSlideAdapter);


        //start automatic slider
        startTimmer();

    }

    //MARK: Timer start function
    private void startTimmer () {

        //create runnable thread
        final Runnable Update = new Runnable() {
            @Override
            public void run() {
                if (currentPage == 10){
                    currentPage = 0;
                }
                viewPagerSlider.setCurrentItem(currentPage++, true);
            }
        };


        //create timer and runnable thread
        swipeTimer = new Timer();
        swipeTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 0, 5000);


    }

    //MARK: Timer stop function
    private void stopTimmer () {

        if(swipeTimer != null) {
            swipeTimer.cancel();
            swipeTimer = null;
        }

    }

    //MARK: Reset view
    private void resetView(){
        stopTimmer();
        btnStop.setVisibility(View.VISIBLE);
        imgArray.clear();
    }
}
