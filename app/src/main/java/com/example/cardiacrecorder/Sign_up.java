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

public class Sign_up extends AppCompatActivity {

    EditText usernameet, passwordet, emailet, confirmpasswordet;
    Button regbtn;
    TextView loginbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        usernameet= findViewById(R.id.usernameet);
        emailet = findViewById(R.id.emailet);
        passwordet = findViewById(R.id.passwordet);
        confirmpasswordet= findViewById(R.id.confirmPasswordet);
        regbtn= findViewById(R.id.regbtn);
        loginbtn= findViewById(R.id.loginbtn);

        regbtn.setOnClickListener(v-> signup() );

        loginbtn.setOnClickListener(v-> finish());
    }
    void signup(){

        String username=usernameet.getText().toString();
        String email=emailet.getText().toString();
        String password= passwordet.getText().toString();
        String confirmpass = confirmpasswordet.getText().toString();

        boolean isvalidated = validateData(username, email, password, confirmpass);
        if(!isvalidated){
            return;
        }
        createAccountInFirebase(email, password);

    }
    void createAccountInFirebase(String email, String password){
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(Sign_up.this,
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //create account done
                           Utility.showToast(Sign_up.this, "Successfuly registered");
                           firebaseAuth.getCurrentUser().sendEmailVerification();
                            firebaseAuth.signOut();
                            finish();
                        }
                        else{
                            //failure
                            Utility.showToast(Sign_up.this, task.getException().getLocalizedMessage());
                        }
                    }
                });
    }

    boolean validateData(String username, String email, String pass, String confirmpass)
    {
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailet.setError("Email is invalid");
            return false;
        }
        if(pass.length()<6){
            passwordet.setError("Password length is invalid");
            return false;
        }
        if(!pass.equals(confirmpass)){
            confirmpasswordet.setError(("password not matched"));
            return false;
        }
        return  true;

    }

}