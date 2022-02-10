/*
Name: Cameron Moreira
Student Number: 300119186
*/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileReader;
import java.io.*;

class ReadTheFile {
    
    // method to read csv file and extract necessary attribute information. Returns the read file attributes
    public ArrayList DataSet(String strFilename) throws Exception {
        
        String line = "";
        ArrayList storage = new ArrayList<String>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(strFilename));

            while((line = br.readLine()) != null) {
                //String[] row = line.split(",");
                storage.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            br.close();
        }
        return storage;
    }

    // file to get the attribute values fromthe csv file in the form of a list of trip records
    public ArrayList<TripRecord> getAttributeValues(ArrayList<String> arrayList) {

        ArrayList<TripRecord> tripRecordsList = new ArrayList<TripRecord>(); // initialize an arraylist that holds the list of trip records
        
        // loop through the given arraylist and extract and assign variables to each of the needed attribute values from the csv file
        for (int i = 1; i < arrayList.size(); i++) {
            String[] array1 = arrayList.get(i).split(",");
           
            String pickup_DateTime = array1[4]; // variable holder for pickup_DateTime
            GPScoord pickup_Location = new GPScoord(Double.parseDouble(array1[8]), Double.parseDouble(array1[9])); // variable holder for pickup_Location
            GPScoord dropoff_Location = new GPScoord(Double.parseDouble(array1[12]), Double.parseDouble(array1[13])); // variable holder for dropoff_Location
            float trip_Distance = Float.parseFloat(array1[7]); // variable holder for the trip_Distance

            // initialize a new trip record that then store's these attribute values for every instance of i in the arraylist
            TripRecord tripRecord = new TripRecord(pickup_DateTime, pickup_Location, dropoff_Location, trip_Distance, "undefined"); 

            tripRecordsList.add(tripRecord); // add these instances to the list of trip records
        }

        return tripRecordsList;
    }
    //ignore    
    public static void main(String[] args) throws Exception {
        ReadTheFile rtf = new ReadTheFile();
        rtf.DataSet("yellow_tripdata_2009-01-15_1hour_clean.csv");

    }
}