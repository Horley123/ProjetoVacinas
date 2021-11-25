package com.example.vacinas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;


public class LoginActivity extends AppCompatActivity {

    Button btnIrCadastro;
    Button btnLogar;
    String email, senha;
    Intent iTelaCadastro1, iTelaPrincipal;
    int idUser;
    Cursor c;
    SQLiteDatabase db;
    boolean verifica;



    public void MostrarMensagem(String str) {

        AlertDialog.Builder dialogo = new AlertDialog.Builder(LoginActivity.this);
        dialogo.setTitle("Aviso");
        dialogo.setNeutralButton("OK", null);
        dialogo.setMessage(str);
        dialogo.show();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnIrCadastro = findViewById(R.id.btn_se_cadastrar);
        btnIrCadastro.setOnClickListener( v -> {
            iTelaCadastro1 = new Intent(LoginActivity.this, CadastroActivity.class);
            startActivity(iTelaCadastro1);
        });

        btnLogar= findViewById(R.id.btn_logar);
        btnLogar.setOnClickListener(v -> {

            email = ((EditText) findViewById(R.id.input_login_email)).getText().toString();
            senha = ((EditText) findViewById(R.id.input_login_senha)).getText().toString();

            try {

                db = openOrCreateDatabase("dbVacinas",
                        Context.MODE_PRIVATE, null);

                c = db.query("usuarios", new String[]
                                {"idUser", "email", "senha"}, "email =? AND senha =?", new String[]{email, senha},
                        null, null, null, null);

                if (c.moveToNext()) {
                    if (c.getCount() > 0) {
                        idUser = (c.getInt(0));        // definição do ID retornado do cursor
                       verifica = true;
                    } else {
                        // caso não retornar nenhum usuario do cursor, o retorno da função será nula
                        verifica = false;
                    }
                }
                // finaliza o SQLiteDatabase
                db.close();

            } catch (Exception e) {
                MostrarMensagem("Erro: " + e.toString());
            }

            if(verifica){
                iTelaPrincipal = new Intent(LoginActivity.this, MainActivityUser.class);
                iTelaPrincipal.putExtra("idUser",idUser);
                startActivity(iTelaPrincipal);
            }else{
                MostrarMensagem("Login inválido!");
            }
            });
    }

}