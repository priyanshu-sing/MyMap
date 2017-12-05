package com.example.user.mymap;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity
        implements OnMapClickListener, OnMapLongClickListener{

    final int RQS_GooglePlayServices = 1;
    private GoogleMap myMap;

    TextView tvLocInfo;

    Circle myCircle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        tvLocInfo = (TextView)findViewById(R.id.locinfo);

        FragmentManager myFragmentManager = getFragmentManager();
        MapFragment myMapFragment
                = (MapFragment)myFragmentManager.findFragmentById(R.id.map);
        myMap = myMapFragment.getMap();

        myMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        myMap.setOnMapClickListener(this);
        myMap.setOnMapLongClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_legalnotices:
                String LicenseInfo = GooglePlayServicesUtil.getOpenSourceSoftwareLicenseInfo(
                        getApplicationContext());
                AlertDialog.Builder LicenseDialog = new AlertDialog.Builder(MainActivity.this);
                LicenseDialog.setTitle("Legal Notices");
                LicenseDialog.setMessage(LicenseInfo);
                LicenseDialog.show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {

        super.onResume();

        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());

        if (resultCode == ConnectionResult.SUCCESS){
            Toast.makeText(getApplicationContext(),
                    "isGooglePlayServicesAvailable SUCCESS",
                    Toast.LENGTH_LONG).show();
        }else{
            GooglePlayServicesUtil.getErrorDialog(resultCode, this, RQS_GooglePlayServices);
        }

    }


    @Override
    public void onMapClick(LatLng point) {
        CircleOptions circleOptions = new CircleOptions()
                .center(point)   //set center
                .radius(500)   //set radius in meters
                .fillColor(Color.TRANSPARENT)  //default
                .strokeColor(Color.BLUE)
                .strokeWidth(5);

        myCircle = myMap.addCircle(circleOptions);
    }

    @Override
    public void onMapLongClick(LatLng point) {
        CircleOptions circleOptions = new CircleOptions()
                .center(point)   //set center
                .radius(500)   //set radius in meters
                .fillColor(0x40ff0000)  //semi-transparent
                .strokeColor(Color.BLUE)
                .strokeWidth(5);

        myCircle = myMap.addCircle(circleOptions);

    }

}
