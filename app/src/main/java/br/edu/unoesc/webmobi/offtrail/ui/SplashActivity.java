package br.edu.unoesc.webmobi.offtrail.ui;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;
import org.androidannotations.annotations.WindowFeature;

import java.sql.SQLException;

import br.edu.unoesc.webmobi.offtrail.R;
import br.edu.unoesc.webmobi.offtrail.helper.DatabaseHelper;

@EActivity(R.layout.activity_splash)
@Fullscreen
//remove barra de titulos
@WindowFeature(Window.FEATURE_NO_TITLE)
public class SplashActivity extends AppCompatActivity {

    @AfterViews
    @Background(delay = 3000)
    public void abrirLogin(){

        Intent itLogin = new Intent(
                this, LoginActivity_.class
        );
        startActivity(itLogin);
        finish();
    }

    //Ao usar o androidanotations não precisa do código a baixo;
/*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent itLogin = new Intent(
                        //No runnable precisa passar o nome da classe;
                        SplashActivity.this, LoginActivity.class
                );
                startActivity(itLogin);
                finish();
            }
        }, 3000);
    }
*/
}
