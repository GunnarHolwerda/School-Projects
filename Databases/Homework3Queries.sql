/* Question 1 */
SELECT COUNT(*) FROM favorite_item WHERE animal="tiger";

/* Question 2 */
SELECT COUNT(*) FROM favorite_item 
WHERE favorite_item.animal IN 
(SELECT common FROM animal WHERE animal.species = "lupus" AND animal.genus="canis");

/* Question 3 */
SELECT COUNT(*) FROM programming_language WHERE type_check is NULL;

/* Question 4 */
SELECT COUNT(*) FROM favorite_language 
LEFT JOIN programming_language ON favorite_language.recently_learned = programming_language.name
WHERE programming_language.declarative = 1;

/* Question 5 */
SELECT DISTINCT most_favorite, programming_language.appeared
FROM favorite_language, programming_language
WHERE most_favorite=name
ORDER BY programming_language.appeared ASC
LIMIT 1;
/* Then do 2014 - the year appeared and that gives you the answer */

/* Question 6 */
SELECT COUNT(*)
FROM favorite_language
LEFT JOIN programming_language ON favorite_language.hardest_to_write=programming_language.name
WHERE programming_language.type_safety = 'strong';

/* Question 7 */
SELECT COUNT(*)
FROM tree
CROSS JOIN plant;

/* Question 8 */
SELECT COUNT(*)
FROM plant
CROSS JOIN plant as beast_plant;

/* Question 9 */
SELECT COUNT(*) FROM favorite_item 
WHERE favorite_item.animal IN 
(SELECT common FROM animal WHERE animal.species = "silvestris" AND animal.genus="felis");

/* Question 10 */
SELECT 
	COUNT(*) as animal_count,
	animal,
	animal.genus,
	animal.species
FROM state_symbol
LEFT JOIN animal ON state_symbol.animal = aid
GROUP BY animal
ORDER BY animal_count DESC
LIMIT 1;

/* Question 11 */
SELECT
	COUNT(*) as animal_count,
	favorite_item.animal,
	A.genus,
	A.species
FROM favorite_item
LEFT JOIN animal as A ON favorite_item.animal=common
GROUP BY favorite_item.animal
ORDER BY animal_count DESC
LIMIT 1;

/* Question 12 */
SELECT
	COUNT(*) as tree_count,
	favorite_item.tree,
	T.genus,
	T.species
FROM favorite_item
LEFT JOIN tree as T ON favorite_item.tree=sppcode
GROUP BY favorite_item.tree
ORDER BY tree_count DESC
LIMIT 1;

/* Question 13 */
SELECT 
	COUNT(*)
FROM favorite_language
LEFT JOIN programming_language ON favorite_language.next_to_learn=programming_language.name
WHERE programming_language.declarative = 1;

/* Question 14 */
SELECT DISTINCT most_favorite, programming_language.appeared
FROM favorite_language, programming_language
WHERE most_favorite=name
ORDER BY programming_language.appeared DESC
LIMIT 1;