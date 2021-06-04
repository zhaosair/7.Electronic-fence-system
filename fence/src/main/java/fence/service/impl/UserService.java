package fence.service.impl;

import javax.servlet.http.HttpServletResponse;

import fence.utils.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fence.dao.IUserDao;
import fence.service.IUserService;
import fence.entity.PageData;
import fence.utils.ExcelUtil;
import fence.utils.PageUtil;
import java.util.LinkedHashMap;
import fence.entity.UserEntity;
import java.util.List;
import java.util.Map;

@Service
public class UserService implements IUserService {

	@Autowired
	private SpringUtil springUtil;

	private IUserDao dao;

	@Autowired
	public UserService(IUserDao dao) {
		this.dao = dao;
	}
	
	@Override
	public void add(UserEntity entity) {
		dao.add(entity);
	}
	
	@Override
	public void delete(UserEntity entity) {
		dao.delete(entity);
	}
	
	@Override
	public void update(UserEntity entity) {
		dao.update(entity);
	}
	
	@Override
	public List<UserEntity> select(UserEntity entity) {
		return dao.select(entity);
	}
	
	@Override
	public PageData<UserEntity> likeSelect(UserEntity entity) {
	
		return PageUtil.getPageData(entity, dao);
	}

	@Override
	public void batchAdd(List<UserEntity> list) {
		dao.batchAdd(list);
	}

	@Override
	public void batchDelete(List<UserEntity> list) {
		dao.batchDelete(list);
	}

	@Override
	public void batchUpdate(List<UserEntity> list) {
		dao.batchUpdate(list);
	}
	
	
	@Override
	public void exportExcel(UserEntity entity, HttpServletResponse response) {

		// 获取头部信息（可以设置为动态）
		String[] headList = new String[] { "编号", "账号", "姓名", "密码", "性别", "电话", "生日", "上次登录", "离线时间", "状态", "部门", "X", "y"};
		
		String[] headEngList = new String[]{ "id", "login", "name", "passwd", "sex", "phone", "birthday", "logintime", "logoutime", "status", "dpid", "lng", "lat"};

		String[] describeList = new String[] { "", "", "", "", "男&0#女&1", "", "", "", "", "", "", "", ""};
		
		//设置头部以及描述信息
        Map<String, String> headAndDescribeMap = new LinkedHashMap<>();
        for (int i = 0; i < headEngList.length; i++) {
            headAndDescribeMap.put(headEngList[i], describeList[i]);
        }

		ExcelUtil.exportExcel(entity, response, dao, headList, headAndDescribeMap);
	}
}
