package application.serializedBackend;

import java.io.Serializable;

public class Prescription implements Serializable {
    private String date;
    private String data;

    public Prescription(String date, String data) {
        this.date = date;
        this.data = data;
    }

    public String[] getPrescription() {
        String[] dataToReturn = {date, data};
        return dataToReturn;
    }
}
