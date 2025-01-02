select
QUARTER,
count(QUARTER) as ECOLI_COUNT
from (
    select
    case
        when substring(DIFFERENTIATION_DATE, 6, 2) in ('01', '02', '03') then '1Q'
        when substring(DIFFERENTIATION_DATE, 6, 2) in ('04', '05', '06') then '2Q'
        when substring(DIFFERENTIATION_DATE, 6, 2) in ('07', '08', '09') then '3Q'
        else '4Q'
    end 'QUARTER'
from ECOLI_DATA
) as d
group by QUARTER
order by 1;