package Models.ChatClients;

import java.time.LocalDateTime;

public class Message {
    private String author;
    private String text;
    private LocalDateTime time;
    public Message(String author, String text){
        this.author = author;
        this.text = text;
        time = LocalDateTime.now();

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
}
