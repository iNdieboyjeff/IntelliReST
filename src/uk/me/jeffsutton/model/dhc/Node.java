
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
public class Node implements Parcelable
{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("lastModified")
    @Expose
    private String lastModified;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("descriptionHeight")
    @Expose
    private int descriptionHeight;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("parentId")
    @Expose
    private String parentId;
    @SerializedName("uriEditor")
    @Expose
    private boolean uriEditor;
    @SerializedName("headers")
    @Expose
    private List<Header> headers = new ArrayList<Header>();
    @SerializedName("method")
    @Expose
    private Method method;
    @SerializedName("body")
    @Expose
    private Body body;
    @SerializedName("headersType")
    @Expose
    private String headersType;
    @SerializedName("uri")
    @Expose
    private Uri uri;
    public final static Parcelable.Creator<Node> CREATOR = new Creator<Node>() {


        public Node createFromParcel(Parcel in) {
            Node instance = new Node();
            instance.id = ((String) in.readValue((String.class.getClassLoader())));
            instance.lastModified = ((String) in.readValue((String.class.getClassLoader())));
            instance.name = ((String) in.readValue((String.class.getClassLoader())));
            instance.type = ((String) in.readValue((String.class.getClassLoader())));
            instance.descriptionHeight = ((int) in.readValue((int.class.getClassLoader())));
            instance.description = ((String) in.readValue((String.class.getClassLoader())));
            instance.parentId = ((String) in.readValue((String.class.getClassLoader())));
            instance.uriEditor = ((boolean) in.readValue((boolean.class.getClassLoader())));
            in.readList(instance.headers, (uk.me.jeffsutton.model.Header.class.getClassLoader()));
            instance.method = ((Method) in.readValue((Method.class.getClassLoader())));
            instance.body = ((Body) in.readValue((Body.class.getClassLoader())));
            instance.headersType = ((String) in.readValue((String.class.getClassLoader())));
            instance.uri = ((Uri) in.readValue((Uri.class.getClassLoader())));
            return instance;
        }

        public Node[] newArray(int size) {
            return (new Node[size]);
        }

    }
    ;

    /**
     * 
     * @return
     *     The id
     */
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The lastModified
     */
    public String getLastModified() {
        return lastModified;
    }

    /**
     * 
     * @param lastModified
     *     The lastModified
     */
    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
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

    /**
     * 
     * @return
     *     The type
     */
    public String getType() {
        return type;
    }

    /**
     * 
     * @param type
     *     The type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 
     * @return
     *     The descriptionHeight
     */
    public int getDescriptionHeight() {
        return descriptionHeight;
    }

    /**
     * 
     * @param descriptionHeight
     *     The descriptionHeight
     */
    public void setDescriptionHeight(int descriptionHeight) {
        this.descriptionHeight = descriptionHeight;
    }

    /**
     * 
     * @return
     *     The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     *     The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     * @return
     *     The parentId
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * 
     * @param parentId
     *     The parentId
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * 
     * @return
     *     The uriEditor
     */
    public boolean isUriEditor() {
        return uriEditor;
    }

    /**
     * 
     * @param uriEditor
     *     The uriEditor
     */
    public void setUriEditor(boolean uriEditor) {
        this.uriEditor = uriEditor;
    }

    /**
     * 
     * @return
     *     The headers
     */
    public List<Header> getHeaders() {
        return headers;
    }

    /**
     * 
     * @param headers
     *     The headers
     */
    public void setHeaders(List<Header> headers) {
        this.headers = headers;
    }

    /**
     * 
     * @return
     *     The method
     */
    public Method getMethod() {
        return method;
    }

    /**
     * 
     * @param method
     *     The method
     */
    public void setMethod(Method method) {
        this.method = method;
    }

    /**
     * 
     * @return
     *     The body
     */
    public Body getBody() {
        return body;
    }

    /**
     * 
     * @param body
     *     The body
     */
    public void setBody(Body body) {
        this.body = body;
    }

    /**
     * 
     * @return
     *     The headersType
     */
    public String getHeadersType() {
        return headersType;
    }

    /**
     * 
     * @param headersType
     *     The headersType
     */
    public void setHeadersType(String headersType) {
        this.headersType = headersType;
    }

    /**
     * 
     * @return
     *     The uri
     */
    public Uri getUri() {
        return uri;
    }

    /**
     * 
     * @param uri
     *     The uri
     */
    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(lastModified);
        dest.writeValue(name);
        dest.writeValue(type);
        dest.writeValue(descriptionHeight);
        dest.writeValue(description);
        dest.writeValue(parentId);
        dest.writeValue(uriEditor);
        dest.writeList(headers);
        dest.writeValue(method);
        dest.writeValue(body);
        dest.writeValue(headersType);
        dest.writeValue(uri);
    }

    public int describeContents() {
        return  0;
    }

    @Override
    public String toString() {
        return this.getName() + " - (" + this.getUri().getScheme().getName() + "://"+this.getUri().getPath() + ")";
    }
}
