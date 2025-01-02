-- 코드를 작성해주세요

select l.id as id, count(r.parent_id) as CHILD_COUNT from ecoli_data as l
left join ecoli_data as r
on l.id = r.parent_id
group by r.parent_id, l.id
order by l.id;


# select *, count(PARENT_ID) as CHILD_COUNT
# from ecoli_data
# group by PARENT_ID;