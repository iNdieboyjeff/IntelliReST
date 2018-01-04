
package uk.me.jeffsutton.json.dhc;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Body implements Parcelable
{

    @SerializedName("autoSetLength")
    @Expose
    private boolean autoSetLength;
    @SerializedName("textBody")
    @Expose
    private String textBody;
    @SerializedName("bodyType")
    @Expose
    private String bodyType;
    public final static Creator<Body> CREATOR = new Creator<Body>() {


        public Body createFromParcel(Parcel in) {
            Body instance = new Body();
            instance.autoSetLength = ((boolean) in.readValue((boolean.class.getClassLoader())));
            instance.textBody = ((String) in.readValue((String.class.getClassLoader())));
            instance.bodyType = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public Body[] newArray(int size) {
            return (new Body[size]);
        }

    }
    ;

    /**
     * 
     * @return
     *     The autoSetLength
     */
    public boolean isAutoSetLength() {
        return autoSetLength;
    }

    /**
     * 
     * @param autoSetLength
     *     The autoSetLength
     */
    public void setAutoSetLength(boolean autoSetLength) {
        this.autoSetLength = autoSetLength;
    }

    /**
     * 
     * @return
     *     The textBody
     */
    public String getTextBody() {
        return textBody;
    }

    /**
     * 
     * @param textBody
     *     The textBody
     */
    public void setTextBody(String textBody) {
        this.textBody = textBody;
    }

    /**
     * 
     * @return
     *     The bodyType
     */
    public String getBodyType() {
        return bodyType;
    }

    /**
     * 
     * @param bodyType
     *     The bodyType
     */
    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(autoSetLength);
        dest.writeValue(textBody);
        dest.writeValue(bodyType);
    }

    public int describeContents() {
        return  0;
    }

}
