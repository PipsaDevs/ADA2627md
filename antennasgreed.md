# Title

## Problem statement

As the Communications Officer of the IKS B’Moth Klingon battle cruiser your duty is to manage communications in the most efficient way. Assume you need to transmit a message $S = s_1 \dots s_m$ given as a string of $m$ symbols. For this purpose, you have r different codes. Let $b_{ij}$ be the number of bits needed to encode the $i$-th symbol of your message in the $j$-th code. Initially, the bridge transmitter is set to code #1, but you can freely change the code at any point within the message and as many times as you want. To do so, you need to send a control code which is composed of $C_{ij}$ bits if you want to switch from current code i to any other code $j$. Your goal is to determine how to send the message in the most efficient way (i.e. using the least number of bits).* Qapla’!*


$s = s_1, s_2, \dots, s_n$

$m$ different codes

$b_{ij}$ = #bits required to encode $s_i$ in the $j$-th code 

$C_{ij}$ = #bits in the control code for switching from code $i$ to code $j$

$C_{ii} = 0$

> Cotta's recommendation for investigation: Hufman

## 1. Solutions

Thesolutn we re looking for is an ordered sequence of codes used for encoding $s_i$

$$E = <e_1,e_2,\dots, \underbrace{e_i}_{\text{code used to encode } s_i
}, \dots, e_n>$$

$$\forall i \in \{1\dots n\}\colon e_i \in \{1\dots m\}$$

## 2. Objective function
What we want to do is to obtain the minimum amount of bits needed to send the message. In other words:

$$\min L(E) = \underbrace{\sum_{i=1}^n (c_{e_{i-1},e_i}+b_{i,e_i})}_{L(E,e_0)}$$

## 3. Decisions
Which code do I use for the $i$-th symbol? (Multi-way)

## 4. Optimal Substructure
We will define our optimal set as $E^* = \{e_1^*, e_2^*, \dots, e_n^*\}$

$$\forall i \in \{1\dots n\}\colon e_i^* \in \{1\dots m\}$$

$$L(E) = L^*$$

### Hypothesis: 

$E' = E^*_{2\to n}$ will be optimal for the remaining codes 2 to N, starting from $s_2$

### Proof by contradiction:

If $E'$ is not the most optimal, then there must exist $E''$ that is more optimal, that is, $L(E'') \lt L(E')$

Assume $E'' = <e_2'', e_3'', \dots, e_n''>$ starting from $s_2$ 

We will thus define $E'''$ as $E''' = E''\cup\{e_1^*\}$

