package fence.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import fence.entity.User;

@Mapper
@Repository
public interface ILoginDao {

	User login(User user);

}
