package fence.service.impl;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fence.dao.IRecordDao;
import fence.service.IRecordService;
import fence.entity.PageData;
import fence.utils.ExcelUtil;
import fence.utils.PageUtil;
import java.util.LinkedHashMap;
import fence.entity.RecordEntity;
import java.util.List;
import java.util.Map;

@Service
public class RecordService implements IRecordService {

	
	private IRecordDao dao;

	@Autowired
	public RecordService(IRecordDao dao) {
		this.dao = dao;
	}
	
	@Override
	public void add(RecordEntity entity) {
		dao.add(entity);
	}
	
	@Override
	public void delete(RecordEntity entity) {
		dao.delete(entity);
	}
	
	@Override
	public void update(RecordEntity entity) {
		dao.update(entity);
	}
	
	@Override
	public List<RecordEntity> select(RecordEntity entity) {
		return dao.select(entity);
	}
	
	@Override
	public PageData<RecordEntity> likeSelect(RecordEntity entity) {
	
		return PageUtil.getPageData(entity, dao);
	}

	@Override
	public void batchAdd(List<RecordEntity> list) {
		dao.batchAdd(list);
	}

	@Override
	public void batchDelete(List<RecordEntity> list) {
		dao.batchDelete(list);
	}

	@Override
	public void batchUpdate(List<RecordEntity> list) {
		dao.batchUpdate(list);
	}
	
	
	@Override
	public void exportExcel(RecordEntity entity, HttpServletResponse response) {

		// 获取头部信息（可以设置为动态）
		String[] headList = new String[] { "id", "用户", "经度", "纬度", "时间"};
		
		String[] headEngList = new String[]{ "id", "uid", "lng", "lat", "creatime"};

		String[] describeList = new String[] { "", "", "", "", ""};
		
		//设置头部以及描述信息
        Map<String, String> headAndDescribeMap = new LinkedHashMap<>();
        for (int i = 0; i < headEngList.length; i++) {
            headAndDescribeMap.put(headEngList[i], describeList[i]);
        }

		ExcelUtil.exportExcel(entity, response, dao, headList, headAndDescribeMap);
	}
}
