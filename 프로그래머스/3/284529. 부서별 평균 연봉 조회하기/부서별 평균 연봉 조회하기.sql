-- 코드를 작성해주세요

select b.dept_id as dept_id, dept_name_en, round(avg(sal), 0) as avg_sal
from hr_employees as a
left join hr_department as b
on a.dept_id = b.dept_id
group by b.dept_id
order by 3 desc