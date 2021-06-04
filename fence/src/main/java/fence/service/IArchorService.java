package fence.service;

import javax.servlet.http.HttpServletResponse;

import fence.entity.ArchorEntity;
import fence.entity.PageData;
import java.util.List;

public interface IArchorService {

	void add(ArchorEntity entity);
	
	void delete(ArchorEntity entity);
	
	void update(ArchorEntity entity);
	
	List<ArchorEntity> select(ArchorEntity entity);
	
	PageData<ArchorEntity> likeSelect(ArchorEntity entity);

	void batchAdd(List<ArchorEntity> list);
	
	void batchDelete(List<ArchorEntity> list);
	
	void batchUpdate(List<ArchorEntity> list);

    void exportExcel(ArchorEntity entity, HttpServletResponse response);

}
