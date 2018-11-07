package br.edu.unoesc.webmobi.offtrail.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;
import org.androidannotations.annotations.ViewById;

import br.edu.unoesc.webmobi.offtrail.R;

@EActivity(R.layout.activity_cadastro_trilheiro)
@Fullscreen
public class CadastroTrilheiroActivity extends AppCompatActivity {

    @ViewById
    EditText edtNome;

    @ViewById
    EditText edtIdade;

    public void Cancelar(View v){
        Intent itPrincipal = new Intent(
                this, PrincipalActivity.class);
        startActivity(itPrincipal);
    }

    public void Salvar(View v){


    }
}
