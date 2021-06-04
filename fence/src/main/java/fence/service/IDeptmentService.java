package fence.service;

import javax.servlet.http.HttpServletResponse;

import fence.entity.DeptmentEntity;
import fence.entity.PageData;

import java.util.List;

public interface IDeptmentService {

	void add(DeptmentEntity entity);
	
	void delete(DeptmentEntity entity);
	
	void update(DeptmentEntity entity);
	
	List<DeptmentEntity> select(DeptmentEntity entity);
	
	PageData<DeptmentEntity> likeSelect(DeptmentEntity entity);

	void batchAdd(List<DeptmentEntity> list);
	
	void batchDelete(List<DeptmentEntity> list);
	
	void batchUpdate(List<DeptmentEntity> list);

    void exportExcel(DeptmentEntity entity, HttpServletResponse response);

}