The total amount of bits for this new set will thus be $L(E''')$.

We compare the two and check if the supposed optimal solution can be lower:

$$L(E''') = \cancel{e_1^*}+L(E'') < \cancel{e_1^*}+L(E') = L(E^*)$$

We are thus left with $L(E'') < L(E')$ which is impossible, since $L(E')$ is directly a subset from out optimal solution. Hence, we have proven that $E'_{2\to n}$ is optimal by contradiction

$\color{red}\text{vale hay algunas cosillas mal, para empezar que no hemos}\\\text{definido el L(E') y no se hace sumando, hay algo más de algoritmo por detrás}$ 

### NOW CORRECT 

$E^* = <e_1^*,\dots, e_i^*,\dots, e_n^*>$

$\forall i \in \{1\dots n\}\colon e_i\in{1\dots m}$

$$
\begin{align*}
L(E^*, e_0) &=  \sum_{i=1}^n(C_{e_{i-1}^*,e_i}+b_{i,e_i^*}) \\ &= L^* \text{ is opt.} \\


&=\sum^j_{i=0}(C_{e_{i-1}^*,e_i^*} + b_{i,e_i^*})
+ 
\sum^n_{i=j+1}(C_{e_{i-1}^*,e_i^*} + b_{i,e_i^*}) \\
&= L(E'',e_0) \\

&= L(E_{1\to j}^*, e_0) + L(E^*_{j+1\to n}, e^*_j)
\end{align*}$$

Hypothesis: $E^*_{j+1\to n}$ is optimal for encoding $s_{j+1} to $s_n$ using $e_j^*$ as initial code.

Assume $E' = <e_{j+1}', \dots, e_n'>$

$\forall i \in \{j+1, dots, n\}\colon e_i'\in \{1\dots m\}$

$L(E',e_j^*)< L (E_{j+1\to n}^*, e_j^*)$

Then $E'' = <\underbrace{e_1^*, \dots e_j^*}_{E_{1\to j}^*}, \underbrace{e_{j+1}', \dots, e_n'}_{E'}>$

$L(E'', e_0) = L(E^*_{1\to j}, e_0) + L(E', e_j^*)< L(E^*_{1\to j}, e_0) + L(E_{j+1\to n}^*, e_j^*)= L^*$

## 5. Subproblems
Recommendation: Look at the hypothesis.

$\boxed{L_{i,j}\text{ = minimum encoding length of symbols i..n starting from code j}}$

## 6. Bellman equation


$$
L_{i,j}=\begin{cases}
0 & i\gt n \\
\min(c_{jk} + b_{ik} + L_{i+1,k}) & i\le n \\
1\le k \le m
\end{cases}
$$

## 7. Data structure

![Data structure table](class/images/T4-antennasgreed.jpeg "da picture")

Space complexity: $\Theta(nm)$  
Time: $\Theta(nm^2)$

## 8. Algorithm

$$
\begin{array}{ll}
\textbf{for } j \leftarrow 1 \textbf{ to } m \textbf{ do} & \\
\quad L_{n+1, j} \leftarrow 0 & \\
\textbf{endfor} & \\
\textbf{for } i \leftarrow n \textbf{ downto } 1 \textbf{ do} & \longrightarrow O(n) \\
\quad \textbf{for } j \leftarrow 1 \textbf{ to } m \textbf{ do} & \longrightarrow O(m) \\
\quad\quad L_{ij} \leftarrow \infty & \\
\quad\quad \textbf{for } k \leftarrow 1 \textbf{ to } m \textbf{ do} & \longrightarrow O(m) \\
\quad\quad\quad q \leftarrow c_{jk} + b_{ik} + L_{i+1, k} & \\
\quad\quad\quad \textbf{if } q < L_{ij} \textbf{ then} & \\
\quad\quad\quad\quad L_{ij} \leftarrow q & \smash{\left.\begin{array}{l} \\ \\ \\ \\ \\ \end{array}\right\} O(1)} \\
\quad\quad\quad\quad D_{ij} \leftarrow k & \\
\quad\quad\quad \textbf{endif} & \\
\quad\quad \textbf{endfor} & \\
\quad \textbf{endfor} & \\
\textbf{endfor} &
\end{array}
$$

### RECONSTRUCTION:
$$
O(n) \left\{
\begin{array}{l}
j \leftarrow 1 \\
\textbf{for } i \leftarrow 1 \textbf{ to } n \textbf{ do} \\
\quad e_i \leftarrow D_{ij} \\
\quad j \leftarrow e_i \\
\textbf{endfor}
\end{array}
\right.
$$

# MAKE THAT MF GREEDYYYYYYYY

## 2. Objective function

$$
\min L(E) = \underbrace{\sum^n_{i=1} 


  \overbrace{\big( c_{e_{i-1}, e_i} + b_{i,e_i}\big)}^{\lambda(i,e_{i-1}, e_i)}    

}_{L(E,e_0)}
$$

## GREEDY INGREDIENTS

### Initialization
$ S \leftarrow \langle \rangle \\ last \leftarrow 1$

### Completion
$|S|=n$

### Candidates
$C(S)=\{1\dots m\}$

### Selection
$Select(<e_1,\dots,e_i>, C) = arg \min\limits_{k\in C} \lambda(i, last, k)$

### Extension
$S.add(K)\\
last \leftarrow k$


## GREEDY ALGORITHM

$$
\begin{array}{l}
S \leftarrow \langle \ \rangle \\
\text{last} \leftarrow 1 \\
\textbf{while } |S| < n \textbf{ do} \\
\quad \textbf{for } i \leftarrow 1 \textbf{ to } n \textbf{ do} \\
\quad\quad \text{min} \leftarrow \infty \\
\quad\quad \textbf{for } k \leftarrow 1 \textbf{ to } m \textbf{ do} \\
\quad\quad\quad q \leftarrow c_{\text{last}, k} + b_{i, k} \\
\quad\quad\quad \textbf{if } q < \text{min} \textbf{ then} \\
\quad\quad\quad\quad \text{min} \leftarrow q \\
\quad\quad\quad\quad \text{best} \leftarrow k \\
\quad\quad\quad \textbf{endif} \\
\quad\quad \textbf{endfor} \\
\quad\quad S.\text{add}(\text{best}) \\
\quad\quad \text{last} \leftarrow \text{best} \\
\quad \textbf{endfor} \\
\textbf{endwhile}
\end{array}
$$