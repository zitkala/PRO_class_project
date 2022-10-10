package Models.Gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private JTextField txtInputName, txtInputMessage;

    private JTextArea txtChat;
    private JButton btnLogin;
    public MainFrame(int width, int height) {
        super("PRO2 ChatClient");
        setSize(width, height);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        initGui();
        setVisible(true);
    }
    private void initGui(){
        JPanel panelMain = new JPanel(new BorderLayout());
        //JPanel panelLogin = new JPanel(new FlowLayout(FlowLayout.LEFT));
        //JPanel panelChat = new JPanel();
        //JPanel panelMessage = new JPanel(new FlowLayout(FlowLayout.LEFT));

        panelMain.add(initLoginPanel(), BorderLayout.NORTH);
        panelMain.add(initChatPanel(), BorderLayout.CENTER);
        panelMain.add(initMessagePanel(), BorderLayout.SOUTH);

        add(panelMain);
    }

    private JPanel initLoginPanel(){
        JPanel panelLogin = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelLogin.add(new JLabel("Username"));
        txtInputName = new JTextField("",30);
        panelLogin.add(txtInputName);
        btnLogin = new JButton("login");
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Login clicked - " + txtInputName.getText());
            }
        });
        panelLogin.add(btnLogin);
        return panelLogin;
    }

    private JPanel initChatPanel(){
        JPanel panelChat = new JPanel();
        panelChat.setLayout(new BoxLayout(panelChat, BoxLayout.X_AXIS));
        txtChat = new JTextArea();
        txtChat.setEditable(false);
        for (int i = 0; i < 50; i++) {
            txtChat.append("Message " + i + "\n");
        }
        JScrollPane scrollPane = new JScrollPane(txtChat);
        panelChat.add(scrollPane);

        return panelChat;
    }

    private JPanel initMessagePanel(){
        JPanel panelMessage = new JPanel(new FlowLayout(FlowLayout.LEFT));
        txtInputMessage = new JTextField("", 50);
        panelMessage.add(txtInputMessage);

        JButton btnSend = new JButton("Send");
        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("btnSend clicked "+ txtInputMessage.getText());
                txtChat.append(txtInputMessage.getText() + "\n");
                txtInputMessage.setText("");
            }
        });
        return panelMessage;
    }
}
