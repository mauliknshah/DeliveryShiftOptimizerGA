package io.vortoaisolution.ga.gene.beans.tests;


import io.vortoaisolution.ga.gene.beans.DeliveryLocation;

import static org.junit.jupiter.api.Assertions.*;

class DeliveryLocationTest {
    @org.junit.jupiter.api.Test
    void testDistanceFrom() {
        DeliveryLocation location1 = new DeliveryLocation(1, 0, 0);
        DeliveryLocation location2 = new DeliveryLocation(2, 3, 4);
        assertEquals(location1.distanceFrom(location2), 5.0);
    }

    @org.junit.jupiter.api.Test
    void testDistanceBetweenTwoPoints() {
        DeliveryLocation location1 = new DeliveryLocation(1, 0, 0);
        DeliveryLocation location2 = new DeliveryLocation(2, 3, 4);
        assertEquals(DeliveryLocation.distanceBetweenTwoPoints(location1,location2), 5.0);
    }
}