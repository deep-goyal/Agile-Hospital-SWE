package application.serializedBackend;

import java.io.Serializable;

public class Prescription implements Serializable {
    private String date;
    private String meds;
    private String notes;

    public Prescription(String date, String meds, String notes) {
        this.date = date;
        this.meds = meds;
        this.notes = notes;
    }

    public String[] getPrescription() {
        String[] dataToReturn = {date, meds, notes};
        return dataToReturn;
    }
}
