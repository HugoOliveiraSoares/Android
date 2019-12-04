package com.example.hugo.alertdialog;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button) findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                AlertDialog.Builder alerta = new AlertDialog.Builder(MainActivity.this);
                alerta.setTitle("Aviso!");
                alerta
                        .setIcon(R.mipmap.ic_aviso)
                        .setMessage("Mensagem da janela de alerta")
                        .setCancelable(true)
                        .setNegativeButton("Cancelar", null)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this, "Ok", Toast.LENGTH_SHORT)
                                        .show();
                            }
                        });
                AlertDialog alertDialog = alerta.create();
                alertDialog.show();
            }
        });
    }
}
