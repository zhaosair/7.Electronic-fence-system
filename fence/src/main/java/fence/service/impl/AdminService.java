package fence.service.impl;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fence.dao.IAdminDao;
import fence.service.IAdminService;
import fence.entity.PageData;
import fence.utils.ExcelUtil;
import fence.utils.PageUtil;
import java.util.LinkedHashMap;
import fence.entity.AdminEntity;
import java.util.List;
import java.util.Map;

@Service
public class AdminService implements IAdminService {

	
	private IAdminDao dao;

	@Autowired
	public AdminService(IAdminDao dao) {
		this.dao = dao;
	}
	
	@Override
	public void add(AdminEntity entity) {
		dao.add(entity);
	}
	
	@Override
	public void delete(AdminEntity entity) {
		dao.delete(entity);
	}
	
	@Override
	public void update(AdminEntity entity) {
		dao.update(entity);
	}
	
	@Override
	public List<AdminEntity> select(AdminEntity entity) {
		return dao.select(entity);
	}
	
	@Override
	public PageData<AdminEntity> likeSelect(AdminEntity entity) {
	
		return PageUtil.getPageData(entity, dao);
	}

	@Override
	public void batchAdd(List<AdminEntity> list) {
		dao.batchAdd(list);
	}

	@Override
	public void batchDelete(List<AdminEntity> list) {
		dao.batchDelete(list);
	}

	@Override
	public void batchUpdate(List<AdminEntity> list) {
		dao.batchUpdate(list);
	}
	
	
	@Override
	public void exportExcel(AdminEntity entity, HttpServletResponse response) {

		// 获取头部信息（可以设置为动态）
		String[] headList = new String[] { "编号", "账号", "密码", "角色"};
		
		String[] headEngList = new String[]{ "id", "login", "passwd", "role"};

		String[] describeList = new String[] { "", "", "", ""};
		
		//设置头部以及描述信息
        Map<String, String> headAndDescribeMap = new LinkedHashMap<>();
        for (int i = 0; i < headEngList.length; i++) {
            headAndDescribeMap.put(headEngList[i], describeList[i]);
        }

		ExcelUtil.exportExcel(entity, response, dao, headList, headAndDescribeMap);
	}
}
