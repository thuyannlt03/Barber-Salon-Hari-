package com.example.da_1.Fragment.Map;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.example.da_1.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.libraries.places.api.Places;

import java.util.ArrayList;

public class fragmentMaps extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    Spinner spinner;
    Location mLastLocation;
    FusedLocationProviderClient mfusedLocationProviderClient;
    ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_maps, container, false);
        mfusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getContext());
        spinner = view.findViewById(R.id.dsTypeMaps);
        progressBar = view.findViewById(R.id.progressbarMaps);
        SupportMapFragment map = (SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.map);
       map.getMapAsync(this);
        addControls();
        addEvents();
        progressBar.setVisibility(View.VISIBLE);
        return view;
    }
    private void addEvents() {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                        break;
                    case 1:
                        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                        break;
                    case 2:
                        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                        break;
                    case 3:
                        mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                        break;
                    case 4:
                        mMap.setMapType(GoogleMap.MAP_TYPE_NONE);
                        break;
                    default:
                        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    void addControls() {
        ArrayList<String> ds_styleMaps = new ArrayList<>();
        ds_styleMaps.add("Normal");
        ds_styleMaps.add("Hybrid");
        ds_styleMaps.add("Satellite");
        ds_styleMaps.add("Terrain");
        ds_styleMaps.add("None");
        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, ds_styleMaps);
        spinner.setAdapter(arrayAdapter);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        String apiKey = "AIzaSyD-4-LbFrfwjN3MqgvgWqqIPP6oJzD-gog";
        Places.initialize(mfusedLocationProviderClient.getApplicationContext(),apiKey);
        mMap = googleMap;
        LatLng toaT = new LatLng(10.8538264, 106.6256344);
        mMap.addMarker(new MarkerOptions()
                .position(toaT) .title("FPT T"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(toaT));
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        progressBar.setVisibility(View.GONE);
        xulyQuyen();

    }
    public void xulyQuyen() {
        if (ActivityCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]
                            {Manifest.permission.ACCESS_FINE_LOCATION},
                    999);
        } else {
            Log.d("a", "getLocation: quyen da duoc gan");
        }
        mMap.setMyLocationEnabled(true);
    }
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        Toast.makeText(getActivity(), "xx", Toast.LENGTH_SHORT).show();
//        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//            Toast.makeText(getActivity(), "xin duoc quyen roi", Toast.LENGTH_SHORT).show();
//            xulyQuyen();
//        }
//    }
}
