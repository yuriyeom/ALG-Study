# select distinct year(sales_date) as YEAR, 
#         month(sales_date) as MONTH, 
#         u.gender as GENDER, 
#         u.user_id as USERS
# from user_info u join online_sale o on (u.user_id = o.user_id)
# where u.gender is not null;

select year(sales_date) as YEAR, 
        month(sales_date) as MONTH, 
        u.gender as GENDER, 
        count(distinct u.user_id) as USERS
from user_info u join online_sale o on (u.user_id = o.user_id)
where u.gender is not null
group by year(sales_date), month(sales_date), u.gender
order by year(sales_date) asc, month(sales_date) asc, u.gender asc;


# select sales_date, gender, u.user_id
# from user_info u join online_sale o on (u.user_id = o.user_id)
# where u.gender is not null and
# year(sales_date) = 2022 and month(sales_date) = 1 and gender = 0;

# select distinct year(sales_date) as YEAR, 
#         month(sales_date) as MONTH, 
#         u.gender as GENDER, 
#         u.user_id as USERS
# from user_info u join online_sale o on (u.user_id = o.user_id)
# where u.gender is not null and
# year(sales_date) = 2022 and month(sales_date) = 3 and gender = 1;