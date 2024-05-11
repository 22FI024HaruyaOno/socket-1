import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.BindException;
import java.net.Socket;
import java.util.InputMismatchException;
import java.util.Scanner;

public class JankenTCPClient {
    public static void main(String arg[]) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("ポートを入力してください(5000など) → ");
            int port = scanner.nextInt();
            System.out.println("localhostの" + port + "番ポートに接続を要求します");
            System.out.print("繰り返しの設定を決めてください(毎回繰り返す:1 or あいこだけ繰り返す:2) → ");
            int option = scanner.nextInt();
            if(option == 1 || option == 2) {
                boolean Continue = true;
            while (Continue) {
                Socket socket = new Socket("localhost", port);
                System.out.println("接続されました");

                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                
                Continue = false;
                System.out.println("じゃんけんを開始します");
                System.out.println("自分の手を決めてください(例:グー → 1, チョキ → 2, パー → 3, 終了→それ以外) ↓");
                int hand = scanner.nextInt();
                if(hand != 1 && hand != 2 && hand != 3) {
                    System.out.println("終了します");
                    System.exit(0);
                }

                Janken You = new Janken();
                You.setHand(hand);
                oos.writeObject(You);
                oos.flush();
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Janken NPC = (Janken) ois.readObject();
                String result = NPC.getmessage();

                System.out.println("\n最初はグー! じゃんけん ポン！");
                System.out.print("君:");
                if(option == 1) {
                    switch (You.getHand()) {
                        case 1:
                            System.out.println("グー");
                            System.out.print("僕:");
    
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
                            Continue = true;
                            break;
                        case 2:
                            System.out.println("チョキ");
                            System.out.print("僕:");
    
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
                            Continue = true;
                            break;
                        case 3:
                            System.out.println("パー");
                            System.out.print("僕:");
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
                            Continue = true;
                            break;
                        default:
                            break;
                    }

                }
                else {
                    switch (You.getHand()) {
                        case 1:
                            System.out.println("グー");
                            System.out.print("僕:");
    
                            switch (NPC.getHand()) {
                                case 1:
                                    System.out.println("グー");
                                    Continue = true;
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
                            System.out.print("僕:");
    
                            switch (NPC.getHand()) {
                                case 1:
                                    System.out.println("グー");
                                    break;
                                case 2:
                                    System.out.println("チョキ");
                                    Continue = true;
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
                            System.out.print("僕:");
                            switch (NPC.getHand()) {
                                case 1:
                                    System.out.println("グー");
                                    break;
                                case 2:
                                    System.out.println("チョキ");
                                    break;
                                case 3:
                                    System.out.println("パー");
                                    Continue = true;
                                    break;
                                default:
                                    break;
                            }
                            break;
                        default:
                            break;
                    }
                }
                
                System.out.println(result + "\n");

                ois.close();
                oos.close();
                socket.close();
            }
            scanner.close();

            }
            else {
                System.out.println("1か2でお願いします");
                System.exit(0);
            }
            
        }
         // エラーが発生したらエラーメッセージを表示してプログラムを終了する
        catch (BindException be) {
            be.printStackTrace();
            System.err.println("ポート番号が不正か、サーバが起動していません");
            System.err.println("サーバが起動しているか確認してください");
            System.err.println("別のポート番号を指定してください(6000など)");
        }
        catch (InputMismatchException i) {
            System.err.println("入力は数値でお願いします");
        }
        catch (Exception e) {
            System.err.println("エラーが発生したのでプログラムを終了します");
            throw new RuntimeException(e);
        }
    }
}
