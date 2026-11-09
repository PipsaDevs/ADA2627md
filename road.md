We have a road that goes from $g_0$ to $g_n$, the cost of going from $g_{i-1]}$ to $g_i$ is $c_i$, $g_0$ is $D$

## 1. Solutions

$S \subseteq \{0,\dots,n\}$

$0 \in S$

$n \in S$

$S = \{S_1,\dots, S_k\}$

$\forall i\in \{2,k\}, C(s_{i-1},S_i) \le D$ ($\color{red}\subseteq $ or $\color{red}\le$??)

give tht $C(i,j) = \sum_{k=i+1}^j c_k$

## 2. Objective function
$\min f(S) = |S|$

## 3. Decisions
- Shall I stop at the current gas station? (Binary)
- In which gas station shall I stop ? (Multiway)

## 4. Optimal substructure
$S^* = \{s_1^*, s_2^*, \dots, s_i, \dots s_k^*\}$

$S_1^* = 0, S_k^* = 1$

Hypothesis: $S' = \{s_i^*, s_{i+1}^*,\dots,s_k^*\}$ is optimal for going from $S_i^*$ to $S_k^* (= n)$

## 5. subproblems
$$
\boxed{F_i = \text{minimal number of stops for going from } g_i \text{ to } g_n }
$$

## 6. Bellman equation

$$ 
F_i = 
\begin{cases} 
1 & i=n \\
\min \left(1+F_k \right) & i\lt n
\end{cases}
$$

$\empty$