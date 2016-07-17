package ngothanhson95.dev.com.sherlock.sherlock.database;

import ngothanhson95.dev.com.sherlock.sherlock.constant.Constant;

/**
 * Created by ngothanhson95 on 7/15/16.
 */
public class SQLHelper {
    public static final String TEXT_TYPE = " TEXT";
    public static final String COMMA_SEP = " , ";
    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + Constant.PERSON_TABLE + "("
                    + Constant.COLUMN_PERSON_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT , "
                    + Constant.COLUMN_PERSON_NAME + TEXT_TYPE + COMMA_SEP
                    + Constant.COLUMN_AGE + TEXT_TYPE + COMMA_SEP
                    + Constant.COLUMN_HEIGHT + TEXT_TYPE + COMMA_SEP
                    + Constant.COMUMN_GENDER + TEXT_TYPE + COMMA_SEP
                    + Constant.COLUMN_HAIRCOLLOUR + TEXT_TYPE + COMMA_SEP
                    + Constant.COLUMN_ADDRESS + TEXT_TYPE + COMMA_SEP
                    + Constant.COLUMN_ADDITIONAL_COMMENT + TEXT_TYPE + COMMA_SEP
                    + Constant.COLUMN_IMAGE + " BLOB" + COMMA_SEP
                    + Constant.COLUMN_MOVEMENT + TEXT_TYPE + " DEFAULT '0'"
                    + ");";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + Constant.PERSON_TABLE;

    public static final String SQL_SELECT_ALL_ENTRIES = "SELECT * FROM " + Constant.PERSON_TABLE;

    public static final String SQL_SEARCH_BY_NAME = "SELECT * FROM " + Constant.PERSON_TABLE + " WHERE "
                                                    + Constant.COLUMN_PERSON_NAME + "= ? " ;
}

