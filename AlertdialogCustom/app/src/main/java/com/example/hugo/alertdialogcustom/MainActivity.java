package com.example.hugo.alertdialogcustom;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnShow = (Button)findViewById(R.id.btnShow);
        btnShow.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
                final View mView = getLayoutInflater().inflate(R.layout.dialog_login, null);
                final EditText mEmail = (EditText) mView.findViewById(R.id.etEmail);
                final EditText mPassword = (EditText) mView.findViewById(R.id.etPass);
                Button mLogin = (Button) mView.findViewById(R.id.btnLogin);

                mLogin.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        if(!mEmail.getText().toString().isEmpty()
                                && !mPassword.getText().toString().isEmpty())
                        {
                            Toast.makeText(MainActivity.this,
                                    "Logado",
                                    Toast.LENGTH_SHORT).show();

                        }else
                        {
                            Toast.makeText(MainActivity.this,
                                    "Preencha todos os campos",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();
            }
        });
    }
}



