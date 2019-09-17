package br.com.lvbfontes.avaliasus;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import br.com.lvbfontes.avaliasus.Modelo.unidadeSus;
import br.com.lvbfontes.avaliasus.Modelo.unidadeSusCompleta;

public class CadastrarUnidadeSUS extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private DatabaseReference mDatabaseUser;
    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;


    private EditText edtNomeUnidade, edtCep, edtEndereco, edtNumero, edtBairro, edtCidade;
    private Button btnSalvar, btnVoltar;

    private String nomeUnidade, cep, endereco, bairro, cidade;
    private int numero;
    private String firebaseKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_unidade_sus);

        mDatabase = FirebaseDatabase.getInstance().getReference("estabelecimentoSaude");

        mAuth = FirebaseAuth.getInstance();
        mCurrentUser =mAuth.getCurrentUser();
        mDatabaseUser = FirebaseDatabase.getInstance().getReference().child("users").child(mCurrentUser.getUid());

        edtNomeUnidade = findViewById(R.id.edt_NomeUnidadeSUS);
        edtCep = findViewById(R.id.edt_cep);
        edtEndereco = findViewById(R.id.edT_endereco);
        edtNumero = findViewById(R.id.edT_numero);
        edtBairro = findViewById(R.id.edT_bairro);
        edtCidade = findViewById(R.id.edT_cidade);

        btnSalvar = findViewById(R.id.btn_salvarUnidade);
        btnVoltar = findViewById(R.id.btn_Voltar);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nomeUnidade = edtNomeUnidade.getText().toString().trim();
                cep = edtCep.getText().toString().trim();
                endereco = edtEndereco.getText().toString().trim();
                numero = Integer.parseInt(edtNumero.getText().toString().trim());
                bairro = edtBairro.getText().toString().trim();
                cidade = edtCidade.getText().toString().trim();

                if(!TextUtils.isEmpty(nomeUnidade) && !TextUtils.isEmpty(cep) && !TextUtils.isEmpty(endereco)
                && numero>0 && !TextUtils.isEmpty(bairro) && !TextUtils.isEmpty(cidade)) {

                    pushUnidadeSUS(nomeUnidade, cep, endereco, numero, bairro, cidade);

                } else {
                    Toast.makeText(CadastrarUnidadeSUS.this, "Nenhum campo deve ficar em branco", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private void pushUnidadeSUS(final String nomeUnidade, String cep, final String endereco, final int numero, final String bairro, final String cidade) {

        mDatabaseUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                final unidadeSusCompleta unidade = new unidadeSusCompleta(nomeUnidade, 10, 10, endereco, numero, bairro, cidade,
                        mCurrentUser.getUid(), dataSnapshot.child("nome").getValue().toString());

                final DatabaseReference newPost = mDatabase.push();
                firebaseKey = newPost.getKey();

                newPost.setValue(unidade)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(CadastrarUnidadeSUS.this, "Unidade SUS adicionada!", Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(CadastrarUnidadeSUS.this, MenuPrincipalActivity.class));
                                    finish();
                                } else {
                                    Toast.makeText(CadastrarUnidadeSUS.this, "Erro ao inserir dados", Toast.LENGTH_LONG).show();
                                }
                            }
                        });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
