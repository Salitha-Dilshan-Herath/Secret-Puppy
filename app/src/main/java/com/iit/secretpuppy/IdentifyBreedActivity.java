package com.iit.secretpuppy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class IdentifyBreedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_breed);

//        StringBuffer sb = new StringBuffer();
//        BufferedReader br = null;
//        try {
//            br = new BufferedReader(new InputStreamReader(getResources().openRawResource(R.raw.dog)));
//            String temp;
//            while ((temp = br.readLine()) != null)
//                sb.append(temp);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                br.close(); // stop reading
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
    }
}
