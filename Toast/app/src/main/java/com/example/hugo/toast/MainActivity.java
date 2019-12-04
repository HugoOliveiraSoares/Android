package com.example.hugo.toast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Button btn;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button)findViewById(R.id.btToast);// recebe uma instancia do tipo Button
        btn.setOnClickListener(new View.OnClickListener() // Cria a função do OnClik
        { // Escuta o evento de clique
            @Override
            public void onClick(View v)// Metodo do OnClik
            {
                Toast t = Toast.makeText(v.getContext(), "toast é muito simples", Toast.LENGTH_SHORT);//Mostra mensagens na tela por um tempo
                t.show(); // Exibe a mensagem
            }
        });
    }
}
