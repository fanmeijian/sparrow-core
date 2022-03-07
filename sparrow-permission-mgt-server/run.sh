#/bin/bash
cd ../sparrow-permission-model
mvn clean install -Dmaven.test.skip=true
cd ../sparrow-permission-core-api
mvn clean install -Dmaven.test.skip=true
cd ../sparrow-permission-core-service
mvn clean install -Dmaven.test.skip=true
cd ../sparrow-permission-mgt-api
mvn clean install
cd ../sparrow-permission-gmt-service
mvn clean install
cd ../sparrow-permission-mgt-server
mvn spring-boot:run
	