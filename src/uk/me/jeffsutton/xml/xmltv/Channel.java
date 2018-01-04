package uk.me.jeffsutton.xml.xmltv;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;

import java.util.List;

/**
 * Created by jeff on 22/12/2015.
 */
public class Channel {

    @ElementList(name = "display-name", required = false, entry = "display-name", inline = true)
    List<String> displayName;

    @Attribute(name = "id", required = false)
    String id;

    public List<String> getDisplayName() {
        return this.displayName;
    }

    public void setDisplayName(List<String> value) {
        this.displayName = value;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String value) {
        this.id = value;
    }

}
