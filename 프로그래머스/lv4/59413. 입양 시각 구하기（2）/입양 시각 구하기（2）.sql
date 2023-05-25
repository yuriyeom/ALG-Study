# with recursive monthly as(
#     select 1 as rnum
#     union all
#     select rnum + 1 from monthly
#     where rnum < 23
# )
# select rnum from monthly;

SELECT rnum as HOUR, IFNULL(COUNT, 0) AS COUNT
from 
(SELECT hour(datetime) as HOUR, count(*) as COUNT 
 from animal_outs
 group by hour(datetime)
) a 
 right outer join 
 (
    with recursive monthly as(
        select 0 as rnum
        union all
        select rnum + 1 from monthly
        where rnum < 23
    )
    select rnum from monthly
) b
on a.hour = b.rnum
