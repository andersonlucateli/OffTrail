package br.edu.unoesc.webmobi.offtrail.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import br.edu.unoesc.webmobi.offtrail.R;
import br.edu.unoesc.webmobi.offtrail.adapter.TrilheiroAdapter;
import br.edu.unoesc.webmobi.offtrail.model.Trilheiro;

@EViewGroup(R.layout.lista_trilheiros)
public class TrilheiroItemView extends LinearLayout {

    @ViewById
    TextView txtNome;

    @ViewById
    TextView txtMoto;

    @ViewById
    ImageView imvFoto;

    //Variavel global
    Trilheiro trilheiro;

    @Bean
    TrilheiroAdapter trilheiroAdapter;


    public TrilheiroItemView(Context context) {
        super(context);
    }

    @Click(R.id.imvEditar)
    public void editar() {
        /**
         * Lembrar de excluir primeiro o Grupo_trilheiro depois o Trilheiro;
         *
         * Criar uma Intente para chamar a tela de cadastro e passar o objeto do trilheiro inteiro
         * todas a classes devem ter Serializable
         */
        //Toast.makeText(getContext(), "Editar: " + trilheiro.getNome(), Toast.LENGTH_SHORT).show();
        //TODO: (2,50) Implementar a edição dos dados do trilheiro. OK

        Intent itCadastro = new Intent(
                getContext(), CadastroTrilheiroActivity_.class
        );
        itCadastro.putExtra("t", trilheiro);
        getContext().startActivity(itCadastro);
    }

    @Click(R.id.imvExcluir)
    public void excluir() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
        dialog.setTitle("Exclusão");
        dialog.setMessage("Deseja realmente excluir? \n " + trilheiro.getNome());
        dialog.setCancelable(false);
        dialog.setNegativeButton("Não", null);
        dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {

            //TODO: (2,50) Implementar a exclusão do trilheiro e grupo trilheiro. OK

            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Implementar a exclusão
                trilheiroAdapter.excluirTrilheiro(trilheiro);

                Toast.makeText(getContext(), "Excluido: " + trilheiro.getNome(), Toast.LENGTH_SHORT).show();
/*
                Intent itPrincipal = new Intent(
                        getContext(), PrincipalActivity_.class
                );
                getContext().startActivity(itPrincipal);
  */
            }
        });
        dialog.show();
    }


    public void bind(Trilheiro t) {
        //Variavel global vai armazenar os dados do trilheiro;
        trilheiro = t;

        txtNome.setText(t.getNome());
        txtMoto.setText(t.getMoto().getModelo() + " - " + t.getMoto().getCilidrada());
        imvFoto.setImageBitmap(BitmapFactory.decodeByteArray(t.getFoto(), 0, t.getFoto().length));
    }
}