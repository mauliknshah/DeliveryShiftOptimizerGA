package io.vortoaisolution.ga.gene;

import io.vortoaisolution.ga.gene.beans.DeliveryLocation;
import io.vortoaisolution.ga.gene.interfaces.ChromosomeInterface;

public class DriverShiftChromosome implements ChromosomeInterface {
    public DeliveryLocation[] locations;

    public DriverShiftChromosome(int size) {
        this.locations = new DeliveryLocation[size];
    }

    public DriverShiftChromosome(DeliveryLocation[] locations) {
        this.locations = locations;
    }

    @Override
    public double fitness() {
        double distance = 0;
        for(int i = 1; i < this.locations.length; i++){
            double currentDistance = DeliveryLocation.distanceBetweenTwoPoints(this.locations[i], this.locations[i-1]);
            if (currentDistance == -1) {
                return -1;
            } else {
                distance += currentDistance;
            }
        }
        //Returning distance.
        distance += DeliveryLocation.distanceBetweenTwoPoints(this.locations[0], this.locations[this.locations.length-1]);
        return distance;
    }

    @Override
    public void clean() {
        this.locations = new DeliveryLocation[this.locations.length];
    }
}
