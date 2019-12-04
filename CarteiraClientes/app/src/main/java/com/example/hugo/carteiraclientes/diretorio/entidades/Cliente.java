package com.example.hugo.carteiraclientes.diretorio.entidades;

import java.io.Serializable;

public class Cliente implements Serializable
{
    public int codigo;
    public String nome;
    public String endereco;
    public String email;
    public String tel;

    public Cliente()
    {
        codigo = 0;
    }

}
