SELECT 
    (CASE
        WHEN group_concat(category) LIKE '%Front End%' AND group_concat(name) LIKE '%Python%' then 'A'
        WHEN group_concat(name) LIKE '%C#%' THEN 'B'
        WHEN group_concat(category) LIKE '%Front End%' THEN 'C'
        ELSE NULL
    END) GRADE,
    ID, 
    max(email) AS EMAIL
FROM developers D 
INNER join skillcodes S ON D.skill_code & S.code = S.code
GROUP BY id
HAVING grade IS NOT NULL
ORDER BY 1 ASC, 2 ASC;



# select *,
# case
#     when category = "Front End" and name = "Python" then "A"
#     when name = "C#" then "B"
#     when category = "Front End" then "C"
# end "grade"
# from developers as d
# left join skillcodes as s
# on d.skill_code = s.code