package com.example.vacinas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.*;
import android.view.*;


public class MainActivityUser extends AppCompatActivity {

    Button btnTelaCadastro;
    Button btnTelaLogin;
    Intent iTelaCadastro;
    Intent iTelaLogin;
    Cursor c, d;
    SQLiteDatabase db;
    AppCompatTextView nome, email, idade, ubs, marca, dose, data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user);

        btnTelaCadastro = findViewById(R.id.btnIr);
        btnTelaLogin =  findViewById(R.id.btnIrLogin);

        btnTelaLogin.setOnClickListener(v -> {
            iTelaLogin = new Intent(MainActivityUser.this, LoginActivity.class);
            startActivity(iTelaLogin);
        });
        btnTelaCadastro.setOnClickListener(v -> {
            iTelaCadastro = new Intent(MainActivityUser.this, CadastroActivity.class);
            startActivity(iTelaCadastro);
        });

        nome = findViewById(R.id.row_nome);
        email =  findViewById(R.id.row_email);
        idade =  findViewById(R.id.row_idade);
        ubs = findViewById(R.id.row_ubs);
        marca = findViewById(R.id.row_marca);
        data =  findViewById(R.id.row_data);
        dose =  findViewById(R.id.row_dose);

        try {
            db = openOrCreateDatabase("dbVacinas",
                    Context.MODE_PRIVATE, null);

            c = db.query("usuarios", new String[]
                            {"nome", "email", "idade"},
                    null, null, null, null, null);


            if (c.getCount() > 0) {
                c.moveToFirst();

                nome.setText(c.getString(0));
                email.setText(c.getString(1));
                idade.setText(c.getString(2));


            } else {
                String frase = "Nenhum Registro";

                nome.setText(frase);
                email.setText(frase);
                idade.setText(frase);
            }
            d = db.query("Vacina", new String[]
                            {"nomeUbs", "marca", "data", "dose"},
                    null, null, null, null, null);


            if (d.getCount() > 0) {
                d.moveToFirst();

                ubs.setText(d.getString(0));
                marca.setText(d.getString(1));
                data.setText(d.getString(2));
                dose.setText(d.getString(3));


            } else {
                String frase = "Nenhum Registro";
                ubs.setText(frase);
                marca.setText(frase);
                data.setText(frase);
                dose.setText(frase);

            }
        } catch (Exception e) {
            MostrarMensagem("Erro: " + e.toString());

        }
    }
    public void MostrarMensagem(String str) {

        AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivityUser.this);
        dialogo.setTitle("Aviso");
        dialogo.setNeutralButton("OK", null);
        dialogo.setMessage(str);
        dialogo.show();
    }
}