package com.iit.secretpuppy.alerts;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.iit.secretpuppy.R;

public class IdentifyBreedWrongWithDetailMessage extends Dialog {

    private Activity current;
    private Button btnOk;
    private TextView txtCorrect;

    private String correctBreed;

    public IdentifyBreedWrongWithDetailMessage(Activity current , String breed) {
        super(current);
        this.current = current;
        this.correctBreed = breed;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.identify_breed_wrong_message_detail);

        setupview();

    }

    private void setupview() {

        btnOk = findViewById(R.id.btnOk);
        txtCorrect = findViewById(R.id.txtCorrectName);

        txtCorrect.setText("Correct answer is " + correctBreed);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                IdentifyBreedWrongWithDetailMessage.this.dismiss();
            }
        });
    }
}
