package br.edu.unoesc.webmobi.offtrail.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;
import org.androidannotations.annotations.LongClick;
import org.androidannotations.annotations.OnActivityResult;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.WindowFeature;

import java.io.ByteArrayOutputStream;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import br.edu.unoesc.webmobi.offtrail.R;
import br.edu.unoesc.webmobi.offtrail.helper.DatabaseHelper;
import br.edu.unoesc.webmobi.offtrail.model.Grupo;
import br.edu.unoesc.webmobi.offtrail.model.GrupoTrilheiro;
import br.edu.unoesc.webmobi.offtrail.model.Moto;
import br.edu.unoesc.webmobi.offtrail.model.Trilheiro;
import br.edu.unoesc.webmobi.offtrail.model.Usuario;

@EActivity(R.layout.activity_cadastro_trilheiro)
@Fullscreen
@WindowFeature(Window.FEATURE_NO_TITLE)
public class CadastroTrilheiroActivity extends AppCompatActivity {

    @ViewById
    ImageView imvFoto;

    @ViewById
    EditText edtNome;

    @ViewById
    EditText edtIdade;

    @ViewById
    Spinner spnMotos;

    @ViewById
    Spinner spnGrupos;

    @Bean
    DatabaseHelper dh;

    Trilheiro trilheiro;

    @AfterViews
    public void inicializar() {
        try {

            //Cria um adapter (Ou datasource)
            ArrayAdapter<Moto> motos = new ArrayAdapter<Moto>(
                    this, android.R.layout.simple_spinner_item, dh.getMotoDao().queryForAll());
            //VIncula o adapter ao spinner
            spnMotos.setAdapter(motos);


            ArrayAdapter<Grupo> grupos = new ArrayAdapter<Grupo>(
                    this, android.R.layout.simple_spinner_item, dh.getGrupoDao().queryForAll());
            spnGrupos.setAdapter(grupos);


            trilheiro = (Trilheiro) getIntent().getSerializableExtra("t");
            if (trilheiro != null) {
                edtNome.setText(trilheiro.getNome());
                edtIdade.setText(trilheiro.getIdade());

                imvFoto.setImageBitmap(BitmapFactory.decodeByteArray(trilheiro.getFoto(), 0, trilheiro.getFoto().length));

                spnMotos.setSelection(motos.getPosition(trilheiro.getMoto()));
                List<GrupoTrilheiro> g = dh.getGrupoTrilheiroDao().queryBuilder().where().eq("codigo", trilheiro.getCodigo()).query();
                spnGrupos.setSelection(grupos.getPosition(g.get(0).getGrupo()));
            }

        } catch (SQLException ex) {
        }

    }


    public void Cancelar(View v) {
     /*      Intent itPrincipal = new Intent(
                this, PrincipalActivity_.class);
        startActivity(itPrincipal);
    */

        //Finaliza a Activity e retorna para tela anterior;
        finish();
    }

    public void Salvar(View v) {
        try {

            Trilheiro t = new Trilheiro();

            if (trilheiro != null) {
                t = trilheiro;
            }
            t.setNome(edtNome.getText().toString());
            t.setIdade(edtIdade.getText().toString());
            t.setMoto((Moto) spnMotos.getSelectedItem());

            //Salvando foto como um vetor de bytes, não haverá problema de codificação
            Bitmap bitmap = ((BitmapDrawable) imvFoto.getDrawable()).getBitmap();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            t.setFoto(baos.toByteArray());


            dh.getTrilheiroDao().createOrUpdate(t);

            GrupoTrilheiro gt = new GrupoTrilheiro();
            gt.setTrilheiro(t);
            gt.setGrupo((Grupo) spnGrupos.getSelectedItem());
            gt.setData(new Date().toString());

            dh.getGrupoTrilheiroDao().createOrUpdate(gt);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        finish();
    }


    //LongClick manter pressionado para abrir a camera;
    @LongClick(R.id.imvFoto)
    public void capturarFoto() {
        //Android sabera abrir a tela para captura de foto;
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, 100);
        }
    }

    /**
     * Quando confirmado a foto ele retorna para o método a baixo, 100 identifica qual janela retornou
     * Caso retorne sejá outro código != 100 deverá ter outro método para recebe-lo
     *
     * @param resultCode
     * @param data
     */
    @OnActivityResult(100)
    void onResult(int resultCode, Intent data) {

        //Se o usuario selecionou a foto tentar mostrar ela;
        if (resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            //Pega o extra que vieram da Camera no argumento "data" converte para bitmap e seta a foto no componenete;
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imvFoto.setImageBitmap(imageBitmap);
        }
    }
}
