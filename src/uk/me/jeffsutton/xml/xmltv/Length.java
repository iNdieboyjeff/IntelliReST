package uk.me.jeffsutton.xml.xmltv;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Text;

/**
 * Created by jeff on 22/12/2015.
 */
public class Length {

    @Text(required = false)
    String textValue;

    @Attribute(name = "units", required = false)
    String units;

    public String getTextValue() {
        return this.textValue;
    }

    public void setTextValue(String value) {
        this.textValue = value;
    }

    public String getUnits() {
        return this.units;
    }

    public void setUnits(String value) {
        this.units = value;
    }

}
