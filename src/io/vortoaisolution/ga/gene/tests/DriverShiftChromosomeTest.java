package io.vortoaisolution.ga.gene.tests;

import io.vortoaisolution.ga.gene.DriverShiftChromosome;
import io.vortoaisolution.ga.gene.beans.DeliveryLocation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DriverShiftChromosomeTest {
    DriverShiftChromosome getChromosome(){
        DriverShiftChromosome chromosome = new DriverShiftChromosome(2);
        DeliveryLocation[] locations = new DeliveryLocation[2];
        locations[0] = new DeliveryLocation(1, 3, 4);
        locations[1] = new DeliveryLocation(2, 6, 8);
        chromosome.locations = locations;
        return chromosome;
    }

    @Test
    void testFitness() {

        //Distance = 20, location = 2  ==> 20/2 = 10
        //MAX_DISTANCE - distance = 700
        //SHIFT PENALTY = 500
        assertEquals(getChromosome().fitness(), 1210.0);
    }

    @Test
    void clean() {
        DriverShiftChromosome chromosome = getChromosome();
        int size = chromosome.size();
        chromosome.clean();
        assertEquals(chromosome.size(), size);
        assertEquals(chromosome.locations[0], null);
    }

    @Test
    void size() {
        assertEquals(getChromosome().size(), 2);
    }

    @Test
    void isValid() {
        assertEquals(getChromosome().isValid(), true);
        DriverShiftChromosome chromosome = new DriverShiftChromosome(2);
        DeliveryLocation[] locations = new DeliveryLocation[2];
        locations[0] = new DeliveryLocation(1, 3, 4);
        locations[1] = null;
        chromosome.locations = locations;
        assertEquals(chromosome.isValid(), false);
        chromosome.locations[1] = new DeliveryLocation(2,10000,10000);
        assertEquals(chromosome.isValid(), false);
    }
}