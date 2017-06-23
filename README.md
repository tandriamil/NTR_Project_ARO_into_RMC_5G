NTR Project - ARO into RMC for 5G
=================================

# Context
The goal of this project was to simulate the two network resource allocation algorithms, that are MaxSNR and Round Robin, and measure the throughput and latency perceived by the simulated users connected to these access points.

We managed it by simulating this world and varying 4 parameters that are:
- The resource allocation algorithm, which is MaxSNR or Round Robin.
- The number of network cells which is 1 or 2. *Note that when there are two, users at the peripheral of each cell will perturb each other.*
- The number of users connected to each access point, going from 2 to 192.
- The time that is represented by a time slot representing 2ms, we do 200 iterations.


# Structure of the program
­In this part, we will describe the structure and the different elements of the program itself.

### Access point and Resource unity
Each **Access point** takes care of allocating a fixed number of resource unity, here it is 128, to the different simulated users by using the resource allocation algorithm.

**Resource unity** are what allows users to consume packets that they want to emit and are allocated by group of 4.

### Users and Packets
**Users** are separated into 2 groups, the users that are near and those that are far from the access point with respectively 6 and 3 mean throughput. They will try to emit packets of 100bits randomly generated, with 25% of probability to generate one at each time slot. Those packets will be stored into a buffer waiting to be entirely sent.

### Resource allocation algorithm
**Resource allocation algorithm** are designed through an interface that allows us some modularity about creating new ones. For now, only two are implemented, Round Robin and MaxSNR.


# Build and execution
You can simply build the simulation by executing `make` into the project directory and run it using `make run`.
