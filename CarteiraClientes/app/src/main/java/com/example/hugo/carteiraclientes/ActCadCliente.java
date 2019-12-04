package com.example.hugo.carteiraclientes;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.hugo.carteiraclientes.database.DadosOpenHelper;
import com.example.hugo.carteiraclientes.diretorio.entidades.Cliente;
import com.example.hugo.carteiraclientes.diretorio.repositorio.ClienteRepositorio;
import java.util.regex.Pattern;

public class ActCadCliente extends AppCompatActivity
{

    private EditText edtNome;
    private EditText edtEndereco;
    private EditText edtMail;
    private EditText edtTel;
    private ConstraintLayout layoutContetActCliente;

    private SQLiteDatabase conexao;
    private DadosOpenHelper dadosOpenHelper;
    private ClienteRepositorio clienteRepositorio;

    private Cliente cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_cad_cliente);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edtNome     = (EditText)findViewById(R.id.edtNome);
        edtEndereco = (EditText)findViewById(R.id.edtEndereco);
        edtMail     = (EditText)findViewById(R.id.edtMail);
        edtTel      = (EditText)findViewById(R.id.edtTel);
        layoutContetActCliente = (ConstraintLayout)findViewById(R.id.layoutContetActCliente);

        criarConexao();
        verificaParametro();


    }


    private void verificaParametro()
    {
        Bundle bundle = getIntent().getExtras();

        cliente = new Cliente();

        if (bundle != null && bundle.containsKey("CLIENTE"))
        {
            cliente = (Cliente) bundle.getSerializable("CLIENTE");

            edtNome.setText(cliente.nome);
            edtEndereco.setText(cliente.endereco);
            edtMail.setText(cliente.email);
            edtTel.setText(cliente.tel);
        }
    }

    private void criarConexao()
    {
        try
        {
            dadosOpenHelper = new DadosOpenHelper(this);

            conexao = dadosOpenHelper.getWritableDatabase();

            Snackbar.make(layoutContetActCliente, R.string.message_conexao_sucess, Snackbar.LENGTH_SHORT)
                    .setAction(R.string.action_ok,null).show();

            clienteRepositorio = new ClienteRepositorio(conexao);


        }catch(SQLException ex)
        {
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle(R.string.title_erro);
            dlg.setMessage(ex.getMessage());
            dlg.setNeutralButton(R.string.action_ok, null);
            dlg.show();
        }
    }

    private void confirmar()
    {

        if(validaCampos() == false)
        {

          try
          {
              if (cliente.codigo == 0)
              {
                  clienteRepositorio.inserir(cliente);
              }else
              {
                  clienteRepositorio.alterar(cliente);
              }

              finish();

          }catch(SQLException ex)
          {
              AlertDialog.Builder dlg = new AlertDialog.Builder(this);
              dlg.setTitle(R.string.title_erro);
              dlg.setMessage(ex.getMessage());
              dlg.setNeutralButton(R.string.action_ok, null);
              dlg.show();
          }

        }
    }


    private boolean validaCampos() // Recupera os valores dos Campos
    {
        boolean res = false;

        String nome     = edtNome.getText().toString();
        String endereco = edtEndereco.getText().toString();
        String email    = edtMail.getText().toString();
        String tel      = edtTel.getText().toString();

        cliente.nome     = nome;
        cliente.endereco = endereco;
        cliente.email    = email;
        cliente.tel      = tel;

        if(res = isCampoVazio(nome))
        {
            edtNome.requestFocus();
        }else
            if(res = isCampoVazio(endereco))
            {
                edtEndereco.requestFocus();
            }else
                if(res = !isEmailValido(email))
                {
                    edtMail.requestFocus();
                }else
                    if(res = isCampoVazio(tel))
                    {
                        edtTel.requestFocus();
                    }

        if(res)
        {
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle(R.string.title_dlgAvisso);
            dlg.setMessage(R.string.message_dlgInvalido);
            dlg.setNeutralButton(R.string.action_ok,null); //Fecha a tela de aviso
            dlg.show();
        }

        return res;
    }

    private boolean isCampoVazio(String valor)
    {
        boolean result = ( TextUtils.isEmpty(valor) || valor.trim().isEmpty() ); //Verifica se nao existe nenhum caracter
                                                       // Retira todos os espaços
        return result;
    }

    private boolean isEmailValido(String email) // Verifica se o email é valido
    {
        boolean result = ( !isCampoVazio(email) &&   Patterns.EMAIL_ADDRESS.matcher(email).matches());
                            // Verifica se o campo não está vazio  &&  Testa se o email é valido atraves da classe Patterns
        return result;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) // Cria o menu
    {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_act_cad_cliente,menu);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        switch(id)
        {
            case R.id.action_ok:
                confirmar();
                //Toast.makeText(this, "Botão Ok selecionado", Toast.LENGTH_SHORT).show();
                break;

            case android.R.id.home:
                //Toast.makeText(this, "Botão Cancelar selecionado", Toast.LENGTH_SHORT).show();
                finish();
                break;

            case R.id.action_excluir:
                //Toast.makeText(this, "Botão Cancelar selecionado", Toast.LENGTH_SHORT).show();

                clienteRepositorio.excluir(cliente.codigo);
                finish();

                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
