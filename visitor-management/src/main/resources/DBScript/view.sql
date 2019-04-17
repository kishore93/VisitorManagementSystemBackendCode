CREATE OR REPLACE VIEW state_view AS SELECT state.state_id,     state.state_name,     country.country_id,     country.country_name    FROM state state      JOIN country country ON state.country_id = country.country_id;
CREATE OR REPLACE VIEW city_view AS  SELECT city.city_id,  city.city_name,   state.state_id,     state.state_name,     country.country_id,     country.country_name    FROM city city  JOIN state state ON city.state_id = state.state_id      JOIN country country ON state.country_id = country.country_id;
CREATE OR REPLACE VIEW user_type_role_permission_view AS 
select users.user_id AS user_id,
`users`.`first_name` AS `first_name`,
`users`.`registered_email` AS `registered_email`,
`users`.`user_token` AS `user_token`,
`users`.`is_enabled` AS `is_enabled`,
`ut`.`login_type` AS `login_type`,
`ut`.`name` AS `userType`,
`up`.`user_permission_id` AS `user_permission_id`,
`ur`.`role_name` AS `role_name`,
`ur`.`role_description` AS `role_description`     
from  (((`user_permission` `up` 
join `user_role` `ur` ON ((`up`.`user_role_id` = `ur`.`role_id`)))         
join `user_type` `ut` ON ((`ut`.`user_type_id` = `up`.`user_type_id`)))         
join `users` ON ((`users`.`user_type_id` = `ut`.`user_type_id`))) ;

CREATE OR REPLACE VIEW users_view AS select users.user_id AS user_id,
`users`.`first_name` AS `first_name`,
`users`.`registered_email` AS `registered_email`,
`user_type`.`name` AS `name`,
`user_type`.`login_type` AS `login_type`
from (`users` join `user_type` ON ((`users`.`user_type_id` = `user_type`.`user_type_id`))) ;
 