package com.iit.secretpuppy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.iit.secretpuppy.utility.Utility;

public class LeaderBoard extends AppCompatActivity {

    private TextView txtLevel1;
    private TextView txtLevel2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_board);

        setupview();
    }

    private void setupview (){

        txtLevel1 = findViewById(R.id.txtViewSc1);
        txtLevel2 = findViewById(R.id.txtViewSc2);

        txtLevel1.setText(Utility.getLevel1Score(this) + "" );
        txtLevel2.setText(Utility.getLevel2Score(this) + "" );


    }
}
