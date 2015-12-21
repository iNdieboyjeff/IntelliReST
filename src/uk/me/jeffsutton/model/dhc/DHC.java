
package uk.me.jeffsutton.model.dhc;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class DHC implements Parcelable
{

    @SerializedName("version")
    @Expose
    private int version;
    @SerializedName("nodes")
    @Expose
    private List<Node> nodes = new ArrayList<Node>();


    public List<Node> getRequestNodes() {
        List<Node> rNode = new ArrayList<Node>();
        for (Node n : nodes) {
            if (n.isUriEditor()) {
                rNode.add(n);
            }
        }
        return rNode;
    }

    public final static Parcelable.Creator<DHC> CREATOR = new Creator<DHC>() {


        public DHC createFromParcel(Parcel in) {
            DHC instance = new DHC();
            instance.version = ((int) in.readValue((int.class.getClassLoader())));
            in.readList(instance.nodes, (Node.class.getClassLoader()));
            return instance;
        }

        public DHC[] newArray(int size) {
            return (new DHC[size]);
        }

    }
    ;

    /**
     * 
     * @return
     *     The version
     */
    public int getVersion() {
        return version;
    }

    /**
     * 
     * @param version
     *     The version
     */
    public void setVersion(int version) {
        this.version = version;
    }

    /**
     * 
     * @return
     *     The nodes
     */
    public List<Node> getNodes() {
        return nodes;
    }

    /**
     * 
     * @param nodes
     *     The nodes
     */
    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(version);
        dest.writeList(nodes);
    }

    public int describeContents() {
        return  0;
    }

}
