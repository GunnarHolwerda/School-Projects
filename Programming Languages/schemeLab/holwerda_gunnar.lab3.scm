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