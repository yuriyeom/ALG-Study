SELECT YEAR(SALES_DATE) AS YEAR, 
MONTH(SALES_DATE) AS MONTH, 
COUNT(DISTINCT UI.USER_ID) AS PURCHASED_USERS, 
ROUND(COUNT(DISTINCT UI.USER_ID) / (SELECT COUNT(DISTINCT USER_ID) FROM USER_INFO WHERE YEAR(JOINED)=2021),1) AS PURCHASED_RATIO
FROM USER_INFO UI INNER JOIN ONLINE_SALE OS ON UI.USER_ID=OS.USER_ID
WHERE YEAR(JOINED)=2021
GROUP BY YEAR, MONTH 
ORDER BY YEAR, MONTH

# SELECT COUNT(DISTINCT UI.USER_ID), YEAR(SALES_DATE) AS YEAR, MONTH(SALES_DATE) AS MONTH
# FROM USER_INFO UI INNER JOIN ONLINE_SALE OS ON UI.USER_ID=OS.USER_ID
# WHERE YEAR(JOINED)=2021 AND MONTH(SALES_DATE)=1
# # GROUP BY YEAR, MONTH 
# ORDER BY UI.USER_ID