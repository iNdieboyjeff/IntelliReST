package uk.me.jeffsutton;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Condition;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.intellij.util.containers.HashMap;
import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;
import org.apache.http.client.utils.URIBuilder;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;
import org.jetbrains.annotations.NotNull;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import uk.me.jeffsutton.json.dhc.DHC;
import uk.me.jeffsutton.json.dhc.Node;
import uk.me.jeffsutton.xml.charles.CharlesSession;
import uk.me.jeffsutton.xml.charles.Header;
import uk.me.jeffsutton.xml.charles.Transaction;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jeff on 16/12/2015.
 */
public class ReSTWindowFactory implements ToolWindowFactory {
    private JComboBox mSchemeComboBox;
    private JPanel rootContent;
    private JTextField mUrlTextField;
    private JComboBox mMethodComboBox;
    private JTable mQuerysTable;
    private JButton button1;
    private JButton removeButton;
    private JComboBox mContentTypeComboBox;
    private JComboBox mAcceptComboBox;
    private JTable mHeadersTable;
    private JButton mRemoveButton;
    private JButton mAddButton;
    private JButton mSubmitButton;
    private JTabbedPane tabbedPane1;
    private JTextArea mResponseHeadersTextArea;
    private JTabbedPane mMainTabs;
    private RTextScrollPane RTextScrollPane1;
    private RSyntaxTextArea mRequestBodyRSyntaxTextArea;
    private RTextScrollPane scroll2;
    private RSyntaxTextArea mResponseBodyTextArea;
    private JTextArea mLogTextArea;
    private JComboBox mUserAgentComboBox;
    private JButton mImportButton;
    private JTextField mBasicAuthUserNameTextField;
    private JPasswordField mBasicAuthPwdPasswordField;
    private JButton commonButton;
    private ToolWindow myToolWindow;

    private String mConfigFilePath;

    public ReSTWindowFactory() {
        mSubmitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitRequest();
            }
        });
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((DefaultTableModel) mQuerysTable.getModel()).addRow(new String[]{null, null});
            }
        });
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = mQuerysTable.getSelectedRow();
                if (row == -1) {
                    row = mQuerysTable.getRowCount() - 1;
                }
                if (row > -1) {
                    ((DefaultTableModel) mQuerysTable.getModel()).removeRow(row);
                }
            }
        });
        mAddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((DefaultTableModel) mHeadersTable.getModel()).addRow(new String[]{null, null});
            }
        });
        mRemoveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = mHeadersTable.getSelectedRow();
                if (row == -1) {
                    row = mHeadersTable.getRowCount() - 1;
                }
                if (row > -1) {
                    ((DefaultTableModel) mHeadersTable.getModel()).removeRow(row);
                }
            }
        });
        mContentTypeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mRequestBodyRSyntaxTextArea.setSyntaxEditingStyle(mContentTypeComboBox.getSelectedItem().toString().split(";")[0]);
            }
        });
        mImportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                importRequest();
            }
        });
