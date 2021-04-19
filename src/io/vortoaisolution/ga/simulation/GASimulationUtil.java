package io.vortoaisolution.ga.simulation;

public class GASimulationUtil {

    public static double getMutationProb(int cycle, int maxCycle){
        return 1 - ((double)cycle/(double)maxCycle);
    }

    public static double getRecombinationProb(int cycle, int maxCycle){
        return 0.5 + ((double)cycle/((double)maxCycle));
    }

}
