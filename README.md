# applicationInsights-java-codeless-app 01
It just helps you with demo java basic application and steps to use it with app insight.

Here there are 2 application one from repo applicationInsights-java-codeless-app 01 and another from applicationInsights-java-codeless-app 02. App 01 calls App 02 to show correlation in application insight.

I've configured swagger, so that you can use swagger APIs to access different controllers.

App 01 http://localhost:8081/swagger-ui/index.html#/ai-hello-controller
App 02 http://localhost:8082/swagger-ui/index.html#/ai-hello-controller

![image](https://user-images.githubusercontent.com/123938615/215460229-bb1e6d4d-506e-4b6a-8078-8ce60d06d94b.png)


To configure application insight, you need to follow below steps:

1. Clone this app or create another java application, make sure you have your working app ready.

2. Download the latest Application Insight Jar from here https://github.com/microsoft/ApplicationInsights-Java/releases and add it to any folder in your machine, make sure 
   process running java should have access to that folder.

3. Create JSON file with name "applicationinsights.json" to configure app insight properties and intrumentation key. Add JSON file to same path or set environment
   variable for it
   - JSON file configuration path https://learn.microsoft.com/en-us/azure/azure-monitor/app/java-standalone-config#configuration-file-path
   - Sample JSON file https://learn.microsoft.com/en-us/azure/azure-monitor/app/java-standalone-config#an-example
   
4. Set Java agent path as per environment https://learn.microsoft.com/en-us/azure/azure-monitor/app/java-standalone-arguments

5. Run your app, generate some app traffic and verify telemetry in the associated application insight resource.
