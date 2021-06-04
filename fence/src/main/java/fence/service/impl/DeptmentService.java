package fence.service.impl;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fence.dao.IDeptmentDao;
import fence.service.IDeptmentService;
import fence.entity.PageData;
import fence.utils.ExcelUtil;
import fence.utils.PageUtil;
import java.util.LinkedHashMap;
import fence.entity.DeptmentEntity;
import java.util.List;
import java.util.Map;

@Service
public class DeptmentService implements IDeptmentService {

	
	private IDeptmentDao dao;

	@Autowired
	public DeptmentService(IDeptmentDao dao) {
		this.dao = dao;
	}
	
	@Override
	public void add(DeptmentEntity entity) {
		dao.add(entity);
	}
	
	@Override
	public void delete(DeptmentEntity entity) {
		dao.delete(entity);
	}
	
	@Override
	public void update(DeptmentEntity entity) {
		dao.update(entity);
	}
	
	@Override
	public List<DeptmentEntity> select(DeptmentEntity entity) {
		return dao.select(entity);
	}
	
	@Override
	public PageData<DeptmentEntity> likeSelect(DeptmentEntity entity) {
	
		return PageUtil.getPageData(entity, dao);
	}

	@Override
	public void batchAdd(List<DeptmentEntity> list) {
		dao.batchAdd(list);
	}

	@Override
	public void batchDelete(List<DeptmentEntity> list) {
		dao.batchDelete(list);
	}

	@Override
	public void batchUpdate(List<DeptmentEntity> list) {
		dao.batchUpdate(list);
	}
	
	
	@Override
	public void exportExcel(DeptmentEntity entity, HttpServletResponse response) {

		// 获取头部信息（可以设置为动态）
		String[] headList = new String[] { "编号", "名称", "绑定区域", "状态"};
		
		String[] headEngList = new String[]{ "id", "name", "regoin", "status"};

		String[] describeList = new String[] { "", "", "", ""};
		
		//设置头部以及描述信息
        Map<String, String> headAndDescribeMap = new LinkedHashMap<>();
        for (int i = 0; i < headEngList.length; i++) {
            headAndDescribeMap.put(headEngList[i], describeList[i]);
        }

		ExcelUtil.exportExcel(entity, response, dao, headList, headAndDescribeMap);
	}
}
