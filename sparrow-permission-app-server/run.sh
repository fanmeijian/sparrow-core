#/bin/bash
cd ../sparrow-permission-core
mvn clean install -Dmaven.test.skip=true
cd ../sparrow-permission-service
mvn clean install
cd ../sparrow-permission-service-impl
mvn clean install
cd ../sparrow-permission-app-server
mvn spring-boot:run
	