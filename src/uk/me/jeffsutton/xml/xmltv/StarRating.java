package uk.me.jeffsutton.xml.xmltv;

import org.simpleframework.xml.Element;

/**
 * Created by jeff on 22/12/2015.
 */
public class StarRating {

    @Element(name = "value", required = false)
    String value;

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
