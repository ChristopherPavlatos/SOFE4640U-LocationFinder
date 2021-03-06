package com.example.locationfinder;


import android.content.Context;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

//Finds long and lat with address

private class MyLocationListener implements LocationListener {

    LocationManager locationManager = (LocationManager)
            getSystemService(Context.LOCATION_SERVICE);

    LocationListener locationListener = new MyLocationListener();
    locationManager.requestLocationUpdates(

    LocationManager.GPS_PROVIDER, 5000, 10, locationListener);

    @Override
    public void onLocationChanged(Location loc) {
        editLocation.setText("");
        pb.setVisibility(View.INVISIBLE);

        Toast.makeText(
                getBaseContext(),
                "Location changed: Lat: " + loc.getLatitude() + " Lng: " + loc.getLongitude(), Toast.LENGTH_SHORT).show();

        String longitude = "Longitude: " + loc.getLongitude();

        Log.v(TAG, longitude);
        String latitude = "Latitude: " + loc.getLatitude();
        Log.v(TAG, latitude);
        String cityName = null;

        Geocoder gcd = new Geocoder(getBaseContext(), Locale.getDefault());
        List<Address> addresses;

        try {
            addresses = gcd.getFromLocation(loc.getLatitude(), loc.getLongitude(), 1);

            if (((List<?>) addresses).size() > 0) {
                System.out.println(addresses.get(0).getLocality());
                cityName = addresses.get(0).getLocality();
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }

        String s = longitude + "\n" + latitude + "\n\nMy Current City is: " + cityName;
        editLocation.setText(s);
    }

    @Override
    public void onProviderDisabled(String provider) {}

    @Override
    public void onProviderEnabled(String provider) {}

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {}

}
