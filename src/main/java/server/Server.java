package server;

public interface Server {


    /**
     * 开启服务端
     * @param port
     */
    void  start(int port);

    /**
     * 停止服务端
     */
    void  stop();
}
