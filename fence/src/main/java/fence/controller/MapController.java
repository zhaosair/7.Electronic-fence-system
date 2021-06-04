package fence.controller;

import java.io.IOException;
import java.util.List;

import fence.core.annotation.LoginRequired;
import fence.core.annotation.RecordLog;
import fence.entity.*;
import fence.service.IRecordService;
import fence.service.ITaskService;
import fence.service.IWarnlogService;
import fence.socket.WebSocketServer;
import io.swagger.annotations.ApiOperation;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * WebSocket服务器端推送消息示例Controller
 * 实现用户定位、轨迹、告警日志查询
 * 告警记录由前台接口insert
 *
 * @author ygc
 *
 */
@RestController
@RequestMapping("/socket")
public class MapController {
    //	@Autowired
    //	private RedisOperator redisOperator;

    @Autowired
    ITaskService taskService;
    @Autowired
    IRecordService recordService;
    IWarnlogService warnlogService;
    /**
     * 监控大厅
     * @param
     * @return
     */
    @RequestMapping(value="/mapCenter", method=RequestMethod.GET)
    public String onlinceUser(){
        try {
            WebSocketServer.BroadCastInfo("1message");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "ok";
    }
    /**
     * 查询用户当前任务
     * @param
     * @return
     */
    @RequestMapping(value="/myTask")
    @ResponseBody
    @PostMapping
    public List<TaskEntity> getUserTask(int uid){
        TaskEntity task= new TaskEntity();
        task.setUid(uid);
        return taskService.select(task);
    }
    /**
     * 查询用户海量轨迹点 生成路线图or 标注点图
     * @param
     * @return
     */
    @RequestMapping(value="/myRecords")
    @ResponseBody
    @PostMapping
    public List<RecordEntity> getUserRecords(RecordEntity re){

        return recordService.select(re);
    }
    /**
     * 查询用户告警日志
     * @param
     * @return
     */
    @RequestMapping(value="/myWarnlog")
    @ResponseBody
    @PostMapping
    public PageData<WarnlogEntity> getUserWarnlog(WarnlogEntity we){

        return warnlogService.likeSelect(we);
    }
    /**
     * 获取定位
     *
     * @return
     */
    @ApiOperation(value = "获取定位")
    @LoginRequired
    @RecordLog
    @PostMapping(value = "/getUserPoint")
    public void getPoint(@RequestBody UserEntity entity) {
        System.out.println(entity.getId());
        WebSocketServer.AppointSending("u"+entity.getId(),"通知浏览器开始定位");
    }
    /**
     * 群发消息内容 可实现socket通知所有在线用户发送实时定位
     * @param message
     * @return
     */
    @RequestMapping(value="/sendAll", method=RequestMethod.GET)
    String sendAllMessage(@RequestParam(required=true) String message){
        try {
            WebSocketServer.BroadCastInfo(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "ok";
    }

    /**
     * 指定会话ID发消息 用于获取指定用户id 获取定位
     * @param message 消息内容
     * @param id 连接会话ID
     * @return
     */
    @RequestMapping(value="/sendOne", method=RequestMethod.GET)
    String sendOneMessage(@RequestParam(required=true) String message,@RequestParam(required=true) String id){
        try {
            WebSocketServer.SendMessage(id,message);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "ok";
    }

}
