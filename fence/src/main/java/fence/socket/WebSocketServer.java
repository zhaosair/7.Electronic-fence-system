package fence.socket;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import fence.entity.UserEntity;
import fence.service.IUserService;
import fence.utils.SpringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * WebSocket服务端
 * @author ygc
 */

@Component
@ServerEndpoint(value = "/websocket/{id}/{reciver}")
public class WebSocketServer {

    private static IUserService userservice = SpringUtil.getBean(IUserService.class);

    private Session session;
    private int id;
    private String name;


    private final static Logger log = LoggerFactory.getLogger(WebSocketServer.class);
    private static final AtomicInteger OnlineCount = new AtomicInteger(0);
    // concurrent包的线程安全Set，用来存放每个客户端对应的Session对象。
    private static CopyOnWriteArraySet<Session> SessionSet = new CopyOnWriteArraySet<>();


    /**
     *  用于存所有的连接服务的客户端，这个对象存储是安全的
     */
    private static ConcurrentHashMap<String, WebSocketServer> webSocketSet = new ConcurrentHashMap<>();
    /**
     *  与某个客户端的连接对话，需要通过它来给客户端发送消息
     */

    /**
     * 连接建立成功调用的方法
     * @throws IOException
     */
    @OnOpen
    public void onOpen(Session session,@PathParam("id") int id) throws IOException {
        this.session = session;
        this.id=id;
        this.name="u"+id;
        SessionSet.add(session);
        webSocketSet.put(name,this);
        int personCount = OnlineCount.incrementAndGet(); // 在线数加1
        System.out.println("有连接加入，当前连接数为："+personCount);
        if(id!=9999)
        {
            UserEntity ue=new UserEntity();
            ue.setId(id);
             ue.setStatus(11);
        userservice.update(ue);}
        System.out.println("有连接加入，当前连接数为："+personCount);

        log.info("有连接加入，当前连接数为：{}", personCount);
//		SendMessage(session, "连接成功,当前连接人数为:"+personCount);
//		SendMessage(session,String.valueOf(personCount));
        BroadCastInfo("在线用户"+String.valueOf(OnlineCount.get()-1));
    }

    /**
     * 连接关闭调用的方法
     * @throws IOException
     */
    @OnClose
    public void onClose() throws IOException {
        int personCount = OnlineCount.decrementAndGet();

        UserEntity ue=new UserEntity();
        ue.setStatus(0);
        ue.setId(this.id);
        userservice.update(ue);
        webSocketSet.remove(this.name);
        System.out.println("有连接关闭，当前连接数为："+personCount);
        log.info("有连接关闭，当前连接数为：{}", personCount);

        SessionSet.remove(this);

    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message
     *            客户端发送过来的消息
     * @throws IOException
     */
    @OnMessage
    public void onMessage(String message, Session session,@PathParam("reciver") int reciver) throws IOException {
        log.info("来自客户端的消息：{}",message);

		System.out.println("来自客户端的消息:"+message);
		AppointSending("u"+reciver, "用户坐标："+message);
        if(message.equals("管理平台")) {
            System.out.println("收到平台类型:"+message);
        }
//		if(message.equals("新增人数")) {
//			System.out.println("打开页面:"+message);
//			BroadCastInfo(String.valueOf(OnlineCount.get()+1));
//		}
        if(message.equals("关闭页面")) {
            System.out.println("收到关闭页面:"+message);
            //在线数加-1
            BroadCastInfo(String.valueOf(OnlineCount.get()-1));
        }
    }

    /**
     * 出现错误
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误：{}，Session ID： {}",error.getMessage(),session.getId());
        System.out.println("发生错误：{}，Session ID： "+error.getMessage()+session.getId());
        error.printStackTrace();
    }

    /**
     * 发送消息，实践表明，每次浏览器刷新，session会发生变化。
     * @param session
     * @param message
     */
    public static void SendMessage(Session session, String message) {
        try {
            session.getBasicRemote().sendText(message);
//			session.getBasicRemote().sendText(String.format("%s (From Server，Session ID=%s)",message,session.getId()));
        } catch (IOException e) {
            log.error("发送消息出错：{}", e.getMessage());
            System.out.println("发送消息出错：{}"+e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 群发消息
     * @param message
     * @throws IOException
     */
    public static void BroadCastInfo(String message) throws IOException {
        for (Session session : SessionSet) {
            if(session.isOpen()){
                SendMessage(session, message);
            }
        }
    }

    /**
     * 指定Session发送消息
     * @param sessionId
     * @param message
     * @throws IOException
     */
    public static void SendMessage(String sessionId,String message) throws IOException {
        Session session = null;
        for (Session s : SessionSet) {
            if(s.getId().equals(sessionId)){
                session = s;
                break;
            }
        }
        if(session!=null){
            SendMessage(session, message);
        }
        else{
            log.warn("没有找到你指定ID的会话：{}",sessionId);
            System.out.println("没有找到你指定ID的会话:"+sessionId);
        }
    }
    /**
     * 指定发送用户
     * @param name
     * @param message
     */
    public static void AppointSending(String name,String message){
        try {
            webSocketSet.get(name).session.getBasicRemote().sendText(message);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
