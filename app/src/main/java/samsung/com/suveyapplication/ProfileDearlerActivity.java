package samsung.com.suveyapplication;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.samsung.object.Dealer;
import com.samsung.object.Util;

/**
 * Created by SamSunger on 5/15/2015.
 */
public class ProfileDearlerActivity extends AppCompatActivity implements View.OnClickListener {
    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    private Dealer dealer;
    private TextView mName;
    private TextView mAddress;
    private TextView mCity;
    private Button mStartSerVey;
    private ImageButton mExit;
    private ImageButton mDealer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profiledealer);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Empezar Encuesta");

        mName = (TextView) findViewById(R.id.txtnameDealer);
        mAddress = (TextView) findViewById(R.id.txtAddress);
        mCity = (TextView) findViewById(R.id.txtCity);
        mStartSerVey = (Button) findViewById(R.id.btnstartServey);
        mStartSerVey.setOnClickListener(this);
        mExit = (ImageButton) findViewById(R.id.imbexit);
        mExit.setOnClickListener(this);
        mDealer = (ImageButton) findViewById(R.id.imbadddeler);
        mDealer.setOnClickListener(this);
        try {
            String s = getIntent().getStringExtra("alldealer");
            if (s.trim().equals("true")) {
                mStartSerVey.setVisibility(View.GONE);
            } else {
                mStartSerVey.setVisibility(View.VISIBLE);
            }
        } catch (Exception e) {
            mStartSerVey.setVisibility(View.VISIBLE);
        }
        //setUpMapIfNeeded();
    }

    boolean flags = true;

    @Override
    protected void onResume() {
        super.onResume();
        dealer = Util.DealerSelected;
        setUpMapIfNeeded();
        SetupView();
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        LocationListener listener = new LocationListener() {

            @Override
            public void onStatusChanged(String provider, int status,
                                        Bundle extras) {
            }

            @Override
            public void onProviderEnabled(String provider) {
            }

            @Override
            public void onProviderDisabled(String provider) {

            }

            @Override
            public void onLocationChanged(Location location) {
                Util.currentLat = location.getLatitude() + "";
                Util.currentLong = location.getLongitude() + "";
                //   Log.e("tuyenpx : lat and long", Util.currentLat+ Util.currentLong);
            }
        };
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                35, 0, listener);
    }

    private void SetupView() {
        mName.setText(dealer.getDealerName());
        mAddress.setText(dealer.getAdress());
        mCity.setText(dealer.getCity() + "," + dealer.getDistric());
    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                mMap.setMyLocationEnabled(true);
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {

        Log.e("ProfileDealerActivity ", dealer.toString());
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(Double.parseDouble(dealer.getLatitud().trim()), Double.parseDouble(dealer.getLongGitude().trim())))
                .title(dealer.getDealerName())
                .snippet(dealer.getAdress())
                .anchor(0.5f, 1)).showInfoWindow();
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(Double.parseDouble(dealer.getLatitud().trim()), Double.parseDouble(dealer.getLongGitude().trim())), 6);

// Zoom in, animating the camera.
        mMap.animateCamera(CameraUpdateFactory.zoomIn());

// Zoom out to zoom level 10, animating with a duration of 2 seconds.
        mMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);

// Construct a CameraPosition focusing on Panama City and animate the camera to that position.
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(Double.parseDouble(dealer.getLatitud().trim()), Double.parseDouble(dealer.getLongGitude().trim())))
                .zoom(17)                   // Sets the zoom
                .bearing(90)                // Sets the orientation of the camera to east
                .tilt(30)                   // Sets the tilt of the camera to 30 degrees
                .build();                   // Creates a CameraPosition from the builder
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imbadddeler:
                Intent i = new Intent(getBaseContext(), MainAcitivity.class);
                i.putExtra("add", "add");
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                break;
            case R.id.imbexit:
                finish();
                break;
            case R.id.btnstartServey:
                Intent ik = new Intent(getBaseContext(), ListServeyActivity.class);
                ik.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(ik);
                break;
            default:
                break;
        }

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
