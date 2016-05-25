package samsung.com.suveyapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
public class ListServeyActivity extends Fragment {
    private ArrayList<ServeyOject> mListServeyOjbect = new ArrayList<ServeyOject>();
    private SerVeyAdapter ServeyAdapter;
    private ListView mListView;
    private ImageButton mNewServey;
    private ImageButton mBack;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View RootView = inflater.inflate(R.layout.servey_layout, container, false);
        mListView = (ListView) RootView.findViewById(R.id.listservey);
        ServeyAdapter = new SerVeyAdapter(getActivity().getBaseContext(), R.layout.servey_item_layout, mListServeyOjbect);
        mListView.setAdapter(ServeyAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getActivity().getBaseContext(), ListQuestionActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                Util.ServeySelected = mListServeyOjbect.get(position);
            }
        });
        mNewServey = (ImageButton) RootView.findViewById(R.id.imbaddnewservey);
        mBack = (ImageButton) RootView.findViewById(R.id.imbexit);
        mNewServey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity().getBaseContext(), AddDealerAcitivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);

            }
        });
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        if (mListServeyOjbect.size() == 0) {
            SetupView();
        }
        return RootView;
    }


    private void SetupView() {
        Cursor c = getActivity().getContentResolver().query(SamsungProvider.URI_ENCUESTA_DISENOS, null, null, null, null);
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
}
