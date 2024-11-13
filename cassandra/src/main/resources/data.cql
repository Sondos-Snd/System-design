INSERT INTO user (id, name, age) VALUES (1, 'Alice', 30), (2, 'Bob', 22), (3, 'Charlie', 35);

INSERT INTO order (id, description, user_id) VALUES
(1, 'Order 1', 1),
(2, 'Order 2', 1),
(3, 'Order 3', 3);


--SELECT * FROM user WHERE age > 25;
--SELECT u.name AS user_name, o.description AS order_description
--  FROM user u
--  JOIN order o ON u.id = o.user_id;
--SELECT u.name AS user_name, COUNT(o.id) AS order_count
--FROM user u
--LEFT JOIN order o ON u.id = o.user_id
--GROUP BY u.name;

