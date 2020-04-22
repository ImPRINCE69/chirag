package com.example.fitnessfactory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FullBody extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_body);

        Button b1=(Button)findViewById(R.id.button11);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FullBody.this, Beginner.class);
                startActivity(i);

            }
        });


        Button b2=(Button)findViewById(R.id.button12);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FullBody.this, Intermediate.class);
                startActivity(i);

            }
        });

        Button b3=(Button)findViewById(R.id.button13);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FullBody.this, Advance.class);
                startActivity(i);

            }
        });
    }
}
