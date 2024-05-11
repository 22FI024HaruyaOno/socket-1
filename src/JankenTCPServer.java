import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

public class JankenTCPServer {
    public static void main(String arg[]) {
        try {
            /* 通信の準備をする */
            Scanner scanner = new Scanner(System.in);
            System.out.print("ポートを入力してください(5000など) → ");
            int port = scanner.nextInt();
            scanner.close();
            System.out.println("localhostの" + port + "番ポートで待機します");
            ServerSocket server = new ServerSocket(port); // ポート番号を指定し、クライアントとの接続の準備を行う
            boolean Continue = true;
            while(Continue) {
                Continue = false;
                Socket socket = server.accept(); // クライアントからの接続要求を待ち、
                // 要求があればソケットを取得し接続を行う
                System.out.println("接続しました。相手の入力を待っています......");

                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

                Janken player = (Janken) ois.readObject();// Integerクラスでキャスト。

                Janken npc = new Janken();
                Random random = new Random();

                npc.setHand(random.nextInt(1,4));
                switch (player.getHand()) {
                    case 1:
                        switch (npc.getHand()) {
                            case 1:
                                npc.setMessage("おっと!\n " + "あいこだね");
                                Continue = true;
                                break;
                            case 2:
                                npc.setMessage("おめでとう!\n" + "君の勝ちだ!");
                                break;
                            case 3:
                                npc.setMessage("やった!\n" + "僕の勝ちだ!");
                                break;
                            default:
                                break;
                        }
                        Continue = true;
                        break;
                    case 2:
                        switch (npc.getHand()) {
                            case 1:
                                npc.setMessage("やった!\n" + "僕の勝ちだ!");
                                break;
                            case 2:
                                npc.setMessage("おっと!\n " + "あいこだね");
                                Continue = true;
                                break;
                            case 3:
                                npc.setMessage("おめでとう!\n" + "君の勝ちだ!");
                                break;
                            default:
                                break;
                        }
                        Continue = true;
                        break;
                    case 3:
                        switch (npc.getHand()) {
                            case 1:
                                npc.setMessage("おめでとう!\n" + "君の勝ちだ!");
                                break;
                            case 2:
                                npc.setMessage("やった!\n" + "僕の勝ちだ!");
                                break;
                            case 3:
                                npc.setMessage("おっと!\n " + "あいこだね");
                                Continue = true;
                                break;
                            default:
                                break;
                        }
                        Continue = true;
                        break;
                    default:
                        npc.setMessage("終了します");
                        break;
                }

                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

                oos.writeObject(npc);
                oos.flush();

                // close処理

                ois.close();
                oos.close();
                // socketの終了。
                socket.close();
            }
            server.close();

        } // エラーが発生したらエラーメッセージを表示してプログラムを終了する
        catch (BindException be) {
            be.printStackTrace();
            System.out.println("ポート番号が不正、ポートが使用中です");
            System.err.println("別のポート番号を指定してください(6000など)");
        } catch (Exception e) {
            System.err.println("エラーが発生したのでプログラムを終了します");
            throw new RuntimeException(e);
        }
    }
}
