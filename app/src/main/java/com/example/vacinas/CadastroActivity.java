package com.example.vacinas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

public class CadastroActivity extends AppCompatActivity {

    ImageButton btVoltar;
    Button btcadastrar;
    SQLiteDatabase db;
    EditText email, senha, nome, ubs, marca, dose, data ,idade;
    Integer idUser = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        btcadastrar = findViewById(R.id.btn_cadastrar);
        btVoltar = findViewById(R.id.btn_voltar);
        btVoltar.setOnClickListener(v -> CadastroActivity.this.finish());


        btcadastrar.setOnClickListener(view -> {
            try {

                db = openOrCreateDatabase("dbVacinas", Context.MODE_PRIVATE, null);
                db.execSQL("create table if not exists " +
                        "usuarios  (idUser integer primary key " +
                        "autoincrement, nome text not null, idade text" +
                        " not null , " + "email text not null, senha text)");

                db.execSQL("create table if not exists Vacina(idVac integer primary key autoincrement," +
                        "nomeUbs text,marca text, dose text, data text, idR text )");


                nome = findViewById(R.id.input_nome) ;
                email =  findViewById(R.id.input_email) ;
                idade =  findViewById(R.id.input_idade) ;
                senha =  findViewById(R.id.input_senha2) ;


                ubs =  findViewById(R.id.input_UBS) ;
                marca = findViewById(R.id.input_marca) ;
                dose =  findViewById(R.id.input_dose) ;
                data = findViewById(R.id.input_data) ;


                try{
                    db = openOrCreateDatabase("dbVacinas", Context.MODE_PRIVATE, null);
                } catch (Exception e){
                    MostrarMensagem("Erro: " + e.toString());
                }


                String Cnome = nome.getText().toString();
                String Cemail = email.getText().toString();
                String Csenha = senha.getText().toString();
                String Cidade = idade.getText().toString();

                try{
                    db.execSQL(" insert into usuarios( nome,  email,  senha,idade) " +
                            "values ('"+ Cnome + "', '"+  Cemail +"' , '" + Csenha +"', '"+ Cidade+"')");

                    MostrarMensagem("Dados deusuario  Cadastrados com sucesso");
                } catch (Exception e){  MostrarMensagem("Erro: " + e.toString());}

                String Cubs = ubs.getText().toString();
                String Cmarca = marca.getText().toString();
                String Cdose = dose.getText().toString();
                String Cdata = data.getText().toString();
                String idR = idUser.toString();

                try{


                    db.execSQL(" insert into Vacina( nomeUbs,  marca,  dose,data, idR) " +
                            "values ('"+ Cubs + "', '"+  Cmarca +"' , ' " + Cdose +"', '"+ Cdata+"','" + idR +"')");
                    MostrarMensagem("Dados da vacina  Cadastrados com sucesso");
                    idUser++;
                } catch (Exception e){  MostrarMensagem("Erro: " + e.toString());}



                AlertDialog.Builder dialogo = new
                        AlertDialog.Builder(CadastroActivity.this);
                dialogo.setTitle("Aviso")
                        .setMessage("Banco de dados criado com sucesso !!!!")
                        .setNeutralButton("OK", null)
                        .show();

                Intent iTelaCadastrado = new Intent(CadastroActivity.this, MainActivityUser.class);
                startActivity(iTelaCadastrado);
            } catch (Exception e) {
                AlertDialog.Builder dialogo = new
                        AlertDialog.Builder(CadastroActivity.this);
                dialogo.setTitle("Aviso")
                        .setMessage(e+"Falha ao cadastra, tente novamente")
                        .setNeutralButton("OK", null)
                        .show();
            }
        });
    }
    public  void MostrarMensagem( String  str){

        AlertDialog.Builder dialogo = new AlertDialog.Builder(CadastroActivity.this);
        dialogo.setTitle("Aviso");
        dialogo.setNeutralButton("OK", null);
        dialogo.setMessage(str);
        dialogo.show();
    }


}