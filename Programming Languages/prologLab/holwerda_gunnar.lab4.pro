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
uncleBlood(U, N)	:- parent(P, N), brother(P, U).
%uncleMarr(N, U)		:-
%auntBlood(N, A)		:- parent(P, N), sister(A, P).
%grandparent
%grandfather
%grandmother
%grandchild