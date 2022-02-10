/*
Name: Cameron Moreira
Student Number: 300119186
*/

import java.io.ObjectInputStream.GetField;
import java.util.Set;

class GPScoord {
    
    // variables for the starting posistion of longitude and latitude
    double startLong; 
    double startLat;

    // GPScoord instructor taking in parameters of the starting longitude and latitude positions
    public GPScoord(double startLong, double startLat) {
        this.startLong = startLong;
        this.startLat = startLat;
    }

    // getter for starting lat
    public double getStartLat() {
        return startLat;
    }
    
    // getter for starting long
    public double getStartLong() {
        return startLong;
    }
}