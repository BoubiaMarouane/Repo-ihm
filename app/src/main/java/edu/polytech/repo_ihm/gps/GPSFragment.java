package edu.polytech.repo_ihm.gps;

import static android.os.Build.VERSION_CODES.P;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import edu.polytech.repo_ihm.R;

public class GPSFragment extends Fragment {
    private IGPSActivity igpsActivity;
    private TextView placeNameTextView;
    public GPSFragment(){}
    public GPSFragment(IGPSActivity activity){igpsActivity=activity;}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_gps,container,false);
        placeNameTextView = rootView.findViewById(R.id.placeName);
        final ImageView imageGPSGranted = rootView.findViewById(R.id.imageGPSGranted);
        final ImageView imageGPSActivated = rootView.findViewById(R.id.imageGPSActivated);
        boolean permissionGranted = ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;

        if(permissionGranted)

        {
            imageGPSGranted.setImageResource(R.drawable.gpson);
            LocationListener listener = new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    currentLocation = location;
                    igpsActivity.moveCamera();
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {
                    imageGPSActivated.setImageResource(R.drawable.unLocked);
                }

                @Override
                public void onProviderDisabled(String provider) {
                    imageGPSActivated.setImageResource(R.drawable.Locked);
                }
            };
        LocationManager locationManager = (LocationManager) (getActivity().getSystemService(Context.LOCATION_SERVICE));
        LocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,5000,1,listener);
        imageGPSActivated.setImageResource(locationManager.isProviderEnabled(locationManager.GPS_PROVIDER)?R.drawable.unlocked :R.drawable.Locked);
        }else{
            imageGPSActivated.setImageResource( R.drawable.Locked );
            imageGPSGranted.setImageResource( R.drawable.gpsoff );
        }
        return rootView;
}

    LatLng getPosition(){
        return new LatLng(currentLocation.getLatitude(),currentLocation.getLongitude());
    }

    String getPlaceName() throws IOException {
        Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
        List<Address> addresses = geocoder.getFromLocation(currentLocation.getLatitude(), currentLocation.getLongitude(),1);
        return addresses.get(0).getLocality();
    }

    void setPlaceName(String placeName){
        placeNameTextView.setText(placeName);
    }

}
