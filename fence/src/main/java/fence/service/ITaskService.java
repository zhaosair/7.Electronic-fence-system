package fence.service;

import javax.servlet.http.HttpServletResponse;

import fence.entity.TaskEntity;
import fence.entity.PageData;
import java.util.List;

public interface ITaskService {

	void add(TaskEntity entity);
	
	void delete(TaskEntity entity);
	
	void update(TaskEntity entity);
	
	List<TaskEntity> select(TaskEntity entity);
	
	PageData<TaskEntity> likeSelect(TaskEntity entity);

	void batchAdd(List<TaskEntity> list);
	
	void batchDelete(List<TaskEntity> list);
	
	void batchUpdate(List<TaskEntity> list);

    void exportExcel(TaskEntity entity, HttpServletResponse response);

}
