
package uk.me.jeffsutton.model.dhc;

import javax.annotation.Generated;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

@Generated("org.jsonschema2pojo")
public class Uri implements Parcelable
{

    @SerializedName("path")
    @Expose
    private String path;
    @SerializedName("scheme")
    @Expose
    private Scheme scheme;
    @SerializedName("query")
    @Expose
    public Query query;
    public final static Parcelable.Creator<Uri> CREATOR = new Creator<Uri>() {


        public Uri createFromParcel(Parcel in) {
            Uri instance = new Uri();
            instance.path = ((String) in.readValue((String.class.getClassLoader())));
            instance.scheme = ((Scheme) in.readValue((Scheme.class.getClassLoader())));
            return instance;
        }

        public Uri[] newArray(int size) {
            return (new Uri[size]);
        }

    }
    ;

    /**
     * 
     * @return
     *     The path
     */
    public String getPath() {
        return path;
    }

    /**
     * 
     * @param path
     *     The path
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 
     * @return
     *     The scheme
     */
    public Scheme getScheme() {
        return scheme;
    }

    /**
     * 
     * @param scheme
     *     The scheme
     */
    public void setScheme(Scheme scheme) {
        this.scheme = scheme;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(path);
        dest.writeValue(scheme);
    }

    public int describeContents() {
        return  0;
    }

}
