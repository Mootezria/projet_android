package com.example.projett;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class recherche extends AppCompatActivity {
    FirebaseDatabase mDatabase;
    DatabaseReference mRef;
    FirebaseStorage mStorage;
    RecyclerView recyclerView;
    InfoAdapter infoAdapter;
    List<InfoModel> infoModelList;
    EditText rechercheEditText;
    TextView searchIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recherche);

        // Initialiser Firebase et RecyclerView
        mDatabase = FirebaseDatabase.getInstance("https://android-projet-b3210-default-rtdb.europe-west1.firebasedatabase.app/");
        mRef = mDatabase.getReference().child("Student");
        mStorage = FirebaseStorage.getInstance();
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialiser les listes et l'adaptateur
        infoModelList = new ArrayList<>();
        infoAdapter = new InfoAdapter(recherche.this, infoModelList);
        recyclerView.setAdapter(infoAdapter);

        // Références aux vues de recherche
        rechercheEditText = findViewById(R.id.rechercheEditText);
        searchIcon = findViewById(R.id.searchIcon);

        // Ajouter un ChildEventListener à la référence Firebase
        mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                InfoModel studentModel = snapshot.getValue(InfoModel.class);
                infoModelList.add(studentModel);
                infoAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {}

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {}

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {}

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });

        // Écouteur pour l'icône de recherche
        searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterResults(rechercheEditText.getText().toString().trim());
            }
        });

        // Écouteur pour le texte de recherche pour mise à jour en temps réel
        rechercheEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterResults(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        // Navigations
        setupNavigationListeners();
    }

    private void filterResults(String query) {
        List<InfoModel> filteredList = new ArrayList<>();
        for (InfoModel item : infoModelList) {
            if (item.getType().toLowerCase().contains(query.toLowerCase()) ||
                    item.getLocalition().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(item);
            }
        }
        infoAdapter.filterList(filteredList);
    }

    @SuppressLint({"MissingInflatedId", "LocalSuppress"})
    private void setupNavigationListeners() {
        TextView pror = findViewById(R.id.compter);
        pror.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(recherche.this, mon_compte.class);
                startActivity(intent);
            }
        });

        TextView favorr = findViewById(R.id.annoncer);
        favorr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(recherche.this, annonce.class);
                startActivity(intent);
            }
        });

        TextView rechr = findViewById(R.id.propietairer);
        rechr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(recherche.this, propertaire.class);
                startActivity(intent);
            }
        });

        TextView hommyr = findViewById(R.id.hommyr);
        hommyr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(recherche.this, home.class);
                startActivity(intent);
            }
        });
    }
}
