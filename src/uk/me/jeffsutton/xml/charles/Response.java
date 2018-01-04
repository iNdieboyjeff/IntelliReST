package uk.me.jeffsutton.xml.charles;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import java.util.List;

/**
 * Created by jeff on 22/12/2015.
 */
public class Response {

    @Attribute(name = "headers", required = false)
    Integer headerCount;

    @Attribute(name = "charset", required = false)
    String charset;

    @Attribute(name = "mime-type", required = false)
    String mimeType;

    @Attribute(name = "contentEncoding", required = false)
    String contentEncoding;


    @Attribute(name = "body", required = false)
    Integer bodyCount;

    @Element(name = "body", required = false)
    Body body;

    @Attribute(name = "status", required = false)
    Integer status;

    @ElementList(name = "headers", required = false)
    List<Header> headers;

    public List<Header> getHeaders() {
        return this.headers;
    }

    public void setHeaders(List<Header> value) {
        this.headers = value;
    }

    public String getCharset() {
        return this.charset;
    }

    public void setCharset(String value) {
        this.charset = value;
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

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer value) {
        this.status = value;
    }

}
