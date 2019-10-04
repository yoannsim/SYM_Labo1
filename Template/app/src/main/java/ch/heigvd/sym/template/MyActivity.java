/*
 * File     : MainActivity.java
 * Project  : TemplateActivity
 * Author   : Markus Jaton 2 juillet 2014
 * 			  Fabien Dutoit 23 juillet 2019
 *            IICT / HEIG-VD
 *
 * mailto:fabien.dutoit@heig-vd.ch
 *
 * This piece of code reads a [email_account / password ] combination.
 * It is used as a template project for the SYM module element given at HEIG-VD
 * Target audience : students IL, TS, IE [generally semester 1, third bachelor year]
 *
 * THIS SOFTWARE IS PROVIDED "AS IS" AND ANY EXPRESSED OR IMPLIED WARRANTIES,
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY
 * AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL
 * THE REGENTS OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED
 * TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
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
