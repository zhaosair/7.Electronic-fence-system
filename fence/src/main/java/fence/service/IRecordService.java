package fence.service;

import javax.servlet.http.HttpServletResponse;

import fence.entity.RecordEntity;
import fence.entity.PageData;
import java.util.List;

public interface IRecordService {

	void add(RecordEntity entity);
	
	void delete(RecordEntity entity);
	
	void update(RecordEntity entity);
	
	List<RecordEntity> select(RecordEntity entity);
	
	PageData<RecordEntity> likeSelect(RecordEntity entity);

	void batchAdd(List<RecordEntity> list);
	
	void batchDelete(List<RecordEntity> list);
	
	void batchUpdate(List<RecordEntity> list);

    void exportExcel(RecordEntity entity, HttpServletResponse response);

}
