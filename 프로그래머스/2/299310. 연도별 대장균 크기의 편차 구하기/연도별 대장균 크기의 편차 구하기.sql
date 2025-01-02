select YEAR, max(SIZE_OF_COLONY) over(partition by YEAR) - SIZE_OF_COLONY as YEAR_DEV, ID
from (
    select *, cast(date_format(DIFFERENTIATION_DATE, '%Y') as unsigned) as year
    from ECOLI_DATA
) as d
order by year, YEAR_DEV;