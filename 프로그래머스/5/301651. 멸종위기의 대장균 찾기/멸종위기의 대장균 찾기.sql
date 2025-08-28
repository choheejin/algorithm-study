with recursive gen_table as (
    select id, parent_id, 1 as gen from ECOLI_DATA where parent_id is null
    
    union all
    
    select s.id, s.parent_id, g.gen + 1
    from gen_table as g
    inner join ECOLI_DATA as s
    on g.id = s.parent_id
), parent_table as (
    select g.id, g.gen, count(l.id) as l_cnt
    from gen_table as g
    left join gen_table as l on l.parent_id = g.id
    group by g.id, g.gen
)

select count(*) as COUNT, gen as GENERATION
from parent_table
where l_cnt = 0
group by gen
order by 2;
