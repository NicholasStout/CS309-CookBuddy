package com.example.primaryfolder.cookbuddy.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.primaryfolder.cookbuddy.R;
import com.google.android.gms.maps.SupportMapFragment;

public class MappingFragment extends Fragment implements OnMapReadyCallback{

  GoogleMap mGoogleMap;
  MapView mMapView;
  View mView;

  @Override
  public void onCreate(Bundle savedInstanceState)
  {
      super.onCreate(savedInstanceState);
  }



  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      mView = inflater.inflate(R.layout.fragment_nearby_stores, container, false);
      return mView;
  }


  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
      super.onViewCreated(view, savedInstanceState);

      mMapView = (MapView) mView.findViewById(R.id.map);
      if (mMapView != null) {
          mMapView.onCreate(null);
          mMapView.onResume();
          mMapView.getMapAsync(this);
      }

  }



  @Override
  public void onMapReady(GoogleMap googleMap) {
    MapsInitializer.initialize(getContext());

    mGoogleMap = googleMap;
    googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);


    googleMap.addMarker(new MarkerOptions().position(new LatLng(40.689247,-74.044502)).title("Statue of Liberty").snippet("I hope to go there soon"));

    CameraPosition Liberty = CameraPosition.builder().target(new LatLng(40.689247, -74.044502)).zoom(16).bearing(0).tilt(45).build();

    googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(Liberty));
  }






}
