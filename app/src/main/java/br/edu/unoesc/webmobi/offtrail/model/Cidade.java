package br.edu.unoesc.webmobi.offtrail.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Cidade {
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
