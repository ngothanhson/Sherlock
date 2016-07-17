package ngothanhson95.dev.com.sherlock.sherlock.model;

/**
 * Created by ngothanhson95 on 7/17/16.
 */
public class Movement {
    private String personName;
    private String movementTime;
    private String movementNote;
    private String movementLocation;

    public Movement(String movementLocation, String movementNote, String movementTime, String personName) {
        this.movementLocation = movementLocation;
        this.movementNote = movementNote;
        this.movementTime = movementTime;
        this.personName = personName;
    }

    public Movement() {
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
}
