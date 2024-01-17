# ALERT SERVICE
Microservice that stores important alerts and warnings in a DB that can be fetched for informing about the alert

# Microservice architecture
The service is part of a microservice architecture that consumes Kafka events and exposes an endpoint from where the alerts can be fetched
As of right now the only topic being consumed is published from police-service

# Running the service in with docker compose
It is recommended to run the service together with the docker multi container environment
Instructions on how to do this can be found here

https://github.com/zokypri/kafka-docker

# Running instructions standalone locally
In order to consume the kafka events Zookeeper Kafka and MySQL needs to be installed
The instructions are for a Mac with homebrew as installer

It is however recommended to use the docker setup since to run it locally one needs to create a DB named AlertService and later a DB table named alerts

    1: Run command "brew services start zookeeper"
    2: Run command "brew services start kafka"
    3: Run command "brew services start mysql"
    4: Start alert-service

# Verify data
After consuming a message we can verify that it has been consumed by looking at the logs and checking the DB
Instructions on how to check the DB

After running the service locally run these commands in the terminal where mysql was started

    1: mysql -u root -p
    2: password is "pass"
    3: show databases;
    4: use AlertService;
    5: select * from alerts;

# DB table ALERTS structure

+----+----------+------------+--------+-------+---------------------+
| id | alert_id | msg        | type   | level | created             |
+----+----------+------------+--------+-------+---------------------+
|  1 |        1 | Test alert | POLICE | LOW   | 2024-01-17 08:22:25 |
+----+----------+------------+--------+-------+---------------------+


# Microservice is under development
List of TODO
    
    1: Add logs
    2: Add tests
    3: Expose endpoint to fetch data from DB
    4: Add JWT token security
    5: Improve error handling
    
    
