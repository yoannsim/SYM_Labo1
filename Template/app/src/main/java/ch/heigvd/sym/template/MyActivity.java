/*
 * File     : MyActivity.java
 * Project  : TemplateActivity (Labo 1)
 * Author   : Spinelli Isaia 04 octobre 2019
 * 			  Simonet Yoann 04 octobre 2019
 *
 */
package ch.heigvd.sym.template;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;


public class MyActivity extends AppCompatActivity {

    private TextView email = null;
    private TextView eimi = null;
    String IMEI_Number_Holder = "";
    TelephonyManager telephonyManager;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activ2);

        Intent intent = getIntent();
        // GUI elements
        this.email = findViewById(R.id.textMail);
        this.eimi = findViewById(R.id.textIMEI);
        ImageView img = findViewById(R.id.img);

        String mail = "";
        if (intent.hasExtra("emailEntered")) { // vérifie qu'une valeur est associée à la clé “edittext”
            mail = intent.getStringExtra("emailEntered"); // on récupère la valeur associée à la clé
        }
        email.setText(mail);

        telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

        // on a forcement les droits si l'application est encore ouverte
        // (Demande pour la compilation)
        if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        IMEI_Number_Holder = telephonyManager.getImei();

        eimi.setText("IMEI Number : " + IMEI_Number_Holder);


        // chemin natel perso : Carte mémoire/Download/perso.jpg
        // chemin retourner : /storage/emulated/0/Download
        // le chemain depent du telephone !
        // Commande a tape : adb push perso.jpg /storage/emulated/0/Download/perso.jpg
        File dcimDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File imgFile = new File(dcimDir+"/perso.jpg");

        if (imgFile.exists()) {
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            img.setImageBitmap(myBitmap);
        }
    }


}
