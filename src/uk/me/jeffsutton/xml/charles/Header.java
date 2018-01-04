package uk.me.jeffsutton.xml.charles;

import org.simpleframework.xml.Element;

/**
 * Created by jeff on 18/12/2015.
 */
public class Header {

    @Element(name = "name", required = false)
    String name;

    @Element(name = "value", required = false)
    String value;

    public String getName() {
        return this.name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
