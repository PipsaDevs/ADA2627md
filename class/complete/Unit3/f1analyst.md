1. Solution
2. Objective functions
3. Decisions
4. Optimal Substructure
5. Subproblems
6. Bellman equation
7. Data Structure
8. Algorithm

# 1. Solutions

The solution will be an ordered sequence composed of the velocities of each lap. It is an ordered sequence because the order matters.

S = <v_1, v_2, v_3, ..., v_N>

$v_i$ is the average speed $i \in N$

Restrictions:

$1 \le v_i \le s(f_i,d_i)$

$f_{i+1} = f_i - g(v_i, f_i) \ge 0$

$d_{i+1} = h(v_i,f_i,d_i)<100$

$f_1=F$ and $d_1=0$

# 2. Objective Function

The function that we will want to minimize is the time, which will be calculated using $L/v_i$

$$
\min T(S) = \sum_{i=1}^N\frac{L}{v_i}
$$

# 3. Decisions

The question we will ask is: “At lap i, with f kg of fuel, with tyre degradation at d %, what is the speed v i should take?” (This will give us a multiway solution)

Restriction:

$1 \le v \le s(f,d)$ (Multiway)

# 4. Optimal Substructures

We will define our optimal solution as:

$S^*=<v_1^*, v_2^*, ... ,v_n^*>$

$T(S^*)=\sum_{i=1}^N \frac{L}{v_i^*} = T^*$ will thus be optimal.

Hypothesis: $S'=S^*_{2\to n}=S^*\setminus\{v_1^*\}=<v_2^*,v_3^*,...,v_n^*>$, will be optimal for the remaining laps from 2 to N, starting from state $(f_2,d_2)$

**Proof by Contradiction:**

If $S'$ is not the most optimal, then there must be $S''$ that is more optimal. 

Assume $S''=<v_2'', v_3'', ...,v_n''>$ starting from $(f_2,d_2)$

We will define $S'''$ as $S'''=<v_1^*, v_2'', v_3'',...,v_n''>=S''\cup \{v_1^*\}$

Its total time will be:

$T(S''')=\cancel{\frac{L}{v_1^*}}+T(S'')<\cancel{\frac{L}{v_1^*}}+T(S')=T(S^*)$yt

$T(S''')<T(S^*)$ is not possible because $S^*$ is the optimal set, hence we have proven that $S'_{2\to n}$ is optimal by contradiction.

# 5. Subproblems

$T_{i, f, d}$ shall be the minimum time required to complete laps from i..N, starting from lap i, with f kg of fuel and tyre degradation d.

Lap index: $i \in \{1,...,N+1\}$

Fuel level: $f\in\{F,...,0\}$

Tyre degradation: $d\in\{0,...,99\}$

What we want to compute at the beginning of a problem is $T_{1,F,0}$

# 6. Bellman equation

The bellman equation states as follows:

$$
T_{i,f,d}=
\begin{cases}
0 & \text{if } i > N \\
\infin & \text{if } i \le N \text{ and } s(f,d)=0 \\
min(\frac{L}{v}+T_{i+1,f-g(v,f),h(v,f,d)}) & \text{if }i\le N \text{ and }f > 0, d < 100

\end{cases}
$$

# 7. Data structure

Our data structure will be a 3D matrix composed of the indices i, f and d.

Our 3D Matrix will be defined as $T_{i,f,d}$, that will go from 1 to N+1 for i, from 0 to F for f, and from 0 to 99 for d. In other words, T[1..N+1][0..F][0..99]

We will have another 3D matrix that will be defined as $V_{i,f,d}$. And this one will go from V[1..N][0..F][0..99]

We would fill the rows with i greater than N (in other words, N+1), with 0.

We would also fill the rows with f = 0 or d = 100 with infinity.

Space complexity: $\Theta(N·F·D_{max})$, with $D_{max}=100$. The reason we put D_{max}=100 is because in a normal case we would want the actual range of the tire degradation. 100 would be the worst case, which would end the race

Time complexity: $\Theta(N·F·D_{max}·V_{max})$

# 8. Algorithm

```java
// Table initialization (Base cases)
for f <- 0 to F do
	for d <- 0 to 99 do
		T[N+1,f,d] <- 0
	endfor
endfor

// Bottom-up table reconstruction (and initialize some base cases)
for i <- N down to 1 do
	for f <- 0 to F do
		for d <- 99 down to 0 do
			minTime <- infinity
			bestV <- -1
			
			maxSpeed <- s(f,d)
			for v <- 1 to maxSpeed do
				fuelUsed <- g(v,f)
				nextDeg <- h(v,f,d)
				
				if fuelUsed <= f and nextDeg < 100 then
				
					remTime <- T[i+1, f-fuelUsed, nextDeg]
					if remTime != infinity then
						currentTime <- (L/v) + remTime
						if currentTime < minTime then
							minTime <- currentTime
							bestV <- v
						endif
					endif
				endif
			endfor
			
			T[i,f,d] <- minTime
			V[i,f,d] <- bestV
		endfor
	endfor
endfor

// Solution reconstruction
S* <- <0,...,0> // length N
f <- F
d <- 0
for i <- 1 to N do
	v <- V[i, f, d]
	S*[i] <- v
	d <- h(v,f,d)
	f <- f - g(v,f)
endfor
```