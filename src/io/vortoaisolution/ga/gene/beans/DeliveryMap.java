package io.vortoaisolution.ga.gene.beans;
import java.util.HashMap;


public class DeliveryMap {
    public HashMap<Integer, DeliveryLocation> map;

    public DeliveryMap() {
        this.map = new HashMap<Integer, DeliveryLocation>();
    }

    public DeliveryMap(HashMap<Integer, DeliveryLocation> map) {
        this.map = map;
    }

    public double distanceBetweenTwoPoints(Integer first, Integer second){
        if (this.map.get(first) != null && this.map.get(second)!= null){
            return DeliveryLocation.distanceBetweenTwoPoints(this.map.get(first), this.map.get(second));
        } else {
            return -1;
        }
    }

}
