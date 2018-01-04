package uk.me.jeffsutton.xml.xmltv;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Text;

/**
 * Created by jeff on 22/12/2015.
 */
public class Desc {

    @Text(required = false)
    String textValue;

    @Attribute(name = "lang", required = false)
    String lang;

    public String getTextValue() {
        return this.textValue;
    }

    public void setTextValue(String value) {
        this.textValue = value;
    }

    public String getLang() {
        return this.lang;
    }

    public void setLang(String value) {
        this.lang = value;
    }

}
