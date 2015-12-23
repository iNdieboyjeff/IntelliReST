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
import com.squareup.okhttp.*;
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
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jeff on 16/12/2015.
 */
public class ReSTWindowFactory implements ToolWindowFactory {

    private JComboBox comboBox1;
    private JPanel rootContent;
    private JTextField textField1;
    private JComboBox comboBox2;
    private JTable table1;
    private JButton button1;
    private JButton removeButton;
    private JComboBox textField2;
    private JComboBox textField3;
    private JTable table2;
    private JButton removeButton1;
    private JButton addButton;
    private JButton SUBMITButton;
    private JTabbedPane tabbedPane1;
    private JTextArea textArea1;
    private JTabbedPane mainTabs;
    private RTextScrollPane RTextScrollPane1;
    private RSyntaxTextArea RSyntaxTextArea1;
    private RTextScrollPane scroll2;
    private RSyntaxTextArea text2;
    private JTextArea textArea2;
    private JComboBox comboBox3;
    private JButton importButton;
    private JTextField textField4;
    private JPasswordField textField5;
    private JButton commonButton;
    private ToolWindow myToolWindow;
    private Project project;

    public ReSTWindowFactory() {
        SUBMITButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitRequest();
            }
        });
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((DefaultTableModel) table1.getModel()).addRow(new String[]{null, null});
            }
        });
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table1.getSelectedRow();
                if (row == -1) {
                    row = table1.getRowCount() - 1;
                }
                if (row > -1) {
                    ((DefaultTableModel) table1.getModel()).removeRow(row);
                }
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((DefaultTableModel) table2.getModel()).addRow(new String[]{null, null});
            }
        });
        removeButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table2.getSelectedRow();
                if (row == -1) {
                    row = table2.getRowCount() - 1;
                }
                if (row > -1) {
                    ((DefaultTableModel) table2.getModel()).removeRow(row);
                }
            }
        });
        textField2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RSyntaxTextArea1.setSyntaxEditingStyle(textField2.getSelectedItem().toString().split(";")[0]);
            }
        });
        importButton.addActionListener(new ActionListener() {
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
        VirtualFile VirtualFile = FileChooser.chooseFile(Descriptor, project, null);
        if (VirtualFile != null) {
            Serializer serializer = new Persister();
            try {
                CharlesSession session = serializer.read(CharlesSession.class, new File(VirtualFile.getCanonicalPath()));

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

                    comboBox1.setSelectedItem(t.getProtocol().toUpperCase());
                    textField1.setText(t.getHost() + t.getPath());
                    comboBox2.setSelectedItem(t.getMethod().toUpperCase());

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

                            RSyntaxTextArea1.setAutoIndentEnabled(true);
                            RSyntaxTextArea1.setCloseCurlyBraces(true);
                            RSyntaxTextArea1.setCloseMarkupTags(true);
                            RSyntaxTextArea1.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_XML);
                            RSyntaxTextArea1.setText(s);
                        } catch (Exception err) {
                            try {
                                JsonParser parser = new JsonParser();
                                JsonElement el = parser.parse(t.getRequest().getBody().getValue());

                                Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().serializeNulls().create();
                                String s = gson.toJson(el);

                                RSyntaxTextArea1.setAutoIndentEnabled(true);
                                RSyntaxTextArea1.setCloseCurlyBraces(true);
                                RSyntaxTextArea1.setCloseMarkupTags(true);
                                RSyntaxTextArea1.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JSON);
                                RSyntaxTextArea1.setText(s);
                            } catch (Exception err2) {
                                err2.printStackTrace();RSyntaxTextArea1.setAutoIndentEnabled(true);
                                RSyntaxTextArea1.setCloseCurlyBraces(true);
                                RSyntaxTextArea1.setCloseMarkupTags(true);
                                RSyntaxTextArea1.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_NONE);

                                RSyntaxTextArea1.setText(t.getRequest().getBody().getValue());
                            }
                        }
                    } else {
                        RSyntaxTextArea1.setText(null);
                    }

                    URIBuilder b = new URIBuilder();
                    b.setScheme(t.getProtocol());
                    b.setHost(t.getHost());
                    b.setPath(t.getPath());
                    b.setCustomQuery(t.getQuery());
                    String column_names[] = {"Key", "Value"};
                    DefaultTableModel table_model = new DefaultTableModel(column_names, 0);
                    table1.setModel(table_model);
                    table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                    try {
                        Map<String,String> q = splitQuery(b.build().toURL());
                        for (Map.Entry<String, String> e : q.entrySet()) {
                            ((DefaultTableModel) table1.getModel()).addRow(new String[] {e.getKey(),e.getValue()});
                        }
                    } catch (UnsupportedEncodingException e) {
                    } catch (MalformedURLException e) {
                    }
                     column_names = new String[] {"Key", "Value"};
                     table_model = new DefaultTableModel(column_names, 0);
                    table2.setModel(table_model);
                    table2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

                    if (t.getRequest().getHeaders() != null && t.getRequest().getHeaders().size() > 0) {
                        for (Header e : t.getRequest().getHeaders()) {
                            try {
                                if (e.getName().equalsIgnoreCase("User-Agent")) {
                                    comboBox3.setSelectedItem(e.getValue());
                                } else if (e.getName().equalsIgnoreCase("Content-Type")) {
                                    textField2.setSelectedItem(e.getValue());
                                    if (e.getValue().endsWith("/json"))
                                        RSyntaxTextArea1.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JSON);
                                } else if (e.getName().equalsIgnoreCase("Accept")) {
                                    textField3.setSelectedItem(e.getValue());
                                } else {
                                    ((DefaultTableModel) table2.getModel()).addRow(new String[]{e.getName(), e.getValue()});
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
                DHC d = gson.fromJson(new FileReader(new File(VirtualFile.getCanonicalPath())), DHC.class);
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
                    comboBox1.setSelectedItem(n.getUri().getScheme().getName().toUpperCase());
                    textField1.setText(n.getUri().getPath());
                    comboBox2.setSelectedItem(n.getMethod().getName().toUpperCase());

                    String[] column_names = new String[] {"Key", "Value"};
                    DefaultTableModel table_model = new DefaultTableModel(column_names, 0);
                    table2.setModel(table_model);
                    table2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

                    if (n.getHeaders() != null && n.getHeaders().size() > 0) {
                        for (uk.me.jeffsutton.json.dhc.Header e : n.getHeaders()) {
                            try {
                                if (e.getName().equalsIgnoreCase("User-Agent")) {
                                    comboBox3.setSelectedItem(e.getValue());
                                } else if (e.getName().equalsIgnoreCase("Content-Type")) {
                                    textField2.setSelectedItem(e.getValue());
                                    if (e.getValue().endsWith("/json"))
                                        RSyntaxTextArea1.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JSON);
                                } else if (e.getName().equalsIgnoreCase("Accept")) {
                                    textField3.setSelectedItem(e.getValue());
                                } else {
                                    ((DefaultTableModel) table2.getModel()).addRow(new String[]{e.getName(), e.getValue()});
                                }
                            } catch (Exception eee) {}
                        }
                    }
                    column_names = new String[] {"Key", "Value"};
                     table_model = new DefaultTableModel(column_names, 0);
                    table1.setModel(table_model);
                    table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                    if (n.getUri() != null && n.getUri().query != null && n.getUri().query.getItems() != null) {
                        try {
                            for (uk.me.jeffsutton.json.dhc.Header e : n.getUri().query.getItems()) {
                                ((DefaultTableModel) table1.getModel()).addRow(new String[]{e.getName(), e.getValue()});
                            }
                        } catch (Exception e) {
                        }
                    }

                    if (n.getBody() != null) {
                        RSyntaxTextArea1.setText(n.getBody().getTextBody());
                    } else {
                        RSyntaxTextArea1.setText(null);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        myToolWindow = toolWindow;
        this.project = project;
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(rootContent, "", false);
        toolWindow.getContentManager().addContent(content);

        String column_names[] = {"Key", "Value"};
        DefaultTableModel table_model = new DefaultTableModel(column_names, 0);
        table1.setModel(table_model);
        table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        DefaultTableModel table_model2 = new DefaultTableModel(column_names, 0);
        table2.setModel(table_model2);

        RTextScrollPane1.setViewportView(RSyntaxTextArea1);
        RSyntaxTextArea1.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JSON);
        RTextScrollPane1.setLineNumbersEnabled(true);
        RTextScrollPane1.setFoldIndicatorEnabled(true);
        RSyntaxTextArea1.setCloseCurlyBraces(true);
        RSyntaxTextArea1.setCloseMarkupTags(true);
        RSyntaxTextArea1.setCloseMarkupTags(true);

        scroll2.setViewportView(text2);
        text2.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JSON);
        scroll2.setLineNumbersEnabled(true);
        scroll2.setFoldIndicatorEnabled(true);
        text2.setCloseCurlyBraces(true);
        text2.setCloseMarkupTags(true);
        text2.setCloseMarkupTags(true);

        File f = new File(project.getWorkspaceFile().getParent().getCanonicalPath(), "restRequest.json");
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
                comboBox1.setSelectedItem(req.uri.getScheme().toUpperCase());
                textField1.setText(req.uri.getHost() + req.uri.getPath());
                comboBox2.setSelectedItem(req.method.toUpperCase());
                RSyntaxTextArea1.setText(req.body);
                try {
                    Map<String,String> q = splitQuery(req.uri.toURL());
                    for (Map.Entry<String, String> e : q.entrySet()) {
                        ((DefaultTableModel) table1.getModel()).addRow(new String[] {e.getKey(),e.getValue()});
                    }
                } catch (UnsupportedEncodingException e) {
                } catch (MalformedURLException e) {
                }
                if (req.headers != null && req.headers.size() > 0) {
                    for (Map.Entry<String, String> e : req.headers.entrySet()) {
                        if (e.getKey().equalsIgnoreCase("User-Agent")) {
                            comboBox3.setSelectedItem(e.getValue());
                        } else if (e.getKey().equalsIgnoreCase("Content-Type")) {
                            textField2.setSelectedItem(e.getValue());
                            if (e.getValue().endsWith("/json"))
                                RSyntaxTextArea1.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JSON);
                        } else if (e.getKey().equalsIgnoreCase("Accept")) {
                            textField3.setSelectedItem(e.getValue());
                        } else {
                            ((DefaultTableModel) table2.getModel()).addRow(new String[]{e.getKey(), e.getValue()});
                        }
                    }
                }
            }
        }

    }

    public static Map<String, String> splitQuery(URL url) throws UnsupportedEncodingException {
        Map<String, String> query_pairs = new LinkedHashMap<String, String>();
        String query = url.getQuery();
        if (query != null && !query.equalsIgnoreCase("")) {
            String[] pairs = query.split("&");
            if (pairs != null && pairs.length > 0)
            for (String pair : pairs) {
                int idx = pair.indexOf("=");
                query_pairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"), URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
            }
        }
        return query_pairs;
    }

    private void submitRequest() {
        OkHttpClient client = new OkHttpClient();

        XRequest request = new XRequest();

        URIBuilder builder = new URIBuilder();
        builder.setScheme(comboBox1.getSelectedItem().toString().toLowerCase());

        String[] str = textField1.getText().split("/", 1);
        builder.setHost(str[0]);

        if (str.length > 1)
            builder.setPath(str[1]);

        for (int i = 0; i < table1.getRowCount(); i++) {
            try {
                builder.addParameter(table1.getValueAt(i, 0).toString(), table1.getValueAt(i, 1).toString());
            } catch (Exception e) {
            }
        }

        try {
            URI uri = builder.build();
            System.out.println("Request to: " + uri.toString());
            request.uri = uri;
            RequestBody body = null;

            if (comboBox2.getSelectedItem().toString().equalsIgnoreCase("POST")) {
                body = new RequestBody() {
                    @Override
                    public MediaType contentType() {
                        return MediaType.parse(textField2.getSelectedItem().toString());
                    }

                    @Override
                    public long contentLength() throws IOException {
                        return RSyntaxTextArea1.getText().getBytes().length;
                    }

                    @Override
                    public void writeTo(BufferedSink sink) throws IOException {
                        sink.write(ByteString.encodeUtf8(RSyntaxTextArea1.getText()));
                    }
                };
            }


            Request.Builder rb = new Request.Builder().url(uri.toString()).method(comboBox2.getSelectedItem().toString(), body);
            request.method = comboBox2.getSelectedItem().toString();
            request.body = RSyntaxTextArea1.getText();

            if (textField2.getSelectedItem().toString() != null && !textField2.getSelectedItem().toString().equalsIgnoreCase("")) {
                rb.addHeader("Content-Type", textField2.getSelectedItem().toString());
            }

            if (textField3.getSelectedItem().toString() != null && !textField3.getSelectedItem().toString().equalsIgnoreCase("")) {
                rb.addHeader("Accept", textField3.getSelectedItem().toString());
            }

            if (comboBox3.getSelectedItem().toString() != null && !comboBox3.getSelectedItem().toString().equalsIgnoreCase("")) {
                rb.addHeader("User-Agent", comboBox3.getSelectedItem().toString());
            }

            for (int i = 0; i < table2.getRowCount(); i++) {
                try {
                    rb.addHeader(table2.getValueAt(i, 0).toString(), table2.getValueAt(i, 1).toString());
                } catch (Exception e) {
                }
            }

            if (textField4.getText() != null && !textField4.getText().equalsIgnoreCase("") && textField5.getText() != null && !textField5.getText().equalsIgnoreCase("")) {
                String credentals = Credentials.basic(textField4.getText(), textField5.getText());
                rb.addHeader("Authorization", credentals);
            }

            Request rest_request = rb.build();

            HashMap<String, String> saveHeaders = new HashMap<>();

            Headers requestHeaders = rest_request.headers();
            for (int i = 0; i < requestHeaders.size(); i++) {
                System.out.println(requestHeaders.name(i) + ": " + requestHeaders.value(i));
                saveHeaders.put(requestHeaders.name(i), requestHeaders.value(i));
            }

            request.headers = saveHeaders;

            Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
            String saveText = gson.toJson(request, XRequest.class);
            File f = new File(project.getWorkspaceFile().getParent().getCanonicalPath(), "restRequest.json");
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
            textArea1.setText("");
            textArea2.setText("");
            text2.setText("");
            client.interceptors().add(new OKHttpLoggingInterceptor(textArea2));
            client.newCall(rest_request).enqueue(new Callback() {
                                                @Override
                                                public void onFailure(Request request, IOException e) {
                                                    System.out.println("Error in request");
                                                }

                                                @Override
                                                public void onResponse(Response response) throws IOException {
                                                    Headers responseHeaders = response.headers();

                                                    try {
                                                        SwingUtilities.invokeAndWait(new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                for (int i = 0; i < responseHeaders.size(); i++) {
                                                                    System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
                                                                    textArea1.append(responseHeaders.name(i) + ": " + responseHeaders.value(i) + "\n");
                                                                }
                                                            }
                                                        });
                                                    } catch (InterruptedException e) {
                                                        e.printStackTrace();
                                                    } catch (InvocationTargetException e) {
                                                        e.printStackTrace();
                                                    }


                                                    if (!comboBox2.getSelectedItem().toString().equalsIgnoreCase("HEAD") && response.body() != null) {
                                                        System.out.println("Content-Type: " + response.body().contentType() + " :: " + response.body().contentType().type());
                                                        try {

                                                            SwingUtilities.invokeAndWait(new Runnable() {
                                                                @Override
                                                                public void run() {
                                                                    for (int i = 0; i < responseHeaders.size(); i++) {
                                                                        text2.setSyntaxEditingStyle(response.body().contentType().type() + "/" + response.body().contentType().subtype());

                                                                        ResponseBody responseBody = response.body();

                                                                        BufferedSource source = null;
                                                                        try {
                                                                            source = responseBody.source();
                                                                        } catch (IOException e) {
                                                                            e.printStackTrace();
                                                                        }
                                                                        try {
                                                                            source.request(Long.MAX_VALUE); // Buffer the entire body.
                                                                        } catch (IOException e) {
                                                                            e.printStackTrace();
                                                                        }
                                                                        Buffer buffer = source.buffer();

                                                                        Charset charset = OKHttpLoggingInterceptor.UTF8;
                                                                        MediaType contentType = responseBody.contentType();
                                                                        if (contentType != null) {
                                                                            charset = contentType.charset(OKHttpLoggingInterceptor.UTF8);
                                                                        }

                                                                        String s = buffer.clone().readString(charset);

                                                                        try {

                                                                            text2.setText((s));

                                                                            if (response.body().contentType().subtype().equalsIgnoreCase("json")) {
                                                                                JsonParser parser = new JsonParser();
                                                                                JsonElement el = parser.parse(s);

                                                                                Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().serializeNulls().create();
                                                                                s = gson.toJson(el);
                                                                                text2.setText((s));
                                                                                text2.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JSON);
                                                                            } else if (response.body().contentType().subtype().equalsIgnoreCase("xml") || response.body().contentType().subtype().equalsIgnoreCase("rss+xml") || response.body().contentType().subtype().equalsIgnoreCase("smil")) {
                                                                                Source xmlInput = new StreamSource(new StringReader(s));
                                                                                StringWriter stringWriter = new StringWriter();
                                                                                StreamResult xmlOutput = new StreamResult(stringWriter);
                                                                                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                                                                                transformerFactory.setAttribute("indent-number", 4);

                                                                                Transformer transformer = transformerFactory.newTransformer();
                                                                                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                                                                                transformer.transform(xmlInput, xmlOutput);
                                                                                String s2 = xmlOutput.getWriter().toString();
                                                                                text2.setText((s2));
                                                                                text2.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_XML);
                                                                            }


                                                                        } catch (Exception e) {
                                                                            e.printStackTrace();
                                                                        }
                                                                    }
                                                                }
                                                            });
                                                        } catch (InterruptedException e) {
                                                            e.printStackTrace();
                                                        } catch (Exception e) {
                                                            e.printStackTrace();
                                                        }


                                                        response.body().close();
                                                    }

                                                    try {
                                                        SwingUtilities.invokeAndWait(new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                mainTabs.setSelectedIndex(1);
                                                            }
                                                        });
                                                    } catch (InterruptedException e) {
                                                        e.printStackTrace();
                                                    } catch (InvocationTargetException e) {
                                                        e.printStackTrace();
                                                    }

                                                }
                                            }

            );


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
