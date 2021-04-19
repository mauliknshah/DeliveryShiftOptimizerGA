package io.vortoaisolution.ga.gene;

import io.vortoaisolution.ga.gene.beans.DeliveryLocation;
import io.vortoaisolution.ga.gene.beans.DeliveryMap;

import java.util.ArrayList;

public class DriverShiftGeneration {
    public ArrayList<DriverShiftChromosome> driverShifts;

    public DriverShiftGeneration(ArrayList<DriverShiftChromosome> driverShifts) {
        this.driverShifts = driverShifts;
    }

    public void setDriverShifts(ArrayList<DriverShiftChromosome> driverShifts) {
        this.driverShifts = driverShifts;
    }

    public void setDriverShift(int index, DriverShiftChromosome driverShift) {
        if (!(index < 0 || index >= this.driverShifts.size())) {
            this.driverShifts.set(index, driverShift);
        }
    }

    public ArrayList<DriverShiftChromosome> getDriverShifts() {
        return driverShifts;
    }

    public DriverShiftChromosome getDriverShifts(int index) {
        if (index < 0 || index >= this.driverShifts.size()){
            return null;
        }
        return this.driverShifts.get(index);
    }

    public double populationFitness() {
        double fitness = 0;
        for(int i=0; i < this.driverShifts.size(); i++){
            fitness += driverShifts.get(i).fitness();
        }
        return fitness;
    }

    public boolean isValid(DeliveryMap map) {
        boolean[] validity = new boolean[map.size()];
        for(DriverShiftChromosome driverShiftChromosome: this.driverShifts){
            for(DeliveryLocation location: driverShiftChromosome.locations){
                if (validity[location.getLocID()]) {
                    return false;
                } else {
                    validity[location.getLocID()] = true;
                }
            }
        }
        return true;
    }

    public DeliveryLocation[] listInvalid(DeliveryMap map) {
        ArrayList<DeliveryLocation> invlaidLocations = new ArrayList<DeliveryLocation>();
        boolean[] validity = new boolean[map.size()];
        for(DriverShiftChromosome driverShiftChromosome: this.driverShifts){
            for(DeliveryLocation location: driverShiftChromosome.locations){
                if (validity[location.getLocID()]) {
                    invlaidLocations.add(location);
                } else {
                    validity[location.getLocID()] = true;
                }
            }
        }
        return (DeliveryLocation[]) invlaidLocations.toArray();
    }


}
