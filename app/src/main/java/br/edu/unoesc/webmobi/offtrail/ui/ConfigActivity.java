package br.edu.unoesc.webmobi.offtrail.ui;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.WindowFeature;
import org.androidannotations.annotations.sharedpreferences.Pref;

import java.util.ArrayList;

import br.edu.unoesc.webmobi.offtrail.R;


@EActivity(R.layout.activity_config)
@Fullscreen
@WindowFeature(Window.FEATURE_NO_TITLE)
public class ConfigActivity extends AppCompatActivity {

    @ViewById
    EditText edtParametro;

    @ViewById
    Spinner spnCores;

    //Injeção de preferencias
    @Pref
    Configuracao_ configuracao;

    @AfterViews
    public void inicializar() {

        ArrayList<String> cores = new ArrayList<String>();
        cores.add("Azul");
        cores.add("Verde");
        cores.add("Vermelho");

        ArrayAdapter<String> coresAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, cores);
        spnCores.setAdapter(coresAdapter);


        spnCores.setSelection(configuracao.cor().get());
        edtParametro.setText(configuracao.parametro().get());

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
    }

    public void salvar(View v) {

        //Escrevendo/Alterando parâmetros
        configuracao.edit().cor().put(getColorString(spnCores.getSelectedItem().toString())).apply();

        configuracao.edit().parametro().put(edtParametro.getText().toString()).apply();

        Toast.makeText(this, "Parâmentro: " +
                configuracao.parametro().get() + " Cor: " +
                configuracao.cor().get(), Toast.LENGTH_LONG).show();

        finish();
    }

    public int getColorString(String s) {
        switch (s) {
            case "Azul":
                return Color.BLUE;
            case "Verde":
                return Color.GREEN;
            case "Vermelho":
                return Color.RED;
            default:
                return Color.GREEN;
        }
    }

}
