package samsung.com.suveyapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by SamSunger on 5/13/2015.
 */
public class MainAcitivity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton btnDealer;
    private ImageButton btnMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        btnDealer = (ImageButton) findViewById(R.id.imbdealer);
        btnMap = (ImageButton) findViewById(R.id.imbmap);
        btnDealer.setOnClickListener(this);
        btnMap.setOnClickListener(this);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imbdealer:
                Intent i = new Intent(getBaseContext(), ListDealersActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                break;
            case R.id.imbmap:
                Intent ik = new Intent(getBaseContext(), MapsActivity.class);
                ik.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(ik);
                break;
            default:
                break;
        }
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
