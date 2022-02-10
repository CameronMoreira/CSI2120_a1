/*
Name: Cameron Moreira
Student Number: 300119186
*/

import java.io.File;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

class TaxiClusters {

   ArrayList<Cluster> clust = new ArrayList<Cluster>(); // initialize an array list clust 

   // constructor for TaxiCluster that takes in a file name, the min points and the epsiolon value. Reads the file and assigns the trip records the values and makes it appart of clust
   public TaxiClusters(String fileName, int minPts, double eps) {
      ReadTheFile rtf = new ReadTheFile();
      ArrayList arr = new ArrayList<>();
      try {
         arr = rtf.DataSet(fileName);
      } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      ArrayList<TripRecord> tripRecords = rtf.getAttributeValues(arr);
      
      this.clust = DBSCAN(tripRecords, eps, minPts);
      
   }

   // method to create a csv file that displays the values of the Cluster number (ID), longitude, latitude, and number of points. Will be used to display the final outputs
   public void clustCSV(ArrayList<Cluster> ac) {
      try {
         PrintWriter pWriter = new PrintWriter(new File("finalOutput.csv"));
         StringBuilder stringBuilder = new StringBuilder();
         stringBuilder.append("ClusterID");
         stringBuilder.append(",");
         stringBuilder.append("Longitude");
         stringBuilder.append(",");
         stringBuilder.append("latitude");
         stringBuilder.append(",");
         stringBuilder.append("NumPts");
         stringBuilder.append("\n");
         for (int i = 0; i < ac.size(); i++) {
            stringBuilder.append(i+ 1);
            stringBuilder.append(",");
            stringBuilder.append(ac.get(i).getAverageLong());
            stringBuilder.append(",");
            stringBuilder.append(ac.get(i).getAverageLat());
            stringBuilder.append(",");
            stringBuilder.append(ac.get(i).getTripRecordsOutputSize());
            stringBuilder.append("\n");
         }

         pWriter.write(stringBuilder.toString());
         pWriter.close();

      } catch (Exception e) {
         //TODO: handle exception
      }
   }

   // method to get the euclidean distance of the starting longitudes and latitudes of 2 different trip records
   public double distFunc(TripRecord t1, TripRecord t2) {
      double x1 = t1.getPickup_Location().getStartLat();
      double x2 = t2.getPickup_Location().getStartLat();
      double y1 = t1.getPickup_Location().getStartLong();
      double y2 = t2.getPickup_Location().getStartLong();

      double euclidean = Math.sqrt(Math.pow((x2-x1), 2) + Math.pow((y2-y1), 2));

      return euclidean;
   }
   
   // Used as apart of the DB cluster algorithm
   ArrayList<TripRecord> rangeQuery (ArrayList<TripRecord> dataBase, TripRecord Q, double eps) {
      ArrayList<TripRecord> N = new ArrayList<TripRecord>();
      for (TripRecord P: dataBase) {

         if (Q != P && distFunc(Q, P) <= eps) { // assumption made: Assuming that Q is not a neighbour of itself.
            if (!N.contains(P)) {
               N.add(P);
            }
         }
      }
      return N;
   }

   // The DBCluster algorithm
   public ArrayList<Cluster> DBSCAN(ArrayList<TripRecord> dataBase, double eps, int minPts) {

      int counter = 0; //creating a counter
      
      ArrayList<Cluster> allClusters = new ArrayList<Cluster>(); // initializing an array list for all of the clusters

      // looping through DB, and determining whether the points are to be outliers in the inititialized cluster or if they are to be apart of the cluster
      for (int i = 0; i < dataBase.size(); i++) { 

         Cluster cluster = new Cluster();

         if (dataBase.get(i).getLabel() != "undefined") { // checked to make sure the label is not undefined, if it isn't -> continue
            continue;
         }
         ArrayList<TripRecord> N = rangeQuery(dataBase, dataBase.get(i), eps);

         if (N.size() < minPts) { // does not have the minimum number of points so labeled as noise
            dataBase.get(i).setLabel("Noise");
            continue;
         }
         counter = counter + 1;
         dataBase.get(i).setLabel(counter + "");
         cluster.addCluster(dataBase.get(i));

         ArrayList<TripRecord> S = (ArrayList<TripRecord>) N.clone();
         S.remove(dataBase.get(i));

         for (int j = 0; j < S.size(); j++) { 
            if (S.get(j).getLabel() == "Noise") {
               S.get(j).setLabel(counter + "");
               cluster.addCluster(S.get(j));
            }
            if (S.get(j).getLabel() != "undefined") {
               continue;
            }
            S.get(j).setLabel(counter + "");
      
            cluster.addCluster(S.get(j));

            ArrayList<TripRecord> M = rangeQuery(dataBase, S.get(j), eps); // adding all points who reach the minPts threshold to tripRecord S
            if (M.size() >= minPts) {
               for (TripRecord F: M) {
                  if (!S.contains(F)) {
                     S.add(F);
                  }
               }
            }
         }
         allClusters.add(cluster); // add the cluster to the arrayList holding all clusters
      } 

      return allClusters;
   }

   public static void main(String[] args) throws Exception { 

      // sample output variables
      TaxiClusters taxiClusters = new TaxiClusters("yellow_tripdata_2009-01-15_1hour_clean.csv", 5, 0.0001);
      taxiClusters.clustCSV(taxiClusters.clust); 
   
      // TripRecord t1 = new TripRecord("", new GPScoord(-73.947828, 40.787172), null, 0, "");
      // TripRecord t2 = new TripRecord("", new GPScoord(-73.95469, 40.765647), null, 0, "");
      // System.out.println(distFunc(t1, t2));
     
   }
}