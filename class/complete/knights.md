```pascal
Iterate Greedy()
  Initialization()
  while not complete() do
      C <- candidates()
      S <- select(C)
      extend(C)
  endwhile
```

```
RecursiveGreedy()
 if not complete() then
   C <- candidates()
   S<- select(S)
   extend(S)
   RecursiveGreedy()
 endif
```

```
Backtracking()
  if not complete()
      C <- candidates()
      for x in C do
          extend(S)
          Backtracking()
      enfor
  endif

```


# Knight path problem

|     |     |     |     |     |     |     |     |
| --- | --- | --- | --- | --- | --- | --- | --- |
|     |     |     |     |     |     |     |     |
|     |     |     |     |     |     |     |     |
|     |     |     |     |     |     |     |     |
|     |     |     |     |     |     |     |     |
|     |     |     |     |     |     |     |     |
|     |     |     |     |     |     |     |     |
|     |     |     |     |     |     |     |     |

> $n \times n$ grid
 
## 1. Solution
S = $<(i_1,j_1), (i_2,j_2), \dots,(i_{n^2},j_{n^2})>$

$\forall k \in \{1\dots n^2\}; i \le i_k, j_k\le n$

$\forall k,k' \in \{1\dots n^2\}\colon (i_k, j_k)=(i_{k'}, j_{k'}) \Leftrightarrow k = k$

$\forall k \in \{2,\dots, n^2\}\colon \exists m \in \{1\dots8\}\colon move(i_{k-1},j_{k+1},m)=(i_k,j_k)$

## 2. Decisions
- Which move to make? (multi-way)



## stepsitos

### Initialization

$S \leftarrow \langle(i_1,j_1)\rangle$

### Completion

$|S| = n^2$

### Partial solution

$\langle(i_1, j_1), \dots ,(i_k,j_k)\rangle$
$k\le n^2$

### Candidates

$\text{Candidates}(\langle (i_1,j_1), \dots, (i_k,j_k) \rangle) = $

$= \{m / 1\le m \le 8, move(i_k,j_k,m)=(i',j'),1\le i', j\le n,$

$\not\exists k'\in\{1\dots k\}\colon$

$\underbrace{(i_{k'},j_{k'})=(i',j')\}}_{O(n^2)}$

### Extension 


### Branching
$\langle(i_1) \dots (i_k,j_k)\rangle = \underbrace{\begin{cases}
... \\
... \\
\langle (i_1,j_1)\dots(i_k,j_k), \text{ move}(i_k,j_k,m)\rangle \\
... \\
... \\
\end{cases}}_{\text{8 cases}}$

### Backtrack
Visited $[i'][j'] \leftarrow False$

## Algorithm

$$
\begin{array}{l}
\textbf{proc } \text{knightPath}(\updownarrow S : \text{list}\langle \mathbb{N}, \mathbb{N} \rangle, \, \updownarrow \text{visited}[1..n][1..n] : \mathbb{B}, \, \uparrow \text{ok} : \mathbb{B}) \\
\textbf{variables} \\
\quad i', j' : \mathbb{N}; \; m : \mathbb{N} \\
\textbf{begin} \\
\quad \textbf{if } |S| \ge n^2 \textbf{ then} \\
\quad \quad \text{ok} \leftarrow \text{True} \\
\quad \textbf{else} \\
\quad \quad \text{ok} \leftarrow \text{False} \\
\quad \quad m \leftarrow 1 \\
\quad \quad \textbf{while } m \le 8 \land \neg \text{ok} \textbf{ do} \\
\quad \quad \quad \textbf{if } \text{factible}(\text{last}(S), m, \text{visited}) \textbf{ then} \\
\quad \quad \quad \quad (i', j') \leftarrow \text{move}(\text{last}(S), m) \\
\quad \quad \quad \quad S.\text{add}((i', j')) \\
\quad \quad \quad \quad \text{visited}[i'][j'] \leftarrow \text{True} \\
\quad \quad \quad \quad \text{knightPath}(S, \text{visited}, \text{ok}) \\
\quad \quad \quad \quad \textbf{if } \neg \text{ok} \textbf{ then} \\
\quad \quad \quad \quad \quad \text{removelast}(S) \\
\quad \quad \quad \quad \quad \text{visited}[i'][j'] \leftarrow \text{False} \\
\quad \quad \quad \quad \textbf{end if} \\
\quad \quad \quad \textbf{end if} \\
\quad \quad \quad m \leftarrow m + 1 \\
\quad \quad \textbf{end while} \\
\quad \textbf{end if} \\
\textbf{end}
\end{array}
$$

$$
\begin{array}{l}
\textbf{func } \text{factible}(\downarrow i, j, m : \mathbb{N}, \, \downarrow \text{visited}[1..n][1..n] : \mathbb{B}) : \mathbb{B} \\
\textbf{variables} \\
\quad i', j' : \mathbb{N} \\
\textbf{begin} \\
\quad (i', j') \leftarrow \text{move}(i, j, m) \\
\quad \textbf{return } (1 \le i') \land (i' \le n) \land (1 \le j') \land (j' \le n) \land \neg \text{visited}[i'][j'] \\
\textbf{end}
\end{array}
$$


![strawberrycat](https://i.pinimg.com/736x/d7/0c/57/d70c574e649fb5eb0088bb5867a1b668.jpg)

![questioningcat](https://i.pinimg.com/1200x/0d/cd/1c/0dcd1ca76e139a1b97bba9d8ceec0708.jpg)

![scaTedcat](https://i.pinimg.com/736x/4e/15/25/4e1525ab1b09030a386da6745f7d874b.jpg)

skate cat :sunglasses: 

![skatecat](https://i.pinimg.com/736x/eb/c9/14/ebc9145e6214dd8bb1dfa5b25ccb64b4.jpg)

![skatecatbutbetter, thats a lie because no cat is better than another and all cats deserve incoditional love and care and for you to say or even think so its so cruel, how could you, really, how could you // i'm sorry dude my bad, i understand that this is a very sensitive subject and i should've- done some things differently. But I'm more mature now, I have changed. I promise I won't make these kinds of statements again1](https://laughingsquid.com/wp-content/uploads/2014/11/Didga-the-Skateboarding-Cat.jpg)

![skatecatbutevenbetter, nah that is cool](https://media.tenor.com/Nsgl_ZLM3TYAAAAC/cat-skate-board.gif)