/*
Name: Cameron Moreira
Student Number: 300119186
*/

import java.util.ArrayList;

class Cluster {
    
    
    // creating an arrayList to hold the trip record outputs
    ArrayList<TripRecord> tripRecordsOutput;

    // constructor for cluster that inititializes the trip record output arraylist
    public Cluster() {
        tripRecordsOutput = new ArrayList<TripRecord>();
    }

    // method to get average latitude from the pickup location and starting latitude posistion
    public double getAverageLat() {
        double latHolder = 0;
        for (int i = 0; i < tripRecordsOutput.size(); i++) {
            latHolder += tripRecordsOutput.get(i).getPickup_Location().getStartLat();
        }

        return latHolder / tripRecordsOutput.size();
    }

    // method to get average longitude from the pickup location and starting longitude posistion
    public double getAverageLong() {
        double longHolder = 0;
        for (int i = 0; i < tripRecordsOutput.size(); i++) {
            longHolder += tripRecordsOutput.get(i).getPickup_Location().getStartLong();
        }

        return longHolder / tripRecordsOutput.size();
    }
    
    // getter for trip record output arraylist size
    public int getTripRecordsOutputSize() {
        return tripRecordsOutput.size();
    }

    //method to add cluster to trip record output arraylist
    public void addCluster(TripRecord t) {
        tripRecordsOutput.add(t);
    }
}