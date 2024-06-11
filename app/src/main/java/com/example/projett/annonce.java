package com.example.projett;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class annonce extends AppCompatActivity {
    FirebaseDatabase mDatabase;
    DatabaseReference mRef;
    FirebaseStorage mStorage;
    ImageButton imageButton;
    EditText et1, et2, et3, et4, et5, et6, et7, et8, et9, et10,discription;
    Button btn;
    private static final int Gallery_Code = 1;
    Uri imageUrl = null;
    ProgressDialog progressDialog;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annonce);
        imageButton = findViewById(R.id.imageView);
        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        et3 = findViewById(R.id.et3);
        et4 = findViewById(R.id.et4);
        et5 = findViewById(R.id.et5);
        et6 = findViewById(R.id.et6);
        et7 = findViewById(R.id.et7);
        et8 = findViewById(R.id.et8);
        et9 = findViewById(R.id.et9);
        et10 = findViewById(R.id.et12);
        discription = findViewById(R.id.discription);
        btn = findViewById(R.id.btn);
        mDatabase = FirebaseDatabase.getInstance("https://android-projet-b3210-default-rtdb.europe-west1.firebasedatabase.app/");
        mRef = mDatabase.getReference().child("Student");
        mStorage = FirebaseStorage.getInstance();
        progressDialog = new ProgressDialog(this);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.setType("image/*");
                startActivityForResult(i, Gallery_Code);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String prix = et1.getText().toString().trim();
                String type = et2.getText().toString().trim();
                String size = et3.getText().toString().trim();
                String nb_pieces = et4.getText().toString().trim();
                String nb_chambre = et5.getText().toString().trim();
                String localisation = et6.getText().toString().trim();
                String espace_exter = et7.getText().toString().trim();
                String Accees = et8.getText().toString().trim();
                String equip = et9.getText().toString().trim();
                String annexes = et10.getText().toString().trim();

                if (!prix.isEmpty() && !type.isEmpty() && !size.isEmpty() && !nb_pieces.isEmpty() && !nb_chambre.isEmpty() && !localisation.isEmpty() && !espace_exter.isEmpty() && !Accees.isEmpty() && !equip.isEmpty() && !annexes.isEmpty() && imageUrl != null) {
                    progressDialog.setTitle("Uploading ...");
                    progressDialog.show();
                    StorageReference filepath = mStorage.getReference().child("imagePost").child(imageUrl.getLastPathSegment());
                    filepath.putFile(imageUrl).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    if (task.isSuccessful()) {
                                        Uri downloadUri = task.getResult();
                                        DatabaseReference newPost = mRef.push();
                                        newPost.child("prix").setValue(prix);
                                        newPost.child("type").setValue(type);
                                        newPost.child("size").setValue(size);
                                        newPost.child("nb_pieces").setValue(nb_pieces);
                                        newPost.child("nb_chambre").setValue(nb_chambre);
                                        newPost.child("localisation").setValue(localisation);
                                        newPost.child("espace_exter").setValue(espace_exter);
                                        newPost.child("Accees").setValue(Accees);
                                        newPost.child("equip").setValue(equip);
                                        newPost.child("annexes").setValue(annexes);
                                        newPost.child("image").setValue(downloadUri.toString());
                                        progressDialog.dismiss();
                                        Intent intent = new Intent(annonce.this, recherche.class);
                                        startActivity(intent);
                                    } else {
                                        progressDialog.dismiss();
                                    }
                                }
                            });
                        }
                    });
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Gallery_Code && resultCode == RESULT_OK && data != null) {
            imageUrl = data.getData();
            imageButton.setImageURI(imageUrl);
        }

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        TextView proa = findViewById(R.id.propietairea);
        proa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(annonce.this, propertaire.class);
                startActivity(intent);
            }
        });
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        TextView comptea = findViewById(R.id.comptea);
        comptea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(annonce.this, mon_compte.class);
                startActivity(intent);
            }
        });
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        TextView recha = findViewById(R.id.recherchea);
        recha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(annonce.this, recherche.class);
                startActivity(intent);
            }
        });
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        TextView hommya= findViewById(R.id.hommya);
        hommya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(annonce.this, home.class);
                startActivity(intent);
            }
        });
    }
}
