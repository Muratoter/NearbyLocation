package com.moter.nearbylocation;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.moter.nearbylocation.Model.PlaceDetail;
import com.moter.nearbylocation.Model.Results;
import com.moter.nearbylocation.Remote.Common;
import com.moter.nearbylocation.Remote.IGoogleService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class LocationItemFragment extends Fragment {

    private TextView tv_place_name, tv_place_address, tv_place_phone, tv_rating,tv_item_counter;
    private Results mResult;
    private Button btn_direction_map;
    private PlaceDetail curPlaceDetail;
    private RatingBar rating_bar;
    IGoogleService mService;


    public LocationItemFragment() {
        // Required empty public constructor
    }

    public static LocationItemFragment newInstance(Results results,int results_size,int position) {
        LocationItemFragment f = new LocationItemFragment();
        Bundle args = new Bundle();
        args.putParcelable("result_item", results);
        args.putInt("results_size",results_size);
        args.putInt("position",position);
        f.setArguments(args);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_location_item, container, false);
        tv_place_name = (TextView) v.findViewById(R.id.tv_place_name);
        tv_place_address = (TextView) v.findViewById(R.id.tv_place_address);
        tv_place_phone = (TextView) v.findViewById(R.id.tv_place_phone);
        btn_direction_map = (Button) v.findViewById(R.id.btn_direction_map);
        rating_bar = (RatingBar) v.findViewById(R.id.rating_bar);
        tv_rating = (TextView) v.findViewById(R.id.tv_rating);
        tv_item_counter=(TextView)v.findViewById(R.id.tv_item_counter);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle args = getArguments();
        mService = Common.getGoogleApiService();

        if (args != null) {
            mResult = args.getParcelable("result_item");

            tv_item_counter.setText(args.getInt("position")+"/"+args.getInt("results_size"));
            tv_place_name.setText(mResult.getName().toString());
            mService.getDetailPlaces(getPlaceDetailUrl(mResult.getPlace_id()))
                    .enqueue(new Callback<PlaceDetail>() {
                        @Override
                        public void onResponse(Call<PlaceDetail> call, Response<PlaceDetail> response) {
                            curPlaceDetail = response.body();
                            tv_place_address.setText(curPlaceDetail.getResult().getFormatted_address().toString());

                            if (curPlaceDetail.getResult().getFormatted_phone_number() != null) {
                                tv_place_phone.setVisibility(View.VISIBLE);
                                tv_place_phone.setText(curPlaceDetail.getResult().getFormatted_phone_number());
                            }

                            if (curPlaceDetail.getResult().getRating() != null) {
                                rating_bar.setRating(Float.parseFloat(curPlaceDetail.getResult().getRating()));
                                tv_rating.setText(curPlaceDetail.getResult().getRating());
                            }
                        }

                        @Override
                        public void onFailure(Call<PlaceDetail> call, Throwable t) {

                        }
                    });

            btn_direction_map.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("result_url", curPlaceDetail.getResult().getUrl());
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(curPlaceDetail.getResult().getUrl()));
                    startActivity(mapIntent);
                }
            });
        }


    }

    private String getPlaceDetailUrl(String place_id) {
        StringBuilder url = new StringBuilder("https://maps.googleapis.com/maps/api/place/details/json");
        url.append("?placeid=" + place_id);
        url.append("&key=" + getResources().getString(R.string.google_web_api));
        Log.d("getUrlDetail", url.toString());
        return url.toString();
    }
}
