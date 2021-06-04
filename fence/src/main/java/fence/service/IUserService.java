package fence.service;

import javax.servlet.http.HttpServletResponse;

import fence.entity.UserEntity;
import fence.entity.PageData;

import java.util.List;

public interface IUserService {

	void add(UserEntity entity);
	
	void delete(UserEntity entity);
	
	void update(UserEntity entity);
	
	List<UserEntity> select(UserEntity entity);
	
	PageData<UserEntity> likeSelect(UserEntity entity);

	void batchAdd(List<UserEntity> list);
	
	void batchDelete(List<UserEntity> list);
	
	void batchUpdate(List<UserEntity> list);

    void exportExcel(UserEntity entity, HttpServletResponse response);

}
