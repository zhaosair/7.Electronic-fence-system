package fence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 分页公共类
 *
 * @param <E>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageData<E> implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;


    /**
     * 当前页
     */
    private Integer currentPage;

    private Integer current;

    private boolean success;

    /**
     * 总个数
     */
    private Integer count;

    private Integer total;

    /**
     * 总页数
     */
    private Integer totalPage;

    /**
     * 查询结果
     */
    private List<E> result;
}

