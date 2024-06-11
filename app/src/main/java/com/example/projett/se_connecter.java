package com.example.projett;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class se_connecter extends AppCompatActivity {
    EditText emaillog;
    EditText passlog;
    Button btnlog;
    FirebaseAuth mAuth;
    TextView to_register;
    ProgressBar progressBarlog;
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(), home.class);
            startActivity(intent);
            finish();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_se_connecter);
        emaillog = findViewById(R.id.emaillog);
        mAuth = FirebaseAuth.getInstance();
        passlog = findViewById(R.id.passlog);
        btnlog = findViewById(R.id.btnlog);
        progressBarlog = findViewById(R.id.progressbarh);
        to_register = findViewById(R.id.to_register);
        to_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Register.class);
                startActivity(i);
                finish();
            }
        });
         btnlog.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 progressBarlog.setVisibility(View.VISIBLE);
                 String email , password;
                 email = String.valueOf(emaillog.getText());
                 password = String.valueOf(passlog.getText());
                 if (TextUtils.isEmpty(email)){
                     Toast.makeText(se_connecter.this,"Entrer email" , Toast.LENGTH_LONG).show();
                     return;
                 }
                 if (TextUtils.isEmpty(password)){
                     Toast.makeText(se_connecter.this,"Entrer password" , Toast.LENGTH_LONG).show();
                     return;
                 }
                 mAuth.signInWithEmailAndPassword(email, password)
                         .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                             @Override
                             public void onComplete(@NonNull Task<AuthResult> task) {
                                 progressBarlog.setVisibility(View.GONE);
                                 if (task.isSuccessful()) {
                                     Toast.makeText(se_connecter.this, "Login Successful.",
                                             Toast.LENGTH_SHORT).show();
                                     Intent intent = new Intent(getApplicationContext(), home.class);
                                     intent.putExtra("login_success", true);
                                     startActivity(intent);
                                     finish();
                                 }else {
                                     Toast.makeText(se_connecter.this, "Authentication failed.",
                                             Toast.LENGTH_SHORT).show();

                                 }
                             }
                         });


             }
         });
    }
}