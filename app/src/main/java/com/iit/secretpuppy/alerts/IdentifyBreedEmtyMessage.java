package com.iit.secretpuppy.alerts;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.iit.secretpuppy.R;

public class IdentifyBreedEmtyMessage extends AlertDialog {

    private Activity current;
    private Button btnOk;

    public IdentifyBreedEmtyMessage(Activity current) {
        super(current);
        this.current = current;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.identify_breed_empty_message);

        setupview();

    }

    private void setupview() {

        this.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        btnOk = findViewById(R.id.btnOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                IdentifyBreedEmtyMessage.this.dismiss();
            }
        });
    }
}
