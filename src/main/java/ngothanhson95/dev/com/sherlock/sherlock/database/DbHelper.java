package ngothanhson95.dev.com.sherlock.sherlock.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import ngothanhson95.dev.com.sherlock.sherlock.constant.Constant;
import ngothanhson95.dev.com.sherlock.sherlock.model.Movement;
import ngothanhson95.dev.com.sherlock.sherlock.model.Person;
/**
 * Created by ngothanhson95 on 7/15/16.
 */
public class DbHelper extends SQLiteOpenHelper{
    SQLiteDatabase db;

    public DbHelper(Context context){
        super(context, Constant.DATABASE_NAME, null, Constant.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQLHelper.SQL_CREATE_PERSON);
        sqLiteDatabase.execSQL(SQLHelper.SQL_CREATE_MOVEMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQLHelper.SQL_DELETE_PERSON);
        sqLiteDatabase.execSQL(SQLHelper.SQL_DELETE_MOVEMENT);
        onCreate(sqLiteDatabase);
    }

    public boolean insertPerson(Person person){
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
        contentValues.put(Constant.COLUMN_MOVEMENT, "0");
        long result =  db.insert(Constant.PERSON_TABLE, null, contentValues);
        return true;
    }

    public List<Person> getAllPerson(){
        List<Person> listPerson = new ArrayList<>();
        db = getReadableDatabase();
        Cursor cursor  = db.rawQuery(SQLHelper.SQL_SELECT_ALL_PERSON, null);
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

    public int deletePersonByID(int personId){
        String selection = Constant.COLUMN_PERSON_ID + " LIKE ?";
        String[] selectionArgs = { String.valueOf(personId) };
        return db.delete(Constant.PERSON_TABLE , selection, selectionArgs);
    }

    public void updatePerson(Person person){
        db = getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constant.COLUMN_PERSON_NAME, person.getName());
        contentValues.put(Constant.COLUMN_AGE, person.getAge());
        contentValues.put(Constant.COLUMN_HEIGHT, person.getHeight());
        contentValues.put(Constant.COMUMN_GENDER, person.getGender());
        contentValues.put(Constant.COLUMN_HAIRCOLLOUR, person.getHairColour());
        contentValues.put(Constant.COLUMN_ADDRESS, person.getAddress());
        contentValues.put(Constant.COLUMN_ADDITIONAL_COMMENT, person.getAdditionalComment());
        contentValues.put(Constant.COLUMN_IMAGE, person.getImage());

        String selection = Constant.COLUMN_PERSON_ID + " LIKE ?";
        String[] selectionArgs = { String.valueOf(person.getId()) };
        int count = db.update(Constant.PERSON_TABLE, contentValues, selection, selectionArgs);
    }

    public long insertMovement(Movement movement){
        db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constant.COLUMN_PERSON_ID_MOVE, movement.getPersonId());
        contentValues.put(Constant.COLUMN_LOCATION, movement.getMovementLocation());
        contentValues.put(Constant.COLUMN_NOTE , movement.getMovementNote());
        contentValues.put(Constant.COLUMN_TIME , movement.getMovementTime());
        contentValues.put(Constant.COLUMN_MOVEMENT_PERSON , movement.getPersonName());
        long result =  db.insert(Constant.MOVEMENT_TABLE, null, contentValues);
        return result;
    }

    public List<Movement> getAllMovement(int personId){
        List<Movement> movementList = new ArrayList<>();
        db = getReadableDatabase();
        String[] arg = {String.valueOf(personId)};
        Cursor cursor = db.rawQuery(SQLHelper.SQL_SELECT_ALL_BY_ID, arg);
        if(cursor.moveToFirst() && cursor.getCount()!=0){
            do{
                Movement move = new Movement();
                move.setMovementId(Integer.parseInt(cursor.getString(0)));
                move.setPersonId(Integer.parseInt(cursor.getString(1)));
                move.setMovementLocation(cursor.getString(2));
                move.setMovementNote(cursor.getString(3));
                move.setMovementTime(cursor.getString(4));
                move.setPersonName(cursor.getString(5));
                movementList.add(move);
            }while (cursor.moveToNext());
        }
        return movementList;
    }

    public int deleteAllMovementById(int personId){
        String selection = Constant.COLUMN_PERSON_ID_MOVE + " LIKE ?";
        String[] selectionArgs = { String.valueOf(personId) };
        return db.delete(Constant.MOVEMENT_TABLE , selection, selectionArgs);
    }

    public int deleMovementByID(int movementId) {
        String selection = Constant.COLUMN_MOVEMENT_ID + " LIKE ?";
        String[] selectionArgs = { String.valueOf(movementId) };
        return db.delete(Constant.MOVEMENT_TABLE , selection, selectionArgs);
    }
}
