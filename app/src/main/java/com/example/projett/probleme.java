package com.example.projett;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

public class probleme extends AppCompatActivity {
    FirebaseDatabase mDatabase;
    DatabaseReference mRef;
    FirebaseStorage mStorage;
    EditText etc, discription1, lmail;
    Button btnEnvoyer;
    ProgressDialog progressDialog;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_probleme);

        etc = findViewById(R.id.etc);
        discription1 = findViewById(R.id.discription1);
        lmail = findViewById(R.id.lmail);
        btnEnvoyer = findViewById(R.id.btnEnvoyer);

        mDatabase = FirebaseDatabase.getInstance("https://android-projet-b3210-default-rtdb.europe-west1.firebasedatabase.app/");
        mRef = mDatabase.getReference().child("Messages");
        mStorage = FirebaseStorage.getInstance();
        progressDialog = new ProgressDialog(this);

        btnEnvoyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String typeMessage = etc.getText().toString().trim();
                String message = discription1.getText().toString().trim();
                String email = lmail.getText().toString().trim();

                if (!typeMessage.isEmpty() && !message.isEmpty() && !email.isEmpty()) {
                    progressDialog.setTitle("Envoi en cours...");
                    progressDialog.show();

                    DatabaseReference newMessage = mRef.push();
                    newMessage.child("typeMessage").setValue(typeMessage);
                    newMessage.child("message").setValue(message);
                    newMessage.child("email").setValue(email);

                    progressDialog.dismiss();
                    Intent intent = new Intent(probleme.this, home.class);
                    startActivity(intent);
                } else {
                    // Afficher un message d'erreur ou notifier l'utilisateur de compléter tous les champs
                }
            }
        });
    }
}
