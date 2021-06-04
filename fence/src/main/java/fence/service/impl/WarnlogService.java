package fence.service.impl;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fence.dao.IWarnlogDao;
import fence.service.IWarnlogService;
import fence.entity.PageData;
import fence.utils.ExcelUtil;
import fence.utils.PageUtil;
import java.util.LinkedHashMap;
import fence.entity.WarnlogEntity;
import java.util.List;
import java.util.Map;

@Service
public class WarnlogService implements IWarnlogService {

	
	private IWarnlogDao dao;

	@Autowired
	public WarnlogService(IWarnlogDao dao) {
		this.dao = dao;
	}
	
	@Override
	public void add(WarnlogEntity entity) {
		dao.add(entity);
	}
	
	@Override
	public void delete(WarnlogEntity entity) {
		dao.delete(entity);
	}
	
	@Override
	public void update(WarnlogEntity entity) {
		dao.update(entity);
	}
	
	@Override
	public List<WarnlogEntity> select(WarnlogEntity entity) {
		return dao.select(entity);
	}
	
	@Override
	public PageData<WarnlogEntity> likeSelect(WarnlogEntity entity) {
	
		return PageUtil.getPageData(entity, dao);
	}

	@Override
	public void batchAdd(List<WarnlogEntity> list) {
		dao.batchAdd(list);
	}

	@Override
	public void batchDelete(List<WarnlogEntity> list) {
		dao.batchDelete(list);
	}

	@Override
	public void batchUpdate(List<WarnlogEntity> list) {
		dao.batchUpdate(list);
	}
	
	
	@Override
	public void exportExcel(WarnlogEntity entity, HttpServletResponse response) {

		// 获取头部信息（可以设置为动态）
		String[] headList = new String[] { "id", "任务", "用户", "时间", "经度", "纬度"};
		
		String[] headEngList = new String[]{ "id", "tid", "uid", "errortime", "lng", "lat"};

		String[] describeList = new String[] { "", "", "", "", "", ""};
		
		//设置头部以及描述信息
        Map<String, String> headAndDescribeMap = new LinkedHashMap<>();
        for (int i = 0; i < headEngList.length; i++) {
            headAndDescribeMap.put(headEngList[i], describeList[i]);
        }

		ExcelUtil.exportExcel(entity, response, dao, headList, headAndDescribeMap);
	}
}
