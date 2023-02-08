package com.example.maps;

import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import kotlinx.coroutines.scheduling.Task;

public class MapActivity extends AppCompatActivity {
    Location currentLocation;
    FusedLocationProviderClient fusedClient;
    private static final int REQUEST_CODE = 101;
    FrameLayout map;
    GoogleMap gmap;
    Marker marker;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        map = findViewById(R.id.map);
        searchView = findViewById(R.id.search);
        searchView.clearFocus();



        fusedClient = LocationServices.getFusedLocationProviderClient(this);
        getLocation();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String loc = searchView.getQuery().toString();
                if(loc==null){
                    Toast.makeText(MapActivity.this, "location not found", Toast.LENGTH_SHORT).show();
                }else{
                    Geocoder geocoder =new Geocoder(MapActivity.this, Locale.getDefault());
                    try{
                        List<Address> addressList=geocoder.getFromLocationName(loc,1);
                        if(addressList.size() > 0){
                            LatLngl latLngl = new LatLng(addressList.get(0).getLatitude(),addressList.get(0).getLongitude());
                            if(marker != null){
                                marker.remove();
                            }
                            MarkerOptions markerOptions = new MarkerOptions().position(latLngl).title(loc);
                            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
                            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLngl,5);
                            gmap.animateCamera(cameraUpdate);
                            marker = gmap.addMarker(markerOptions);


                        }
                    }catch (IOException e){
                        e.printStackTrace();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

            private void getLocation() {
                private void getLocation{
                    if(ActivityCompat.checkSelfPermission(
                            this,Manifest.permission.ACCESS_FINE_LOCTION) != PackageManager.PERMISSION_GRANTED
                            && ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS-FINE-LOCATION},REQUEST_CODE);
                        return;
                    }
                    Task<Location> task = getLastLocation();

                    task.addOnSuccessListener(new OnSuccessListener<Location>)


                }
    }


    }
}
