package uk.me.jeffsutton;

import java.net.URI;
import java.util.Map;

/**
 * Created by jeff on 17/12/2015.
 */
public class XRequest {
    public URI uri;
    public String method;
    public String body;
    public Map<String, String> headers;
}
