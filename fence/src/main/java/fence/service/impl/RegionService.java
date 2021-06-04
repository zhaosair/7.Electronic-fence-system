package fence.service.impl;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fence.dao.IRegionDao;
import fence.service.IRegionService;
import fence.entity.PageData;
import fence.utils.ExcelUtil;
import fence.utils.PageUtil;
import java.util.LinkedHashMap;
import fence.entity.RegionEntity;
import java.util.List;
import java.util.Map;

@Service
public class RegionService implements IRegionService {

	
	private IRegionDao dao;

	@Autowired
	public RegionService(IRegionDao dao) {
		this.dao = dao;
	}
	
	@Override
	public void add(RegionEntity entity) {
		dao.add(entity);
	}
	
	@Override
	public void delete(RegionEntity entity) {
		dao.delete(entity);
	}
	
	@Override
	public void update(RegionEntity entity) {
		dao.update(entity);
	}
	
	@Override
	public List<RegionEntity> select(RegionEntity entity) {
		return dao.select(entity);
	}
	
	@Override
	public PageData<RegionEntity> likeSelect(RegionEntity entity) {
	
		return PageUtil.getPageData(entity, dao);
	}

	@Override
	public void batchAdd(List<RegionEntity> list) {
		dao.batchAdd(list);
	}

	@Override
	public void batchDelete(List<RegionEntity> list) {
		dao.batchDelete(list);
	}

	@Override
	public void batchUpdate(List<RegionEntity> list) {
		dao.batchUpdate(list);
	}
	
	
	@Override
	public void exportExcel(RegionEntity entity, HttpServletResponse response) {

		// 获取头部信息（可以设置为动态）
		String[] headList = new String[] { "id", "名称", "类型", "颜色代码", "状态"};
		
		String[] headEngList = new String[]{ "id", "name", "type", "color", "status"};

		String[] describeList = new String[] { "", "", "", "", ""};
		
		//设置头部以及描述信息
        Map<String, String> headAndDescribeMap = new LinkedHashMap<>();
        for (int i = 0; i < headEngList.length; i++) {
            headAndDescribeMap.put(headEngList[i], describeList[i]);
        }

		ExcelUtil.exportExcel(entity, response, dao, headList, headAndDescribeMap);
	}
}
