package fence.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import fence.entity.ArchorEntity;

@Mapper
@Repository
public interface IArchorDao extends IBaseDao<ArchorEntity> {

}
