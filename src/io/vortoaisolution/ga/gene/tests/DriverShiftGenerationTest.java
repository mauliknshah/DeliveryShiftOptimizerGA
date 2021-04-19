package io.vortoaisolution.ga.gene.tests;

import io.vortoaisolution.ga.gene.DriverShiftChromosome;
import io.vortoaisolution.ga.gene.DriverShiftGeneration;
import io.vortoaisolution.ga.gene.beans.DeliveryLocation;
import io.vortoaisolution.ga.gene.beans.DeliveryMap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DriverShiftGenerationTest {
    DeliveryMap getDeliveryMap(){
        DeliveryMap deliveryMap = new DeliveryMap();
        DeliveryLocation location1 = new DeliveryLocation(1, 3, 4);
        DeliveryLocation location2 = new DeliveryLocation(2, 6, 8);
        DeliveryLocation location3 = new DeliveryLocation(3, 4, 3);
        DeliveryLocation location4 = new DeliveryLocation(4, 8, 6);
        deliveryMap.map.put(location1.getLocID(), location1);
        deliveryMap.map.put(location2.getLocID(), location2);
        deliveryMap.map.put(location3.getLocID(), location3);
        deliveryMap.map.put(location4.getLocID(), location4);
        return deliveryMap;
    }

    DriverShiftGeneration getGeneration(){
        DriverShiftGeneration generation = new DriverShiftGeneration();

        DriverShiftChromosome chromosome = new DriverShiftChromosome(2);
        DeliveryLocation[] locations = new DeliveryLocation[2];
        locations[0] = new DeliveryLocation(1, 3, 4);
        locations[1] = new DeliveryLocation(2, 6, 8);
        chromosome.locations = locations;

        DriverShiftChromosome chromosome2 = new DriverShiftChromosome(2);
        DeliveryLocation[] locations2 = new DeliveryLocation[2];
        locations2[0] = new DeliveryLocation(3, 4, 3);
        locations2[1] = new DeliveryLocation(4, 8, 6);
        chromosome2.locations = locations2;

        generation.driverShifts.add(chromosome);
        generation.driverShifts.add(chromosome2);

        return generation;
    }


    @Test
    void testPopulationDistance() {
        assertEquals(getGeneration().populationDistance(), 40);
    }

    @Test
    void testIsValid() {
        assertEquals(getGeneration().isValid(getDeliveryMap()), true);

        DeliveryMap deliveryMap = getDeliveryMap();
        deliveryMap.map.put(5, new DeliveryLocation(5,5,5));
        assertEquals(getGeneration().isValid(deliveryMap), false);
    }

    @Test
    void testListDuplicates() {
        DriverShiftGeneration generation = getGeneration();
        DriverShiftChromosome chromosome = new DriverShiftChromosome(1);
        DeliveryLocation[] locations = new DeliveryLocation[1];
        locations[0] = new DeliveryLocation(1, 3, 4);
        chromosome.locations = locations;
        generation.driverShifts.add(chromosome);

        DeliveryMap deliveryMap = getDeliveryMap();
        assertEquals(generation.listDuplicates(deliveryMap).get(0), 1);
    }
}