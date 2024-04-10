# Candy-Kingdom

The Candy Kingdom is a project related to network and distributed programming.
# Description

This is a simulation of a candy factory.</br>
## FactoryApp
The factory use FactoryApp for managing its products. </br>
All products are stored in a Redis database. </br>
Orders are gathered and stored in RabbitMQ until they are processed by an operator.
## CustomerApp and OperatorApp
The CustomerApp enables customers to create orders, while the OperatorApp allows operators to accept or reject these orders.</br> 
Once the operator processes an order, the customer receives an email regarding the status of their order.</br>
## ProxyMailServer
To handle email communication, an ProxyMailServer application is utilized.</br>
## DistributorApp
DistributorApp allows distributors to publish products which the factory can then purchase. </br>
## FactoryREST
FactoryRESET is RESTful applications that factory uses with the HTTP protocol for operations involving products and customer information.
