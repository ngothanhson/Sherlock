package ngothanhson95.dev.com.sherlock.sherlock.model;

/**
 * Created by ngothanhson95 on 7/17/16.
 */

import com.google.api.client.util.Key;

import java.io.Serializable;
import java.util.List;

/** Implement this class from "Serializable"
 * So that you can pass this class Object to another using Intents
 * Otherwise you can't pass to another actitivy
 * */
public class PlacesList implements Serializable {

    @Key
    public String status;

    @Key
    public List<Place> results;

}
