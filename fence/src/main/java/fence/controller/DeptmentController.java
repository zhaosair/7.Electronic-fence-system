package fence.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import fence.service.IDeptmentService;
import fence.core.annotation.LoginRequired;
import fence.core.annotation.RecordLog;
import fence.entity.PageData;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import fence.entity.DeptmentEntity;
import java.util.List;

@RestController
@Api(tags = "deptment接口")
@RequestMapping("/deptment")
public class DeptmentController {


	private IDeptmentService service;

	@Autowired
	public DeptmentController(IDeptmentService service) {
		this.service = service;
	}
	
	/**
	 * 查询
	 *
	 * @return
	 */
	@ApiOperation(value = "查询")
	@LoginRequired
	@RecordLog
	@PostMapping(value = "/select")
	public List<DeptmentEntity> select(@RequestBody DeptmentEntity entity) {
		return service.select(entity);
	}

	/**
	 * 模糊查询
	 *
	 * @return
	 */
	@ApiOperation(value = "模糊查询")
	@LoginRequired
	@RecordLog
	@PostMapping(value = "/likeSelect")
	public PageData<DeptmentEntity> likeSelect(@RequestBody DeptmentEntity entity) {
		return service.likeSelect(entity);
	}

	/**
	 * 更新
	 *
	 * @return
	 */
	@ApiOperation(value = "更新")
	@LoginRequired
	@RecordLog
	@PostMapping(value = "/update")
	public void update(@RequestBody DeptmentEntity entity) {
		service.update(entity);
	}

	/**
	 * 添加
	 *
	 * @return
	 */
	@ApiOperation(value = "添加")
    @LoginRequired
	@RecordLog
	@PostMapping(value = "/add")
	public void add(@RequestBody DeptmentEntity entity) {
		service.add(entity);
	}

	/**
	 * 删除
	 *
	 * @return
	 */
	@ApiOperation(value = "删除")
	@LoginRequired
	@RecordLog
	@PostMapping(value = "/delete")
	public void delete(@RequestBody DeptmentEntity entity) {
		service.delete(entity);
	}

	@ApiOperation(value = "删除")
	@LoginRequired
	@RecordLog
	@PostMapping(value = "/remove")
	public void deleteByList(@RequestBody String input) {
		String output = input.substring(input.indexOf("[") + 1 ,input.indexOf("]"));
		String[] idstr = output.split(",");
		DeptmentEntity ue =new DeptmentEntity();
		for (String id : idstr) {
			ue.setId(Integer.parseInt(id));
			service.delete(ue);
		}
	}
	/**
	 * 导出excel
	 *
	 * @return
	 */
	@ApiOperation(value = "导出excel")
	@LoginRequired
	@RecordLog
	@GetMapping("/exportExcel")
	public void exportExcel(DeptmentEntity entity, HttpServletResponse response) {
		service.exportExcel(entity, response);
	}

}
