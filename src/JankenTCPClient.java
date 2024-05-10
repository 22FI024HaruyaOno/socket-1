import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.BindException;
import java.net.Socket;
import java.util.Scanner;

public class JankenTCPClient {
    public static void main(String arg[]) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("ポートを入力してください(5000など) → ");
            int port = scanner.nextInt();
            System.out.println("localhostの" + port + "番ポートに接続を要求します");
            Socket socket = new Socket("localhost", port);
            System.out.println("接続されました");

            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

            boolean Continue = true;
            while(Continue) {
                System.out.println("じゃんけんを開始します");
                System.out.println("自分の手を決めてください(例:グー → 1, チョキ → 2, パー → 3, 終了→4) ↓");
                int hand = scanner.nextInt();

                Janken You = new Janken();
                You.setHand(hand);

                oos.writeObject(You);
                oos.flush();

                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

                Janken NPC = (Janken) ois.readObject();

                String result = NPC.getmessage();
                System.out.println("最初はグー! じゃんけん ポン！");
                System.out.print("あなた:");
                switch (You.getHand()) {
                    case 1:
                        System.out.println("グー");
                        System.out.print("NPC:");
                        switch (NPC.getHand()) {
                            case 1:
                                System.out.println("グー");
                                break;
                            case 2:
                                System.out.println("チョキ");
                                break;
                            case 3:
                                System.out.println("パー");
                                break;
                            default:
                                break;
                        }
                        break;
                    case 2:
                        System.out.println("チョキ");
                        System.out.print("NPC:");
                        switch (NPC.getHand()) {
                            case 1:
                                System.out.println("グー");
                                break;
                            case 2:
                                System.out.println("チョキ");
                                break;
                        case 3:
                                System.out.println("パー");
                                break;
                            default:
                                break;
                        }
                        break;
                    case 3:
                        System.out.println("パー");
                        System.out.print("NPC:");
                        switch (NPC.getHand()) {
                            case 1:
                                System.out.println("グー");
                                break;
                            case 2:
                                System.out.println("チョキ");
                                break;
                            case 3:
                                System.out.println("パー");
                                break;
                            default:
                                break;
                        }
                        break;
                    default:
                        Continue = false;
                        break;
                }
                System.out.println(result);
                ois.close();
                oos.close();
            }
            scanner.close();
            socket.close();

        } // エラーが発生したらエラーメッセージを表示してプログラムを終了する
        catch (BindException be) {
            be.printStackTrace();
            System.err.println("ポート番号が不正か、サーバが起動していません");
            System.err.println("サーバが起動しているか確認してください");
            System.err.println("別のポート番号を指定してください(6000など)");
        } catch (Exception e) {
            System.err.println("エラーが発生したのでプログラムを終了します");
            throw new RuntimeException(e);
        }
    }
}
