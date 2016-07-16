package ngothanhson95.dev.com.sherlock.sherlock.database;

/**
 * Created by ngothanhson95 on 7/15/16.
 */
public class SQLHelper {
    public static final String TEXT_TYPE = " TEXT";
    public static final String COMMA_SEP = " , ";
    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + PersonDbHelper.TABLE_NAME + "("
                    + PersonDbHelper.COLUMN_PERSON_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT , "
                    + PersonDbHelper.COLUMN_PERSON_NAME + TEXT_TYPE + COMMA_SEP
                    + PersonDbHelper.COLUMN_AGE + TEXT_TYPE + COMMA_SEP
                    + PersonDbHelper.COLUMN_HEIGHT + TEXT_TYPE + COMMA_SEP
                    + PersonDbHelper.COMUMN_GENDER + TEXT_TYPE + COMMA_SEP
                    + PersonDbHelper.COLUMN_HAIRCOLLOUR + TEXT_TYPE + COMMA_SEP
                    + PersonDbHelper.COLUMN_ADDRESS + TEXT_TYPE + COMMA_SEP
                    + PersonDbHelper.COLUMN_ADDITIONAL_COMMENT + TEXT_TYPE + COMMA_SEP
                    + PersonDbHelper.COLUMN_IMAGE + " BLOB"
                    + ");";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + PersonDbHelper.TABLE_NAME;

    public static final String SQL_SELECT_ALL_ENTRIES = "SELECT * FROM " + PersonDbHelper.TABLE_NAME;

    public static final String SQL_SEARCH_BY_NAME = "SELECT * FROM " + PersonDbHelper.TABLE_NAME + " WHERE "
                                                    + PersonDbHelper.COLUMN_PERSON_NAME + "= ? " ;
}

