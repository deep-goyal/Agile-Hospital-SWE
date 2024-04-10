package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NurseUtil {
	private static final String REPORTDIRECTORY = System.getProperty("user.dir") + File.separator + "VITALS";
	
	public static void main(String args[]) throws IOException {
		/*
		 * Code for testing
		 
		 
		String ID_1 = "12345";
		String report = "\"name: Margie#weight: 80lbs\"";
		//
		
		writePatientReportToFile(ID_1, report);
		report = "\"name: Carly#weight: 60lbs\"";
		writePatientReportToFile(ID_1, report);
		
		report = "\"name: Angie#weight: 90lbs\"";
		writePatientReportToFile(ID_1, report);
		
		
		report = "\"name: Grace#weight: 160lbs\"";
		writePatientReportToFile(ID_1, report);
		System.out.println(getPatientReportData(ID_1));
		System.out.println("finished");
		*/
		
	}
	
	public static void writePatientReportToFile(String patientID, String report) throws IOException {
    	//filename rule: XXXXX_VITALS.txt
    	
		//Create directory if it doesn't exist
		File directory = new File(REPORTDIRECTORY);
		if (!directory.exists()) {
			boolean created = directory.mkdir();
			
			 if (created) {
                System.out.println("Directory created successfully!");
            } else {
                System.out.println("Failed to create directory.");
            }
		}
		
		String filename = REPORTDIRECTORY + File.separator + patientID + "_VITALS.txt";
    	
    	
    	
    	try {
            File file = new File(filename);
            boolean fileExists = file.exists();

            if (!fileExists || Files.size(Paths.get(filename)) == 0) {
                // Create the file if it doesn't exist or is empty
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, false))) {
                    writer.write(report);
                    System.out.println("Text added to the file.");
                }
            } else {
                // Append text to the existing file
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                    writer.newLine(); // Move to the next line
                    writer.write(report);
                    System.out.println("Text appended to the file.");
                }
            }
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }
    	

    	
    	
    }
    
	/**
     * Checks if patient reports exist
     * 
     * @param patientID
     * @return true if reports exist, false otherwise
     */
    private static boolean checkPatientReportExists(String patientID) {
    	String reportPath = REPORTDIRECTORY + File.separator + patientID + "_VITALS.txt";
    	return new File(reportPath).exists();    
    }
    
    
    public static HashMap<Integer, String> getPatientReportData(String patientID) throws IOException {
        String reportPath = REPORTDIRECTORY + File.separator + patientID + "_VITALS.txt";
        if (!checkPatientReportExists(patientID)) {
            throw new IOException("Report file for patient ID " + patientID + " does not exist.");
        }

        
        
        HashMap<Integer, String> dataMap = new HashMap<>();
        int key = 1;

        try (BufferedReader br = new BufferedReader(new FileReader(reportPath))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                // Using regex to extract text between quotation marks
                Pattern pattern = Pattern.compile("\"([^\"]+)\"");
                Matcher matcher = pattern.matcher(line);

                while (matcher.find()) {
                    sb.append(matcher.group(1));
                }

                if (sb.length() > 0) {
                    dataMap.put(key++, sb.toString());
                    sb.setLength(0); // Reset the StringBuilder
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        

        // Print the HashMap for verification
        for (Integer k : dataMap.keySet()) {
        	String result = String.join("\n", dataMap.get(k).split("#"));
        	
        	System.out.println(result);
        }
        
        
        return dataMap;
       
    }
}
