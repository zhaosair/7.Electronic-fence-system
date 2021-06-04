package fence.service.impl;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fence.dao.IArchorDao;
import fence.service.IArchorService;
import fence.entity.PageData;
import fence.utils.ExcelUtil;
import fence.utils.PageUtil;
import java.util.LinkedHashMap;
import fence.entity.ArchorEntity;
import java.util.List;
import java.util.Map;

@Service
public class ArchorService implements IArchorService {

	
	private IArchorDao dao;

	@Autowired
	public ArchorService(IArchorDao dao) {
		this.dao = dao;
	}
	
	@Override
	public void add(ArchorEntity entity) {
		dao.add(entity);
	}
	
	@Override
	public void delete(ArchorEntity entity) {
		dao.delete(entity);
	}
	
	@Override
	public void update(ArchorEntity entity) {
		dao.update(entity);
	}
	
	@Override
	public List<ArchorEntity> select(ArchorEntity entity) {
		return dao.select(entity);
	}
	
	@Override
	public PageData<ArchorEntity> likeSelect(ArchorEntity entity) {
	
		return PageUtil.getPageData(entity, dao);
	}

	@Override
	public void batchAdd(List<ArchorEntity> list) {
		dao.batchAdd(list);
	}

	@Override
	public void batchDelete(List<ArchorEntity> list) {
		dao.batchDelete(list);
	}

	@Override
	public void batchUpdate(List<ArchorEntity> list) {
		dao.batchUpdate(list);
	}
	
	
	@Override
	public void exportExcel(ArchorEntity entity, HttpServletResponse response) {

		// 获取头部信息（可以设置为动态）
		String[] headList = new String[] { "编号", "名称", "经度", "Y坐标纬度", "序列", "所属"};
		
		String[] headEngList = new String[]{ "id", "name", "lng", "lat", "order_sec", "rid"};

		String[] describeList = new String[] { "", "", "", "", "", ""};
		
		//设置头部以及描述信息
        Map<String, String> headAndDescribeMap = new LinkedHashMap<>();
        for (int i = 0; i < headEngList.length; i++) {
            headAndDescribeMap.put(headEngList[i], describeList[i]);
        }

		ExcelUtil.exportExcel(entity, response, dao, headList, headAndDescribeMap);
	}
}
