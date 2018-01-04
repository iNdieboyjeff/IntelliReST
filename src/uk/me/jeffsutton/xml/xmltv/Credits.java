package uk.me.jeffsutton.xml.xmltv;

import org.simpleframework.xml.ElementList;

import java.util.List;

/**
 * Created by jeff on 22/12/2015.
 */
public class Credits {

    @ElementList(name = "actor", required = false, entry = "actor", inline = true)
    List<String> actor;

    @ElementList(name = "presenter", required = false, entry = "presenter", inline = true)
    List<String> presenter;

    @ElementList(name = "director", required = false, entry = "director", inline = true)
    List<String> director;

    public List<String> getActor() {
        return this.actor;
    }

    public void setActor(List<String> value) {
        this.actor = value;
    }

    public List<String> getPresenter() {
        return this.presenter;
    }

    public void setPresenter(List<String> value) {
        this.presenter = value;
    }

    public List<String> getDirector() {
        return this.director;
    }

    public void setDirector(List<String> value) {
        this.director = value;
    }

}
