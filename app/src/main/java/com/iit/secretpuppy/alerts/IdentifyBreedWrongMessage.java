package com.iit.secretpuppy.alerts;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.iit.secretpuppy.R;

public class IdentifyBreedWrongMessage extends AlertDialog {

    //MARK: UI Components
    private Activity  current;
    private Button    btnOk;
    private TextView  txtCorrect;
    private ImageView imgCorrect;

    //MARK: Instance Variables
    private Drawable correctImage;

    public IdentifyBreedWrongMessage(Activity current, Drawable correctImage) {
        super(current);
        this.current = current;
        this.correctImage = correctImage;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.identify_breed_wrong_message);

        setupView();

    }

    //MARK: Setup view
    private void setupView() {

        this.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        btnOk      = findViewById(R.id.btnOk);
        txtCorrect = findViewById(R.id.txtCorrectName);
        imgCorrect = findViewById(R.id.imgCorrect);

        imgCorrect.setImageDrawable(correctImage);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                IdentifyBreedWrongMessage.this.dismiss();
            }
        });
    }
}
