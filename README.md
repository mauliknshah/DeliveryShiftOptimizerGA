## Delivery Shifts with Genetic Algorithms

The codebase creates a genetic algorithm for scheduling DriversShifts. It has following properties:

```Chromosome```: A single unit chromosome is a shift of delivery, which includes an array of ```DeliveryLocation```.

```Generation```: It follows **Pittsburgh** approach to keep an entire solution as a generation. In other words, all the shifts in combination would have all the possible delivery location. 

Both, chromosome can be found in ```<>/gene/DriverShiftChromosome``` and generation is in ```<>/gene/DriverShiftGeneration``` class.


```Mutation```: Two sets of mutations are used here. 
1. Swap Mutation: Swaps two random locations in a chromosome.
2. BreakDown Mutation: Random split of a chromosome into two.

```Recombination```:  Two sets of recombination operators used as well.
1. Uniform: Uniformly recombines two chromosomes to create two new children.
2. SimpleRandomPoint: Using a random probability number, this recombination would either merge two chromosomes into one child, or would create two children using point combination.

Both, mutation and recombinations are available in ```<>/gene/DriverShiftGAOperations```.

```Mutation and Recombination Rates```: Mutation rates decreases with the cycle, while the recombination rates increases. They are available in ```<>/simulation/GASimulationUtil``` class.

```Simulation```: ```<>/simulation/DriverShiftSimulation``` not only randomly initiates chromosomes, but runs evolution until either the maximum cycles or maximum time is met. 

```Running the code```: ```<>/DriverShiftRunner``` is the gateway to run this simulation.