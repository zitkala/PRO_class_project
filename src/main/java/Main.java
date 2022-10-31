import Models.ChatClients.ChatClient;
import Models.ChatClients.ChatFileOperation.ChatFileOperations;
import Models.ChatClients.ChatFileOperation.JsonChatFileOperations;
import Models.ChatClients.ChatFileOperation.ToFileChatClient;
import Models.ChatClients.Message;
import Models.ChatClients.inMemoryChatClient;
import Models.Gui.MainFrame;

public class Main {
    public static void main(String[] args) {
        ChatFileOperations chatFileOperations = new JsonChatFileOperations();
        ChatClient client = new ToFileChatClient(chatFileOperations);
        //ChatClient client = new inMemoryChatClient();
        MainFrame window = new MainFrame(400, 400, client);
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