package uk.me.jeffsutton.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Text;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.net.URL;
import java.util.List;

@Root(name="charles-session")
public class CharlesSession {

    @ElementList(name="transaction", required=false, entry="transaction", inline=true)
    List<Transaction> transaction;

    public List<Transaction> getTransaction() {return this.transaction;}
    public void setTransaction(List<Transaction> value) {this.transaction = value;}

    public static class Request {

        @Attribute(name="charset", required=false)
        String charset;

        @Attribute(name="headers", required=false)
        Headers headers;

        @Attribute(name="mime-type", required=false)
        String mimeType;

        @Attribute(name="body", required=false)
        Body body;

        public String getCharset() {return this.charset;}
        public void setCharset(String value) {this.charset = value;}

        public Headers getHeaders() {return this.headers;}
        public void setHeaders(Headers value) {this.headers = value;}

        public String getMimeType() {return this.mimeType;}
        public void setMimeType(String value) {this.mimeType = value;}

        public Body getBody() {return this.body;}
        public void setBody(Body value) {this.body = value;}

    }

    public static class Headers {

        @Element(name="first-line", required=false)
        FirstLine firstLine;

        @ElementList(name="header", required=false, entry="header", inline=true)
        List<Header> header;

        public FirstLine getFirstLine() {return this.firstLine;}
        public void setFirstLine(FirstLine value) {this.firstLine = value;}

        public List<Header> getHeader() {return this.header;}
        public void setHeader(List<Header> value) {this.header = value;}

    }

    public static class Response {

        @Attribute(name="headers", required=false)
        Headers headers;

        @Attribute(name="charset", required=false)
        String charset;

        @Attribute(name="mime-type", required=false)
        String mimeType;

        @Attribute(name="body", required=false)
        Body body;

        @Attribute(name="status", required=false)
        Integer status;

        public Headers getHeaders() {return this.headers;}
        public void setHeaders(Headers value) {this.headers = value;}

        public String getCharset() {return this.charset;}
        public void setCharset(String value) {this.charset = value;}

        public String getMimeType() {return this.mimeType;}
        public void setMimeType(String value) {this.mimeType = value;}

        public Body getBody() {return this.body;}
        public void setBody(Body value) {this.body = value;}

        public Integer getStatus() {return this.status;}
        public void setStatus(Integer value) {this.status = value;}

    }

    public static class FirstLine {

        @Element(name="#cdata-section", required=false)
        String cdataSection;

        public String getCdataSection() {return this.cdataSection;}
        public void setCdataSection(String value) {this.cdataSection = value;}

    }

    public static class Header {

        @Element(name="name", required=false)
        String name;

        @Element(name="value", required=false)
        String value;

        public String getName() {return this.name;}
        public void setName(String value) {this.name = value;}

        public String getValue() {return this.value;}
        public void setValue(String value) {this.value = value;}

    }

    public static class Body {

        @Element(name="#cdata-section", required=false)
        String cdataSection;

        @Attribute(name="encoding", required=false)
        String encoding;

        public String getCdataSection() {return this.cdataSection;}
        public void setCdataSection(String value) {this.cdataSection = value;}

        public String getEncoding() {return this.encoding;}
        public void setEncoding(String value) {this.encoding = value;}

    }

    public static class Transaction {

        @Attribute(name="requestBeginTime", required=false)
        Double requestBeginTime;

        @Element(name="request", required=false)
        Request request;

        @Attribute(name="method", required=false)
        String method;

        @Attribute(name="startTimeMillis", required=false)
        Long startTimeMillis;

        @Attribute(name="actualPort", required=false)
        Integer actualPort;

        @Attribute(name="requestTimeMillis", required=false)
        Long requestTimeMillis;

        @Attribute(name="responseTime", required=false)
        Double responseTime;

        @Attribute(name="query", required=false)
        String query;

        @Attribute(name="responseTimeMillis", required=false)
        Long responseTimeMillis;

        @Attribute(name="clientAddress", required=false)
        Integer clientAddress;

