package fence.service.impl;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fence.dao.ITaskDao;
import fence.service.ITaskService;
import fence.entity.PageData;
import fence.utils.ExcelUtil;
import fence.utils.PageUtil;
import java.util.LinkedHashMap;
import fence.entity.TaskEntity;
import java.util.List;
import java.util.Map;

@Service
public class TaskService implements ITaskService {

	
	private ITaskDao dao;

	@Autowired
	public TaskService(ITaskDao dao) {
		this.dao = dao;
	}
	
	@Override
	public void add(TaskEntity entity) {
		dao.add(entity);
	}
	
	@Override
	public void delete(TaskEntity entity) {
		dao.delete(entity);
	}
	
	@Override
	public void update(TaskEntity entity) {
		dao.update(entity);
	}
	
	@Override
	public List<TaskEntity> select(TaskEntity entity) {
		return dao.select(entity);
	}
	
	@Override
	public PageData<TaskEntity> likeSelect(TaskEntity entity) {
	
		return PageUtil.getPageData(entity, dao);
	}

	@Override
	public void batchAdd(List<TaskEntity> list) {
		dao.batchAdd(list);
	}

	@Override
	public void batchDelete(List<TaskEntity> list) {
		dao.batchDelete(list);
	}

	@Override
	public void batchUpdate(List<TaskEntity> list) {
		dao.batchUpdate(list);
	}
	
	
	@Override
	public void exportExcel(TaskEntity entity, HttpServletResponse response) {

		// 获取头部信息（可以设置为动态）
		String[] headList = new String[] { "id", "名称", "用户", "路径", "创建时间", "开始时间", "终止时间", "完成时间", "状态", "起点经度", "起点纬度", "终点经度", "终点纬度"};
		
		String[] headEngList = new String[]{ "id", "name", "uid", "rid", "creatime", "startime", "endtime", "finishtime", "status", "startlng", "startlat", "endlng", "endlat"};

		String[] describeList = new String[] { "", "", "", "", "", "", "", "", "", "", "", "", ""};
		
		//设置头部以及描述信息
        Map<String, String> headAndDescribeMap = new LinkedHashMap<>();
        for (int i = 0; i < headEngList.length; i++) {
            headAndDescribeMap.put(headEngList[i], describeList[i]);
        }

		ExcelUtil.exportExcel(entity, response, dao, headList, headAndDescribeMap);
	}
}
