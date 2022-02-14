
package org.example.orderswsdlfile;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.example.orderswsdlfile package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetOrderRequest_QNAME = new QName("http://www.example.org/OrdersWSDLFile/", "getOrderRequest");
    private final static QName _GetOrderResponse_QNAME = new QName("http://www.example.org/OrdersWSDLFile/", "getOrderResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.example.orderswsdlfile
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetOrderRequest }
     * 
     */
    public GetOrderRequest createGetOrderRequest() {
        return new GetOrderRequest();
    }

    /**
     * Create an instance of {@link GetOrderResponse }
     * 
     */
    public GetOrderResponse createGetOrderResponse() {
        return new GetOrderResponse();
    }

    /**
     * Create an instance of {@link Product }
     * 
     */
    public Product createProduct() {
        return new Product();
    }

    /**
     * Create an instance of {@link Customer }
     * 
     */
    public Customer createCustomer() {
        return new Customer();
    }

    /**
     * Create an instance of {@link Order }
     * 
     */
    public Order createOrder() {
        return new Order();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetOrderRequest }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetOrderRequest }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.example.org/OrdersWSDLFile/", name = "getOrderRequest")
    public JAXBElement<GetOrderRequest> createGetOrderRequest(GetOrderRequest value) {
        return new JAXBElement<GetOrderRequest>(_GetOrderRequest_QNAME, GetOrderRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetOrderResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetOrderResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.example.org/OrdersWSDLFile/", name = "getOrderResponse")
    public JAXBElement<GetOrderResponse> createGetOrderResponse(GetOrderResponse value) {
        return new JAXBElement<GetOrderResponse>(_GetOrderResponse_QNAME, GetOrderResponse.class, null, value);
    }

}
