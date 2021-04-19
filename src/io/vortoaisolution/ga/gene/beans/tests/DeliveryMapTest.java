package io.vortoaisolution.ga.gene.beans.tests;

import io.vortoaisolution.ga.gene.beans.DeliveryLocation;
import io.vortoaisolution.ga.gene.beans.DeliveryMap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeliveryMapTest {

    @Test
    void distanceBetweenTwoPoints() {
        DeliveryMap deliveryMap = new DeliveryMap();
        DeliveryLocation location1 = new DeliveryLocation(1, 0, 0);
        DeliveryLocation location2 = new DeliveryLocation(2, 3, 4);
        deliveryMap.map.put(1, location1);
        deliveryMap.map.put(2, location2);
        assertEquals(deliveryMap.distanceBetweenTwoPoints(1,2),5.0);

    }

    @Test
    void size() {
        DeliveryMap deliveryMap = new DeliveryMap();
        DeliveryLocation location1 = new DeliveryLocation(1, 0, 0);
        DeliveryLocation location2 = new DeliveryLocation(2, 3, 4);
        assertEquals(deliveryMap.size(),0);
        deliveryMap.map.put(1, location1);
        deliveryMap.map.put(2, location2);
        assertEquals(deliveryMap.size(),2);
    }
}