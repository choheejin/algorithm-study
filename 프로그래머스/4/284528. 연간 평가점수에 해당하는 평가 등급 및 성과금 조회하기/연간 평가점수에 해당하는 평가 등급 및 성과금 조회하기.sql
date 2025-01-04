select emp.EMP_NO, emp.EMP_NAME,
case
    when avg(grad.score) >= 96 then 'S'
    when avg(grad.score) >= 90 then 'A'
    when avg(grad.score) >= 80 then 'B'
    else 'C'
end 'grade',
case
    when avg(grad.score) >= 96 then 0.2 * emp.SAL
    when avg(grad.score) >= 90 then 0.15 * emp.SAL
    when avg(grad.score) >= 80 then 0.1 * emp.SAL
    else 0
end 'bonus'
from hr_employees as emp
left join HR_DEPARTMENT as dep
on dep.DEPT_ID = emp.DEPT_ID
left join HR_GRADE as grad
on emp.EMP_NO = grad.EMP_NO
group by emp.EMP_NO, grad.EMP_NO
order by 1


# select emp.EMP_NO, emp.EMP_NAME, avg(grad.score), emp.sal
# from hr_employees as emp
# left join HR_DEPARTMENT as dep
# on dep.DEPT_ID = emp.DEPT_ID
# left join HR_GRADE as grad
# on emp.EMP_NO = grad.EMP_NO
# group by emp.EMP_NO, grad.EMP_NO
