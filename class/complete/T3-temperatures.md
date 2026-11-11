## PROBLEM STATEMENT

Assume that you are in a research station in antarctica and you need to adjust the temperature of the station and for this purpose you have gathered information from all the equipment in the station and you've come to the ... how comfortable it would be to have certain temperatures at certain moments

$c_{ij}$ = comfort at time $i$ if temp is $j \text{ºC}$

we need to operate the control of the temperature

the temperature can be between $T_{min}$ and $T_{max}$ -> min/max temp

and you cannot just adjust the temperature arbitrarily, you can only adjust it up / down by $k$ degrees 

$\pm k$ degrees at most of temp change at any time

we can leave it as is

let's assume that time goes from $1 \dots n$

at a certain time we have 1 temperature and after a time i it can be a different temperature

if $k$ for example is $3$ and temperature is $20ºC$, in the next moment, temperature can be $17ºC$, $23ºC$, or anything in between

at the beginning we can set up the temperature and then operate on it

## 1. Solutions

$S=(\text{t}_0,<\xi_1,\dots,\xi_n>)$

$t_i = t_{i-1}+\xi_{i} \qquad (1\le i \le n)$

$\color{hotpink}\text{This is the temperature range constraint:}$

$T_{min} \le t_i \le T_{max} \qquad (1 \le i \le n)$

$k \le \xi \le k \qquad (1\le i\le n )$

## 2. Objective function  

$\max F(S) = \sum_{i=1}^n c_{i,t_i}$


## 3. Decisions

- By how many degrees shall I change the temperature at time $i$? (Multiway)


## 4. Optimal substructure

$S^* = (t_0^*,<\xi_1^*,\dots, \xi_n^*>) \\ \hookrightarrow t_0^*,t_1^*,\dots,t_n^*$

$F(S^*)=\sum_{i=1}^n c_{i, t_i^*}=C^* \text{ is optimal}$

$\Xi_{2\rightarrow n}^A=<\xi_2^*,\cdots,\xi_n^*>$

$F((t_1^*, \Xi^*_{i=2}))=\sum_{i=2}^n c_{i,t_i^*}=C^*-c_{1,t_1^*}$

**Hyp:** $\Xi^*_{2\rightarrow n}$ is optimal for times $2..n$ starting at temperature $t_1^*$

Assume $\Xi' = <\xi_2', \cdots, \xi_n'>$

$t_1'=t_1^* \\ t_i=t_{i-1}' + \xi_i' \\ T_{min} \le t_i' \le T_{max} \\ -k \le \xi_i' \le k$

$F((t_1^*, \Xi')) > F((t_1^*, \Xi^*_{2\rightarrow n}))$


Then $\Xi'' = <\xi_1^*,\xi_2', \dots, \xi'_n>$

$F((t_0^*, \Xi'')) = c_{1,t_i^*}+F((t_1^*,\underbrace{\Xi''_{2\rightarrow n}}_{\Xi'}))>\cancel{c_{1,t_i^*}}+\underbrace{F((t_1^*, \Xi_{2\rightarrow n}^*))}_{c^*-\cancel{c_{1,t_i^*}}}$

Because it will end up being greater than the optimal comfort, we have found a contradiction



## 5. Subproblems
$\boxed{F_ij = \text{max confort for time } i .. n\text{ starting at temperature }j}$

$\begin{matrix}  & \xi_1^*, \xi_2^*, \dots, \xi_i^* & \xi_{i+1}^*,\dots, \xi_n^* \\ t_0^* & t_1^*,t_2^*, \dots, t_i^* & \dots  \end{matrix}$

## 6. Bellman equations

$\begin{cases} 
0 & i > n \\
\max(c_{i,j+r}+F_{i+1,j+r}) & i \le n
\end{cases}$

$\max(-K, j - T_{min}) \le r \le \min(K, T_{max} - j)$

## 7. Data structure


Space: $O(n \Delta T)$  
Time: $O(n \Delta T k)$

## 8. Algorithm

$$
\begin{array}{l}
\textbf{for } j \leftarrow T_{min} \textbf{ to } T_{max} \textbf{ do} \\
\quad F_{n+1,j} \leftarrow 0 \\
\textbf{endfor} \\
\textbf{for } i \leftarrow n \textbf{ downto } 1 \textbf{ do} \\
\quad \textbf{for } j \leftarrow T_{min} \textbf{ to } T_{max} \textbf{ do} \\
\quad\quad \textit{best} \leftarrow -\infty \\
\quad\quad \textbf{for } r \leftarrow \max(-k, T_{min}-j) \textbf{ to } \min(k, T_{max}-j) \textbf{ do} \\
\quad\quad\quad q \leftarrow c_{i, j} + F_{i+1, j+r} \\
\quad\quad\quad \textbf{if } q > \textit{best} \textbf{ then} \\
\quad\quad\quad\quad \textit{best} \leftarrow q \\
\quad\quad\quad\quad D_{i,j} \leftarrow r \\
\quad\quad\quad \textbf{endif} \\
\quad\quad \textbf{endfor} \\
\quad\quad F_{i,j} \leftarrow \textit{best} \\
\quad \textbf{endfor} \\
\textbf{endfor} \\
\\
t_0 \leftarrow T_{min} \\
\textbf{for } j \leftarrow T_{min} + 1 \textbf{ to } T_{max} \textbf{ do} \\
\quad \textbf{if } F_{1, j} > F_{1, t_0} \textbf{ then} \\
\quad\quad t_0 \leftarrow j \\
\quad \textbf{endif} \\
\textbf{endfor} \\
\\
j \leftarrow t_0 \\
\textbf{for } i \leftarrow 1 \textbf{ to } n \textbf{ do} \\
\quad \xi \leftarrow D_{i,j} \\
\quad j \leftarrow j + \xi_i \\
\textbf{endfor}
\end{array}
$$












































