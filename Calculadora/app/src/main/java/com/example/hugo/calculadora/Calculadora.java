package com.example.hugo.calculadora;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class Calculadora extends AppCompatActivity
{
    private EditText edNum1;
    private EditText edNum2;
    private Button btSoma;
    private TextView edSoma;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculadora);

        edNum1 = (EditText)findViewById(R.id.edNum1);
        edNum2 = (EditText)findViewById(R.id.edNum2);

        btSoma = (Button)findViewById(R.id.btSoma);

        edSoma = (TextView)findViewById(R.id.edSoma);

    }

    public void calc(View v)
    {

        int x, y;
        x = Integer.valueOf(edNum1.getText().toString());
        y = Integer.valueOf(edNum2.getText().toString());

        int soma;
        soma = x + y;

        edSoma.setText( String.valueOf(soma) );

    }


}
