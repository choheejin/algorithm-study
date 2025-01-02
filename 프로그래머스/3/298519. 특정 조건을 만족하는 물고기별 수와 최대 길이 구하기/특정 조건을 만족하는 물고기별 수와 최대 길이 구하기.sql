select count(id) as FISH_COUNT, max(real_length) as MAX_LENGTH, FISH_TYPE
from (
    select *, ifnull(LENGTH, 10) as real_length
    from FISH_INFO
) as d
group by FISH_TYPE
having AVG(real_length) >= 33
order by 3;

