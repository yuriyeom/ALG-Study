with recursive hourly as(
    select 0 as rnum
    union all
    select rnum+1 
    from hourly
    where rnum < 23
)
select rnum as HOUR, IFNULL(COUNT,0) as COUNT
# select *
from hourly h 
left outer join
(
    select hour(datetime) as hour, count(*) as COUNT
    from animal_outs
    group by hour
) a
on(h.rnum = a.hour)
# group by rnum