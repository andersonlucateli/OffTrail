package br.edu.unoesc.webmobi.offtrail.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

@DatabaseTable
public class Cidade implements Serializable {
    //anota campo com chave primaria e autoencrmente
    @DatabaseField(generatedId = true)
    private Integer codigo;

    //anota para impedir campo nulo
    @DatabaseField(canBeNull = false)
    private String nome;

    //o Androidanotation obriga a ter um construtor vaziu
    public Cidade(){

    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
