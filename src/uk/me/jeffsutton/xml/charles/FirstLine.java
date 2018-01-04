package uk.me.jeffsutton.xml.charles;

import org.simpleframework.xml.Element;

/**
 * Created by jeff on 18/12/2015.
 */
public class FirstLine {

    @Element(name = "#cdata-section", required = false)
    String cdataSection;

    public String getCdataSection() {
        return this.cdataSection;
    }

    public void setCdataSection(String value) {
        this.cdataSection = value;
    }

}
