select l.item_id as item_id, item_name
from item_info as l
left join item_tree as r
on l.item_id = r.item_id
where PARENT_ITEM_ID is null
order by 1