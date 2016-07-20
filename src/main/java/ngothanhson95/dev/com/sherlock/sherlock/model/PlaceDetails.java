package ngothanhson95.dev.com.sherlock.sherlock.model;

import com.google.api.client.util.Key;

/**
 * Created by ngothanhson95 on 7/17/16.
 */
public class PlaceDetails {
    @Key
    public String status;

    @Key
    public Place result;

    @Override
    public String toString() {
        if (result!=null) {
            return result.toString();
        }
        return super.toString();
    }
}
