package fence.service;

import javax.servlet.http.HttpServletResponse;

import fence.entity.AdminEntity;
import fence.entity.PageData;
import java.util.List;

public interface IAdminService {

	void add(AdminEntity entity);
	
	void delete(AdminEntity entity);
	
	void update(AdminEntity entity);
	
	List<AdminEntity> select(AdminEntity entity);
	
	PageData<AdminEntity> likeSelect(AdminEntity entity);

	void batchAdd(List<AdminEntity> list);
	
	void batchDelete(List<AdminEntity> list);
	
	void batchUpdate(List<AdminEntity> list);

    void exportExcel(AdminEntity entity, HttpServletResponse response);

}
