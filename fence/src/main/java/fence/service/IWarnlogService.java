package fence.service;

import javax.servlet.http.HttpServletResponse;

import fence.entity.WarnlogEntity;
import fence.entity.PageData;
import java.util.List;

public interface IWarnlogService {

	void add(WarnlogEntity entity);
	
	void delete(WarnlogEntity entity);
	
	void update(WarnlogEntity entity);
	
	List<WarnlogEntity> select(WarnlogEntity entity);
	
	PageData<WarnlogEntity> likeSelect(WarnlogEntity entity);

	void batchAdd(List<WarnlogEntity> list);
	
	void batchDelete(List<WarnlogEntity> list);
	
	void batchUpdate(List<WarnlogEntity> list);

    void exportExcel(WarnlogEntity entity, HttpServletResponse response);

}
