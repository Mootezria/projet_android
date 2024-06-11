package com.example.projett;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.LayoutInflaterCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class home extends AppCompatActivity {
    RecyclerView.LayoutManager layoutManager;
    FirebaseDatabase mDatabase;
    DatabaseReference mRef;
    FirebaseStorage mStorage;
    LinearLayoutManager linearLayoutManager;

    RecyclerView recyclerView;
    InfoAdapter infoAdapter;
    List<InfoModel> infoModelList;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        linearLayoutManager = new LinearLayoutManager(home.this , LinearLayoutManager.HORIZONTAL,false);
        mDatabase = FirebaseDatabase.getInstance("https://android-projet-b3210-default-rtdb.europe-west1.firebasedatabase.app/");
        mRef = mDatabase.getReference().child("Student");
        mStorage = FirebaseStorage.getInstance();
        recyclerView = findViewById(R.id.recyclerview2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        infoModelList=new ArrayList<InfoModel>();
        infoAdapter=new InfoAdapter(home.this,infoModelList);
        recyclerView.setAdapter(infoAdapter);
        mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                InfoModel studentModel = snapshot.getValue(InfoModel.class);
                infoModelList.add(studentModel);
                infoAdapter.notifyDataSetChanged();
            }


            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        TextView proa = findViewById(R.id.comptea);
        proa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this, mon_compte.class);
                startActivity(intent);
            }
        });
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        TextView favora = findViewById(R.id.annoncea);
        favora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this, annonce.class);
                startActivity(intent);
            }
        });
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        TextView recha = findViewById(R.id.recherchea);
        recha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this, recherche.class);
                startActivity(intent);
            }
        });
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        TextView hommya = findViewById(R.id.hommya);
        hommya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this, home.class);
                startActivity(intent);
            }
        });



        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        TextView btnhooooo = findViewById(R.id.btnhooooo);
        btnhooooo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this,recherche.class);
                startActivity(intent);
            }
        });
    }}