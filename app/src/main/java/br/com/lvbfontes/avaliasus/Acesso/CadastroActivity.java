package br.com.lvbfontes.avaliasus.Acesso;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.com.lvbfontes.avaliasus.MenuPrincipalActivity;
import br.com.lvbfontes.avaliasus.R;

public class CadastroActivity extends AppCompatActivity {

    private static final String TAG = "CadastroActivity";
    private EditText edtEmail, edtSenha;
    private EditText edtNome, edtSobrenome;
    private Button btnCadastrar;

    private String email, password, nome, sobrenome;
    private String userId;

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("users");

        edtEmail = findViewById(R.id.cadastro_edt_email);
        edtSenha = findViewById(R.id.cadastro_edt_senha);
        edtNome = findViewById(R.id.cadastro_edt_nome);
        edtSobrenome = findViewById(R.id.cadastro_edt_sobrenome);

        btnCadastrar = findViewById(R.id.cadastro_btn_Cadastrar);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nome = edtNome.getText().toString().trim();
                sobrenome = edtSobrenome.getText().toString().trim();
                email = edtEmail.getText().toString().trim();
                password = edtSenha.getText().toString().trim();

                if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password) && password.length() >= 6) {
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(CadastroActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d(TAG, "createUserWithEmail:success");

                                        FirebaseUser user = mAuth.getCurrentUser();
                                        userId = user.getUid();

                                        writeNewUser(userId, nome, sobrenome, email);

                                        updateUI(user);
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                        Toast.makeText(CadastroActivity.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                        updateUI(null);
                                    }
                                }
                            });
                } else {
                    Toast.makeText(CadastroActivity.this, "Digitar email e senha!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void updateUI(FirebaseUser currentUser) {
        if (currentUser != null) {
            Intent intent = new Intent (CadastroActivity.this, MenuPrincipalActivity.class);
            startActivity(intent);
            finish();        }
    }

    private void writeNewUser(String userId, String nome, String sobrenome, String email) {

        DatabaseReference currentUserDb = mDatabase.child(userId);

        currentUserDb.child("nome").setValue(nome);
        currentUserDb.child("sobrenome").setValue(sobrenome);
        currentUserDb.child("email").setValue(email);
    }
}
