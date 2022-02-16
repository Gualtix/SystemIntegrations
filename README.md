# SystemIntegrations

## Assignment: WebServices Client/Server - Order Status Tracker (16/02/2022)

### Java Soap Client (Walter Roberto Morales)

- First up the server
- Go to: Assigments/Walter/JeiSoapCon
- execute: mvn clean install
- Go to: target
- Execute: java -jar JSoapClient-jar-with-dependencies.jar
- [Source Code](https://github.com/Gualtix/SystemIntegrations/tree/main/Assigments/Walter/JeiSoapCon/src/main/java/com/mycompany/jeisoapcon)
- [Project Executable](https://github.com/Gualtix/SystemIntegrations/tree/main/Assigments/Walter/JeiSoapCon/target)

### Python Soap Client (Sebastian Sanchez)

To run this client you need Python 3.6+, following these instructions:
```
cd Assigments/Sebastian
pip install zeep
python soap-client.py
```
This is the [source code](https://github.com/Gualtix/SystemIntegrations/blob/main/Assigments/Sebastian/soap-client.py)

### JAX-WS SOAP service (Oscar Rodolfo Umaña)

- Go to: Assigments/Oscar/webservice-server
- Install the dependencies
```
mvn clean install
```
- to execute the spring boot app you need to run:
```
mvn spring-boot:run
```
- If you're using spring tools, you can righ click on the project and run as Spring Boot App.
- [Source Code](https://github.com/Gualtix/SystemIntegrations/blob/main/Assigments/Oscar/webservice-server/src/main/java/com/oumana/OrderWsImpl.java)
- In order to test the service with SOAP UI you can use the following URI: "http://localhost:8080/orders/ordersservice?wsdl"
