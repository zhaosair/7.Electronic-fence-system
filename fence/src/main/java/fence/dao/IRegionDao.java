package fence.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import fence.entity.RegionEntity;

@Mapper
@Repository
public interface IRegionDao extends IBaseDao<RegionEntity> {

}
