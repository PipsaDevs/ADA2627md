We want to build an antenna of height $H$, we have $n$ types of blocks of different sizes, each of height $h_i$ and weight $w_i$, we have an unlimited supply of each type of block

Our goal is to build the antenna having exactly heigh $H$ using the blocks, minimising the total weight of the structure

!image.png

The order in which we stack the blocks is irrelevant

!image

Our objective is to find the tallest structure (height H) with the minimum weight

## (1) Solutions

$S = \{S_1,S_2,\dots , s_m\}$

$S_i\in\{1,\dots,n\}$

$S=<S_1,\dots,S_n>$

$S_i$ → number of copies $\ge 0$ of element $i$

The height is defined as $H(S)=\sum_{i\in S}[S]_i·h_i=H$,

$[S]_i$ is the number of copies of i in S.

$H(S)=\sum_{i=1}^n S_i·h_i$

## (2) Objective function

$$
\text{min }W(S)=\sum_{i\in S}[S]_i · w_i=\sum_{i=1}^n S_i·w_i
$$

## (3) Decisions

- Which block to add next? ( Multiway )
- Shall I use onem ore block of type i ? ( Binary )
- How many copies of block $i$ to use? ( Multiway )

## (4) Optimal substructures

$S^*=<S_1^*,\dots, S_n^*>$

$\forall i \in \{1..n\}: S_1^*\ge 0$

$H(S^*)=\sum_{i=1}^n S_i^*·h_i=H$

$W(S^*)=\sum_{i=0}^n S^*_i \dot w_i=W^* \text{ is opt.}$

- $s_1^*=0; \qquad s'=s_{2\rightarrow n}^* = <s_2^*, \dots, s_n^*>$

H(S’) = H

W(S’) = W*

S’ is opt for an antenna of height H using blocks of type 2..n

- $s_1^*\gt 0$

$s' = <s_1'...s_n'>$

$s'_1=s_1^*-1$

$s_i=s_i^*, \qquad 2\le i\le n$

$\forall i \in \{1\dots n\}:s_i'\ge0$

$H(S')=\sum_{i=1}^n s_i'·h_i = H(S^*) -h_1$

$W(S')=W^*-w_1$

Hyp: S’ is opt. for an antenna of height $H-h_1$ using blocks of type 1..n

Assume $S’’=<{S’’}_1,\dots,{S’’}_n>$

$\forall i \in {1 \dots n}: {S’’}_i \ge 0$

$H(S’’)=H-h_1$

$W(S’’)\lt W(S)$

Then $S''' = <s_1''',\dots S_n'''>$

$S_1'''=S_1''+1$

$S_i''' = S_i'', \qquad 2 \le i \le n$

$H(S''')=H(S'')+h_1=H-\cancel{h_1}+\cancel{h_1}$

$W(S''')=W(S'')+W_1 < W(S')+W_1=W^*-\cancel{w_1}+\cancel{w_1}$ 

$W(S''') < W^*$ contradiction!

## (5) Subproblems

$\boxed{W_{ij}=\text{minimum weight of an antenna of height j using blocks of type }i\dots n}$

## (6) Bellman eq.

$W_{ij} = \begin{cases}
0 & j=0 \\
\infty & j\gt0,i\gt n \\
W_{i+1,j} & j\gt0,i\le n,h_i \gt j \\ \text{min}\big(W_{i+j,j},w_i+W_i,j-h_i\big) & j\gt 0, i \le n, h_i \le j
\end{cases}$

## (7) Data structure

!image.png

Space: O(nH)

time: O(nH)

$$

\begin{array}{c|ccccc}  & 0 & \dots & i & \dots & H \\\hline1      & 0 &       &   &       &   \\\vdots &   &       &   &       &   \\i      &   &       &   &       &   \\\vdots &   &       &   &       &   \\n+1    &   &       &   &       &   \\\end{array}
$$

# (8) Algorithm

$$
\begin{aligned}&\textbf{for } i \gets 1 \textbf{ to } n+1 \textbf{ do} \\&\quad W_{i, 0} \gets 0 \\&\textbf{end for} \\[6pt]&\textbf{for } j \gets 1 \textbf{ to } H \textbf{ do} \\&\quad W_{n+1, j} \gets \infty \\&\textbf{end for} \\[6pt]&\textbf{for } i \gets n \textbf{ down to } 1 \textbf{ do} \\&\quad \textbf{for } j \gets 1 \textbf{ to } H \textbf{ do} \\&\qquad \textbf{if } h_i > j \textbf{ then} \\&\qquad\quad W_{i, j} \gets W_{i+1, j} \\&\qquad \textbf{else} \\&\qquad\quad W_{i, j} \gets \min(W_{i+1, j},\, w_i + W_{i, j - h_i}) \\&\qquad \textbf{end if} \\&\quad \textbf{end for} \\&\textbf{end for} \\[6pt]&S^* \gets \langle 0, \dots, 0 \rangle \quad \text{\textit{// length }} n \\&i \gets 1; \quad j \gets H \\&\textbf{while } i \le n \textbf{ do} \\&\quad \textbf{if } W_{i, j} = W_{i+1, j} \textbf{ then} \\&\qquad i \gets i + 1 \\&\quad \textbf{else} \\&\qquad S_i^* \gets S_i^* + 1; \quad j \gets j - h_i \\&\quad \textbf{end if} \\&\textbf{end while}\end{aligned}
$$

```pascal
for i <- 1 to n+1 do
	W_{i,0} <- 0
endfor

for j <- 1 to H do
	W_{n+1,j} <- infinity
endfor

for i <- n down to 1 do
	for j <- 1 to H do
		if h_i > j then
			W_{i, j} <- W_{i+1, j}
		else
			W_{i, j} <- min(W_{i+1, j}, w_i+W_{i,j-h_i})
		endif
	endfor
endfor

S* <- <0,...,0> // length n
i <- 1; j <- H
while i<=n do 
	if W_{i, j} = W_{i+1, j} then i <- i+1
	else S_i* <- S_i* + 1 ; j <- j - h_i
	endif
endwhile
```

## ??? For the another way to solve de exercise ???

- $s'=s_{2\rightarrow n}^* = <s_2^*, \dots, s_n^*>$

$H(S’) = H$

$W(S’) = W^*$

$S’$ is opt for an antenna of height $H$ using blocks of type $2..n$

$S’’ =<S’_2,\dots,S’_n>$

$H(S’’)=H-s_1^* h_1$

$W(S’’)\lt W(S’)$

$S’’’=<S_1^*, S’’_2,\dots,s’’_n>$

$H(S’’’) = H$

$W(S’’’)\lt W^*$

$W_{ij} = \begin{cases} 
0 & j = 0 \\
\infin & j>0, i>n \\
\min(K·w_i+W_{i+1, j}, j-k·h_i) & j>0, i\le n, 0 \le k \le \lfloor{\frac{i}{h}\rfloor}
\end{cases}$

## ??? data structure ???

!image.png

Space: O(nH)

Time: O(nH²)

## ??? ultimo caso ???

$W_j$ = minimum weight of an antenna of height j

$F_j = \{ i/hi \le j$

$W_j\begin{cases}

0 & j = 0 \\
\infin & F_j = \varnothing, j>0 \\
\min(w_i + W_{j-h_i}) F_j \not = \varnothing, j > 0 \\
i \in F_j

\end{cases}$

!image.png

O(H)

O(nH)

+nlog(n)

$a$

ppp