package com.example.fitnessfactory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    EditText txt_fullName,txt_Username,txt_email,txt_password,txt_confirm;
    Button btn_register;
    RadioButton radioGenderMale,radioGenderFemale;

    DatabaseReference databaseReference;
    String gender="";

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        txt_fullName=(EditText)findViewById(R.id.Fullname);
        txt_Username=(EditText)findViewById(R.id.Username);
        txt_email=(EditText)findViewById(R.id.Email);
        txt_password=(EditText)findViewById(R.id.Password);
        txt_confirm=(EditText)findViewById(R.id.ConfirmPass);

        btn_register=(Button)findViewById(R.id.register);
        radioGenderMale=(RadioButton)findViewById(R.id.radioMale);
        radioGenderFemale=(RadioButton)findViewById(R.id.radioFemale);

        databaseReference= FirebaseDatabase.getInstance().getReference("Members");
        firebaseAuth=FirebaseAuth.getInstance();

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String fullName=txt_fullName.getText().toString();
                final String userName=txt_Username.getText().toString();
                final String email=txt_email.getText().toString();
                String password=txt_password.getText().toString();
                String confirm=txt_confirm.getText().toString();

                if(radioGenderMale.isChecked())
                {
                    gender="Male";
                }
                if(radioGenderFemale.isChecked())
                {
                    gender="Female";
                }

                if (TextUtils.isEmpty(fullName))
                {
                    Toast.makeText(Register.this,"Please enter your Full Name ",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(userName))
                {
                    Toast.makeText(Register.this,"Please enter Username ",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(email))
                {
                    Toast.makeText(Register.this,"Please enter your email ",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password))
                {
                    Toast.makeText(Register.this,"Please enter your Password ",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(confirm))
                {
                    Toast.makeText(Register.this,"Please enter your Confirm Password ",Toast.LENGTH_SHORT).show();
                    return;
                }


                firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    members information = new members(
                                            fullName,
                                            userName,
                                            email,
                                            gender
                                    );

                                    FirebaseDatabase.getInstance().getReference("Members")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(information)
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    Toast.makeText(Register.this,"Registration Completed !! ",Toast.LENGTH_SHORT).show();
                                                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                                }
                                            });
                                } else {

                                }

                                // ...
                            }
                        });

            }
        });
    }
}
