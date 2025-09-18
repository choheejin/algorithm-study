-- 코드를 입력하세요

select flavor
from (
select FLAVOR, total_order from FIRST_HALF
union 
select FLAVOR, total_order from july
) as a
group by FLAVOR
order by sum(total_order) desc
limit 3;