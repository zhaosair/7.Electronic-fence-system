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
public class AdminEntity extends CommonEntity implements Serializable {

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
	 *  login
	 */
	@ApiModelProperty(value = "login", name = "login")
    private String login;
    /**
	 *  passwd
	 */
	@ApiModelProperty(value = "passwd", name = "passwd")
    private String passwd;
    /**
	 *  role
	 */
	@ApiModelProperty(value = "role", name = "role")
    private String role;


}
