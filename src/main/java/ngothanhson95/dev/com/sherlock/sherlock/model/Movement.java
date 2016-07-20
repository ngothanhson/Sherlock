package ngothanhson95.dev.com.sherlock.sherlock.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ngothanhson95 on 7/17/16.
 */
public class Movement implements Parcelable {
    private String personName;
    private String movementTime;
    private String movementNote;
    private String movementLocation;
    private int personId;
    private int movementId;

    public Movement(int movementId, String movementLocation, String movementNote, String movementTime, int personId, String personName) {
        this.movementId = movementId;
        this.movementLocation = movementLocation;
        this.movementNote = movementNote;
        this.movementTime = movementTime;
        this.personId = personId;
        this.personName = personName;
    }

    public Movement() {
    }

    protected Movement(Parcel in) {
        personName = in.readString();
        movementTime = in.readString();
        movementNote = in.readString();
        movementLocation = in.readString();
        personId = in.readInt();
        movementId = in.readInt();
    }

    public static final Creator<Movement> CREATOR = new Creator<Movement>() {
        @Override
        public Movement createFromParcel(Parcel in) {
            return new Movement(in);
        }

        @Override
        public Movement[] newArray(int size) {
            return new Movement[size];
        }
    };

    public int getMovementId() {
        return movementId;
    }

    public void setMovementId(int movementId) {
        this.movementId = movementId;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getMovementLocation() {
        return movementLocation;
    }

    public void setMovementLocation(String movementLocation) {
        this.movementLocation = movementLocation;
    }

    public String getMovementNote() {
        return movementNote;
    }

    public void setMovementNote(String movementNote) {
        this.movementNote = movementNote;
    }

    public String getMovementTime() {
        return movementTime;
    }

    public void setMovementTime(String movementTime) {
        this.movementTime = movementTime;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(personName);
        parcel.writeString(movementTime);
        parcel.writeString(movementNote);
        parcel.writeString(movementLocation);
        parcel.writeInt(personId);
        parcel.writeInt(movementId);
    }
}
