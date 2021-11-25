package com.example.vacinas;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditarActivity extends AppCompatActivity {

    Button btalterar;

    int indice;

    int idUser;

    Cursor c, d;
    SQLiteDatabase db;
    EditText email, senha, nome, ubs, marca, dose, data ,idade;
    DialogInterface.OnClickListener diAlteraInformacoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

       Bundle bundle = new Bundle();
        if(getIntent().hasExtra("idUser")){
            bundle = getIntent().getExtras();
            idUser = bundle.getInt("idUser");
        }
        nome = findViewById(R.id.input_nomea) ;
        email =  findViewById(R.id.input_emaila) ;
        idade =  findViewById(R.id.input_idadea) ;
        senha =  findViewById(R.id.input_senha2a) ;

        ubs =  findViewById(R.id.input_UBSa) ;
        marca = findViewById(R.id.input_marcaa) ;
        dose =  findViewById(R.id.input_dosea) ;
        data = findViewById(R.id.input_dataa) ;
        int idVac;

        btalterar = (Button) findViewById(R.id.btn_editar);
        try {
            db = openOrCreateDatabase("dbVacinas",
                    Context.MODE_PRIVATE, null);

            c = db.query("usuarios", new String[]
                            {"idUser", "nome", "idade", "email", "senha"},
                    null, null, null, null, null);





            if (c.getCount() > 0) {
                c.moveToFirst();



                nome.setText(c.getString(1));
                idade.setText(c.getString(2));
                email.setText(c.getString(3));
                senha.setText(c.getString(4));

                ubs.setText(c.getString(0));
                marca.setText(c.getString(1));
                data.setText(c.getString(3));
                dose.setText(c.getString(2));
                idVac = (c.getInt(4));



            } else {
                String frase = "Nenhum Registro";

                nome.setText(frase);
                email.setText(frase);
                idade.setText(frase);
            }
            d = db.query("Vacina", new String[]
                            {"nomeUbs", "marca", "data", "dose","idVac"},
                    "idR =?", new String[]{String.valueOf(idUser)}, null, null, null);
            if (d.getCount() > 0) {
                d.moveToFirst();




                ubs.setText(c.getString(0));
                marca.setText(c.getString(1));
                data.setText(c.getString(3));
                dose.setText(c.getString(2));
                idVac = (c.getInt(4));



            } else {
                String frase = "Nenhum Registro";

                nome.setText(frase);
                email.setText(frase);
                idade.setText(frase);
            }
        } catch (Exception e) {


        }
        diAlteraInformacoes = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                String Enome = nome.getText().toString();
                String Eemail = email.getText().toString();
                String Esenha = senha.getText().toString();
                String Eidade = idade.getText().toString();

                String Eubs = ubs.getText().toString();
                String Emarca = marca.getText().toString();
                String Edose = dose.getText().toString();
                String Edata = data.getText().toString();


                try {
                    ContentValues valor = new ContentValues();
                    ContentValues valor2 = new ContentValues();

                    valor.put("nome", Enome);
                    valor.put("idade", Eidade);
                    valor.put("email", Eemail);
                    valor.put("senha", Esenha);

                    valor2.put("nomeUbs", Eubs);
                    valor2.put("marca", Emarca);
                    valor2.put("dose", Edose);
                    valor2.put("data", Edata);

                    db.update("usuarios", valor, "idUser = " + idUser, null);

                    db.update("Vacina", valor2, "idVac = " + idUser, null);



//                    db.execSQL("update usuarios set nome = '" + Enome + "', " +
//                            "senha= '" + Esenha + "' , email= '" + Eemail +
//                            "', idade= '"+Eidade+"' where idUser = " + 0);
                    MostrarMensagem("Dados alterados com sucesso.");
                    Intent iTelaEditar = new Intent(EditarActivity.this, MainActivityUser.class);
                   iTelaEditar.putExtra("idUser", idUser);
                    startActivity(iTelaEditar);
                } catch (Exception e) {
                    MostrarMensagem("error: " + e.toString());
                }
            }




        };

        btalterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialogo = new AlertDialog.Builder(
                        EditarActivity.this);
                dialogo.setTitle("confirmar");
                dialogo.setNegativeButton("Não", null);
                dialogo.setPositiveButton("sim", diAlteraInformacoes);
                dialogo.setMessage("deseja aleterar as informações ");
                dialogo.show();
            }
        });
    }
    public void MostrarMensagem(String str) {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(
                EditarActivity.this);
        dialogo.setTitle("Aviso");
        dialogo.setNeutralButton("OK", null);
        dialogo.setMessage(str);
        dialogo.show();
    }
}