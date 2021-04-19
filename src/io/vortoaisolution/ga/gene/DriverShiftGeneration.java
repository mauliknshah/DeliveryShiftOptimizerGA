package io.vortoaisolution.ga.gene;

import io.vortoaisolution.ga.gene.beans.DeliveryLocation;
import io.vortoaisolution.ga.gene.beans.DeliveryMap;

import java.util.ArrayList;

public class DriverShiftGeneration {
    public ArrayList<DriverShiftChromosome> driverShifts;

    public DriverShiftGeneration() {
        this.driverShifts = new ArrayList<DriverShiftChromosome>();
    }

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
        int count = 0;
        for(DriverShiftChromosome driverShiftChromosome: this.driverShifts){
            for(DeliveryLocation location: driverShiftChromosome.locations){
                if (location.getLocID() < map.size() && validity[location.getLocID()-1]) {
                    return false;
                } else {
                    validity[location.getLocID()-1] = true;
                }
                count += 1;
            }
        }

        if (count != map.size()) {
            return false;
        } else {
            return true;
        }
    }

    public ArrayList<Integer> listDuplicates(DeliveryMap map) {
        ArrayList<Integer> invlaidLocations = new ArrayList<Integer>();
        boolean[] validity = new boolean[map.size()];
        for(DriverShiftChromosome driverShiftChromosome: this.driverShifts){
            for(DeliveryLocation location: driverShiftChromosome.locations){
                if (validity[location.getLocID()-1]) {
                    invlaidLocations.add(location.getLocID());
                } else {
                    validity[location.getLocID()-1] = true;
                }
            }
        }
        return invlaidLocations;
    }


}
