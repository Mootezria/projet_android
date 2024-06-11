package com.example.projett;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity5 extends AppCompatActivity {

    TextView detailDesc, detailType, detailSize, detailNbChambre, detailNbPieces, detailAcc, detailAnn, detailEquip, detailEspac, detailPrix;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail5);

        detailDesc = findViewById(R.id.desc);
        detailType = findViewById(R.id.type);
        detailPrix = findViewById(R.id.prix);
        detailSize = findViewById(R.id.size);
        detailNbChambre = findViewById(R.id.nbp);
        detailNbPieces = findViewById(R.id.nbc);
        detailAcc = findViewById(R.id.acc);
        detailAnn = findViewById(R.id.ann);
        detailEquip = findViewById(R.id.equip);
        detailEspac = findViewById(R.id.espac);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            detailType.setText(bundle.getString("type"));
            detailPrix.setText(bundle.getString("prix")); // Assurez-vous que "prix" est une chaîne
            detailSize.setText(bundle.getString("size"));
            detailNbChambre.setText(bundle.getString("nbp"));
            detailNbPieces.setText(bundle.getString("nbc"));
            detailDesc.setText(bundle.getString("desc"));
            detailAcc.setText(bundle.getString("acc"));
            detailAnn.setText(bundle.getString("ann"));
            detailEquip.setText(bundle.getString("equip"));
            detailEspac.setText(bundle.getString("espac"));
        }
    }
}
