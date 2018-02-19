package com.moter.nearbylocation;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.github.nitrico.mapviewpager.MapViewPager;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.moter.nearbylocation.Model.Results;

import java.util.ArrayList;


/**
 * Created by moter on 14.02.2018.
 */

public class LocationAdapter extends MapViewPager.Adapter {

    private ArrayList<Results> results;
    private ArrayList<String> page_title = new ArrayList<>();
    private ArrayList<CameraPosition> CAMERA_POSITIONS= new ArrayList<>();


    public LocationAdapter(FragmentManager fm, ArrayList<Results> mResults) {
        super(fm);
        results=new ArrayList<>();
        this.results = mResults;
        page_title.clear();
        CAMERA_POSITIONS.clear();
        for (int i = 0; i < results.size(); i++) {
            double lat = Double.parseDouble(results.get(i).getGeometry().getLocation().getLat());
            double lng = Double.parseDouble(results.get(i).getGeometry().getLocation().getLng());
            CameraPosition cameraPosition = CameraPosition.fromLatLngZoom(new LatLng(lat, lng), 18f);
            CAMERA_POSITIONS.add(cameraPosition);
            page_title.add(results.get(i).getName());
        }
    }

    @Override
    public int getCount() {
        return page_title.size();
    }

    @Override
    public Fragment getItem(int position) {
        Log.d("getItemPos", "" + position);
        return LocationItemFragment.newInstance(results.get(position), results.size(), position + 1);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return page_title.get(position);
    }

    @Override
    public CameraPosition getCameraPosition(int position) {
        return CAMERA_POSITIONS.get(position);
    }
}
