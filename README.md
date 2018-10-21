# Microservices-JWT-Zuul-Kafka-docker
### This repository would help beginners understand how to start with simple microservices having an api gateway and registering all your microservices in eureka-server and dockerize of all it.
<p>  
  In this project we have</br>
  <b>Auth</b> Service which does users signup and signin </br>
  <b>Users</b> Service which provides apis to fetch all user details and details of any specific user who is signed up using <b>Auth</b> service.</br>
  <b>Products</b> Service which has got list of products users can order.(These products are hardcoded for now).</br>
  <b>Orders</b> Service which helps users to order for the products. </br>
  <b>Invoices</b> Service which gives out users invoice for the ordered prodcuts.</br>
  <b>Zuul</b> Service which serves as api gateway for all the above mentioned services.</br>
  <b>EurekaServer</b> which registers all our services. </br>
</p>
<p>
  <b>Role Of Kafka</b> </br>
  To send users information who registered via <b>Auth</b> Service to <b>Users</b> Service.</br>
  To send orders information placed via <b>Orders</b> Service to <b>Invoices</b> Service. </br>
</p>
<p>
  <b>Databases</b> </br>
  We use <b>postgres:9.5</b> image.</br>
  <b>Auth</b> Service - authservicedb </br>
  <b>Orders</b> Service - orders </br>
  <b>Invoices</b> Service - invoices </br> 
</p>
<p>
  <b>How To Run?</b> </br>
  Every microservice has got docker-maven-plugin in their pom. Reach the folder of each microservice and run </br>
  <b> mvn clean install docker:build </b> </br>
  Above command creates a dockerfile in the respecitve target folder of that service and the relative path of dockerfile is     used in docker-compose.</br>
  Now cd into the docker folder in the repository here and run below commands.</br>
  <b>sudo docker-compose build</b> </br>
  <b>sudo docker-compose -f docker-compose.yml up</b> </br>
  Now you should be able to see all the services up and running
</p>
<p>
  <b>How To Use?</b> </br>
  We need to get the ip of the docker container which is running <b>Zuul</b> api gateway</br>
  List all docker containers.</br>
  <b>sudo docker container ps</b></br>
  Check for the id of the container which is runnig zuul image and inspect it to get the ip.</br>
  <b>sudo docker inspect <container_id></b></br>
  Let the ip of the api gateway be <ip_gateway>. </br>
  <b> Register an user - (POST) http://<ip_gateway>:8082/api/auth/users/signup</b> </br>
  Payload </br>
  {
  "name":"John",
  "username":"joe",
  "email":"john@gmail.com",
  "password":"qwedsa"
  }</br>
  Response</br>
  {
   "usernameOrEmail":"john@gmail.com",
   "password":"qwedsa"
  }</br>
 <b> Login of an user - (POST) http://<ip_gateway>:8082/api/auth/signin </b> </br>
  Payload </br>
  {
  "usernameOrEmail":"john@gmail.com",
  "password":"qwedsa"
  }</br>
  Response</br>
  {
  "accessToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNTQwMTI5NjI4LCJleHAiOjE1NDA3MzQ0Mjh9.fvwXFjTV7YlbkLItFg-              kAVfXpW9ciwfM3Lgy2UIKdJNWFcuRFQPW6NKQz_xJ1YaLgpAdq-VRojMuQ6SIXiVHkA",
   "tokenType": "Bearer"
}</br>
<b> To Access Any Apis Of Other Services We Should Be Using Above Obtained AccessToken </b> </br>
<b> For example Authorization header would be </br> 
Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNTQwMTI5NjI4LCJleHAiOjE1NDA3MzQ0Mjh9.fvwXFjTV7YlbkLItFg-              kAVfXpW9ciwfM3Lgy2UIKdJNWFcuRFQPW6NKQz_xJ1YaLgpAdq-VRojMuQ6SIXiVHkA <b> </br>
<b>Get all Products - (GET) http://<ip_gateway>:8082/products</b></br>
<b>Get all Users - (GET) http://<ip_gateway>:8082/users</b></br>
<b>Get user details of user with userId 1 - (GET)  http://<ip_gateway>:8082/users?userId=1</b></br>
<b>Create order for an user with userId 1 - (POST)http://<ip_gateway>:8082/order?userId=1&productname=product3&price=1000</b></br>
You can use any of the products obtained from above get all products api to place an order.</br>
<b>Get all orders of an user with userId 1 - (GET) http://<ip_gateway>:8082/orders?userId=1</b></br>
<b>Get all invoices of an user with userId 1 - (GET) http://<ip_gateway>:8082/invoices?userId=1</b></br>
</p>
