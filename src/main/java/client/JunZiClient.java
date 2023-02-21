package client;

import com.alibaba.fastjson.JSON;
import params.BaseMessage;
import util.IpUtil;

/**
 * 客户端，提供给用户使用
 * wangzhangzhen
 */
public class JunZiClient {




    public void send(String address,BaseMessage baseMessage){

        Object[] array = IpUtil.parseIpPort(address);
        String host = (String) array[0];
        Integer port = (Integer) array[1];
        JunZiConnect junZiConnect =new JunZiConnect();
        junZiConnect.init(host,port);
        junZiConnect.getChannel().writeAndFlush(JSON.toJSON(baseMessage));

    }
}
