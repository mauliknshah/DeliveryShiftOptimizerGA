package io.vortoaisolution.ga.gene.tests;

import io.vortoaisolution.ga.gene.DriverShiftChromosome;
import io.vortoaisolution.ga.gene.DriverShiftGAOperations;
import io.vortoaisolution.ga.gene.beans.DeliveryLocation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DriverShiftGAOperationsTest {
    DriverShiftChromosome getChromosome(){
        DriverShiftChromosome chromosome = new DriverShiftChromosome(5);
        DeliveryLocation[] locations = new DeliveryLocation[5];
        locations[0] = new DeliveryLocation(1, 3, 4);
        locations[1] = new DeliveryLocation(2, 6, 8);
        locations[2] = new DeliveryLocation(3, 4, 3);
        locations[3] = new DeliveryLocation(4, 8, 6);
        locations[4] = new DeliveryLocation(5, 5, 5);
        chromosome.locations = locations;
        return chromosome;
    }

    @Test
    void testSwapMutation() {
        DriverShiftChromosome chromosome = getChromosome();
        DriverShiftGAOperations.swapMutation(chromosome, 1);
        assertEquals(chromosome.locations[0].getLocID(), 4);
        assertEquals(chromosome.locations[2].getLocID(), 3);
    }

    @Test
    void testRandomBreakMutation() {
        DriverShiftChromosome chromosome = getChromosome();
        DriverShiftChromosome[] mutatedChromosomes= DriverShiftGAOperations.randomBreakMutation(chromosome, 1);
        assertEquals(mutatedChromosomes[0].locations[0].getLocID(), 1);
        assertEquals(mutatedChromosomes[1].locations[0].getLocID(), 5);
    }

    @Test
    void testSimpleRandomPointRecombination() {
        DriverShiftChromosome chromosome = new DriverShiftChromosome(2);
        DeliveryLocation[] locations = new DeliveryLocation[2];
        locations[0] = new DeliveryLocation(1, 3, 4);
        locations[1] = new DeliveryLocation(2, 6, 8);
        chromosome.locations = locations;

        DriverShiftChromosome chromosome1 = new DriverShiftChromosome(2);
        DeliveryLocation[] locations1 = new DeliveryLocation[2];
        locations1[0] = new DeliveryLocation(3, 4, 3);
        locations1[1] = new DeliveryLocation(4, 8, 6);
        chromosome1.locations = locations1;

        DriverShiftChromosome chromosome2 = new DriverShiftChromosome(3);
        DeliveryLocation[] locations2 = new DeliveryLocation[3];
        locations2[0] = new DeliveryLocation(1, 3, 4);
        locations2[1] = new DeliveryLocation(2, 6, 8);
        locations2[2] = new DeliveryLocation(5, 5, 5);
        chromosome2.locations = locations2;

        DriverShiftChromosome[] recombined = DriverShiftGAOperations.simpleRandomPointRecombination(chromosome, chromosome1,1);
        assertEquals(recombined[0].locations[0].getLocID(),1);
        assertEquals(recombined[0].locations[1].getLocID(),2);
        assertEquals(recombined[0].locations[2].getLocID(),3);
        assertEquals(recombined[1].locations[0].getLocID(),4);

        recombined = DriverShiftGAOperations.simpleRandomPointRecombination(chromosome2, chromosome1,123456);
        assertEquals(recombined[0].locations[0].getLocID(),1);
        assertEquals(recombined[0].locations[1].getLocID(),2);
        assertEquals(recombined[0].locations[2].getLocID(),5);
        assertEquals(recombined[0].locations[3].getLocID(),3);
        assertEquals(recombined[0].locations[4].getLocID(),4);

    }

    @Test
    void testUniformRecombination() {
        DriverShiftChromosome chromosome = new DriverShiftChromosome(2);
        DeliveryLocation[] locations = new DeliveryLocation[2];
        locations[0] = new DeliveryLocation(1, 3, 4);
        locations[1] = new DeliveryLocation(2, 6, 8);
        chromosome.locations = locations;

        DriverShiftChromosome chromosome1 = new DriverShiftChromosome(2);
        DeliveryLocation[] locations1 = new DeliveryLocation[2];
        locations1[0] = new DeliveryLocation(3, 4, 3);
        locations1[1] = new DeliveryLocation(4, 8, 6);
        chromosome1.locations = locations1;

        DriverShiftChromosome chromosome2 = new DriverShiftChromosome(3);
        DeliveryLocation[] locations2 = new DeliveryLocation[3];
        locations2[0] = new DeliveryLocation(1, 3, 4);
        locations2[1] = new DeliveryLocation(2, 6, 8);
        locations2[2] = new DeliveryLocation(5, 5, 5);
        chromosome2.locations = locations2;


        DriverShiftChromosome[] recombined = DriverShiftGAOperations.uniformRecombination(chromosome, chromosome1);
        assertEquals(recombined[0].locations[0].getLocID(),1);
        assertEquals(recombined[0].locations[1].getLocID(),3);
        assertEquals(recombined[1].locations[0].getLocID(),2);
        assertEquals(recombined[1].locations[1].getLocID(),4);

        DriverShiftChromosome[] recombined1 = DriverShiftGAOperations.uniformRecombination(chromosome2, chromosome1);
        assertEquals(recombined1[0].locations[0].getLocID(),1);
        assertEquals(recombined1[0].locations[1].getLocID(),5);
        assertEquals(recombined1[0].locations[2].getLocID(),4);
        assertEquals(recombined1[1].locations[0].getLocID(),2);
        assertEquals(recombined1[1].locations[1].getLocID(),3);
    }
}