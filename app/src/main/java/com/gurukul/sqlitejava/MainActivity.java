package com.gurukul.sqlitejava;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.net.ContentHandler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText etName,etEmail,etPass;
        RadioGroup rdG;
        final CheckBox chkAnd, chkJava, chkKotlin;
        final Spinner spCity;
        final RadioButton selectedBtn;

        Button btnSignIn = (Button) findViewById(R.id.btnSignIn);

        etName =(EditText) findViewById(R.id.etName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPass =(EditText) findViewById(R.id.etPass);

        rdG = (RadioGroup) findViewById(R.id.rdGroup);

        chkAnd = (CheckBox) findViewById(R.id.chkAnd);
        chkJava = (CheckBox) findViewById(R.id.chkJava);
        chkKotlin = (CheckBox) findViewById(R.id.chkKotlin);

        spCity = (Spinner) findViewById(R.id.spCt);

        int selectedId = rdG.getCheckedRadioButtonId();

        selectedBtn = (RadioButton) findViewById(selectedId);


        final SQLiteDatabase dBase = openOrCreateDatabase("Dbsignin", Context.MODE_PRIVATE,null);
        dBase.execSQL("create table if not exists tblInfo (_id integer primary key autoincrement,name varchar(20), email varchar(20), pass varchar(20)/*, gender varchar(10)*/, lang varchar(40), city varchar(10))");

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues cv = new ContentValues();
                cv.put("name",etName.getText().toString());
                cv.put("email",etEmail.getText().toString());
                cv.put("pass",etPass.getText().toString());
                /*String text = selectedBtn.getText().toString();
                cv.put("gender",text);*/
                String lang = "";
                if(chkAnd.isChecked()){
                    lang = lang + " " + chkAnd.getText().toString();
                }
                if (chkJava.isChecked()){
                    lang = lang + " " + chkJava.getText().toString();
                }
                if (chkKotlin.isChecked()){
                    lang = lang + " " + chkKotlin.getText().toString();
                }
                cv.put("lang",lang);
                cv.put("city",spCity.getSelectedItem().toString());
                Long res = dBase.insert("tblInfo",null,cv);

                if(res != -1L){
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                            startActivity(intent);
                        }
                    }).setMessage("Data Inserted").show();
                }else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    }).setMessage("Data Insertion is failed...").show();
                }
            }
        });
    }
}
