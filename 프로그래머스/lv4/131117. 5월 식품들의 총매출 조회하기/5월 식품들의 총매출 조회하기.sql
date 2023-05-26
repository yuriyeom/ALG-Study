-- 코드를 입력하세요
SELECT p.product_id as PRODUCT_ID,
    p.product_name as PRODUCT_NAME,
    p.price * o.amount as TOTAL_SALES
# select *
from food_product p 
join 
(select product_id, sum(amount) as amount
from food_order 
where produce_date between '2022-05-01' and '2022-05-31'
group by product_id) o
on (p.product_id = o.product_id)

order by TOTAL_SALES desc, PRODUCT_ID asc
# 생산일자가 2022-05


# 총매출 내림차순, 식품id 오름차순
# 총매출?? 가격 * 0.amount

# select *
# from food_product p 
# join 
# (select product_id, sum(amount) as amount, produce_date
# from food_order 
# # where produce_date between '2022-05-01' and '2022-05-31'
# group by product_id) o
# on (p.product_id = o.product_id);

# select product_id, produce_date, sum(amount)
# from food_order 
# where produce_date between '2022-05-01' and '2022-05-31'
# group by product_id

# select product_id, sum(amount) as amount, produce_date
# from food_order 
# where produce_date between '2022-05-01' and '2022-05-31'
# group by product_id;