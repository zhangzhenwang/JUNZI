package client;


public class JunziClientTest {

     public  static void main(String[] args) throws InterruptedException {

        JunZiConnect junZiConnect =new JunZiConnect();
        junZiConnect.init("127.0.0.1",9999);
        junZiConnect.getChannel().writeAndFlush("123456789").sync();

     }
}
