-- 코드를 작성해주세요

select fish_info.id as id, d.FISH_NAME as FISH_NAME, fish_info.LENGTH as LENGTH
from fish_info,
(
    select distinct f.id, FISH_NAME, max(length) over(partition by fish_name) as max_value
    from FISH_INFO f
    left join FISH_NAME_INFO i
    on f.FISH_TYPE = i.FISH_TYPE
) as d
where length = max_value and d.id = fish_info.id
order by 1

# select f.ID as ID, FISH_NAME as FISH_NAME, max(length) over(partition by fish_name) as LENGTH
# from FISH_INFO f
# left join FISH_NAME_INFO i
# on f.FISH_TYPE = i.FISH_TYPE
