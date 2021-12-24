--CUSTOMERS
insert into tbl_customers (id, customer_name, customer_last_name) values
(1, 'john', 'goodman'),
(2, 'paul', 'newman'),
(3, 'grace', 'williams');

--TRANSACTIONS
insert into tbl_transactions (id, customer_id, product_desc, amount, created_at) values
(1, 1, 'cellphone s20', 130.0, '2021-01-12'),
(2, 1, 'tablet galaxy', 90.0, '2021-01-11'),
(3, 1, 'battery charger', 205.0, '2021-01-10'),
(4, 2, 'iphone 9', 990.0, '2021-02-12'),
(5, 2, 'nokia smart', 45.0, '2021-02-11'),
(6, 2, 'cellphone', 305.0, '2021-02-10'),
(7, 3, 'smart watch', 20.0, '2021-03-12'),
(8, 3, 'sport battery', 190.0, '2021-03-11'),
(9, 3, 'cellphone', 550.0, '2021-03-10');