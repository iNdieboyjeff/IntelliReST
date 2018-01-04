package uk.me.jeffsutton.xml.xmltv;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Text;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name="tv")
public class Tv {

    @Attribute(name="generator-info-name", required=false)
    String generatorInfoName;

    @Attribute(name="generator-info-url", required=false)
    String generatorInfoUrl;

    @Attribute(name="source-info-name", required=false)
    String sourceInfoName;

    @ElementList(name="channel", required=false, entry="channel", inline=true)
    List<Channel> channel;

    @ElementList(name="programme", required=false, entry="programme", inline=true)
    List<Programme> programme;

    public String getGeneratorInfoName() {return this.generatorInfoName;}
    public void setGeneratorInfoName(String value) {this.generatorInfoName = value;}

    public String getGeneratorInfoUrl() {return this.generatorInfoUrl;}
    public void setGeneratorInfoUrl(String value) {this.generatorInfoUrl = value;}

    public String getSourceInfoName() {return this.sourceInfoName;}
    public void setSourceInfoName(String value) {this.sourceInfoName = value;}

    public List<Channel> getChannel() {return this.channel;}
    public void setChannel(List<Channel> value) {this.channel = value;}

    public List<Programme> getProgramme() {return this.programme;}
    public void setProgramme(List<Programme> value) {this.programme = value;}

}