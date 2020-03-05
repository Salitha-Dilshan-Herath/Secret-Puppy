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
import com.iit.secretpuppy.utility.ImageSlideAdapter;
import com.iit.secretpuppy.utility.Utility;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class SearchBreed extends AppCompatActivity {

    private AutoCompleteTextView txtSearch;
    private ViewPager viewPagerSlider;
    private Button btnSearch;
    private Button btnStop;

    private ArrayList<Drawable> imgArray = new ArrayList<Drawable>();
    private int currentPage = 0;
    private Timer swipeTimer = new Timer();
    final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_breed);

        setupView();
    }

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
                setupSlider();
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(swipeTimer != null) {
                    swipeTimer.cancel();
                    swipeTimer = null;
                }

            }
        });
    }

    private void setupSlider(){

        imgArray.clear();
        String searchText = txtSearch.getText().toString();

        if (searchText == null || searchText.isEmpty()) {
            Toast.makeText(getApplicationContext(),"Please insert search text", Toast.LENGTH_SHORT).show();
            return;
        }


        searchText  = searchText.replaceAll("\\s+","").toLowerCase();

        if (!DogCategories.getInstance().getBreeds().contains(searchText)) {
            Toast.makeText(getApplicationContext(),"Invalid breed name", Toast.LENGTH_SHORT).show();
            return;
        }


        for (int i=0; i<10; i++) {
            String imageName = DogCategories.getInstance().getRandomDogImageName(searchText);

            imgArray.add(Utility.getDrawable(SearchBreed.this,imageName));
        }

        viewPagerSlider.setAdapter(new ImageSlideAdapter(SearchBreed.this, imgArray));

        final Runnable Update = new Runnable() {
            @Override
            public void run() {
                if (currentPage == 10){
                    currentPage = 0;
                }
                viewPagerSlider.setCurrentItem(currentPage++, true);
            }
        };


        swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 2500, 2500);
    }
}
