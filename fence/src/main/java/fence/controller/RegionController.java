package fence.controller;

import javax.servlet.http.HttpServletResponse;

import fence.entity.ArchorEntity;
import fence.service.IArchorService;
import fence.service.impl.ArchorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import fence.service.IRegionService;
import fence.core.annotation.LoginRequired;
import fence.core.annotation.RecordLog;
import fence.entity.PageData;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import fence.entity.RegionEntity;
import java.util.List;

@RestController
@Api(tags = "region接口")
@RequestMapping("/region")
public class RegionController {


	@Autowired
	private IRegionService service;
	@Autowired
	private IArchorService archorService;


	
	/**
	 * 查询
	 *
	 * @return
	 */
	@ApiOperation(value = "查询")
	@LoginRequired
	@RecordLog
	@PostMapping(value = "/select")
	public List<RegionEntity> select(@RequestBody RegionEntity entity) {
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
	public PageData<RegionEntity> likeSelect(@RequestBody RegionEntity entity) {
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
	public void update(@RequestBody RegionEntity entity) {
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
	public void add(@RequestBody RegionEntity entity) {
		service.add(entity);
	}
	/**
	 * 添加区域并记录坐标
	 *
	 * @return
	 */
	@ApiOperation(value = "添加")
	@LoginRequired
	@RecordLog
	@PostMapping(value = "/addMap")
	public void addMap(@RequestBody  Param param) {
		System.out.println(param.toString());
		RegionEntity entity = param.getRegionEntity();
		ArchorEntity archorEntity =param.getArchorEntity();
		entity.setStatus(0);
		String pointstr = archorEntity.getName();
		String[] points = pointstr.split(";");
		service.add(entity);
		System.out.println(entity.getId());
		int i=1;
		for(String str:points){

			ArchorEntity archor=new ArchorEntity();
			archor.setRid(entity.getId());
			archor.setOrder_sec(i+"");
			archor.setLng(str.split("_")[0]);
			archor.setLat(str.split("_")[1]);
			archorService.add(archor);
			i++;
		}

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
	public void delete(@RequestBody RegionEntity entity) {
		service.delete(entity);
	}


	@ApiOperation(value = "删除")
	@LoginRequired
	@RecordLog
	@PostMapping(value = "/remove")
	public void deleteByList(@RequestBody String input) {
		String output = input.substring(input.indexOf("[") + 1 ,input.indexOf("]"));
		String[] idstr = output.split(",");
		RegionEntity ue =new RegionEntity();
		for (String id : idstr) {
			ue.setId(Integer.parseInt(id));
			service.delete(ue);
		}
		//JSONObject js = JSONObject.parseObject(jsonObject.getString("id"));
		//service.delete(entity);
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
	public void exportExcel(RegionEntity entity, HttpServletResponse response) {
		service.exportExcel(entity, response);
	}

	private static class Param{
		private RegionEntity regionEntity;
		private ArchorEntity ae;

		public RegionEntity getRegionEntity() {
			return regionEntity;
		}

		public void setRegionEntity(RegionEntity regionEntity) {
			this.regionEntity = regionEntity;
		}

		public fence.entity.ArchorEntity getArchorEntity() {
			return ae;
		}

		public void setArchorEntity(fence.entity.ArchorEntity archorEntity) {
			ae = archorEntity;
		}
	}
}
