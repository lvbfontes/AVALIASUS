package br.com.lvbfontes.avaliasus;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import br.com.lvbfontes.avaliasus.Acesso.LoginActivity;

public class MenuPrincipalActivity extends AppCompatActivity {

    private String userId;

    private Button btnConsultarEstabelecimentoSUS, btnSelecionarNoMapa, btnSair, btnCadastrarUnidadeSUS;
    private TextView txtSaudacaoNome;

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        userId = user.getUid();

        mDatabase = FirebaseDatabase.getInstance().getReference().child("users").child(userId);

        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                txtSaudacaoNome.setText("Olá " + dataSnapshot.child("nome").getValue().toString() + "!");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        btnConsultarEstabelecimentoSUS = findViewById(R.id.menu_btn_consultar);
        btnSelecionarNoMapa = findViewById(R.id.menu_btn_selecionarNoMapa);
        btnSair = findViewById(R.id.menu_btn_SignOut);
        btnCadastrarUnidadeSUS = findViewById(R.id.menu_btn_CadastroUnid);

        btnCadastrarUnidadeSUS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuPrincipalActivity.this, CadastrarUnidadeSUS.class);
                startActivity(intent);
            }
        });

        txtSaudacaoNome = findViewById(R.id.menu_txtV_Nome);

        btnConsultarEstabelecimentoSUS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuPrincipalActivity.this, ConsultarUnidadeSUSActivity.class);
                startActivity(intent);
            }
        });

        btnSelecionarNoMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuPrincipalActivity.this, SelecionarUnidadeMapaActivity.class);
                startActivity(intent);
            }
        });

        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();
            }
        });
    }

    private void signOut() {
        mAuth.signOut();
        updateUI(null);
    }

    private void updateUI(FirebaseUser currentUser) {
        if (currentUser == null) {
            Intent intent = new Intent (MenuPrincipalActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
