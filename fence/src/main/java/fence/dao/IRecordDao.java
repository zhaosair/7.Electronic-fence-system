package fence.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import fence.entity.RecordEntity;

@Mapper
@Repository
public interface IRecordDao extends IBaseDao<RecordEntity> {

}
