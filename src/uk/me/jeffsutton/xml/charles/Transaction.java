package uk.me.jeffsutton.xml.charles;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

/**
 * Created by jeff on 22/12/2015.
 */
public class Transaction {

    @Attribute(name = "requestBeginTime", required = true)
    String requestBeginTime;

    @Element(name = "request", required = false)
    Request request;

    @Attribute(name = "method", required = true)
    String method;

    @Attribute(name = "startTimeMillis", required = true)
    Long startTimeMillis;

    @Attribute(name = "actualPort", required = true)
    Integer actualPort;

    @Attribute(name = "requestTimeMillis", required = false)
    Long requestTimeMillis;

    @Attribute(name = "responseTime", required = true)
    String responseTime;

    @Attribute(name = "query", required = false)
    String query;

    @Attribute(name = "responseTimeMillis", required = true)
    Long responseTimeMillis;

    @Attribute(name = "clientAddress", required = true)
    String clientAddress;

    @Attribute(name = "requestTime", required = false)
    String requestTime;

    @Attribute(name = "path", required = true)
    String path;

    @Attribute(name = "endTimeMillis", required = true)
    Long endTimeMillis;

    @Attribute(name = "protocol", required = true)
    String protocol;

    @Element(name = "response", required = false)
    Response response;

    @Attribute(name = "connectDuration", required = false)
    String connectDuration;

    @Attribute(name = "host", required = false)
    String host;

    @Attribute(name = "requestBeginTimeMillis", required = false)
    Long requestBeginTimeMillis;

    @Attribute(name = "protocolVersion", required = false)
    String protocolVersion;

    @Attribute(name = "startTime", required = false)
    String startTime;

    @Attribute(name = "endTime", required = false)
    String endTime;

    @Attribute(name = "dnsDuration", required = false)
    Integer dnsDuration;

    @Attribute(name = "remoteAddress", required = true)
    String remoteAddress;

    @Attribute(name = "status", required = false)
    String status;

    @Override
    public String toString() {
        return "[" + this.getMethod() + "] " + this.protocol + "://" + this.getHost() + this.getPath();
    }

    public String getRequestBeginTime() {
        return this.requestBeginTime;
    }

    public void setRequestBeginTime(String value) {
        this.requestBeginTime = value;
    }

    public Request getRequest() {
        return this.request;
    }

    public void setRequest(Request value) {
        this.request = value;
    }

    public String getMethod() {
        return this.method;
    }

    public void setMethod(String value) {
        this.method = value;
    }

    public Long getStartTimeMillis() {
        return this.startTimeMillis;
    }

    public void setStartTimeMillis(Long value) {
        this.startTimeMillis = value;
    }

    public Integer getActualPort() {
        return this.actualPort;
    }

    public void setActualPort(Integer value) {
        this.actualPort = value;
    }

    public Long getRequestTimeMillis() {
        return this.requestTimeMillis;
    }

    public void setRequestTimeMillis(Long value) {
        this.requestTimeMillis = value;
    }

    public String getResponseTime() {
        return this.responseTime;
    }

    public void setResponseTime(String value) {
        this.responseTime = value;
    }

    public String getQuery() {
        return this.query;
    }

    public void setQuery(String value) {
        this.query = value;
    }

    public Long getResponseTimeMillis() {
        return this.responseTimeMillis;
    }

    public void setResponseTimeMillis(Long value) {
        this.responseTimeMillis = value;
    }

    public String getClientAddress() {
        return this.clientAddress;
    }

    public void setClientAddress(String value) {
        this.clientAddress = value;
    }

    public String getRequestTime() {
        return this.requestTime;
    }

    public void setRequestTime(String value) {
        this.requestTime = value;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String value) {
        this.path = value;
    }

    public Long getEndTimeMillis() {
        return this.endTimeMillis;
    }

    public void setEndTimeMillis(Long value) {
        this.endTimeMillis = value;
    }

    public String getProtocol() {
        return this.protocol;
    }

    public void setProtocol(String value) {
        this.protocol = value;
    }

    public Response getResponse() {
        return this.response;
    }

    public void setResponse(Response value) {
        this.response = value;
    }

    public String getConnectDuration() {
        return this.connectDuration;
    }

    public void setConnectDuration(String value) {
        this.connectDuration = value;
    }

    public String getHost() {
        return this.host;
    }

    public void setHost(String value) {
        this.host = value;
    }

    public Long getRequestBeginTimeMillis() {
        return this.requestBeginTimeMillis;
    }

    public void setRequestBeginTimeMillis(Long value) {
        this.requestBeginTimeMillis = value;
    }

    public String getProtocolVersion() {
        return this.protocolVersion;
    }

    public void setProtocolVersion(String value) {
        this.protocolVersion = value;
    }

    public String getStartTime() {
        return this.startTime;
    }

    public void setStartTime(String value) {
        this.startTime = value;
    }

    public String getEndTime() {
        return this.endTime;
    }

    public void setEndTime(String value) {
        this.endTime = value;
    }

    public Integer getDnsDuration() {
        return this.dnsDuration;
    }

    public void setDnsDuration(Integer value) {
        this.dnsDuration = value;
    }

    public String getRemoteAddress() {
        return this.remoteAddress;
    }

    public void setRemoteAddress(String value) {
        this.remoteAddress = value;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String value) {
        this.status = value;
    }

}
