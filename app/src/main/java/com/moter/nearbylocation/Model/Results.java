package com.moter.nearbylocation.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by moter on 06.02.2018.
 */

public class Results implements Parcelable {
    private String icon;

    private String place_id;

    private String scope;

    private String reference;

    private Geometry geometry;

    private Opening_hours opening_hours;

    private String id;

    private Photos[] photos;

    private String price_level;

    private String vicinity;

    private String name;

    private String rating;

    private String[] types;

    protected Results(Parcel in) {
        icon = in.readString();
        place_id = in.readString();
        scope = in.readString();
        reference = in.readString();
        id = in.readString();
        price_level = in.readString();
        vicinity = in.readString();
        name = in.readString();
        rating = in.readString();
        types = in.createStringArray();
    }

    public static final Creator<Results> CREATOR = new Creator<Results>() {
        @Override
        public Results createFromParcel(Parcel in) {
            return new Results(in);
        }

        @Override
        public Results[] newArray(int size) {
            return new Results[size];
        }
    };

    public String getIcon ()
    {
        return icon;
    }

    public void setIcon (String icon)
    {
        this.icon = icon;
    }

    public String getPlace_id ()
    {
        return place_id;
    }

    public void setPlace_id (String place_id)
    {
        this.place_id = place_id;
    }

    public String getScope ()
    {
        return scope;
    }

    public void setScope (String scope)
    {
        this.scope = scope;
    }

    public String getReference ()
    {
        return reference;
    }

    public void setReference (String reference)
    {
        this.reference = reference;
    }

    public Geometry getGeometry ()
    {
        return geometry;
    }

    public void setGeometry (Geometry geometry)
    {
        this.geometry = geometry;
    }

    public Opening_hours getOpening_hours ()
    {
        return opening_hours;
    }

    public void setOpening_hours (Opening_hours opening_hours)
    {
        this.opening_hours = opening_hours;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public Photos[] getPhotos ()
    {
        return photos;
    }

    public void setPhotos (Photos[] photos)
    {
        this.photos = photos;
    }

    public String getPrice_level ()
    {
        return price_level;
    }

    public void setPrice_level (String price_level)
    {
        this.price_level = price_level;
    }

    public String getVicinity ()
    {
        return vicinity;
    }

    public void setVicinity (String vicinity)
    {
        this.vicinity = vicinity;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getRating ()
    {
        return rating;
    }

    public void setRating (String rating)
    {
        this.rating = rating;
    }

    public String[] getTypes ()
    {
        return types;
    }

    public void setTypes (String[] types)
    {
        this.types = types;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [icon = "+icon+", place_id = "+place_id+", scope = "+scope+", reference = "+reference+", geometry = "+geometry+", opening_hours = "+opening_hours+", id = "+id+", photos = "+photos+", price_level = "+price_level+", vicinity = "+vicinity+", name = "+name+", rating = "+rating+", types = "+types+"]";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(icon);
        parcel.writeString(place_id);
        parcel.writeString(scope);
        parcel.writeString(reference);
        parcel.writeString(id);
        parcel.writeString(price_level);
        parcel.writeString(vicinity);
        parcel.writeString(name);
        parcel.writeString(rating);
        parcel.writeStringArray(types);
    }
}
