package samsung.com.suveyapplication;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.samsung.adapter.DealerAdapter;
import com.samsung.object.Dealer;
import com.samsung.object.Util;
import com.samsung.provider.SamsungProvider;
import com.samsung.table.tblDistritos;
import com.samsung.table.tblProvincias;
import com.samsung.table.tblPuntosDeVenta;
import com.samsung.table.tblZonas;

import java.util.ArrayList;

/**
 * Created by Computer on 4/2/2016.
 */
public class AllDealerActivity extends Fragment {

    private ArrayList<Dealer> mListDealer = new ArrayList<Dealer>();
    private DealerAdapter dealerAdapter;
    private ListView mListView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View RooView = inflater.inflate(R.layout.all_dealer_layout, container, false);

        mListView = (ListView) RooView.findViewById(R.id.list_all_dealers);
        dealerAdapter = new DealerAdapter(getActivity().getBaseContext(), R.layout.dealeritem, mListDealer);
        mListView.setAdapter(dealerAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Dealer dealer = (Dealer) dealerAdapter.getItem(position);
                Log.e("ListDealersActivity.px", "tuyen.px " + dealer.toString());
                Util.DealerSelected = dealer;
                Intent i = new Intent(getActivity().getBaseContext(), ProfileDearlerActivity.class);
                i.putExtra("alldealer", "true");
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });
        if (mListDealer.size() > 0) {
            mListDealer.removeAll(mListDealer);
        }
        SetupView();


        return RooView;
    }

    private void SetupView() {
        String[] projections = new String[]{tblPuntosDeVenta.TBL_NAME + "." + tblPuntosDeVenta.PK_ID,
                tblPuntosDeVenta.TBL_NAME + "." + tblPuntosDeVenta.NOMBRE + " AS Name", tblDistritos.TBL_NAME + "." + tblDistritos.NOMBRE + " AS Dictrict", tblProvincias.TBL_NAME + "." + tblProvincias.NOMBRE + " AS Province",
                tblPuntosDeVenta.TBL_NAME + "." + tblPuntosDeVenta.DIRECCION, tblPuntosDeVenta.TBL_NAME + "." + tblPuntosDeVenta.POSION_LAT, tblPuntosDeVenta.TBL_NAME + "." + tblPuntosDeVenta.POSION_LON,
                tblPuntosDeVenta.TBL_NAME + "." + tblPuntosDeVenta.ACTIVO, tblZonas.TBL_NAME + "." + tblZonas.NOMBRE + " AS Zone", tblPuntosDeVenta.TBL_NAME + "." + tblPuntosDeVenta.TELEFONO
        };
        Cursor c = getActivity().getContentResolver().query(SamsungProvider.URI_JOIN_DETAIL_ORDER, projections, null, null, tblPuntosDeVenta.TBL_NAME + "." + tblPuntosDeVenta.NOMBRE);
        if (c.getCount() == 0) {
            return;
        }
        while (c.moveToNext()) {
            String PK_ID = c.getString(c.getColumnIndexOrThrow(tblPuntosDeVenta.PK_ID));
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
        c.close();
    }
}
