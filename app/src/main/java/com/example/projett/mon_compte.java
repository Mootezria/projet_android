package com.example.projett;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class mon_compte extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mon_compte);

        TextView conditionTextView = findViewById(R.id.condition);
        conditionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.seloger.com/Conditions_Generales_d_Utilisation.html?app=1&fbclid=IwZXh0bgNhZW0CMTAAAR3xnmMysKvC9JNbDlKA36K9NrQUM-X8Vo_xpu-3CZe81cmUrhCQvrdUGOM_aem_ARX3VUS_YKq8RcfZVI_Tw3CT9FswBQkje2gGG1r0gZPRIju_mZDbcuyN4KIjoGhrYn5aeRZCoLmFKkhi_JG7ro73";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        TextView protection = findViewById(R.id.protection);
        protection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.seloger.com/CGU_politique_de_confidentialite.html?app=1&fbclid=IwZXh0bgNhZW0CMTAAAR3uEagMgSgKGForLTmom6FGHZ44QHLsKPhLVtNvGrVei6Ugye52sYl0yoo_aem_ARWutR4Gs3oXwmv7a-Yl4DAoOdR8uJFYw4uUuBjGquRNgXu7nVjhAzK235RsY9rAdVDT4lCkQCAvwgVtKbEMRx7g";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });


        TextView conn = findViewById(R.id.conn);

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            // Si l'utilisateur est connecté
            conn.setText("Se déconnecter");
            conn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Déconnectez l'utilisateur
                    FirebaseAuth.getInstance().signOut();
                    // Changez le texte de la TextView en "Me connecter/M'inscrire"
                    conn.setText("Me connecter/M'inscrire");
                    // Affichez un message de déconnexion réussie
                    Toast.makeText(mon_compte.this, "Déconnexion réussie.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(mon_compte.this, se_connecter.class);
                    startActivity(intent);

                }
            });
        } else {
            // Si l'utilisateur n'est pas connecté
            conn.setText("Me connecter/M'inscrire");
            conn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mon_compte.this, se_connecter.class);
                    startActivity(intent);
                }
            });
        }



        TextView pro = findViewById(R.id.propietaire);
        pro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mon_compte.this, propertaire.class);
                startActivity(intent);
            }
        });  TextView suggestion = findViewById(R.id.suggestion);
        suggestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mon_compte.this, probleme.class);
                startActivity(intent);
            }
        });
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        TextView anno = findViewById(R.id.annonce);
        anno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mon_compte.this, annonce.class);
                startActivity(intent);
            }
        });
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        TextView rech = findViewById(R.id.recherche);
        rech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mon_compte.this, recherche.class);
                startActivity(intent);
            }
        });
        TextView hommy= findViewById(R.id.hommy);
        hommy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mon_compte.this, home.class);
                startActivity(intent);
            }
        });
    }
}
