CREATE DATABASE IF NOT EXISTS ExecutionApp;

CREATE TABLE ExecutionApp.executions (
    `execution_id` INT AUTO_INCREMENT,
    `order_id` INT,
    `instrument_id` INT,
    `quantity` DOUBLE,
    `price` DOUBLE,
    `executed_at` DATETIME,
PRIMARY KEY (execution_id)) ENGINE=MyISAM DEFAULT CHARSET=latin1;

CREATE TABLE ExecutionApp.execution_venues (
    `execution_venue_id` INT AUTO_INCREMENT,
    `execution_id` INT,
    `exchange_id` INT,
    `venue_code` VARCHAR(255),
PRIMARY KEY (execution_venue_id)) ENGINE=MyISAM DEFAULT CHARSET=latin1;

CREATE TABLE ExecutionApp.execution_reports (
    `execution_report_id` INT AUTO_INCREMENT,
    `execution_id` INT,
    `report_type` VARCHAR(255),
    `status` VARCHAR(255),
    `received_at` DATETIME,
PRIMARY KEY (execution_report_id)) ENGINE=MyISAM DEFAULT CHARSET=latin1;

CREATE TABLE ExecutionApp.execution_costs (
    `execution_cost_id` INT AUTO_INCREMENT,
    `execution_id` INT,
    `commission` DOUBLE,
    `fees` DOUBLE,
    `slippage` DOUBLE,
PRIMARY KEY (execution_cost_id)) ENGINE=MyISAM DEFAULT CHARSET=latin1;

USE drcvelocity;
