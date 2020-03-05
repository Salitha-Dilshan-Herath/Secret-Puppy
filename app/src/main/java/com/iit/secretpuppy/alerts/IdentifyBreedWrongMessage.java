package com.iit.secretpuppy.alerts;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.iit.secretpuppy.R;

public class IdentifyBreedWrongMessage extends Dialog {

    private Activity current;
    private Button btnOk;
    private TextView txtCorrect;

    public IdentifyBreedWrongMessage(Activity current) {
        super(current);
        this.current = current;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.identify_breed_wrong_message);

        setupview();

    }

    private void setupview() {

        btnOk = findViewById(R.id.btnOk);
        txtCorrect = findViewById(R.id.txtCorrectName);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                IdentifyBreedWrongMessage.this.dismiss();
            }
        });
    }
}
