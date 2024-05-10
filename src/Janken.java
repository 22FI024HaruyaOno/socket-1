import java.io.Serializable;
public class Janken implements Serializable{
    int hand;
    String message;

    public int getHand() {
        return hand;
    }

    public void setHand(int hand) {
        this.hand = hand;
    }

    public String getmessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
