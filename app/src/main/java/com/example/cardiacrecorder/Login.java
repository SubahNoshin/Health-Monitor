package com.example.cardiacrecorder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    EditText passwordet, emailet;
    Button loginbtn;
    TextView regbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        passwordet=findViewById(R.id.passwordet);
        emailet=findViewById(R.id.emailet);
        loginbtn=findViewById(R.id.login_btn);
        regbtn=findViewById(R.id.regbtn);

        loginbtn.setOnClickListener((v)->loginuser());
        regbtn.setOnClickListener((v)->startActivity(new Intent(Login.this, Sign_up.class)));
    }
    void loginuser(){

        String email=emailet.getText().toString();
        String password= passwordet.getText().toString();


        boolean isvalidated = validateData(email, password);
        if(!isvalidated){
            return;
        }
        loginAccountInFirebase(email, password);
    }
    void loginAccountInFirebase(String email, String password){
        FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    //login is successful
                   if(firebaseAuth.getCurrentUser().isEmailVerified()){

                       startActivity(new Intent(Login.this, MainActivity.class));
                   }else{
                       Utility.showToast(Login.this, "Email is not verified. Please verify your email");
                   }
                }
                else{
                    Utility.showToast(Login.this, task.getException().getLocalizedMessage());
                }
            }
        });
    }
    boolean validateData(String email, String pass)
    {
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailet.setError("Email is invalid");
            return false;
        }
        if(pass.length()<6){
            passwordet.setError("Password length is invalid");
            return false;
        }
        return  true;

    }
}