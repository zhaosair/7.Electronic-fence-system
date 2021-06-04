package fence.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import fence.entity.WarnlogEntity;

@Mapper
@Repository
public interface IWarnlogDao extends IBaseDao<WarnlogEntity> {

}
