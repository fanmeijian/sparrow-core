生成rest客户端，typescript客户端
java -jar swagger-codegen-cli-3.0.33.jar generate -i swagger.json -l typescript-angular -o ./


openApi生成：

openapi-generator-cli generate -g typescript-angular -i http://localhost:8091/api/v3/api-docs.yaml -o ./src