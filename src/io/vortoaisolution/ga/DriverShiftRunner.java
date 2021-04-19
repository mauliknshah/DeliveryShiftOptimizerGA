package io.vortoaisolution.ga;
import io.vortoaisolution.ga.gene.DriverShiftGeneration;
import io.vortoaisolution.ga.gene.beans.DeliveryLocation;
import io.vortoaisolution.ga.gene.beans.DeliveryMap;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import io.vortoaisolution.ga.simulation.DriverShiftSimulation;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DriverShiftRunner {
    static DeliveryMap getMapFromJson(){
        DeliveryMap deliveryMap = new DeliveryMap();
        JSONParser parser = new JSONParser();
        JSONArray array = null;
        try {
            array = (JSONArray) parser.parse(new FileReader("resources/testSmall.json"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Object o : array)
        {
            JSONObject location = (JSONObject) o;
            long id = (Long) location.get("ID");
            JSONArray coordinates = (JSONArray) location.get("Location");
            double x = (Double) coordinates.get(0);
            double y = (Double) coordinates.get(1);
            DeliveryLocation deliveryLocation = new DeliveryLocation((int)id, x, y);
            deliveryMap.map.put((int)id, deliveryLocation);
        }
        return deliveryMap;
    }

    public static void main(String[] args){
        int MAX_CYCLE = 10;
        long MAX_TIME = 30000;
        DeliveryMap deliveryMap = getMapFromJson();
        DriverShiftSimulation simulation = new DriverShiftSimulation();
        DriverShiftGeneration generation = simulation.run(MAX_CYCLE, MAX_TIME, deliveryMap);
        //TODO: Convert generation to JSON.
    }

}
