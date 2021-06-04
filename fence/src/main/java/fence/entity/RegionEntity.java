package fence.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import java.io.Serializable;

@ApiModel
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class RegionEntity extends CommonEntity implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
	 *  id
	 */
	@ApiModelProperty(value = "id", name = "id")
    private Integer id;
    /**
	 *  name
	 */
	@ApiModelProperty(value = "name", name = "name")
    private String name;
    /**
	 *  type
	 */
	@ApiModelProperty(value = "type", name = "type")
    private Integer type;
    /**
	 *  color
	 */
	@ApiModelProperty(value = "color", name = "color")
    private String color;
    /**
	 *  status
	 */
	@ApiModelProperty(value = "status", name = "status")
    private Integer status;


}
