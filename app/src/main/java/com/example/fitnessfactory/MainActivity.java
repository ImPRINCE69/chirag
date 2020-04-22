 package com.example.fitnessfactory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

 public class MainActivity extends AppCompatActivity {

    RelativeLayout rellay1,rellay2;
    private FirebaseAuth firebaseAuth;

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {

            rellay1.setVisibility(View.VISIBLE);
            rellay2.setVisibility(View.VISIBLE);


        }
    };

    EditText txtEmail,txtPasswd;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rellay1=(RelativeLayout)findViewById(R.id.rellay1);
        rellay2=(RelativeLayout)findViewById(R.id.rellay2);
        handler.postDelayed(runnable,2000);

        Button b2=(Button)findViewById(R.id.signupid);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Register.class);
                startActivity(i);

            }
        });

        Button b3=(Button)findViewById(R.id.forgotid);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ForgotPassword.class);
                startActivity(i);

            }
        });

        txtEmail=(EditText)findViewById(R.id.emailid);
        txtPasswd=(EditText)findViewById(R.id.passwordid);

        firebaseAuth=FirebaseAuth.getInstance();

        Button b1=(Button)findViewById(R.id.loginid);
        b1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String email = txtEmail.getText().toString().trim();
                String password = txtPasswd.getText().toString().trim();

                if (TextUtils.isEmpty(email))
                {
                    Toast.makeText(MainActivity.this,"Please enter your email ",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password))
                {
                    Toast.makeText(MainActivity.this,"Please enter your password ",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(password.length()<6)
                {
                    Toast.makeText(MainActivity.this,"Password is too short",Toast.LENGTH_SHORT).show();

                }


                firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>()
                        {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task)
                            {
                                if (task.isSuccessful())
                                {
                                    Intent i = new Intent(MainActivity.this, Homepage.class);
                                    startActivity(i);

                                    Toast.makeText(MainActivity.this,"Login Successful !",Toast.LENGTH_SHORT).show();

                                } else
                                    {
                                    Toast.makeText(MainActivity.this,"",Toast.LENGTH_SHORT).show();
                                    }

                                // ...
                            }
                        });

                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Intent i = new Intent(MainActivity.this, Homepage.class);
                                    startActivity(i);
                                    Toast.makeText(MainActivity.this,"Login Successful !",Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(MainActivity.this,"Please Sign Up!",Toast.LENGTH_SHORT).show();

                                }

                                // ...
                            }
                        });


            }
        });

    }
}
