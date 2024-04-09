package application;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PatientFunctions {
		//directory paths
		private static final String DIRECTORY = System.getProperty("user.dir") + File.separator + "PatientInfo";
		
		private static final String PRESCRIPTIONDIRECTORY = System.getProperty("user.dir") + File.separator + "PatientPrescriptions";
		
		 public static boolean checkPatientInfoExists(String patientID) {
		        String filePath = DIRECTORY + File.separator + patientID + "_PatientInfo.txt";
		        return new File(filePath).exists();
		 }
		
		
        public static String[] getPatientInfoData(String patientID) throws IOException {
            String infoPath = DIRECTORY + File.separator + patientID + "_PatientInfo.txt";
            if (!checkPatientInfoExists(patientID)) {
                throw new IOException("Info file for patient ID " + patientID + " does not exist.");
            }

            String infoData = new String(Files.readAllBytes(Paths.get(infoPath)));
            // Split the string by a sequence of three backslashes
            return infoData.split("\\");
        }
        
        public static boolean checkPatientPresExists(String patientID) {
	        String filePath = PRESCRIPTIONDIRECTORY + File.separator + patientID + "_PatientPrescription.txt";
	        return new File(filePath).exists();
        }
        
        public static String[] getPatientPrescription(String patientID) throws IOException {
            String presPath = PRESCRIPTIONDIRECTORY + File.separator + patientID + "_PatientPrescription.txt";
            if (!checkPatientPresExists(patientID)) {
                throw new IOException("Perscription file for patient ID " + patientID + " does not exist.");
            }

            String presData = new String(Files.readAllBytes(Paths.get(presPath)));
            // Split the string by a sequence of three backslashes
            return presData.split("\\");
        }
}    
