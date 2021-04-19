package io.vortoaisolution.ga.simulation;

import io.vortoaisolution.ga.gene.DriverShiftChromosome;
import io.vortoaisolution.ga.gene.DriverShiftGAOperations;
import io.vortoaisolution.ga.gene.DriverShiftGeneration;
import io.vortoaisolution.ga.gene.beans.DeliveryLocation;
import io.vortoaisolution.ga.gene.beans.DeliveryMap;

import java.util.Date;
import java.util.Random;

public class DriverShiftSimulation {
    public DriverShiftGeneration run(int maxCycle, long maxTimeInMili, DeliveryMap deliveryMap){
        DriverShiftGeneration generation = randomGenerationInitialization(deliveryMap);
        System.out.println("Initial Number of Shifts: "  + generation.driverShifts.size()
                + " Total Duration:" + generation.populationDistance());
        int cycle = 0;
        long beginTime = new Date().getTime();

        while (cycle < maxCycle && maxTimeInMili > (new Date().getTime() - beginTime)) {
             step(deliveryMap, generation, cycle, maxCycle);
//             if (cycle%10 == 0){
                 System.out.println("Cycle:" + cycle + " Number of Shifts:" + generation.driverShifts.size()
                         + " Total Duration:" + generation.populationDistance());
//             }
             cycle++;
        }
        return generation;
    }

    private DriverShiftGeneration randomGenerationInitialization(DeliveryMap deliveryMap){
        DriverShiftGeneration generation = new DriverShiftGeneration();
        int sum = 0;
        //Start with 5 location sized shifts and sequential initialization.
        for (int i=1; i <= deliveryMap.size(); i = i+5){
            int size = 5;
            DeliveryLocation[] locations = new DeliveryLocation[size];
            for(int j=0; j<size;j++){
                locations[j] = deliveryMap.map.get(i+j);
            }
            DriverShiftChromosome chromosome = new DriverShiftChromosome(size);
            chromosome.locations = locations;
            System.out.println("Shift Minutes:" + chromosome.shiftDistance());
            generation.driverShifts.add(chromosome);
            sum += chromosome.size();
        }
        return generation;
    }

    private DriverShiftGeneration step(DeliveryMap deliveryMap, DriverShiftGeneration generation, int cycle, int maxCycle){
        Random random = new Random();
        if (random.nextDouble() < GASimulationUtil.getMutationProb(cycle, maxCycle)){
            if(random.nextDouble() <= 0.5){
                int shift = random.nextInt(generation.driverShifts.size());
                DriverShiftChromosome mutated = DriverShiftGAOperations.swapMutation(generation.driverShifts.get(shift));
                if (mutated.fitness() < generation.driverShifts.get(shift).fitness()){
                    generation.driverShifts.set(shift,mutated);
                }
            } else {
                int shift = random.nextInt(generation.driverShifts.size());
                DriverShiftChromosome[] mutated = DriverShiftGAOperations.randomBreakMutation(generation.driverShifts.get(shift));
                if (mutated != null){
                    generation.driverShifts.set(shift,mutated[0]);
                    generation.driverShifts.add(mutated[1]);
                }
            }
        }

        if (random.nextDouble() < GASimulationUtil.getRecombinationProb(cycle, maxCycle)){
            DriverShiftChromosome[] recombined = null;
            int first = random.nextInt(generation.driverShifts.size());
            int second = first;
            do{
                second = random.nextInt(generation.driverShifts.size());
            } while(first == second);
            if(random.nextDouble() <= 0.5){
                recombined = DriverShiftGAOperations.uniformRecombination(
                        generation.driverShifts.get(first), generation.driverShifts.get(second));
            } else {
                recombined = DriverShiftGAOperations.simpleRandomPointRecombination(
                        generation.driverShifts.get(first), generation.driverShifts.get(second));
            }

            if (recombined != null){
                if (recombined.length == 1){
                    if (recombined[0].fitness() <
                            generation.driverShifts.get(first).fitness() + generation.driverShifts.get(second).fitness()){
                        generation.driverShifts.remove(first);
                        generation.driverShifts.remove(second);
                        generation.driverShifts.add(recombined[0]);
                    }
                } else {
                    if (recombined[0].fitness() + recombined[1].fitness() <
                            generation.driverShifts.get(first).fitness() + generation.driverShifts.get(second).fitness()){
                        generation.driverShifts.set(first, recombined[0]);
                        generation.driverShifts.set(second, recombined[1]);
                    }
                }
            }
        }

        return generation;
    }

}
