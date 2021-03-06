insert into CATEGORIES (ID, NAME, VISIBLE) values (1, 'Intellect', true);
insert into CATEGORIES (ID, NAME, VISIBLE) values (2, 'Standard', true);
insert into PROPERTIES (ID, NAME, TYPE, CATEGORY_ID) values (1, 'Number of holds', 'items', 1);
insert into PROPERTIES (ID, NAME, TYPE, CATEGORY_ID) values (2, 'Number of holds', 'items', 2);
insert into PROPERTIES (ID, NAME, CATEGORY_ID) values (3, 'Color', 1);
insert into PROPERTIES (ID, NAME, CATEGORY_ID) values (4, 'Color', 2);
insert into PRODUCTS (ID, NAME, PRICE, WEIGHT, VOLUME, STOCK, VISIBLE, CATEGORY_ID) values (1, 'CASUS', 105, 2.8, 0.01, 5, true, 1);
insert into PRODUCTS (ID, NAME, PRICE, WEIGHT, VOLUME, STOCK, VISIBLE, CATEGORY_ID) values (2, 'START PACK 1', 150, 6.65, 0.01, 2, true, 2);
insert into property_values (ID, VALUE, PROPERTY_ID, PRODUCT_ID) values (1, '6', 1, 1);
insert into property_values (ID, VALUE, PROPERTY_ID, PRODUCT_ID) values (2, '16', 2, 2);
insert into property_values (ID, VALUE, PROPERTY_ID, PRODUCT_ID) values (3, 'red', 3, 1);
insert into property_values (ID, VALUE, PROPERTY_ID, PRODUCT_ID) values (4, 'orange', 4, 2);
insert into ROLES (ID, NAME) values (1, 'ROLE_CUSTOMER');
insert into ROLES (ID, NAME) values (2, 'ROLE_MANAGER');
insert into USERS (ID, EMAIL, PASSWORD, ROLE_ID) values (1, 'manager@mms.com', '$2y$12$kS27RcsQxs40Ttwz7JB3suLjIhlaO9BbQw4RN3VwC/BaOo/fVeL6m', 2);
insert into payment_methods (ID, NAME) values (1, 'Credit card');
insert into payment_methods (ID, NAME) values (2, 'PayPal');
insert into payment_methods (ID, NAME) values (3, 'Cash');
insert into payment_statuses (ID, NAME) values (1, 'Unpaid');
insert into payment_statuses (ID, NAME) values (2, 'Paid');
insert into delivery_methods (ID, NAME) values (1, 'Pickup from the warehouse');
insert into delivery_methods (ID, NAME) values (2, 'DHL');
insert into delivery_methods (ID, NAME) values (3, 'EMS');
insert into delivery_statuses (ID, NAME) values (1, 'Order created');
insert into delivery_statuses (ID, NAME) values (2, 'Ready for delivery');
insert into delivery_statuses (ID, NAME) values (3, 'Ready for pickup');
insert into delivery_statuses (ID, NAME) values (4, 'Sent');
insert into delivery_statuses (ID, NAME) values (5, 'Delivered');
