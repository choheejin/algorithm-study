-- 코드를 작성해주세요

select count(*) as FISH_COUNT
from FISH_INFO f, FISH_NAME_INFO i
where f.FISH_TYPE = i.FISH_TYPE and (i.fish_name in ('BASS', 'SNAPPER'));
