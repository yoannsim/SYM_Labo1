/*
 * File     : MainActivity.java
 * Project  : TemplateActivity
 * Author   : Markus Jaton 2 juillet 2014
 * 			  Fabien Dutoit 23 juillet 2019
 *            IICT / HEIG-VD
 *
 * Modifié : Spinelli Isaia 04 octobre 2019
* 			 Simonet Yoann 04 octobre 2019
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
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

	// For logging purposes
	private static final String TAG = MainActivity.class.getSimpleName();
	// Codes constants pour les permissions
	private static final int MY_PERMISSIONS_REQUEST_READ_PHONE_STATE = 2;
	private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 3;

	// Just for test purposes : please destroy !
	private static final String validEmail = "toto@tutu.com";
	private static final String validPassword = "tata";

	// GUI elements
	private EditText email = null;
	private EditText password = null;
	private Button signIn = null;

	@RequiresApi(api = Build.VERSION_CODES.M)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.w(TAG, "CREATE !!");
		// Show the welcome screen / login authentication dialog
		setContentView(R.layout.authent);

		Log.w(TAG, "CREATE !!");
		// Link to GUI elements
		this.email = findViewById(R.id.email);
		this.password = findViewById(R.id.psw);
		this.signIn = findViewById(R.id.buttOk);

		// Then program action associated to "Ok" button
		signIn.setOnClickListener((v) -> {
			// get mail and password
			String mail = email.getText().toString();
			String passwd = password.getText().toString();
			// check email
			if (!mail.contains("@")) {
				Toast.makeText(MainActivity.this, R.string.wrongEmail, Toast.LENGTH_SHORT).show();
			} else if (isValid(mail, passwd)) {
				// lance la 2eme activite si c'est valide
				Intent intent = new Intent(this, ch.heigvd.sym.template.MyActivity.class);
				intent.putExtra("emailEntered", mail);
				intent.putExtra("passwordGiven", passwd);
				this.startActivity(intent);

			} else {
				// Wrong combination, display pop-up dialog and stay on login screen
				email.setText("");
				password.setText("");
				showErrorDialog(mail, passwd);
			}
		});
	}

	@RequiresApi(api = Build.VERSION_CODES.M)
	protected void onStart () {
		super.onStart();
		Log.w(TAG, "START !!");

		// Demande les autorisations au lancement de l'app

		// Si il a deja accepte
		if (!checkPermReadState()) {
			// Demande l'autorisation pour lire le stockage externe
			checkPermExternStorage();
		}
	}

	// Handler des reponses aux autorisations
	@RequiresApi(api = Build.VERSION_CODES.M)
	@Override
	public void onRequestPermissionsResult(int requestCode,
										   String[] permissions, int[] grantResults) {
		switch (requestCode) {
			case MY_PERMISSIONS_REQUEST_READ_PHONE_STATE: {
				// If request is cancelled, the result arrays are empty.
				if (grantResults.length > 0
						&& grantResults[0] == PackageManager.PERMISSION_GRANTED) {
					// permission was granted, yay!

					// Demande l'autorisation pour lire le stockage externe
					Log.w(TAG, "DEMANDE 2 !!");
					checkPermExternStorage();


				} else {
					// permission denied, boo!
					finish();
				}
				return;
			}
			case MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE: {
				if (grantResults.length > 0
						&& grantResults[0] == PackageManager.PERMISSION_GRANTED) {
					// permission was granted, yay!
					Log.w(TAG, "PERMISSION !!");

				} else {
					// permission denied, boo!
					finish();
				}
				return;
			}

		}
	}

	// demande la permission pour la lecture du stockage externe
	@RequiresApi(api = Build.VERSION_CODES.M)
	private void checkPermExternStorage(){
		if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
			// Permission is not granted
			if (ActivityCompat.shouldShowRequestPermissionRationale(this,
					Manifest.permission.READ_EXTERNAL_STORAGE)) {

				//This is called if user has denied the permission before
				//In this case I am just asking the permission again
				Log.w(TAG, "demande 123 !!");
				ActivityCompat.requestPermissions(this,
						new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);

			} else {
				// No explanation needed; request the permission
				Log.w(TAG, "demande 321 !!");
				ActivityCompat.requestPermissions(this,
						new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);

			}
		}
	}

	// demande la permission pour la lecture de l'IMEI
	@RequiresApi(api = Build.VERSION_CODES.M)
	private boolean checkPermReadState(){
		// Demande l'autorisation pour lire l'IMEI
		if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
			// Permission is not granted
			if (ActivityCompat.shouldShowRequestPermissionRationale(this,
					Manifest.permission.READ_PHONE_STATE)) {

				//This is called if user has denied the permission before
				//In this case I am just asking the permission again
				ActivityCompat.requestPermissions(this,
						new String[]{Manifest.permission.READ_PHONE_STATE}, MY_PERMISSIONS_REQUEST_READ_PHONE_STATE);

			} else {
				// No explanation needed; request the permission
				ActivityCompat.requestPermissions(this,
						new String[]{Manifest.permission.READ_PHONE_STATE}, MY_PERMISSIONS_REQUEST_READ_PHONE_STATE);

			}
			return true;
		} else {
			return false;
		}
	}

	protected void onRestart () {
		super.onRestart();
		Log.w(TAG, "RESTART !!");
	}

	protected void onResume () {
		super.onResume();
		Log.w(TAG, "RESUME !!");
	}

	protected void onPause () {
		super.onPause();
		Log.w(TAG, "PAUSE !!");
	}

	protected void onStop () {
		super.onStop();
		Log.w(TAG, "STOP !!");
	}

	protected void onDestroy () {
		super.onDestroy();
		Log.w(TAG, "DESTROY !!");
	}

	
	private boolean isValid(String mail, String passwd) {
        if(mail == null || passwd == null) {
            Log.w(TAG, "isValid(mail, passwd) - mail and passwd cannot be null !");
            return false;
        }
		// Return true if combination valid, false otherwise
		return (mail.equals(validEmail) && passwd.equals(validPassword));
	}

	private void showErrorDialog(String mail, String passwd) {
		/*
		 * Pop-up dialog to show error
		 */
		AlertDialog.Builder alertbd = new AlertDialog.Builder(this);
        alertbd.setIcon(R.mipmap.error_log);
		alertbd.setTitle(R.string.wronglogin);
	    alertbd.setMessage(R.string.wrong);
	    alertbd.setPositiveButton(android.R.string.ok, (dialog, which) -> {
			// we do nothing...
			// dialog close automatically
	    });
	    alertbd.create().show();
	}
	
}
