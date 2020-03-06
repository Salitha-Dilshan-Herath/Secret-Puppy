package com.iit.secretpuppy.alerts;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.iit.secretpuppy.R;

public class IdentifyBreedCorrectMessage extends AlertDialog {

    private Activity current;
    private Button btnOk;

    public IdentifyBreedCorrectMessage(Activity current) {
        super(current);
        this.current = current;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.identify_breed_correct_message);

        setupview();

    }

    private void setupview() {

        this.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        btnOk = findViewById(R.id.btnOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                IdentifyBreedCorrectMessage.this.dismiss();
            }
        });
    }
}
