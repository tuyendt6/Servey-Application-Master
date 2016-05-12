package samsung.com.suveyapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.samsung.adapter.SerVeyAdapter;
import com.samsung.object.ServeyOject;
import com.samsung.object.Util;
import com.samsung.provider.SamsungProvider;
import com.samsung.table.tblEncuestaDisenos;

import java.util.ArrayList;

/**
 * Created by SamSunger on 5/13/2015.
 */
public class ListServeyActivity extends AppCompatActivity {
    private ArrayList<ServeyOject> mListServeyOjbect = new ArrayList<ServeyOject>();
    private SerVeyAdapter ServeyAdapter;
    private ListView mListView;
    private ImageButton mNewServey;
    private ImageButton mBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.servey_layout);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Encuestas");

        mListView = (ListView) findViewById(R.id.listservey);
        ServeyAdapter = new SerVeyAdapter(getBaseContext(), R.layout.servey_item_layout, mListServeyOjbect);
        mListView.setAdapter(ServeyAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getBaseContext(), ListQuestionActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                Util.ServeySelected = (ServeyOject) mListServeyOjbect.get(position);
            }
        });
        mNewServey = (ImageButton) findViewById(R.id.imbaddnewservey);
        mBack = (ImageButton) findViewById(R.id.imbexit);
        mNewServey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), AddDealerAcitivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                finish();
            }
        });
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mListServeyOjbect.size() == 0) {
            SetupView();
        }
    }

    private void SetupView() {
        Cursor c = getContentResolver().query(SamsungProvider.URI_ENCUESTA_DISENOS, null, null, null, null);
        if (c.getCount() == 0) {
            return;
        }
        while (c.moveToNext()) {
            //  String PK_ID, String NOMBRE, String DESCRIPCION, String ACTIVO, String TIMEDONE, String GRUPO_RESPUESTAS_ID
            ServeyOject serveyOject = new ServeyOject(c.getString(c.getColumnIndexOrThrow(tblEncuestaDisenos.PK_ID)), c.getString(c.getColumnIndexOrThrow(tblEncuestaDisenos.NOMBRE)),
                    c.getString(c.getColumnIndexOrThrow(tblEncuestaDisenos.DESCRIPCION)), c.getString(c.getColumnIndexOrThrow(tblEncuestaDisenos.ACTIVO)), "");
            Log.e("ListServeyActivity ", serveyOject.toString());
            if (serveyOject.getACTIVO().equals("True")) {
                mListServeyOjbect.add(serveyOject);
                ServeyAdapter.notifyDataSetChanged();
            }
        }
        c.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.manu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.showalldealer:
                startActivity(new Intent(getApplicationContext(), AllDealerActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
