package ngothanhson95.dev.com.sherlock.sherlock.constant;

/**
 * Created by ngothanhson95 on 7/17/16.
 */
public class Constant {
    public static final String DATABASE_NAME = "MyDB.db";
    public static final int DATABASE_VERSION = 1;

    //value for table person
    public static final String PERSON_TABLE = "Person";
    public static final String COLUMN_PERSON_ID = "ID";
    public static final String COLUMN_PERSON_NAME = "Name";
    public static final String COLUMN_AGE = "Age";
    public static final String COLUMN_HEIGHT = "Height";
    public static final String COMUMN_GENDER = "Gender";
    public static final String COLUMN_HAIRCOLLOUR = "Hair_Colour";
    public static final String COLUMN_ADDRESS = "Address";
    public static final String COLUMN_ADDITIONAL_COMMENT = "Additional_Comment";
    public static final String COLUMN_IMAGE = "Image";
    public static final String COLUMN_MOVEMENT = "Movement";


    //value for movement table
    public static  final String MOVEMENT_TABLE = "Movement";
    public static  final String COLUMN_MOVEMENT_ID = "ID";
    public static  final String COLUMN_PERSON_ID_MOVE ="IDPERSON";
    public static  final String COLUMN_TIME = "TIME";
    public static  final String COLUMN_NOTE = "NOTE";
    public static  final String COLUMN_LOCATION ="LOCATION";
    public static  final String COLUMN_MOVEMENT_PERSON = "NAME";

    //request  & result Code
    public static  final int ADD_NAME_PLACE_RESULT_CODE = 1;
    public static  final int ADD_NAME_PLACE_REQUEST_CODE = 2;
    public static  final int ADD_MOVEMENT_REQUEST_CODE = 3;
    public static  final int ADD_MOVEMENT_RESULT_CODE = 4;
    public static  final int ADD_PERSON_RESULT_CODE = 5;
    public static  final int ADD_PERSON_REQUEST_CODE = 6;

    //key for intent to put
    public static final String BUNDLE_KEY = "12323q123";
    public static final String ID_KEY = "idkey";
    public static final String NAME_KEY = "NAME";
    public static final String AGE_KEY = "AGE";
    public static final String HEIGHT_KEY ="HEIGHT";
    public static final String GENDER_KEY = "GENDER";
    public static final String HAIR_KEY = "HAIR";
    public static final String ADRESS_KEY = "ADDRESS";
    public static final String ADDITIONAL_COMMENT_KEY = "ADDI";
    public static final String IMAGE_KEY ="IMAGE";
    public static final String MOVEMENT_PARCE_KEY = "PARCE";
    public static final String PERSON_PARCE_KEY = "PARCELABLE";
    public static String ID_PLACE_KEY = "ID_PLACE"; // id of the place
    public static String NAME_PLACE_KEY = "Name_place"; // name of the place

    //gg api key
//    public static final String API_KEY1 ="AIzaSyDSO_lEQhRryRH-BAvt1OjJMSEHmNN95wY";
    public static final String API_KEY = "AIzaSyBtW-Sv9HsNkPzCucI-gt93wV7Topx6wXc";
    // Google Places serach url's
    public static final String PLACES_SEARCH_URL = "https://maps.googleapis.com/maps/api/place/search/json?";
    public static final String PLACES_TEXT_SEARCH_URL = "https://maps.googleapis.com/maps/api/place/search/json?";
    public static final String PLACES_DETAILS_URL = "https://maps.googleapis.com/maps/api/place/details/json?";



}
