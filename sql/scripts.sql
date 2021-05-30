SELECT g, avg(f.grade) AS avrg_grade 
FROM good g LEFT OUTER JOIN feedback f ON f.good_uuid = g.uuid 
WHERE g.catalog_uuid = '982994c3-c5b1-4564-9416-b526cba23075' 
GROUP BY g.uuid 
ORDER BY avrg_grade DESC NULLS LAST
LIMIT 200 OFFSET 0;

SELECT g, count(gr) AS request_count
FROM good g LEFT OUTER JOIN good_request gr ON gr.good_uuid = g.uuid 
WHERE g.catalog_uuid = '982994c3-c5b1-4564-9416-b526cba23075' 
GROUP BY g.uuid  
ORDER BY request_count DESC
LIMIT 200 OFFSET 0;

SELECT g, avg(f.grade) AS avrg_grade 
FROM good g LEFT OUTER JOIN feedback f ON f.good_uuid = g.uuid  
WHERE g.catalog_uuid = '982994c3-c5b1-4564-9416-b526cba23075' 
GROUP BY g.uuid 
ORDER BY avrg_grade DESC NULLS LAST
LIMIT 200 OFFSET 0;


CREATE VIEW all_by_reqeust_count AS

SELECT g,
	  (SELECT name FROM "catalog" ct WHERE ct.uuid = g.catalog_uuid) AS catal, 
	   count(gr) AS request_count
FROM good g LEFT OUTER JOIN good_request gr ON gr.good_uuid = g.uuid 
GROUP BY g.uuid  
ORDER BY request_count DESC;


CREATE VIEW all_by_grades AS
SELECT g, 
       (SELECT name FROM "catalog" ct WHERE ct.uuid = g.catalog_uuid) AS catal, 
       avg(f.grade) AS avrg_grade 
FROM good g LEFT OUTER JOIN feedback f ON f.good_uuid = g.uuid  
GROUP BY g.uuid 
ORDER BY avrg_grade DESC NULLS LAST;


Использование.
select * from all_by_grades;
select * from all_by_reqeust_count;
