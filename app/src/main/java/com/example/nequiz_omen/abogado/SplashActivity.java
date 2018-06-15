package com.example.nequiz_omen.abogado;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by Nequiz_OMEN on 10/06/2018.
 */

public class SplashActivity extends Activity {

    private static boolean splashLoaded = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            if (splashLoaded) {    /*DESDE AQUI SE CONTROLA EL SCREEN SPLASH  con un !splashLoaded   para negar la preposicion*/
                setContentView(R.layout.activity_splash);
                int secondsDelayed = 1;
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                        finish();
                    }
                }, secondsDelayed * 5000);

                splashLoaded = true;
            } else {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }
        }catch (Exception e) {
            finish();
            e.printStackTrace();
            finish();
    }
  }

}