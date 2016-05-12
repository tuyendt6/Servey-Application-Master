package samsung.com.suveyapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.samsung.adapter.DealerAdapter;
import com.samsung.object.Dealer;
import com.samsung.object.Util;
import com.samsung.provider.SamsungProvider;
import com.samsung.table.tblDistritos;
import com.samsung.table.tblFrecuenciaVisitas;
import com.samsung.table.tblProvincias;
import com.samsung.table.tblPuntosDeVenta;
import com.samsung.table.tblVendedoresPorPuntosDeVenta;
import com.samsung.table.tblZonas;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by SamSunger on 5/13/2015.
 */
public class ListDealersActivity extends AppCompatActivity {
    private ArrayList<Dealer> mListDealer = new ArrayList<Dealer>();
    private DealerAdapter dealerAdapter;
    private ListView mListView;
    private ImageButton mAddDealer;
    private ImageButton mBack;
    private ImageButton mSearch;
    private EditText mTextSearch;

    private ArrayList<Dealer> mListDealerSearch = new ArrayList<Dealer>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listdealer_layout);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        actionBar.setTitle("Listar Dealers");

        mSearch = (ImageButton) findViewById(R.id.btnsearch);
        mTextSearch = (EditText) findViewById(R.id.textsearch);
        mSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dealerAdapter.getFilter().filter(mTextSearch.getText().toString().trim());
                dealerAdapter.notifyDataSetChanged();
            }
        });
        mListView = (ListView) findViewById(R.id.listView);
        dealerAdapter = new DealerAdapter(getBaseContext(), R.layout.dealeritem, mListDealer);
        mListView.setAdapter(dealerAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Dealer dealer = (Dealer) dealerAdapter.getItem(position);
                Log.e("ListDealersActivity.px", "tuyen.px " + dealer.toString());
                Util.DealerSelected = dealer;
                Intent i = new Intent(getBaseContext(), ProfileDearlerActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });
        mAddDealer = (ImageButton) findViewById(R.id.imbadddeler);
        mBack = (ImageButton) findViewById(R.id.imbexit);
        mAddDealer.setOnClickListener(new View.OnClickListener() {
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
        if (mListDealer.size() > 0) {
            mListDealer.removeAll(mListDealer);
        }
        SetupView();
    }

    private void SetupView() {
        String[] projections = new String[]{tblPuntosDeVenta.TBL_NAME + "." + tblPuntosDeVenta.PK_ID,
                tblPuntosDeVenta.TBL_NAME + "." + tblPuntosDeVenta.NOMBRE + " AS Name", tblDistritos.TBL_NAME + "." + tblDistritos.NOMBRE + " AS Dictrict", tblProvincias.TBL_NAME + "." + tblProvincias.NOMBRE + " AS Province",
                tblPuntosDeVenta.TBL_NAME + "." + tblPuntosDeVenta.DIRECCION, tblPuntosDeVenta.TBL_NAME + "." + tblPuntosDeVenta.POSION_LAT, tblPuntosDeVenta.TBL_NAME + "." + tblPuntosDeVenta.POSION_LON,
                tblPuntosDeVenta.TBL_NAME + "." + tblPuntosDeVenta.ACTIVO, tblZonas.TBL_NAME + "." + tblZonas.NOMBRE + " AS Zone", tblPuntosDeVenta.TBL_NAME + "." + tblPuntosDeVenta.TELEFONO
        };
        Cursor c = getContentResolver().query(SamsungProvider.URI_JOIN_DETAIL_ORDER, projections, null, null, tblPuntosDeVenta.TBL_NAME + "." + tblPuntosDeVenta.NOMBRE);
        if (c.getCount() == 0) {
            c.close();
            return;
        }

        ArrayList<String> arrayList = getListPDVID();

        while (c.moveToNext()) {
            String PK_ID = c.getString(c.getColumnIndexOrThrow(tblPuntosDeVenta.PK_ID));
            Log.e("tuyenpx ", "tuyenpx _ check dealer : " + PK_ID);
            if (arrayList.contains(PK_ID)) {
                Dealer dealer = new Dealer(PK_ID, c.getString(c.getColumnIndexOrThrow("Name")),
                        c.getString(c.getColumnIndexOrThrow("Dictrict")),
                        c.getString(c.getColumnIndexOrThrow("Province")),
                        c.getString(c.getColumnIndexOrThrow(tblPuntosDeVenta.DIRECCION)),
                        c.getString(c.getColumnIndexOrThrow(tblPuntosDeVenta.POSION_LAT)),
                        c.getString(c.getColumnIndexOrThrow(tblPuntosDeVenta.POSION_LON)),
                        c.getString(c.getColumnIndexOrThrow(tblPuntosDeVenta.ACTIVO)),
                        c.getString(c.getColumnIndexOrThrow("Zone")),
                        c.getString(c.getColumnIndexOrThrow(tblPuntosDeVenta.TELEFONO)));
                Log.e("ListDealersActivity ", dealer.toString());
                mListDealer.add(dealer);
                dealerAdapter.notifyDataSetChanged();
            }
        }
        c.close();
    }

    private String getCurrentDay() {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE", Locale.ENGLISH);
        Date d = new Date();
        return sdf.format(d);
    }

    private int getCurrentID() {
        SharedPreferences sharedPreferences = getSharedPreferences("question", Context.MODE_PRIVATE);
        String id = sharedPreferences.getString("venderID", "1");
        return Integer.parseInt(id.trim());
    }

    private ArrayList<String> getListPDVID() {

        ArrayList<String> mListPDVID = new ArrayList<>();

        String mListPKID = "";
        Log.e("tuyenpx ", "tuyenpx _getCurrentDay() : " + getCurrentDay());
        Cursor c = getContentResolver().query(SamsungProvider.URI_FRECUENCIA_VISITAS, null, tblFrecuenciaVisitas.CODIGO + " =?", new String[]{getCurrentDay()}, null);
        c.moveToFirst();
        if (c.getCount() == 0) {
            c.close();
            return mListPDVID;
        }
        mListPKID = c.getString(c.getColumnIndexOrThrow(tblFrecuenciaVisitas.PK_ID));
        // }
        Log.e("tuyenpx ", "tuyenpx _ tblFrecuenciaVisitas.PK_ID : " + mListPKID);

        c.close();
        Cursor d = getContentResolver().query(SamsungProvider.URI_VENDEDORES_POR_PUNTOS_DEVENTA, null, tblVendedoresPorPuntosDeVenta.VENDEDOR_ID + " =?", new String[]{getCurrentID() + ""}, null);
        while (d.moveToNext()) {
            String PKID = d.getString(d.getColumnIndexOrThrow(tblVendedoresPorPuntosDeVenta.FRECUENCIA_VISITA_ID));
            String PDVID = d.getString(d.getColumnIndexOrThrow(tblVendedoresPorPuntosDeVenta.PDVID));

            String[] listday = PKID.split(",");
            for (String day : listday) {
                Log.e("tuyenpx ", "tuyenpx _ day  : " + mListPKID);

                if (day.trim().equalsIgnoreCase(mListPKID.trim())) {
                    mListPDVID.add(PDVID);
                    Log.e("tuyenpx ", "tuyenpx _add PDVID  : " + PDVID);
                    break;
                }
            }
        }
        d.close();
        return mListPDVID;
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
