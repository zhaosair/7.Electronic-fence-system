package fence.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import fence.entity.DeptmentEntity;

@Mapper
@Repository
public interface IDeptmentDao extends IBaseDao<DeptmentEntity> {

}
