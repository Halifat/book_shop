
package service;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the service package. 
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

    private final static QName _GetAllResponse_QNAME = new QName("http://service/", "getAllResponse");
    private final static QName _GetById_QNAME = new QName("http://service/", "getById");
    private final static QName _GetAll_QNAME = new QName("http://service/", "getAll");
    private final static QName _GetByIdResponse_QNAME = new QName("http://service/", "getByIdResponse");
    private final static QName _GetByKeys_QNAME = new QName("http://service/", "getByKeys");
    private final static QName _GetByKeysResponse_QNAME = new QName("http://service/", "getByKeysResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: service
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetAll }
     * 
     */
    public GetAll createGetAll() {
        return new GetAll();
    }

    /**
     * Create an instance of {@link GetByIdResponse }
     * 
     */
    public GetByIdResponse createGetByIdResponse() {
        return new GetByIdResponse();
    }

    /**
     * Create an instance of {@link GetById }
     * 
     */
    public GetById createGetById() {
        return new GetById();
    }

    /**
     * Create an instance of {@link GetAllResponse }
     * 
     */
    public GetAllResponse createGetAllResponse() {
        return new GetAllResponse();
    }

    /**
     * Create an instance of {@link GetByKeysResponse }
     * 
     */
    public GetByKeysResponse createGetByKeysResponse() {
        return new GetByKeysResponse();
    }

    /**
     * Create an instance of {@link GetByKeys }
     * 
     */
    public GetByKeys createGetByKeys() {
        return new GetByKeys();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "getAllResponse")
    public JAXBElement<GetAllResponse> createGetAllResponse(GetAllResponse value) {
        return new JAXBElement<GetAllResponse>(_GetAllResponse_QNAME, GetAllResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetById }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "getById")
    public JAXBElement<GetById> createGetById(GetById value) {
        return new JAXBElement<GetById>(_GetById_QNAME, GetById.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAll }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "getAll")
    public JAXBElement<GetAll> createGetAll(GetAll value) {
        return new JAXBElement<GetAll>(_GetAll_QNAME, GetAll.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetByIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "getByIdResponse")
    public JAXBElement<GetByIdResponse> createGetByIdResponse(GetByIdResponse value) {
        return new JAXBElement<GetByIdResponse>(_GetByIdResponse_QNAME, GetByIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetByKeys }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "getByKeys")
    public JAXBElement<GetByKeys> createGetByKeys(GetByKeys value) {
        return new JAXBElement<GetByKeys>(_GetByKeys_QNAME, GetByKeys.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetByKeysResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "getByKeysResponse")
    public JAXBElement<GetByKeysResponse> createGetByKeysResponse(GetByKeysResponse value) {
        return new JAXBElement<GetByKeysResponse>(_GetByKeysResponse_QNAME, GetByKeysResponse.class, null, value);
    }

}
