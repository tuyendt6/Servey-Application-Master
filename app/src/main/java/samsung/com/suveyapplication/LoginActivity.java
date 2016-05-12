package samsung.com.suveyapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.samsung.provider.SamsungProvider;
import com.samsung.table.tblVendedores;

/**
 * Created by SamSunger on 5/13/2015.
 */
public class LoginActivity extends AppCompatActivity  {


    private EditText mTxtName;
    private EditText mTxtPass;
    private Button mLogin;
    private String VenderesID = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        mTxtName = (EditText) findViewById(R.id.txtusername);
        mTxtPass = (EditText) findViewById(R.id.txtpassword);
        mLogin = (Button) findViewById(R.id.btnlogin);
        SharedPreferences sharedPreferences = getSharedPreferences("question", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTxtName.getText().toString().equals("") || (mTxtPass.getText().toString().equals(""))) {
                    Toast.makeText(getBaseContext(), "Username and Password are not null", Toast.LENGTH_SHORT).show();
                } else {
                    boolean login = CheckLogin(mTxtName.getText().toString(), mTxtPass.getText().toString());
                    if (login) {
                        editor.putString("venderID", VenderesID);
                        editor.commit();
                        Intent i = new Intent(getBaseContext(), MainAcitivity.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i);
                        finish();
                    } else {
                        Intent i = new Intent(getBaseContext(), LoginFailAcitivity.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i);
                        finish();
                    }
                }
            }
        });
    }




    private boolean CheckLogin(String UserName, String PassWord) {
        Cursor c = getContentResolver().query(SamsungProvider.URI_VENDEDORES, null, null, null, null);

        if (c.getCount() == 0) {
            return false;
        }

        while (c.moveToNext()) {

            String User = c.getString(c.getColumnIndexOrThrow(tblVendedores.NOMBRE_USUARIO));
            String Pass = c.getString(c.getColumnIndexOrThrow(tblVendedores.CLAVE));
            String ID = c.getString(c.getColumnIndexOrThrow(tblVendedores.PK_ID));
            if (User.equals(UserName) && Pass.equals(PassWord)) {
                VenderesID = ID;
                return true;
            }
        }
        return false;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
