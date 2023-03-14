package tn.esprit.pidevcrashcode.Configuration;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.List;



public class Ia {



    public class CsvReader {

        public  void main(String[] args) throws IOException {
            String fileName = "camping_data.csv";
            FileReader reader = new FileReader(fileName);
            CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader());
            List<CSVRecord> records = parser.getRecords();

            for (CSVRecord record : records) {
                String gender = record.get("Gender");
                int age = Integer.parseInt(record.get("Age"));
                String state = record.get("State");
                int kayaking = Integer.parseInt(record.get("Kayaking"));
                int canoeing = Integer.parseInt(record.get("Canoeing"));
                int rafting = Integer.parseInt(record.get("Rafting"));
                int fishing = Integer.parseInt(record.get("Fishing"));
                int snorkeling = Integer.parseInt(record.get("Snorkeling"));
                int scubaDiving = Integer.parseInt(record.get("Scuba diving"));
                int swimming = Integer.parseInt(record.get("Swimming"));
                int waterSkiing = Integer.parseInt(record.get("Water skiing"));
                int wakeboarding = Integer.parseInt(record.get("Wakeboarding"));
                int rockClimbing = Integer.parseInt(record.get("Rock climbing"));
                int bouldering = Integer.parseInt(record.get("Bouldering"));
                int trailRunning = Integer.parseInt(record.get("Trail running"));
                int mountainBiking = Integer.parseInt(record.get("Mountain biking"));
                int camelTrekking = Integer.parseInt(record.get("Camel trekking"));
                int sandboarding = Integer.parseInt(record.get("Sandboarding"));
                int duneBashing = Integer.parseInt(record.get("Dune bashing"));
                int hotAirBalloonRiding = Integer.parseInt(record.get("Hot air balloon riding"));
                int atvTours = Integer.parseInt(record.get("ATV tours"));
                int birdWatching = Integer.parseInt(record.get("Bird watching"));
                int mushroomHunting = Integer.parseInt(record.get("Mushroom hunting"));
                int treeClimbing = Integer.parseInt(record.get("Tree climbing"));
                int zipLining = Integer.parseInt(record.get("Zip lining"));

                // Do something with the data, such as storing it in a database or analyzing it.
            }

            parser.close();
        }
    }

}
