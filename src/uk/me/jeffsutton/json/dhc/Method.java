
package uk.me.jeffsutton.json.dhc;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Method implements Parcelable
{

    @SerializedName("requestBody")
    @Expose
    private boolean requestBody;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("name")
    @Expose
    private String name;
    public final static Creator<Method> CREATOR = new Creator<Method>() {


        public Method createFromParcel(Parcel in) {
            Method instance = new Method();
            instance.requestBody = ((boolean) in.readValue((boolean.class.getClassLoader())));
            instance.link = ((String) in.readValue((String.class.getClassLoader())));
            instance.name = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public Method[] newArray(int size) {
            return (new Method[size]);
        }

    }
    ;

    /**
     * 
     * @return
     *     The requestBody
     */
    public boolean isRequestBody() {
        return requestBody;
    }

    /**
     * 
     * @param requestBody
     *     The requestBody
     */
    public void setRequestBody(boolean requestBody) {
        this.requestBody = requestBody;
    }

    /**
     * 
     * @return
     *     The link
     */
    public String getLink() {
        return link;
    }

    /**
     * 
     * @param link
     *     The link
     */
    public void setLink(String link) {
        this.link = link;
    }

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

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(requestBody);
        dest.writeValue(link);
        dest.writeValue(name);
    }

    public int describeContents() {
        return  0;
    }

}
