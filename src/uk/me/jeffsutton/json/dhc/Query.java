package uk.me.jeffsutton.json.dhc;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jeff on 21/12/2015.
 */
public class Query {

    @SerializedName("items")
    @Expose
    private List<Header> items = new ArrayList<>();

    public List<Header> getItems() {
        return items;
    }

    public void setItems(List<Header> items) {
        this.items = items;
    }
}
