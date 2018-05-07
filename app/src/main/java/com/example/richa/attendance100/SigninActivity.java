package com.example.richa.attendance100;

import android.app.ProgressDialog;
import android.content.Intent;
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
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class SigninActivity extends AppCompatActivity {
    EditText signinname, signinppass;
    Button login;
    FirebaseAuth signinfirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        signinname=findViewById(R.id.siginusernameed);
        signinppass=findViewById(R.id.siginpassworded);
        login=findViewById(R.id.siginloginbtn);
        signinfirebaseAuth=FirebaseAuth.getInstance();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                final ProgressDialog progressDialog=ProgressDialog.show(SigninActivity.this,
                        "please wait..","processing",true);
                (signinfirebaseAuth.signInWithEmailAndPassword(signinname.getText().toString(),signinppass.getText().toString()))
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>()
                        {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressDialog.dismiss();
                                if(task.isSuccessful())
                                {
                                    Toast.makeText(SigninActivity.this,"Login Successfull",Toast.LENGTH_SHORT).show();
                                    Intent intent=new Intent(SigninActivity.this,Student.class);
                                    startActivity(intent);
                                }
                                else
                                {
                                    Log.e("Error",task.getException().toString());
                                    Toast.makeText(SigninActivity.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}
