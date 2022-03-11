#/bin/bash
cd ../sparrow-permission-model
mvn clean install
cd ../sparrow-permission-core-api
mvn clean install
cd ../sparrow-permission-core-service
mvn clean install
cd ../sparrow-permission-mgt-api
mvn clean install
cd ../sparrow-permission-gmt-service
mvn clean install
cd ../sparrow-permission-mgt-server
mvn spring-boot:run -Dspring-boot.run.profiles=h2
