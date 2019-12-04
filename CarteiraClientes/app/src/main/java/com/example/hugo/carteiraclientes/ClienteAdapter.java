package com.example.hugo.carteiraclientes;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hugo.carteiraclientes.diretorio.entidades.Cliente;

import org.w3c.dom.Text;

import java.util.List;

public class ClienteAdapter extends RecyclerView.Adapter<ClienteAdapter.ViewHolderCliente>
{
    private List<Cliente> dados;

    public ClienteAdapter(List<Cliente> dados)
    {
        this.dados = dados;
    }

    @Override
    public ClienteAdapter.ViewHolderCliente onCreateViewHolder(ViewGroup parent, int viewType)
    {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.linha_clientes, parent, false);

        ViewHolderCliente holderCliente = new ViewHolderCliente(view, parent.getContext());

        return holderCliente;

    }

    @Override
    public void onBindViewHolder(ClienteAdapter.ViewHolderCliente holder, int position)
    {
        if ((dados != null) && (dados.size() > 0) )
        {
            Cliente cliente = dados.get(position);

            holder.txtnome.setText(cliente.nome);
            holder.txttelefone.setText(cliente.tel);
        }


    }

    @Override
    public int getItemCount() {
        return dados.size();
    }


    public class ViewHolderCliente extends RecyclerView.ViewHolder
    {

        public TextView txtnome;
        public TextView txttelefone;

        public ViewHolderCliente(View itemView, final Context context)
        {
            super(itemView);

            txtnome     = (TextView) itemView.findViewById(R.id.txtNome);
            txttelefone = (TextView) itemView.findViewById(R.id.txtTel);

            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {

                    if(dados.size() > 0)
                    {
                        Cliente cliente = dados.get(getLayoutPosition());

                        Intent it = new Intent(context, ActCadCliente.class);
                        it.putExtra("CLIENTE", cliente);
                        ((AppCompatActivity) context).startActivityForResult(it, 0);

//                     Toast.makeText(context, "Cliente: " + cliente.nome, Toast.LENGTH_SHORT)
//                                .show();
                    }

                }
            });

        }
    }

}
