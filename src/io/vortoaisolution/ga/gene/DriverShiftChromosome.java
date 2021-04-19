package io.vortoaisolution.ga.gene;

import io.vortoaisolution.ga.gene.beans.DeliveryLocation;
import io.vortoaisolution.ga.gene.interfaces.ChromosomeInterface;

public class DriverShiftChromosome implements ChromosomeInterface {
    public DeliveryLocation[] locations;
    public final double MAX_DISTANCE = 60*12;
    public final double SHIFT_PENALTY = 500;

    public DriverShiftChromosome(int size) {
        this.locations = new DeliveryLocation[size];
    }

    public DriverShiftChromosome(DeliveryLocation[] locations) {
        this.locations = locations;
    }

    @Override
    public double fitness() {
        double fitness = 0;
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

        //Fitness function.
        //Punishes less distance travelled in the shift.
        //Punishes for having a shift.
        //Reduces fitness value for doing more locations.
        fitness = (distance/this.locations.length) + (MAX_DISTANCE-distance) + SHIFT_PENALTY;

        return fitness;
    }

    @Override
    public void clean() {
        this.locations = new DeliveryLocation[this.locations.length];
    }

    public int size(){
        return this.locations.length;
    }

    public boolean isValid(){
        return this.fitness() < this.MAX_DISTANCE;
    }
}
