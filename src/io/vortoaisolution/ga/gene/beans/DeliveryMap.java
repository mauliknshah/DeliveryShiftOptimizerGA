package io.vortoaisolution.ga.gene.beans;
import java.util.TreeMap;


public class DeliveryMap {
    public TreeMap<Integer, DeliveryLocation> map;

    public DeliveryMap() {
        this.map = new TreeMap<Integer, DeliveryLocation>();
    }

    public DeliveryMap(TreeMap<Integer, DeliveryLocation> map) {
        this.map = map;
    }

    public double distanceBetweenTwoPoints(Integer first, Integer second){
        if (this.map.get(first) != null && this.map.get(second)!= null){
            return DeliveryLocation.distanceBetweenTwoPoints(this.map.get(first), this.map.get(second));
        } else {
            return -1;
        }
    }

    public int size(){
        return this.map.size();
    }

}