        @Attribute(name="requestTime", required=false)
        Double requestTime;

        @Attribute(name="path", required=false)
        String path;

        @Attribute(name="endTimeMillis", required=false)
        Long endTimeMillis;

        @Attribute(name="protocol", required=false)
        String protocol;

        @Element(name="response", required=false)
        Response response;

        @Attribute(name="connectDuration", required=false)
        String connectDuration;

        @Attribute(name="host", required=false)
        Double host;

        @Attribute(name="requestBeginTimeMillis", required=false)
        Long requestBeginTimeMillis;

        @Attribute(name="protocolVersion", required=false)
        String protocolVersion;

        @Attribute(name="startTime", required=false)
        Double startTime;

        @Attribute(name="endTime", required=false)
        Double endTime;

        @Attribute(name="dnsDuration", required=false)
        Integer dnsDuration;

        @Attribute(name="remoteAddress", required=false)
        Double remoteAddress;

        @Attribute(name="status", required=false)
        String status;

        public Double getRequestBeginTime() {return this.requestBeginTime;}
        public void setRequestBeginTime(Double value) {this.requestBeginTime = value;}

        public Request getRequest() {return this.request;}
        public void setRequest(Request value) {this.request = value;}

        public String getMethod() {return this.method;}
        public void setMethod(String value) {this.method = value;}

        public Long getStartTimeMillis() {return this.startTimeMillis;}
        public void setStartTimeMillis(Long value) {this.startTimeMillis = value;}

        public Integer getActualPort() {return this.actualPort;}
        public void setActualPort(Integer value) {this.actualPort = value;}

        public Long getRequestTimeMillis() {return this.requestTimeMillis;}
        public void setRequestTimeMillis(Long value) {this.requestTimeMillis = value;}

        public Double getResponseTime() {return this.responseTime;}
        public void setResponseTime(Double value) {this.responseTime = value;}

        public String getQuery() {return this.query;}
        public void setQuery(String value) {this.query = value;}

        public Long getResponseTimeMillis() {return this.responseTimeMillis;}
        public void setResponseTimeMillis(Long value) {this.responseTimeMillis = value;}

        public Integer getClientAddress() {return this.clientAddress;}
        public void setClientAddress(Integer value) {this.clientAddress = value;}

        public Double getRequestTime() {return this.requestTime;}
        public void setRequestTime(Double value) {this.requestTime = value;}

        public String getPath() {return this.path;}
        public void setPath(String value) {this.path = value;}

        public Long getEndTimeMillis() {return this.endTimeMillis;}
        public void setEndTimeMillis(Long value) {this.endTimeMillis = value;}

        public String getProtocol() {return this.protocol;}
        public void setProtocol(String value) {this.protocol = value;}

        public Response getResponse() {return this.response;}
        public void setResponse(Response value) {this.response = value;}

        public String getConnectDuration() {return this.connectDuration;}
        public void setConnectDuration(String value) {this.connectDuration = value;}

        public Double getHost() {return this.host;}
        public void setHost(Double value) {this.host = value;}

        public Long getRequestBeginTimeMillis() {return this.requestBeginTimeMillis;}
        public void setRequestBeginTimeMillis(Long value) {this.requestBeginTimeMillis = value;}

        public String getProtocolVersion() {return this.protocolVersion;}
        public void setProtocolVersion(String value) {this.protocolVersion = value;}

        public Double getStartTime() {return this.startTime;}
        public void setStartTime(Double value) {this.startTime = value;}

        public Double getEndTime() {return this.endTime;}
        public void setEndTime(Double value) {this.endTime = value;}

        public Integer getDnsDuration() {return this.dnsDuration;}
        public void setDnsDuration(Integer value) {this.dnsDuration = value;}

        public Double getRemoteAddress() {return this.remoteAddress;}
        public void setRemoteAddress(Double value) {this.remoteAddress = value;}

        public String getStatus() {return this.status;}
        public void setStatus(String value) {this.status = value;}

    }

}