# SELECT distinct car.car_id as CAR_ID, 
#                 car.car_type as CAR_TYPE, 
#                 FLOOR(((car.daily_fee - (car.daily_fee * plan.discount_rate / 100))  * 30))  as FEE
# # select *

# from 
# CAR_RENTAL_COMPANY_CAR car
# # join CAR_RENTAL_COMPANY_RENTAL_HISTORY history
# join CAR_RENTAL_COMPANY_DISCOUNT_PLAN plan
# on  (car.car_type = plan.car_type) and (plan.duration_type = '30일 이상')

# # # 종류가 세단 or SUV
# where car.car_type = '세단' or car.car_type = 'SUV'

# # 대여 가능 2022-11-01 ~ 2022-11-30
# # start나 end가 11월 안이면 대여 불가능
# and car.car_id not in
#             (select distinct car_id
#             from CAR_RENTAL_COMPANY_RENTAL_HISTORY history
#             where (history.start_date >= '2022-11-01' and history.start_date <= '2022-11-30')
#                     or  (history.end_date >= '2022-11-01' and history.end_date <= '2022-11-30'))

# # 30일 대여금액이 50만 <= x < 200만
# # (car.daily_fee - (car.daily_fee * plan.discount_rate / 100))  * 30  

# order by FEE DESC, car_type asc, car_id desc

select *
from
(
    SELECT distinct car.car_id as CAR_ID, 
                car.car_type as CAR_TYPE, 
                FLOOR(((car.daily_fee * (100 - plan.discount_rate) / 100))  * 30)  as FEE

    from CAR_RENTAL_COMPANY_CAR car
    join CAR_RENTAL_COMPANY_DISCOUNT_PLAN plan
    on (car.car_type = plan.car_type) 
     # and (plan.duration_type = '30일 이상') 

    where 
        car.car_type in ('세단', 'SUV') and plan.duration_type = '30일 이상'
    and car.car_id not in
                (select car_id
                from CAR_RENTAL_COMPANY_RENTAL_HISTORY history
                
                  WHERE END_DATE >= '2022-11-01' AND START_DATE <= '2022-11-30'
                 # where (history.start_date between '2022-11-01' and '2022-11-30')
                 #        or  (history.end_date between '2022-11-01' and '2022-11-30')
    )
) a
where FEE >= 500000 and FEE < 2000000
order by FEE DESC, car_type asc, car_id desc