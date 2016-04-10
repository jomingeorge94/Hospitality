package com.example.jomin.hospitality;

import android.graphics.Point;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jomin on 08/04/2016.
 */
public class LocationFragment extends Fragment {



    String s;
    boolean mutex = false;
    private LocationManager locationManager;



    private static View view;
    /**
     * Note that this may be null if the Google Play services APK is not
     * available.
     */

    public  GoogleMap mMap;
    // Default newcastle. But that's only if the user doesn't have GPS.
    private static double latitude = 54.97;
    private static double longitude = -1.62;
    private String provider;
    private MyLocationListener mylistener;
    private Criteria criteria;
    View pic;
            View txt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       pic = container.getRootView().findViewById(R.id.pic);
         txt = container.getRootView().findViewById(R.id.text);

        Bundle bundle = getArguments();
        s = bundle.getString("SERVICE");
        if (container == null) {
            return null;
        }
        view = (RelativeLayout) inflater.inflate(R.layout.map_frag, container, false);



        setUpMapIfNeeded(); // For setting up the MapFragment

        return view;
    }

    private GoogleMap.OnMyLocationChangeListener myLocationChangeListener = new GoogleMap.OnMyLocationChangeListener() {
        @Override
        public void onMyLocationChange(Location location) {


            if(!mutex) {
                mutex = true;
                latitude = location.getLatitude();
                longitude = location.getLongitude();
                new places().execute();
            }

            // For zooming automatically to the Dropped PIN Location
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude,
                    longitude), 12.0f));

        }
    };

    private class MyLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {
            // Initialize the location fields

            // For dropping a marker at a point on the Map







        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {


        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    }
    /***** Sets up the map if it is possible to do so *****/
    public void setUpMapIfNeeded() {

        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {

            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getChildFragmentManager()
                    .findFragmentById(R.id.location_map)).getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null)
                setUpMap();
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the
     * camera.
     * <p>
     * This should only be called once and when we are sure that {@link #mMap}
     * is not null.
     */
    private void setUpMap() {
        Log.i("MARIE", "MARIE");
        // For showing a move to my loction button
        mMap.setMyLocationEnabled(true);
        mMap.setOnMyLocationChangeListener(myLocationChangeListener);

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener(){

            @Override
            public void onMapClick(LatLng latLng) {
                Display display = LocationFragment.this.getActivity().getWindowManager().getDefaultDisplay();
                Point size = new Point();
                display.getSize(size);
                int width = size.x;
                int height = size.y;

                pic.setVisibility(View.GONE);
                txt.setVisibility(View.GONE);



                LocationFragment.this.getView().setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height));
            }
        });




        }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        if (mMap != null)
            setUpMap();

        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getChildFragmentManager()
                    .findFragmentById(R.id.location_map)).getMap(); // getMap is deprecated
            // Check if we were successful in obtaining the map.
            if (mMap != null)
                setUpMap();
        }
    }

    List<MapsRepresenter> res = new ArrayList<MapsRepresenter>();

    /**** The mapfragment's id must be removed from the FragmentManager
     **** or else if the same it is passed on the next time then
     **** app will crash ****/
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mMap != null) {
            MyMap.fragmentManager.beginTransaction()
                    .remove(MyMap.fragmentManager.findFragmentById(R.id.location_map)).commit();
            mMap = null;
        }
    }



    public class places extends AsyncTask<Void, Void, String> {

        String responseStr = null;

        public places() {

        }

        @Override
        protected String doInBackground(Void... params) {

            HttpParams httpRequestParams = new BasicHttpParams();


            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpGet post = new HttpGet("https://maps.googleapis.com/maps/api/place/nearbysearch/json?key=AIzaSyDYH1r7M3QD0847WQKuWcUR3AlX-AIkxe4"+
                    "&location=" + latitude + "," + longitude + "&radius=16000&sensor=false&types=" + s);

            try {
                HttpResponse httpResponse = client.execute(post);


                responseStr = EntityUtils.toString(httpResponse.getEntity());

            } catch (Exception e) {
                e.printStackTrace();
            }

            return responseStr;
        }

        @Override
        protected void onPostExecute(String v) {

            try {
                JSONObject jObject = new JSONObject(v);
                JSONArray jArray = jObject.getJSONArray("results");


                for (int i=0; i < jArray.length(); i++)
                {

                    MapsRepresenter r = new MapsRepresenter();

                    try {
                        JSONObject oneObject = jArray.getJSONObject(i);

                        r.lat = oneObject.getJSONObject("geometry").getJSONObject("location").getDouble("lat");
                        r.lng = oneObject.getJSONObject("geometry").getJSONObject("location").getDouble("lng");
                        // Pulling items from the array
                        r.name = oneObject.getString("name");
                        r.addr = oneObject.getString("vicinity");
                        LocationFragment.this.res.add(r);



                    } catch (JSONException e) {
                        Log.i("Weed", "Nsmoker");
                    }



                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            for(MapsRepresenter m : LocationFragment.this.res){
                LocationFragment.this.mMap.addMarker(new MarkerOptions().position(new LatLng(m.lat, m.lng)).title(m.name).snippet(m.addr).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
            }

        }
    }
}