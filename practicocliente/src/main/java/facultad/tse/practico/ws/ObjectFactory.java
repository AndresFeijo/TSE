
package facultad.tse.practico.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the facultad.tse.practico.ws package. 
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

    private final static QName _Agregar_QNAME = new QName("http://ws.practico.tse.facultad/", "agregar");
    private final static QName _AgregarResponse_QNAME = new QName("http://ws.practico.tse.facultad/", "agregarResponse");
    private final static QName _BuscarPorId_QNAME = new QName("http://ws.practico.tse.facultad/", "buscarPorId");
    private final static QName _BuscarPorIdResponse_QNAME = new QName("http://ws.practico.tse.facultad/", "buscarPorIdResponse");
    private final static QName _BuscarPorPaciente_QNAME = new QName("http://ws.practico.tse.facultad/", "buscarPorPaciente");
    private final static QName _BuscarPorPacienteResponse_QNAME = new QName("http://ws.practico.tse.facultad/", "buscarPorPacienteResponse");
    private final static QName _DtDocumento_QNAME = new QName("http://ws.practico.tse.facultad/", "dtDocumento");
    private final static QName _DtListaDocumentos_QNAME = new QName("http://ws.practico.tse.facultad/", "dtListaDocumentos");
    private final static QName _Listar_QNAME = new QName("http://ws.practico.tse.facultad/", "listar");
    private final static QName _ListarResponse_QNAME = new QName("http://ws.practico.tse.facultad/", "listarResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: facultad.tse.practico.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Agregar }
     * 
     */
    public Agregar createAgregar() {
        return new Agregar();
    }

    /**
     * Create an instance of {@link AgregarResponse }
     * 
     */
    public AgregarResponse createAgregarResponse() {
        return new AgregarResponse();
    }

    /**
     * Create an instance of {@link BuscarPorId }
     * 
     */
    public BuscarPorId createBuscarPorId() {
        return new BuscarPorId();
    }

    /**
     * Create an instance of {@link BuscarPorIdResponse }
     * 
     */
    public BuscarPorIdResponse createBuscarPorIdResponse() {
        return new BuscarPorIdResponse();
    }

    /**
     * Create an instance of {@link BuscarPorPaciente }
     * 
     */
    public BuscarPorPaciente createBuscarPorPaciente() {
        return new BuscarPorPaciente();
    }

    /**
     * Create an instance of {@link BuscarPorPacienteResponse }
     * 
     */
    public BuscarPorPacienteResponse createBuscarPorPacienteResponse() {
        return new BuscarPorPacienteResponse();
    }

    /**
     * Create an instance of {@link DtDocumento }
     * 
     */
    public DtDocumento createDtDocumento() {
        return new DtDocumento();
    }

    /**
     * Create an instance of {@link DtListaDocumentos }
     * 
     */
    public DtListaDocumentos createDtListaDocumentos() {
        return new DtListaDocumentos();
    }

    /**
     * Create an instance of {@link Listar }
     * 
     */
    public Listar createListar() {
        return new Listar();
    }

    /**
     * Create an instance of {@link ListarResponse }
     * 
     */
    public ListarResponse createListarResponse() {
        return new ListarResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Agregar }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Agregar }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.practico.tse.facultad/", name = "agregar")
    public JAXBElement<Agregar> createAgregar(Agregar value) {
        return new JAXBElement<Agregar>(_Agregar_QNAME, Agregar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AgregarResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AgregarResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.practico.tse.facultad/", name = "agregarResponse")
    public JAXBElement<AgregarResponse> createAgregarResponse(AgregarResponse value) {
        return new JAXBElement<AgregarResponse>(_AgregarResponse_QNAME, AgregarResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BuscarPorId }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BuscarPorId }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.practico.tse.facultad/", name = "buscarPorId")
    public JAXBElement<BuscarPorId> createBuscarPorId(BuscarPorId value) {
        return new JAXBElement<BuscarPorId>(_BuscarPorId_QNAME, BuscarPorId.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BuscarPorIdResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BuscarPorIdResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.practico.tse.facultad/", name = "buscarPorIdResponse")
    public JAXBElement<BuscarPorIdResponse> createBuscarPorIdResponse(BuscarPorIdResponse value) {
        return new JAXBElement<BuscarPorIdResponse>(_BuscarPorIdResponse_QNAME, BuscarPorIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BuscarPorPaciente }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BuscarPorPaciente }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.practico.tse.facultad/", name = "buscarPorPaciente")
    public JAXBElement<BuscarPorPaciente> createBuscarPorPaciente(BuscarPorPaciente value) {
        return new JAXBElement<BuscarPorPaciente>(_BuscarPorPaciente_QNAME, BuscarPorPaciente.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BuscarPorPacienteResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BuscarPorPacienteResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.practico.tse.facultad/", name = "buscarPorPacienteResponse")
    public JAXBElement<BuscarPorPacienteResponse> createBuscarPorPacienteResponse(BuscarPorPacienteResponse value) {
        return new JAXBElement<BuscarPorPacienteResponse>(_BuscarPorPacienteResponse_QNAME, BuscarPorPacienteResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DtDocumento }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DtDocumento }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.practico.tse.facultad/", name = "dtDocumento")
    public JAXBElement<DtDocumento> createDtDocumento(DtDocumento value) {
        return new JAXBElement<DtDocumento>(_DtDocumento_QNAME, DtDocumento.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DtListaDocumentos }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DtListaDocumentos }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.practico.tse.facultad/", name = "dtListaDocumentos")
    public JAXBElement<DtListaDocumentos> createDtListaDocumentos(DtListaDocumentos value) {
        return new JAXBElement<DtListaDocumentos>(_DtListaDocumentos_QNAME, DtListaDocumentos.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Listar }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Listar }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.practico.tse.facultad/", name = "listar")
    public JAXBElement<Listar> createListar(Listar value) {
        return new JAXBElement<Listar>(_Listar_QNAME, Listar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ListarResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.practico.tse.facultad/", name = "listarResponse")
    public JAXBElement<ListarResponse> createListarResponse(ListarResponse value) {
        return new JAXBElement<ListarResponse>(_ListarResponse_QNAME, ListarResponse.class, null, value);
    }

}
