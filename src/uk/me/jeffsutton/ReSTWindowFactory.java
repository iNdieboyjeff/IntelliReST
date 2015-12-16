package uk.me.jeffsutton;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

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
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTable table2;
    private JButton removeButton1;
    private JButton addButton;
    private ToolWindow myToolWindow;

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        myToolWindow = toolWindow;
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(rootContent, "", false);
        toolWindow.getContentManager().addContent(content);

        String column_names[]= {"Key", "Value"};
        DefaultTableModel table_model=new DefaultTableModel(column_names,1);
        table1.setModel(table_model);
    }
}
