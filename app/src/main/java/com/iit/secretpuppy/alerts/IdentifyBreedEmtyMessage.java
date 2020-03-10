package com.iit.secretpuppy.alerts;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.iit.secretpuppy.R;

public class IdentifyBreedEmtyMessage extends AlertDialog {

    //MARK: UI Components
    private Activity current;
    private Button btnOk;
    private ConstraintLayout constraintLayoutBack;

    public IdentifyBreedEmtyMessage(Activity current) {
        super(current);
        this.current = current;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.identify_breed_empty_message);

        setupView();

    }

    //MARK: Setup view
    private void setupView() {

        this.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        constraintLayoutBack = findViewById(R.id.constraintLayout13);
        btnOk = findViewById(R.id.btnOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                IdentifyBreedEmtyMessage.this.dismiss();
            }
        });

        //shake animation for background view
        final Animation animShake = AnimationUtils.loadAnimation(getContext(), R.anim.shake);
        constraintLayoutBack.startAnimation(animShake);
    }
}
