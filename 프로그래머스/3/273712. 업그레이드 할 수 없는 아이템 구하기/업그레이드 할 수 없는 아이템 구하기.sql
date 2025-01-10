select l.item_id as item_id, item_name, rarity
from item_info as l
left join item_tree as r
on l.item_id = r.PARENT_ITEM_ID
where parent_item_id is null
order by 1 desc