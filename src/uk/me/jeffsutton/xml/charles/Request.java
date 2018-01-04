package uk.me.jeffsutton.xml.charles;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import java.util.List;

/**
 * Created by jeff on 22/12/2015.
 */
public class Request {

    @Attribute(name = "charset", required = false)
    String charset;

    @ElementList(name = "headers", required = false)
    List<Header> headers;

    @Attribute(name = "headers", required = false)
    Integer headerCount;

    @Attribute(name = "mime-type", required = false)
    String mimeType;

    @Element(name = "body", required = false)
    Body body;

    @Attribute(name = "body", required = false)
    Integer bodyCount;

    public String getCharset() {
        return this.charset;
    }

    public void setCharset(String value) {
        this.charset = value;
    }

    public List<Header> getHeaders() {
        return this.headers;
    }

    public void setHeaders(List<Header> value) {
        this.headers = value;
    }

    public String getMimeType() {
        return this.mimeType;
    }

    public void setMimeType(String value) {
        this.mimeType = value;
    }

    public Body getBody() {
        return this.body;
    }

    public void setBody(Body value) {
        this.body = value;
    }

}
