package com.example.vacinas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class LoginActivity extends AppCompatActivity {

    Button btnIrCadastro;
    Intent iTelaCadastro1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnIrCadastro = findViewById(R.id.btn_se_cadastrar);
        btnIrCadastro.setOnClickListener(v -> {
            iTelaCadastro1 = new Intent(LoginActivity.this, CadastroActivity.class);
            startActivity(iTelaCadastro1);
        });
    }

}