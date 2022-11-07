package Models.ChatClients;

import com.google.gson.annotations.Expose;

import java.time.LocalDateTime;

public class Message {

    @Expose(serialize = true, deserialize = true)
    private String author;
    @Expose(serialize = true, deserialize = true)
    private String text;
    @Expose(serialize = true, deserialize = true)
    private LocalDateTime time;

    public static final int USER_LOGGED_IN = 1;
    public static final int USER_LOGGED_OUT = 2;

    private static final String AUTHOR_SYSTEM = "System";
    public Message(String author, String text){
        this.author = author;
        this.text = text;
        time = LocalDateTime.now();

    }
    public Message(int type, String userName){
        this.author = AUTHOR_SYSTEM;
        time = LocalDateTime.now();
        if (type == USER_LOGGED_IN) {
            text = userName + " has joined the chat\n";
        } else if (type == USER_LOGGED_OUT){
            text = userName + " has logged out\n";
        }

    }
    public String getAuthor() {
        return author;
    }

    public String getText() {
        return text;
    }

    public LocalDateTime getTime(){
        return time;
    }

    @Override
    public String toString() {
        if (author.equals(AUTHOR_SYSTEM)){
            return text;
        }
        String tempText = author + " [" + time+"]\n";
        tempText += text+"\n";
        return tempText;
    }
}
