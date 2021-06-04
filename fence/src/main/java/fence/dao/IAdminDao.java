package fence.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import fence.entity.AdminEntity;

@Mapper
@Repository
public interface IAdminDao extends IBaseDao<AdminEntity> {

}
