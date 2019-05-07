package com.gurukul.sqlitejava;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText etLEmail = (EditText) findViewById(R.id.etLEmail);
        final EditText etLPass = (EditText) findViewById(R.id.etLPass);

        Button btnLogin = (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etLEmail.getText().toString();
                String pass = etLPass.getText().toString();

                SQLiteDatabase dBase = openOrCreateDatabase("Dbsignin", Context.MODE_PRIVATE,null);

                String args[] = new String[]{name,pass};
                Cursor res = dBase.query("tblInfo",null,"email=? and pass=?",new String[]{etLEmail.getText().toString(),etLPass.getText().toString()},null,null,null);

                int a = res.getCount();

                if(a>0){
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                    builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).setMessage("Login Successful...").show();
                }else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                    builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).setMessage("Login Failed...").show();
                }
            }
        });
    }
}
