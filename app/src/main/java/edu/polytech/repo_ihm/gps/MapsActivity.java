package edu.polytech.repo_ihm.gps;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

import edu.polytech.repo_ihm.R;
import edu.polytech.repo_ihm.activities.MainActivity;
import edu.polytech.repo_ihm.activities.MarketPlaceActivity;

public class MapsActivity extends FragmentActivity implements IGPSActivity, OnMapReadyCallback {

    private GPSFragment gpsFragment;
    private NavigationFragment navigationFragment;
    GoogleMap map;
    Button exitButton;
    SearchView searchView;
    private SupportMapFragment supportMapFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);

        gpsFragment = (GPSFragment) getSupportFragmentManager().findFragmentById(R.id.gpsLocation);
        if (gpsFragment == null) {
            gpsFragment = new GPSFragment(this);
            FragmentTransaction gpsTransaction = getSupportFragmentManager().beginTransaction();
            gpsTransaction.replace(R.id.gpsLocation, gpsFragment);
            gpsTransaction.addToBackStack(null);
            gpsTransaction.commit();
        }
        navigationFragment = (NavigationFragment) getSupportFragmentManager().findFragmentById(R.id.navigation);
        if (navigationFragment == null) {
            navigationFragment = new NavigationFragment();
            FragmentTransaction gpsTransaction = getSupportFragmentManager().beginTransaction();
            gpsTransaction.replace(R.id.gpsLocation, navigationFragment);
            gpsTransaction.addToBackStack(null);
            gpsTransaction.commit();
        }

        exitButton = findViewById(R.id.back);
        Intent intent = new Intent(MapsActivity.this, MarketPlaceActivity.class);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });

        searchView = findViewById(R.id.location);
        supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                String location = searchView.getQuery().toString();
                List<Address> addressList =null;
                if(location !=null || !location.equals("")){
                    Geocoder geocoder = new Geocoder(MapsActivity.this);
                    try {
                        addressList =geocoder.getFromLocationName(location,1);

                    }catch (IOException e){
                        e.printStackTrace();
                    }
                    Address address =addressList.get(0);
                    LatLng latLng = new LatLng(address.getLatitude(),address.getLongitude());
                    map.addMarker(new MarkerOptions().position(latLng).title(location));
                    map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;
        LatLng Nice =  new LatLng(50.681035, 2.224034);
        map.addMarker(new MarkerOptions().position(Nice).title("Nice"));
        map.moveCamera(CameraUpdateFactory.newLatLng(Nice));
    }

    @Override
    public void moveCamera() {
        try {
        gpsFragment.setPlaceName("City: " + gpsFragment.getPlaceName());
        } catch (IOException e) {
        gpsFragment.setPlaceName("Unknown city");
        }
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(gpsFragment.getPosition(), 15f));
    }

}