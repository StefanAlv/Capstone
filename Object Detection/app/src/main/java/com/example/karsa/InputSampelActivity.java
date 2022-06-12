package com.example.karsa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.karsa.data.DialogForm;
import com.example.karsa.fragment.ProfileFragment;

public class InputSampelActivity extends AppCompatActivity {

    Button btnInputSampel;
    ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_sampel);

        btnBack = findViewById(R.id.btnBack);
        btnInputSampel = findViewById(R.id.btnInput);

        btnInputSampel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogForm dialogForm = new DialogForm();
                dialogForm.show(getSupportFragmentManager(), "form");
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InputSampelActivity.this, ProfileFragment.class);
                startActivity(intent);
            }
        });

    }
}