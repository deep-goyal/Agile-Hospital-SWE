package application.serializedBackend;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;

public class Patient implements Serializable {
    private String patientID;
    public ArrayList<Visit> visits = new ArrayList<Visit>();

    public Patient(String patientID) {
        this.patientID = patientID;
    }
}
