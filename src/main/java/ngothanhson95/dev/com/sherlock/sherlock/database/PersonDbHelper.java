package ngothanhson95.dev.com.sherlock.sherlock.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;
import ngothanhson95.dev.com.sherlock.sherlock.constant.Constant;
import ngothanhson95.dev.com.sherlock.sherlock.model.Person;
/**
 * Created by ngothanhson95 on 7/15/16.
 */
public class PersonDbHelper extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "PersonDb.db";

    public PersonDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQLHelper.SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQLHelper.SQL_DELETE_ENTRIES);
        onCreate(sqLiteDatabase);
    }

    public boolean insert(Person person){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constant.COLUMN_PERSON_NAME, person.getName());
        contentValues.put(Constant.COLUMN_AGE, person.getAge());
        contentValues.put(Constant.COLUMN_HEIGHT, person.getHeight());
        contentValues.put(Constant.COMUMN_GENDER, person.getGender());
        contentValues.put(Constant.COLUMN_HAIRCOLLOUR, person.getHairColour());
        contentValues.put(Constant.COLUMN_ADDRESS, person.getAddress());
        contentValues.put(Constant.COLUMN_ADDITIONAL_COMMENT, person.getAdditionalComment());
        contentValues.put(Constant.COLUMN_IMAGE, person.getImage());
        long result =  db.insert(Constant.PERSON_TABLE, null, contentValues);
        return true;
    }

    public List<Person> getAllPerson(){
        List<Person> listPerson = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor  = db.rawQuery(SQLHelper.SQL_SELECT_ALL_ENTRIES, null);
        if(cursor.moveToFirst() && cursor.getCount()!=0){
            do {
                Person person = new Person();
                person.setId(Integer.parseInt(cursor.getString(0)));
                person.setName(cursor.getString(1));
                person.setAge(cursor.getString(2));
                person.setHeight(cursor.getString(3));
                person.setGender(cursor.getString(4));
                person.setHairColour(cursor.getString(5));
                person.setAddress(cursor.getString(6));
                person.setAdditionalComment(cursor.getString(7));
                person.setImage(cursor.getBlob(8));
                listPerson.add(person);
            }while (cursor.moveToNext());
        }
        return listPerson;
    }
}
