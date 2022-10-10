import Models.ChatClients.ChatClient;
import Models.ChatClients.Message;
import Models.ChatClients.inMemoryChatClient;
import Models.Gui.MainFrame;

public class Main {
    public static void main(String[] args) {
        MainFrame window = new MainFrame(400, 400);

    }
    private static void test(){
        ChatClient client = new inMemoryChatClient();
        client.login("Bruh");
        System.out.println(client.isAuthenticated());
        client.sendMessage("heelo");
        client.sendMessage("hello");
        for (Message msg :
                client.getMessages()) {
            System.out.println(msg.getText());
        }
        client.logout();
        System.out.println(client.isAuthenticated());
    }
}