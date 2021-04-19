package io.vortoaisolution.ga.gene;

import io.vortoaisolution.ga.gene.beans.DeliveryLocation;

import java.util.Random;

public class DriverShiftGAOperations {
    public static void swapMutation(DriverShiftChromosome chromosome){
        swapMutation(chromosome,-1);
    }
    public static void swapMutation(DriverShiftChromosome chromosome, long seed){
        Random random = new Random();
        if (seed != -1){
            random.setSeed(seed);
        }
        int first = random.nextInt(chromosome.size());
        int second = 0;
        do{
            second = random.nextInt(chromosome.size());
        } while (second == first);

        DeliveryLocation temp = chromosome.locations[first];
        chromosome.locations[first] = chromosome.locations[second];
        chromosome.locations[second] = temp;
    }
    public static DriverShiftChromosome[] randomBreakMutation(DriverShiftChromosome chromosome){
        return randomBreakMutation(chromosome, -1);
    }

    public static DriverShiftChromosome[] randomBreakMutation(DriverShiftChromosome chromosome, long seed){
        Random random = new Random();
        if (seed != -1){
            random.setSeed(seed);
        }
        int breakPoint = random.nextInt(chromosome.size());
        while (breakPoint == 0 || breakPoint == chromosome.size()-1){
            breakPoint = random.nextInt(chromosome.size());
        }

        DriverShiftChromosome out1 = new DriverShiftChromosome(breakPoint+1);
        DriverShiftChromosome out2 = new DriverShiftChromosome(chromosome.size()-breakPoint-1);

        int index = 0;
        for (int i=0; i<= breakPoint; i++){
            out1.locations[index] = chromosome.locations[i];
            index++;
        }

        index= 0;
        for (int i=breakPoint+1; i< chromosome.size(); i++){
            out2.locations[index] = chromosome.locations[i];
            index++;
        }
        return new DriverShiftChromosome[]{out1, out2};
    }


    static DriverShiftChromosome[] simpleRandomPointRecombination(DriverShiftChromosome chromosome1, DriverShiftChromosome chromosome2){
        double SINGLE_OUTPUT_PROBABILITY = 0.5;
        double singleOutput = new Random().nextDouble();

        //Generate single output with 50% probability.
        if ((singleOutput<= SINGLE_OUTPUT_PROBABILITY) && (chromosome1.fitness() + chromosome2.fitness() <= chromosome1.MAX_DISTANCE)){
            DriverShiftChromosome out = new DriverShiftChromosome(chromosome1.size() + chromosome2.size());
            int count = 0;
            for(DeliveryLocation location: chromosome1.locations){
                out.locations[count] = location;
                count ++;
            }
            for(DeliveryLocation location: chromosome2.locations){
                out.locations[count] = location;
                count ++;
            }

            return new DriverShiftChromosome[]{out};
        } else {
            int intersection = new Random().nextInt(chromosome1.size()+chromosome2.size());
            while ( intersection == 0 || intersection == (chromosome1.size() + chromosome2.size()-1)){
                intersection = new Random().nextInt(chromosome1.size()+chromosome2.size());
            }

            //Initiate the recombined outputs.
            DriverShiftChromosome out1 = new DriverShiftChromosome(intersection+1);
            DriverShiftChromosome out2 = new DriverShiftChromosome(chromosome1.size() + chromosome2.size()-1 - intersection);

            int count = 0;
            for (DeliveryLocation location: chromosome1.locations){
                if (count <= intersection){
                    out1.locations[count] = location;
                } else {
                    out2.locations[count] = location;
                }
                count++;
            }

            for (DeliveryLocation location: chromosome2.locations){
                if (count <= intersection){
                    out1.locations[count] = location;
                } else {
                    out2.locations[count] = location;
                }
                count++;
            }

            //Return only if the offsprings are valid. Otherwise return null.
            if (out1.isValid() && out2.isValid()) {
                return new DriverShiftChromosome[]{out1, out2};
            } else {
                return null;
            }
        }
    }

    static DriverShiftChromosome[] uniformRecombination(DriverShiftChromosome chromosome1, DriverShiftChromosome chromosome2){
        //Decide the size.
        int size1 = (int)((chromosome1.size() + chromosome2.size())/2);
        int size2 = 0;
        if ((chromosome1.size() + chromosome2.size())%2 == 0) {
            size2 = size1;
        } else {
            size2 = size1;
            size1 += 1;
        }

        //Initiate the recombined outputs.
        DriverShiftChromosome out1 = new DriverShiftChromosome(size1);
        DriverShiftChromosome out2 = new DriverShiftChromosome(size2);

        int count = 0;
        for (DeliveryLocation location: chromosome1.locations){
            if (count%2 == 0){
                out1.locations[(int)count/2] = location;
            } else {
                out2.locations[(int)(count/2) + 1] = location;
            }
            count++;
        }

        for (DeliveryLocation location: chromosome2.locations){
            if (count%2 == 0){
                out1.locations[(int)count/2] = location;
            } else {
                out2.locations[(int)(count/2) + 1] = location;
            }
            count++;
        }

        //Return only if the offsprings are valid. Otherwise return null.
        if (out1.isValid() && out2.isValid()) {
            return new DriverShiftChromosome[]{out1, out2};
        } else {
            return null;
        }
    }

}
