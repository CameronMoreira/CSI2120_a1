/*
Name: Cameron Moreira
Student Number: 300119186
*/

import java.util.Set;

class TripRecord {

    // variable holders for needed attributes
    String pickup_DateTime;
    GPScoord pickup_Location;
    GPScoord dropoff_Location;
    float trip_Distance;

    String label; // holder for the label that will be assigned to each point

    // constructer for trip record taking in parameters of required attributes from csv file
    public TripRecord(String pickup_DateTime, GPScoord pickup_Location, GPScoord dropoff_Location, float trip_Distance, String label) {
        this.dropoff_Location = dropoff_Location;
        this.pickup_DateTime = pickup_DateTime;
        this.trip_Distance = trip_Distance;
        this.pickup_Location = pickup_Location;
        this.label = label;
    }

    // setters and getters for respected attributes

    public GPScoord getDropoff_Location() {
        return dropoff_Location;
    }

    public String getLabel() {
        return label;
    }

    public String getPickup_DateTime() {
        return pickup_DateTime;
    }

    public GPScoord getPickup_Location() {
        return pickup_Location;
    }

    public float getTrip_Distance() {
        return trip_Distance;
    }

    public void setDropoff_Location(GPScoord dropoff_Location) {
        this.dropoff_Location = dropoff_Location;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setPickup_DateTime(String pickup_DateTime) {
        this.pickup_DateTime = pickup_DateTime;
    }

    public void setPickup_Location(GPScoord pickup_Location) {
        this.pickup_Location = pickup_Location;
    }

    public void setTrip_Distance(float trip_Distance) {
        this.trip_Distance = trip_Distance;
    }

}