package uk.me.jeffsutton.xml.xmltv;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import java.util.List;

/**
 * Created by jeff on 22/12/2015.
 */
public class Programme {

    @Element(name = "date", required = false)
    String date;

    @Element(name = "sub-title", required = false)
    SubTitle subTitle;

    @Attribute(name = "stop", required = false)
    String stop;

    @Element(name = "credits", required = false)
    Credits credits;

    @ElementList(name = "episode-num", required = false, entry = "episode-num", inline = true)
    List<EpisodeNum> episodeNum;

    @Attribute(name = "channel", required = false)
    String channel;

    @Attribute(name = "start", required = false)
    String start;

    @Element(name = "length", required = false)
    Length length;

    @Element(name = "title", required = false)
    Title title;

    @ElementList(name = "category", required = false, entry = "category", inline = true)
    List<Category> category;

    @Element(name = "star-rating", required = false)
    StarRating starRating;

    @Element(name = "desc", required = false)
    Desc desc;

    public String getDate() {
        return this.date;
    }

    public void setDate(String value) {
        this.date = value;
    }

    public SubTitle getSubTitle() {
        return this.subTitle;
    }

    public void setSubTitle(SubTitle value) {
        this.subTitle = value;
    }

    public String getStop() {
        return this.stop;
    }

    public void setStop(String value) {
        this.stop = value;
    }

    public Credits getCredits() {
        return this.credits;
    }

    public void setCredits(Credits value) {
        this.credits = value;
    }

    public List<EpisodeNum> getEpisodeNum() {
        return this.episodeNum;
    }

    public void setEpisodeNum(List<EpisodeNum> value) {
        this.episodeNum = value;
    }

    public String getChannel() {
        return this.channel;
    }

    public void setChannel(String value) {
        this.channel = value;
    }

    public String getStart() {
        return this.start;
    }

    public void setStart(String value) {
        this.start = value;
    }

    public Length getLength() {
        return this.length;
    }

    public void setLength(Length value) {
        this.length = value;
    }

    public Title getTitle() {
        return this.title;
    }

    public void setTitle(Title value) {
        this.title = value;
    }

    public List<Category> getCategory() {
        return this.category;
    }

    public void setCategory(List<Category> value) {
        this.category = value;
    }

    public StarRating getStarRating() {
        return this.starRating;
    }

    public void setStarRating(StarRating value) {
        this.starRating = value;
    }

    public Desc getDesc() {
        return this.desc;
    }

    public void setDesc(Desc value) {
        this.desc = value;
    }

}
