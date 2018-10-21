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
  <b>EurekaServer></b> which registers all our services. </br>
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
