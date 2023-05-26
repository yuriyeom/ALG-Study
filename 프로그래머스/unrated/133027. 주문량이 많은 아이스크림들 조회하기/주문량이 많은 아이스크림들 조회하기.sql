# SELECT * 
# from first_half f
# join july
# on (f.flavor = july.flavor)


select flavor
from (
        select f.flavor, sum(f.total_order + jsum) as su
        # select f.flavor, total_order, jsum
        from first_half f
        join (
                select flavor, sum(total_order) as jsum
                from july
                group by flavor) j
        on (f.flavor = j.flavor)
        group by flavor
        order by su desc ) a
limit 3