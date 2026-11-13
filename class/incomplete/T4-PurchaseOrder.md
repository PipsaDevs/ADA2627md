## Problem Statement
An international trading company has to import n machines. All of them have the same initial price tag v, but each will increase their price by some pi (depending on the particular machine) each passing week. Given that we can only buy a machine per week due to importation restrictions, we need to find the best purchasing order.

$P_i = [0.01, 0.03, 0.02, 0.04] = [P_1, P_2, P_3, P_4]$

$v \in \R$

## 1. Solutions
Ordered sequence:

$S = \{P_{s_1}, P_{s_2}, \dots, P_{s_n}\}$

## 2. Objective functions

Our objective function:

$$\min\left(\sum_{i=1}^m V·(1+S_{p_i})^{i-1}\right)$$

A **naïve approach** would be:

Select(S,C) = $\text{arg}_{\min_{i\in C}}\left(V·(1-S_{p_i}\right)^{|s|}$ 

^^^ Which is just picking the cheapest machine each week.^^^

A **better solution** would be:

Select(S,C) = $\text{arg}_{\max_{i\in C}}(p_i)$

^^^Which is choosing the greatest $p_i$ each week.^^^

## 3. Decisions
Which machine should we pick each week? (Multi-way)

## 4. Optimal Substructure

$S^* \rightarrow \{P_{s_1},\dots, P_{s_n}\}$

We are going to prove this by contradiction:

$P(S')=P(S^*)-v[(1+S^*_{p_i})^i+(1+S_{p_j}^*)^j]+v[(1+S^*_{p_j})^i+(1+S_{p_i}^*)^j]$

we left this problem here lolzie