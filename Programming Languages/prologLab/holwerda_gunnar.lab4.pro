% Gunnar Holwerda
% CSCI 305 Lab 4

mother(M, C)	:- parent(M, C), female(M).
father(M, C)	:- parent(M, C), male(M).
spouse(M, F)	:- married(M, F), male(M), female(F).
child(C, P)		:- parent(P, C).
son(C, P)		:- child(C, P), male(C).
daughter(C, P)	:- child(C, P), female(C).
sibling(C, S)	:- child(C, P), parent(P, S), C \= S.
%brother
%sister
%uncle
%aunt
%grandparent
%grandfather
%grandmother
%grandchild