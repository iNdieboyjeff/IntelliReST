package uk.me.jeffsutton.xml.xmltv;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Text;

/**
 * Created by jeff on 22/12/2015.
 */
public class EpisodeNum {

    @Attribute(name = "system", required = false)
    String system;

    @Text(required = false)
    String textValue;

    public String getSystem() {
        return this.system;
    }

    public void setSystem(String value) {
        this.system = value;
    }

    public String getTextValue() {
        return this.textValue;
    }

    public void setTextValue(String value) {
        this.textValue = value;
    }

}
