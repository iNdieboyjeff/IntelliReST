package uk.me.jeffsutton.xml.charles;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Text;

/**
 * Created by jeff on 22/12/2015.
 */
public class Body {

    @Text(required = false)
    String cdataSection;

    @Attribute(name = "encoding", required = false)
    String encoding;

    public String getValue() {
        return this.cdataSection;
    }

    public void setValue(String value) {
        this.cdataSection = value;
    }

    public String getEncoding() {
        return this.encoding;
    }

    public void setEncoding(String value) {
        this.encoding = value;
    }

}
