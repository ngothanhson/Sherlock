package ngothanhson95.dev.com.sherlock.sherlock.model;

import com.google.api.client.util.Key;

import java.io.Serializable;
/**
 * Created by ngothanhson95 on 7/17/16.
 */
public class Place implements Serializable {
    @Key
    public String id;

    @Key
    public String name;

    @Key
    public String reference;

    @Key
    public String icon;

    @Key
    public String vicinity;

    @Key
    public Geometry geometry;

    @Key
    public String formatted_address;

    @Key
    public String formatted_phone_number;

    @Override
    public String toString() {
        return name + " - " + id + " - " + reference;
    }

    public static class Geometry implements Serializable
    {
        @Key
        public Location location;
    }

    public static class Location implements Serializable
    {
        @Key
        public double lat;

        @Key
        public double lng;
    }

}
