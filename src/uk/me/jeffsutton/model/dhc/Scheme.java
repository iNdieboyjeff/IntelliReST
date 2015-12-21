
package uk.me.jeffsutton.model.dhc;

import javax.annotation.Generated;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Scheme implements Parcelable
{

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("version")
    @Expose
    private String version;
    public final static Parcelable.Creator<Scheme> CREATOR = new Creator<Scheme>() {


        public Scheme createFromParcel(Parcel in) {
            Scheme instance = new Scheme();
            instance.name = ((String) in.readValue((String.class.getClassLoader())));
            instance.version = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public Scheme[] newArray(int size) {
            return (new Scheme[size]);
        }

    }
    ;

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The version
     */
    public String getVersion() {
        return version;
    }

    /**
     * 
     * @param version
     *     The version
     */
    public void setVersion(String version) {
        this.version = version;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(name);
        dest.writeValue(version);
    }

    public int describeContents() {
        return  0;
    }

}
