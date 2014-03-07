---- Server Fine tuning
---- my.ini  [mysqld] section
---- # this adds 35% speed increase
---- innodb_flush_log_at_trx_commit=2
---- # To store big blobs
---- max_allowed_packet=200M
---- transaction-isolation = READ-COMMITTED

-- initialization
DROP USER 'vwserver';
CREATE USER 'vwserver' IDENTIFIED BY 'vwserver';
DROP DATABASE vwserver;
CREATE DATABASE vwserver DEFAULT CHARACTER SET utf8 DEFAULT COLLATE utf8_general_ci;
-- GRANT ALL PRIVILEGES ON vwserver.* TO 'vwserver'@'%';
GRANT ALL PRIVILEGES ON vwserver.* TO vwserver@localhost IDENTIFIED BY 'vwserver' WITH GRANT OPTION;

--  if you get error:
--  ERROR 1396 (HY000) at line 3: Operation CREATE USER failed for 'vwserver'@'%'
--  then run the following commands:
-- DROP USER 'vwserver'@'localhost'
-- delete from mysql.user where user='vwserver';
-- delete from mysql.db where user='vwserver';
-- FLUSH PRIVILEGES;
