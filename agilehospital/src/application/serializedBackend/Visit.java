package application.serializedBackend;

import java.io.Serializable;

public class Visit implements Serializable {
    private String date;
    private Vitals vitals;
    private Prescription prescription;
    private String allergies;
    private String concerns;

    public Visit(String date, Vitals vitals, Prescription prescription, String allergies, String concerns) {
        this.date = date;
        this.vitals = vitals;
        this.prescription = prescription;
        this.allergies = allergies;
        this.concerns = concerns;
    }

    public Visit(String date, Vitals vitals, String allergies, String concerns) {
        this.date = date;
        this.vitals = vitals;
        this.allergies = allergies;
        this.concerns = concerns;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }

    public String getDate() {
        return date;
    }

    public Vitals getVitals() {
        return vitals;
    }

    public Prescription getPrescription() {
        return prescription;
    }

    public String getAllergies() {
        return allergies;
    }

    public String getConcerns() {
        return concerns;
    }
}
