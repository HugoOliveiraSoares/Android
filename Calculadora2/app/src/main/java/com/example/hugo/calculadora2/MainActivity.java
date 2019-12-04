package com.example.hugo.calculadora2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    public EditText edtNum1;
    public EditText edtNum2;
    public Button btnS;
    public Button btnSub;
    public Button btnM;
    public Button btnD;
    public EditText txtResult;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNum1   =  (EditText)findViewById(R.id.edtNum1);
        edtNum2   =  (EditText)findViewById(R.id.edtNum2);
        btnS      =  (Button)findViewById(R.id.btnS);
        btnSub    =  (Button)findViewById(R.id.btnSub);
        btnM      =  (Button)findViewById(R.id.btnM);
        btnD      =  (Button)findViewById(R.id.btnD);
        txtResult =  (EditText)findViewById(R.id.txtResult);

        btnS.setOnClickListener(new View.OnClickListener() // SOMA
        {
            @Override
            public void onClick(View v)
            {
                float x, y;
                float soma;

                String num1 = edtNum1.getText().toString();
                String num2 = edtNum2.getText().toString();

                if(TextUtils.isEmpty(num1) || num1.trim().isEmpty())
                {
                    Toast t = Toast.makeText(v.getContext(),"Falta Operando",Toast.LENGTH_SHORT);
                    t.show();
                    edtNum1.requestFocus();
                }else if (TextUtils.isEmpty(num2) || num2.trim().isEmpty())
                {
                    Toast t = Toast.makeText(v.getContext(),"Falta Operando",Toast.LENGTH_SHORT);
                    t.show();
                    edtNum2.requestFocus();
                }else
                {
                    x = (float) Integer.valueOf(edtNum1.getText().toString());
                    y = (float) Integer.valueOf(edtNum2.getText().toString());

                    soma = x + y;
                    txtResult.setText(String.valueOf(soma));
                }
            }
        });

        btnSub.setOnClickListener(new View.OnClickListener() // SUBTRAÇÂO
        {
            @Override
            public void onClick(View v)
            {
                float x, y;
                float sub;

                String num1 = edtNum1.getText().toString();
                String num2 = edtNum2.getText().toString();

                if(TextUtils.isEmpty(num1) || num1.trim().isEmpty())
                {
                    Toast t = Toast.makeText(v.getContext(),"Falta Operando",Toast.LENGTH_SHORT);
                    t.show();
                    edtNum1.requestFocus();
                }else if (TextUtils.isEmpty(num2) || num2.trim().isEmpty())
                {
                    Toast t = Toast.makeText(v.getContext(),"Falta Operando",Toast.LENGTH_SHORT);
                    t.show();
                    edtNum2.requestFocus();
                }else
                {
                    x = (float) Integer.valueOf(edtNum1.getText().toString());
                    y = (float) Integer.valueOf(edtNum2.getText().toString());

                    sub = x - y;

                    txtResult.setText(String.valueOf(sub));
                }
            }
        });

        btnM.setOnClickListener(new View.OnClickListener()  // MULTIPLICAÇÂO
        {
            @Override
            public void onClick(View v)
            {

                String num1 = edtNum1.getText().toString();
                String num2 = edtNum2.getText().toString();
                float x, y;
                float multi;

                if(TextUtils.isEmpty(num1) || num1.trim().isEmpty())
                {
                    Toast t = Toast.makeText(v.getContext(),"Falta Operando",Toast.LENGTH_SHORT);
                    t.show();
                    edtNum1.requestFocus();
                }else if (TextUtils.isEmpty(num2) || num2.trim().isEmpty())
                {
                    Toast t = Toast.makeText(v.getContext(),"Falta Operando",Toast.LENGTH_SHORT);
                    t.show();
                    edtNum2.requestFocus();
                }else
                {
                    x = (float) Integer.valueOf(edtNum1.getText().toString());
                    y = (float) Integer.valueOf(edtNum2.getText().toString());
                    multi = x * y;
                    txtResult.setText( String.valueOf(multi) );
                }
            }
        });

        btnD.setOnClickListener(new View.OnClickListener() // DIVISÂO
        {
            @Override
            public void onClick(View v)
            {
                float x, y;
                float div;

                String num1 = edtNum1.getText().toString();
                String num2 = edtNum2.getText().toString();

                if(TextUtils.isEmpty(num1) || num1.trim().isEmpty())
                {
                    Toast t = Toast.makeText(v.getContext(),"Falta Operando",Toast.LENGTH_SHORT);
                    t.show();
                    edtNum1.requestFocus();
                }else if (TextUtils.isEmpty(num2) || num2.trim().isEmpty())
                {
                    Toast t = Toast.makeText(v.getContext(),"Falta Operando",Toast.LENGTH_SHORT);
                    t.show();
                    edtNum2.requestFocus();
                }else
                {
                    x =  Integer.valueOf(edtNum1.getText().toString());
                    y =  Integer.valueOf(edtNum2.getText().toString());

                    if(y == 0)
                    {
                        Toast t = Toast.makeText(v.getContext(),"Não é possivel dividir por 0",Toast.LENGTH_LONG);
                        t.show();
                        edtNum2.requestFocus();
                    }else
                    {

                        div = x / y;
                        txtResult.setText( String.valueOf(div) );
                    }
                }
            }
        });

    }

}