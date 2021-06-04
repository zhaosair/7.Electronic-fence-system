package fence.service;

import javax.servlet.http.HttpServletResponse;

import fence.entity.RegionEntity;
import fence.entity.PageData;
import java.util.List;

public interface IRegionService {

	void add(RegionEntity entity);
	
	void delete(RegionEntity entity);
	
	void update(RegionEntity entity);
	
	List<RegionEntity> select(RegionEntity entity);
	
	PageData<RegionEntity> likeSelect(RegionEntity entity);

	void batchAdd(List<RegionEntity> list);
	
	void batchDelete(List<RegionEntity> list);
	
	void batchUpdate(List<RegionEntity> list);

    void exportExcel(RegionEntity entity, HttpServletResponse response);

}
