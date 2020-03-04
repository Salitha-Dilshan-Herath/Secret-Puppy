package com.iit.secretpuppy.Alerts;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.iit.secretpuppy.R;

public class IdentifyBreedCorrectMessage extends Dialog {

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

        btnOk = findViewById(R.id.btnOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                IdentifyBreedCorrectMessage.this.dismiss();
            }
        });
    }
}
