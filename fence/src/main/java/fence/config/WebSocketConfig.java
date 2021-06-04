package fence.config;


import org.springframework.context.annotation.Bean;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

/**
 * 开启WebSocket支持
 */


@Component
public class WebSocketConfig extends ServerEndpointConfig.Configurator{

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

    /* 修改握手,就是在握手协议建立之前修改其中携带的内容 */
/*    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
        HttpSession httpSession = (HttpSession)request.getHttpSession();
        if (httpSession != null) {
            // 读取session域中存储的数据
            sec.getUserProperties().put(HttpSession.class.getName(),httpSession);
        }
        super.modifyHandshake(sec, request, response);
    }*/



}


