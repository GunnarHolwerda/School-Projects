; Warmup

(define (f lst)
	; (a) ;
	(if (null? lst)
		; (b) ;
		'()
		; (c) ;
		(cons (+ 1 (car lst)) (f (cdr lst)))
	)
)

(display (f '(3 1 4 1 5 9)))
(display "\n")

; e 	  - value to check if exists in list
; list	  - list to check
;
; returns - #t if found, #f otherwise
(define (member? e lst)
	(cond 
	 	[(null? lst) #f]
	 	[(eqv? (car lst) e) #t]
	 	[else (member? e (cdr lst))]
	)
)

(display (member? 'one '(1 2 3 4)))
(display "\n")

; lst 	  -	the list to assure if it is a set or not
; 
; returns - #t if the list is a set, #f otherwise
(define (set? lst)
	(cond 
		[(null? lst) #t]
		[(member? (car lst) (cdr lst)) #f]
		[else (set? (cdr lst))]
	)
)

(display (set? '(it was the best of times, it was the worst of times)))
(display "\n")

(define (union lst1 lst2)
	(cond
		; if lst1 is empty then we have finished recursion, return lst2
		[(null? lst1) lst2] 
		; if the first element of lst1 is not a member of lst2
		[(not (member? (car lst1) lst2)) 
			; Then we call union on the rest of list one with lst2 + the first element of lst1
			(union (cdr lst1) (cons (car lst1) lst2))
		]
		; else we call union on the rest of lst1 and lst2
		; this occurs when an element exists in both lists
		[else (union (cdr lst1) lst2)]
	)	
)

(display (union '(2 3 7 8) '(1 2 3 4 5)))
(display "\n")

(define (intersect lst1 lst2)
	(cond
		[(null? lst1) lst2] 
		[(not (member? (car lst1) lst2)) 
			(intersect (cdr lst1) (cons (car lst1) lst2))
		]
		[else ]
	)
)