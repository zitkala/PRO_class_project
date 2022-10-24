package Models.Gui;

import Models.ChatClients.ChatClient;
import Models.ChatClients.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private ChatClient chatClient;
    private JTextField txtInputName, txtInputMessage;

    private JTextArea txtChat;
    private JButton btnLogin;
    public MainFrame(int width, int height, ChatClient chatClient) {
        super("PRO2 ChatClient");
        setSize(width, height);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.chatClient = chatClient;
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
        panelMain.add(initLoggedUsersPanel(), BorderLayout.EAST);

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
                if (chatClient.isAuthenticated()){
                    chatClient.logout();
                    btnLogin.setText("Login");
                    System.out.println("Logout clicked "+ txtInputName.getText());
                    txtInputName.setEditable(true);
                    txtChat.setEnabled(false);
                    txtInputMessage.setEnabled(false);

                }else {
                    String name = txtInputName.getText();
                    if (name.length()<1){
                        JOptionPane.showMessageDialog(null,"Enter your user name", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    chatClient.login(txtInputName.getText());
                    btnLogin.setText("Logout");
                    System.out.println("Login clicked " + txtInputName.getText());
                    txtInputName.setEditable(false);
                    txtChat.setEnabled(true);
                    txtInputMessage.setEnabled(true);
                }
                chatClient.login(txtInputName.getText());
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
        JScrollPane scrollPane = new JScrollPane(txtChat);
        panelChat.add(scrollPane);

        return panelChat;
    }
    private JPanel initLoggedUsersPanel(){
        JPanel panel = new JPanel();

        JTable tbLoggedUsers = new JTable();
        LoggedUsersTableModel loggedUsersTableModel = new LoggedUsersTableModel(chatClient);
        tbLoggedUsers.setModel(loggedUsersTableModel);

        chatClient.addActionListenerLoggedUsersChanged(e -> {
            loggedUsersTableModel.fireTableDataChanged();
        });

        JScrollPane scrollPane = new JScrollPane(tbLoggedUsers);
        scrollPane.setPreferredSize(new Dimension(250,500));
        panel.add(scrollPane);
        return panel;
    }
    private JPanel initMessagePanel(){
        JPanel panelMessage = new JPanel(new FlowLayout(FlowLayout.LEFT));
        txtInputMessage = new JTextField("", 50);
        panelMessage.add(txtInputMessage);
        txtInputMessage.setEnabled(false);
        JButton btnSend = new JButton("Send");
        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String messageText = txtInputMessage.getText();
                System.out.println("btnSend clicked "+ txtInputMessage.getText());
                if (messageText.length() < 1){
                    return;
                }
                if (!chatClient.isAuthenticated()){
                    return;
                }
                chatClient.sendMessage(messageText);
                txtInputMessage.setText("");
                chatClient.addActionListenerMessagesChanged(e1 -> {
                    refreshMessage();
                });
            }
        });
        panelMessage.add(btnSend);
        return panelMessage;
    }
    private void refreshMessage(){
        if (!chatClient.isAuthenticated()){
            return;
        }
        txtChat.setText("");
        for (Message msg : chatClient.getMessages()){
            txtChat.append(msg.toString());
        }
    }
}
