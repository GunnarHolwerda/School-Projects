% Gunnar Holwerda
% CSCI 305 Lab 4

mother(M, C)		:- parent(M, C), female(M).
father(M, C)		:- parent(M, C), male(M).
spouse(M, F)		:- married(M, F), male(M), female(F).
child(C, P)			:- parent(P, C).
son(C, P)			:- child(C, P), male(C).
daughter(C, P)		:- child(C, P), female(C).
sibling(S, C)		:- child(C, P), parent(P, S), C \= S.
brother(B, C)		:- sibling(B, C), male(B).
sister(S, C)		:- sibling(S, C), female(S).
uncle(U, N)			:- parent(P, N), brother(U, P).
aunt(A, N)			:- parent(P, N), sister(A, P).
grandparent(G, C)	:- parent(P, C), parent(G, P).
grandfather(G, C)	:- grandparent(G, C), male(G).
grandmother(G, C)	:- grandparent(G, C), female(G).
grandchild(C, G)	:- child(P, G), child(C, P).