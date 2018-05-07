package com.example.richa.attendance100;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {
    EditText sigupname, siguppass;
    Button registered;
    FirebaseAuth sigupfirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        sigupname = findViewById(R.id.sigupusernameed);
        siguppass = findViewById(R.id.siguppassworded);
        registered =(Button)findViewById(R.id.registeredbtn);
        sigupfirebaseAuth=FirebaseAuth.getInstance();
        registered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog progressDialog = ProgressDialog.show(SignupActivity.this,
                        "please wait", "processing", true);
                (sigupfirebaseAuth.createUserWithEmailAndPassword(sigupname.getText().toString(), siguppass.
                        getText().toString())).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            Toast.makeText(SignupActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                        } else {
                            Log.e("Error", task.getException().toString());
                            Toast.makeText(SignupActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }

        });


    }
}