//        commonButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String[] headers = new String[] {"X-Requested-With", "DNT", "X-Forward-For", "X-Wap-Profile",
//                        "X-Csrf-Token", "X-Clacks", "Pragma", "If-Match", "If-Modified-Since", "If-None-Match",
//                        "If-Range", "Max-Forwards", "Origin", "Proxy-Authorization", "Referer"};
//                String[] commonValues = new String[] {"XMLHttpRequest", "1", "", "", "", "GNU Terry Pratchett",
//                "","","","","","","","",""};
//                String[] uaprof = new String[] {"http://wap.sonymobile.com/UAprof/D6503R1701.xml",
//                "http://wap.sonymobile.com/UAprof/SGP521R1701.xml", "http://wap.samsungmobile.com/uaprof/SM-G920A.xml",
//                "http://wap.samsungmobile.com/uaprof/SM-G900A.xml", "http://wap.samsungmobile.com/uaprof/GT-I9305.xml"};
//            }
//        });
    }

    private void importRequest() {
        FileChooserDescriptor Descriptor = new FileChooserDescriptor(true, false, false, false, false, false);
        Descriptor.setShowFileSystemRoots(true);
        Descriptor.withFileFilter(new Condition<VirtualFile>() {
            @Override
            public boolean value(VirtualFile virtualFile) {
                return virtualFile != null && virtualFile.getExtension() != null && (virtualFile.getExtension().equalsIgnoreCase("xml") || virtualFile.getExtension().equalsIgnoreCase("json") );
            }
        });
        VirtualFile importFile = FileChooser.chooseFile(Descriptor, null, null);
        if (importFile != null) {
            Serializer serializer = new Persister();
            try {
                CharlesSession session = serializer.read(CharlesSession.class, new File(importFile.getCanonicalPath()));

                if (session == null || session.getTransaction() == null || session.getTransaction().size() == 0) {
                    // We have no requests to load. Show an error
                }

                Transaction t;

                if (session.getTransaction().size() == 1) {
                    t = session.getTransaction().get(0);
                } else {

                    t = (Transaction) JOptionPane.showInputDialog(null, "Select request to load", "Load Charles Proxy Request", JOptionPane.QUESTION_MESSAGE, null, session.getTransaction().toArray(), session.getTransaction().get(0));
                }

                if (t != null) {

                    mSchemeComboBox.setSelectedItem(t.getProtocol().toUpperCase());
                    mUrlTextField.setText(t.getHost() + t.getPath());
                    mMethodComboBox.setSelectedItem(t.getMethod().toUpperCase());

                    if (t.getRequest().getBody() != null && t.getRequest().getBody().getValue() != null) {
                        try {
                            Source xmlInput = new StreamSource(new StringReader(t.getRequest().getBody().getValue()));
                            StringWriter stringWriter = new StringWriter();
                            StreamResult xmlOutput = new StreamResult(stringWriter);
                            TransformerFactory transformerFactory = TransformerFactory.newInstance();
                            transformerFactory.setAttribute("indent-number", 4);

                            Transformer transformer = transformerFactory.newTransformer();
                            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                            transformer.transform(xmlInput, xmlOutput);
                            String s = xmlOutput.getWriter().toString();

                            mRequestBodyRSyntaxTextArea.setAutoIndentEnabled(true);
                            mRequestBodyRSyntaxTextArea.setCloseCurlyBraces(true);
                            mRequestBodyRSyntaxTextArea.setCloseMarkupTags(true);
                            mRequestBodyRSyntaxTextArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_XML);
                            mRequestBodyRSyntaxTextArea.setText(s);
                        } catch (Exception err) {
                            try {
                                JsonParser parser = new JsonParser();
                                JsonElement el = parser.parse(t.getRequest().getBody().getValue());

                                Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().serializeNulls().create();
                                String s = gson.toJson(el);

                                mRequestBodyRSyntaxTextArea.setAutoIndentEnabled(true);
                                mRequestBodyRSyntaxTextArea.setCloseCurlyBraces(true);
                                mRequestBodyRSyntaxTextArea.setCloseMarkupTags(true);
                                mRequestBodyRSyntaxTextArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JSON);
                                mRequestBodyRSyntaxTextArea.setText(s);
                            } catch (Exception err2) {
                                err2.printStackTrace();
                                mRequestBodyRSyntaxTextArea.setAutoIndentEnabled(true);
                                mRequestBodyRSyntaxTextArea.setCloseCurlyBraces(true);
                                mRequestBodyRSyntaxTextArea.setCloseMarkupTags(true);
                                mRequestBodyRSyntaxTextArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_NONE);

                                mRequestBodyRSyntaxTextArea.setText(t.getRequest().getBody().getValue());
                            }
                        }
                    } else {
                        mRequestBodyRSyntaxTextArea.setText(null);
                    }

                    URIBuilder b = new URIBuilder();
                    b.setScheme(t.getProtocol());
                    b.setHost(t.getHost());
                    b.setPath(t.getPath());
                    b.setCustomQuery(t.getQuery());
                    String column_names[] = {"Key", "Value"};
                    DefaultTableModel table_model = new DefaultTableModel(column_names, 0);
                    mQuerysTable.setModel(table_model);
                    mQuerysTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                    try {
                        Map<String,String> q = splitQuery(b.build().toURL());
                        for (Map.Entry<String, String> e : q.entrySet()) {
                            ((DefaultTableModel) mQuerysTable.getModel()).addRow(new String[] {e.getKey(),e.getValue()});
                        }
                    } catch (UnsupportedEncodingException | MalformedURLException e) {
                        e.printStackTrace();
                    }
                     column_names = new String[] {"Key", "Value"};
                     table_model = new DefaultTableModel(column_names, 0);
                    mHeadersTable.setModel(table_model);
                    mHeadersTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

                    if (t.getRequest().getHeaders() != null && t.getRequest().getHeaders().size() > 0) {
                        for (Header e : t.getRequest().getHeaders()) {
                            try {
                                if (e.getName().equalsIgnoreCase("User-Agent")) {
                                    mUserAgentComboBox.setSelectedItem(e.getValue());
                                } else if (e.getName().equalsIgnoreCase("Content-Type")) {
                                    mContentTypeComboBox.setSelectedItem(e.getValue());
                                    if (e.getValue().endsWith("/json"))
                                        mRequestBodyRSyntaxTextArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JSON);
                                } else if (e.getName().equalsIgnoreCase("Accept")) {
                                    mAcceptComboBox.setSelectedItem(e.getValue());
                                } else {
                                    ((DefaultTableModel) mHeadersTable.getModel()).addRow(new String[]{e.getName(), e.getValue()});
                                }
                            } catch (Exception eee) {}
                        }
                    }
                }
                return;
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                Gson gson = new Gson();
                DHC d = gson.fromJson(new FileReader(new File(importFile.getCanonicalPath())), DHC.class);
                List<Node> nodes = d.getRequestNodes();
                Node n = null;
                if (nodes != null ) {
                    if (nodes.size() == 1) {
                        n = nodes.get(0);
                    } else {
                        n = (Node) JOptionPane.showInputDialog(null, "Select request to load", "Load DHC Request", JOptionPane.QUESTION_MESSAGE, null, nodes.toArray(), nodes.get(0));
                    }
                }
                if (n != null) {
                    mSchemeComboBox.setSelectedItem(n.getUri().getScheme().getName().toUpperCase());
                    mUrlTextField.setText(n.getUri().getPath());
                    mMethodComboBox.setSelectedItem(n.getMethod().getName().toUpperCase());

                    String[] column_names = new String[] {"Key", "Value"};
                    DefaultTableModel table_model = new DefaultTableModel(column_names, 0);
                    mHeadersTable.setModel(table_model);
                    mHeadersTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

                    if (n.getHeaders() != null && n.getHeaders().size() > 0) {
                        for (uk.me.jeffsutton.json.dhc.Header e : n.getHeaders()) {
                            try {
                                if (e.getName().equalsIgnoreCase("User-Agent")) {
                                    mUserAgentComboBox.setSelectedItem(e.getValue());
                                } else if (e.getName().equalsIgnoreCase("Content-Type")) {
                                    mContentTypeComboBox.setSelectedItem(e.getValue());
                                    if (e.getValue().endsWith("/json"))
                                        mRequestBodyRSyntaxTextArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JSON);
                                } else if (e.getName().equalsIgnoreCase("Accept")) {
                                    mAcceptComboBox.setSelectedItem(e.getValue());
                                } else {
                                    ((DefaultTableModel) mHeadersTable.getModel()).addRow(new String[]{e.getName(), e.getValue()});
                                }
                            } catch (Exception eee) {
                                eee.printStackTrace();
                            }
                        }
                    }
                    column_names = new String[] {"Key", "Value"};
                     table_model = new DefaultTableModel(column_names, 0);
                    mQuerysTable.setModel(table_model);
                    mQuerysTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                    if (n.getUri() != null && n.getUri().query != null && n.getUri().query.getItems() != null) {
                        try {
                            for (uk.me.jeffsutton.json.dhc.Header e : n.getUri().query.getItems()) {
                                ((DefaultTableModel) mQuerysTable.getModel()).addRow(new String[]{e.getName(), e.getValue()});
                            }
                        } catch (Exception e) {
                        }
                    }

                    if (n.getBody() != null) {
                        mRequestBodyRSyntaxTextArea.setText(n.getBody().getTextBody());
                    } else {
                        mRequestBodyRSyntaxTextArea.setText(null);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        mConfigFilePath = project.getWorkspaceFile().getParent().getCanonicalPath();
        myToolWindow = toolWindow;
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(rootContent, "", false);
        toolWindow.getContentManager().addContent(content);

        String column_names[] = {"Key", "Value"};
        DefaultTableModel table_model = new DefaultTableModel(column_names, 0);
        mQuerysTable.setModel(table_model);
        mQuerysTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        DefaultTableModel table_model2 = new DefaultTableModel(column_names, 0);
        mHeadersTable.setModel(table_model2);

        RTextScrollPane1.setViewportView(mRequestBodyRSyntaxTextArea);
        mRequestBodyRSyntaxTextArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JSON);
        RTextScrollPane1.setLineNumbersEnabled(true);
        RTextScrollPane1.setFoldIndicatorEnabled(true);
        mRequestBodyRSyntaxTextArea.setCloseCurlyBraces(true);
        mRequestBodyRSyntaxTextArea.setCloseMarkupTags(true);
        mRequestBodyRSyntaxTextArea.setCloseMarkupTags(true);

        scroll2.setViewportView(mResponseBodyTextArea);
        mResponseBodyTextArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JSON);
        scroll2.setLineNumbersEnabled(true);
        scroll2.setFoldIndicatorEnabled(true);
        mResponseBodyTextArea.setCloseCurlyBraces(true);
        mResponseBodyTextArea.setCloseMarkupTags(true);
        mResponseBodyTextArea.setCloseMarkupTags(true);

        File f = new File(mConfigFilePath, "restRequest.json");
        if (f.exists()) {
            byte[] b = new byte[0];
            try {
                b = Files.readAllBytes(Paths.get(f.toURI()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            String s = new String(b);
            Gson gson = new Gson();
            XRequest req = gson.fromJson(s, XRequest.class);

            if (req != null) {
                mSchemeComboBox.setSelectedItem(req.uri.getScheme().toUpperCase());
                mUrlTextField.setText(req.uri.getHost() + req.uri.getPath());
                mMethodComboBox.setSelectedItem(req.method.toUpperCase());
                mRequestBodyRSyntaxTextArea.setText(req.body);
                try {
                    Map<String,String> q = splitQuery(req.uri.toURL());
                    for (Map.Entry<String, String> e : q.entrySet()) {
                        ((DefaultTableModel) mQuerysTable.getModel()).addRow(new String[] {e.getKey(),e.getValue()});
                    }
                } catch (UnsupportedEncodingException | MalformedURLException e) {
                    e.printStackTrace();
                }
                if (req.headers != null && req.headers.size() > 0) {
                    for (Map.Entry<String, String> e : req.headers.entrySet()) {
                        if (e.getKey().equalsIgnoreCase("User-Agent")) {
                            mUserAgentComboBox.setSelectedItem(e.getValue());
                        } else if (e.getKey().equalsIgnoreCase("Content-Type")) {
                            mContentTypeComboBox.setSelectedItem(e.getValue());
                            if (e.getValue().endsWith("/json"))
                                mRequestBodyRSyntaxTextArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JSON);
                        } else if (e.getKey().equalsIgnoreCase("Accept")) {
                            mAcceptComboBox.setSelectedItem(e.getValue());
                        } else {
                            ((DefaultTableModel) mHeadersTable.getModel()).addRow(new String[]{e.getKey(), e.getValue()});
                        }
                    }
                }
            }
        }
    }

    private Map<String, String> splitQuery(URL url) throws UnsupportedEncodingException {
        Map<String, String> query_pairs = new LinkedHashMap<String, String>();
        String query = url.getQuery();
        if (query != null && !query.equalsIgnoreCase("")) {
            String[] pairs = query.split("&");
            if (pairs.length > 0)
            for (String pair : pairs) {
                int idx = pair.indexOf("=");
                query_pairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"), URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
            }
        }
        return query_pairs;
    }

    private OkHttpClient getHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String s) {
                mLogTextArea.append(s + "\n");
            }
        });
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.interceptors().add(logging);

        return builder.build();
    }

    private HttpUrl getUrl() {
        HttpUrl.Builder builder = new HttpUrl.Builder();
        builder.scheme(mSchemeComboBox.getSelectedItem().toString().toLowerCase());

        String[] str = mUrlTextField.getText().split("/", 1);
        builder.host(str[0]);

        if (str.length > 1) {
            builder.addPathSegment(str[1]);
        }

        for (int i = 0; i < mQuerysTable.getRowCount(); i++) {
            try {
                builder.setQueryParameter(mQuerysTable.getValueAt(i, 0).toString(),
                        mQuerysTable.getValueAt(i, 1).toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return builder.build();
    }

    private Request getRequest() throws URISyntaxException, MalformedURLException {
        Request.Builder builder = new Request.Builder();
        builder.url(getUrl());

        RequestBody body = null;
        String method = mMethodComboBox.getSelectedItem().toString();
        if (!method.equalsIgnoreCase("GET")) {
            body = new RequestBody() {
                @Override
                public MediaType contentType() {
                    return MediaType.parse(mContentTypeComboBox.getSelectedItem().toString());
                }

                @Override
                public long contentLength() throws IOException {
                    return mRequestBodyRSyntaxTextArea.getText().getBytes().length;
                }

                @Override
                public void writeTo(BufferedSink sink) throws IOException {
                    sink.write(ByteString.encodeUtf8(mRequestBodyRSyntaxTextArea.getText()));
                }
            };
        }
        builder.method(method, body);

        String contentType = mContentTypeComboBox.getSelectedItem().toString();
        if (null != contentType && contentType.length() > 0) {
            builder.addHeader("Content-Type", contentType);
        }
        String accept = mAcceptComboBox.getSelectedItem().toString();
        if (null != accept && accept.length() > 0) {
            builder.addHeader("Accept", accept);
        }
        String userAgent = mUserAgentComboBox.getSelectedItem().toString();
        if (null != userAgent && userAgent.length() > 0) {
            builder.addHeader("User-Agent", userAgent);
        }
        String basicAuthUserName = mBasicAuthUserNameTextField.getText();
        String basicAuthPwd = String.valueOf(mBasicAuthPwdPasswordField.getPassword());
        if (null != basicAuthUserName && basicAuthUserName.length() > 0) {
            String basicAuth = Credentials.basic(basicAuthUserName, basicAuthPwd);
            builder.addHeader("Authorization", basicAuth);
        }
        for (int i = 0; i < mHeadersTable.getRowCount(); i++) {
            try {
                builder.addHeader(mHeadersTable.getValueAt(i, 0).toString(),
                        mHeadersTable.getValueAt(i, 1).toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return builder.build();
    }

    private void submitRequest() {
        OkHttpClient client = getHttpClient();

        XRequest request = new XRequest();

        try {
            URI uri = getUrl().uri();
            System.out.println("Request to: " + uri.toString());
            request.uri = uri;
            request.method = mMethodComboBox.getSelectedItem().toString();
            request.body = mRequestBodyRSyntaxTextArea.getText();

            Request rest_request = getRequest();

            HashMap<String, String> saveHeaders = new HashMap<>();

            final Headers requestHeaders = rest_request.headers();
            for (int i = 0; i < requestHeaders.size(); i++) {
                System.out.println(requestHeaders.name(i) + ": " + requestHeaders.value(i));
                saveHeaders.put(requestHeaders.name(i), requestHeaders.value(i));
            }

            request.headers = saveHeaders;

            Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
            String saveText = gson.toJson(request, XRequest.class);
            File f = new File(mConfigFilePath, "restRequest.json");
            System.out.println("Saving to " + f.getAbsolutePath());
            System.out.println(saveText);
            if (!f.exists()) {
                f.createNewFile();
            }
            PrintWriter out = new PrintWriter(f);
            out.write(saveText);
            out.close();
            if (f.exists()) {

                VirtualFile virtualFile = LocalFileSystem.getInstance().refreshAndFindFileByIoFile(f);
                if (virtualFile != null) {
                    virtualFile.refresh(false, true);
                }
            }

            System.out.println("--------------------------------------------------------------\n\n");
            mResponseHeadersTextArea.setText("");
            mLogTextArea.setText("");
            mResponseBodyTextArea.setText("");

            Call call = client.newCall(rest_request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    System.out.println("Error in request");
                }

                @Override
                public void onResponse(Call call, final Response response) throws IOException {
                    showResponseHeaders(response.headers());
                    String method = mMethodComboBox.getSelectedItem().toString();
                    ResponseBody body = response.body();
                    if (!method.equalsIgnoreCase("HEAD") && body != null) {
                        showResponseBody(body);
                    }

                    switchTab(1);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void switchTab(final int index) {
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    mMainTabs.setSelectedIndex(index);
                }
            });
        } catch (InterruptedException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private void showResponseHeaders(final Headers headers) {
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < headers.size(); i++) {
                        System.out.println(headers.name(i) + ": " + headers.value(i));
                        mResponseHeadersTextArea.append(headers.name(i) + ": " + headers.value(i) + "\n");
                    }
                }
            });
        } catch (InterruptedException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private void showResponseBody(final ResponseBody responseBody) {
        System.out.println("Content-Type: " + responseBody.contentType() + " :: " + responseBody.contentType().type());
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    MediaType contentType = responseBody.contentType();
                    String subType = contentType.subtype();
                    mResponseBodyTextArea.setSyntaxEditingStyle(contentType.toString());

                    BufferedSource source = responseBody.source();
                    try {
                        source.request(Long.MAX_VALUE); // Buffer the entire body.
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Buffer buffer = source.buffer();

                    String s = buffer.clone().readUtf8();

                    try {

                        mResponseBodyTextArea.setText(s);

                        if (subType.equalsIgnoreCase("json")) {
                            JsonParser parser = new JsonParser();
                            JsonElement el = parser.parse(s);

                            Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().serializeNulls().create();
                            s = gson.toJson(el);
                            mResponseBodyTextArea.setText((s));
                            mResponseBodyTextArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JSON);
                        } else if (subType.equalsIgnoreCase("xml") || subType.equalsIgnoreCase("rss+xml") || subType.equalsIgnoreCase("smil")) {
                            Source xmlInput = new StreamSource(new StringReader(s));
                            StringWriter stringWriter = new StringWriter();
                            StreamResult xmlOutput = new StreamResult(stringWriter);
                            TransformerFactory transformerFactory = TransformerFactory.newInstance();
                            transformerFactory.setAttribute("indent-number", 4);

                            Transformer transformer = transformerFactory.newTransformer();
                            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                            transformer.transform(xmlInput, xmlOutput);
                            String s2 = xmlOutput.getWriter().toString();
                            mResponseBodyTextArea.setText((s2));
                            mResponseBodyTextArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_XML);
                        } else if (subType.toLowerCase().contains("html")) {
                            Source xmlInput = new StreamSource(new StringReader(s));
                            StringWriter stringWriter = new StringWriter();
                            StreamResult xmlOutput = new StreamResult(stringWriter);
                            TransformerFactory transformerFactory = TransformerFactory.newInstance();
                            transformerFactory.setAttribute("indent-number", 4);

                            Transformer transformer = transformerFactory.newTransformer();
                            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                            transformer.transform(xmlInput, xmlOutput);
                            String s2 = xmlOutput.getWriter().toString();
                            mResponseBodyTextArea.setText((s2));
                            mResponseBodyTextArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_HTML);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
