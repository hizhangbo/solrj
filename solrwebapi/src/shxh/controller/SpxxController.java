package shxh.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import main.shxh.business.SpxxSolrService;
import main.shxh.domain.Spxx;
import shxh.BaseController;

@RestController
@RequestMapping("spxx")
public class SpxxController extends BaseController<SpxxSolrService, Spxx> {

	@Override
	public void Init() {
		service = new SpxxSolrService();		
	}

	@Override
	@ApiOperation(value="获取商品信息", notes="获取一个默认商品信息，一般为solr查询第1个")
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public Spxx get() {
		return service.GetModel();
	}

	@Override
	@ApiOperation(value="获取商品信息", notes="根据商品ID获取对应商品信息")
	@ApiImplicitParam(name = "id",value = "商品id", dataType = "Long", paramType = "path")
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public Spxx getById(@PathVariable("id") Long id) {
		return service.GetModel(id);
	}

	@Override
	@ApiOperation(value="获取商品信息", notes="获取一组默认商品信息，一般为solr查询前10个")
	@RequestMapping(value = "/getlst", method = RequestMethod.GET)
	public List<Spxx> getLst() {
		return service.GetModels();
	}
	
	@Override
	@ApiOperation(value="获取商品信息", notes="获取分页商品信息")
    @ApiImplicitParams({
        @ApiImplicitParam(dataType = "Integer", name = "pagenum", value = "页码", required = false, paramType = "path"),
        @ApiImplicitParam(dataType = "Integer", name = "pagesize", value = "页容量", required = false, paramType = "path")
    })
	@RequestMapping(value = "/getpage", method = RequestMethod.GET)
	public List<Spxx> getPage(@RequestParam(value="pagenum", defaultValue="1") int pagenum
			, @RequestParam(value = "pagesize", defaultValue="10") int pagesize) {
		return service.GetModelsPage(pagenum, pagesize);
	}

	@SuppressWarnings("unchecked")
	@Override
	@ApiOperation(value="获取商品信息", notes="获取分页商品信息,并可提交Json格式查询参数,不包含{}")
    @ApiImplicitParams({
        @ApiImplicitParam(dataType = "Integer", name = "pagenum", value = "页码", required = false, paramType = "path"),
        @ApiImplicitParam(dataType = "Integer", name = "pagesize", value = "页容量", required = false, paramType = "path"),
        @ApiImplicitParam(dataType = "String", name = "parameters", value = "查询参数", required = false, paramType = "path")
    })
	@RequestMapping(value = "/getLstByParas", method = RequestMethod.GET)
	public List<Spxx> getLstByParas(@RequestParam(value="pagenum", defaultValue="1") int pagenum
			, @RequestParam(value = "pagesize", defaultValue="10") int pagesize
			, @RequestParam(value = "parameters", defaultValue="") String parameters) {

		Map<String, String> parameters_map = new HashMap<>();
		if(!parameters.isEmpty()){
			ObjectMapper mapper = new ObjectMapper();
			try {
				parameters_map = mapper.readValue('{'+parameters+'}', Map.class);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return service.GetModels(pagenum, pagesize, parameters_map);
	}

	@Override
	@ApiOperation(value="更新商品信息", notes="传入商品信息model更新对应id的商品信息")
	@ApiImplicitParam(dataType = "Spxx", name = "model", value = "商品信息", required = true)
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public void update(@RequestBody Spxx model) {
		service.UpdateModel(model);		
	}

	@SuppressWarnings("unchecked")
	@Override
	@ApiOperation(value="更新商品信息", notes="传入商品信息更新字段更新对应id的商品信息")
    @ApiImplicitParams({
        @ApiImplicitParam(dataType = "Long", name = "id", value = "商品id", required = true, paramType = "path"),
        @ApiImplicitParam(dataType = "String", name = "fields", value = "更新字段", required = true)
    })
	@RequestMapping(value = "/updatefield/{id}", method = RequestMethod.PUT)
	public void update(@PathVariable("id") Long id, @RequestBody String fields) {
		Map<String, Object> field = new HashMap<>();
		ObjectMapper mapper = new ObjectMapper();
		try {
			field = mapper.readValue(fields, Map.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		service.UpdateModel(id, field);
	}

	@Override
	@ApiOperation(value="删除商品信息", notes="传入商品信息model删除对应id的商品信息")
	@ApiImplicitParam(dataType = "Spxx", name = "model", value = "商品信息", required = true)
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public void delete(@RequestBody Spxx model) {
		service.DeleteModel(model);		
	}

	@Override
	@ApiOperation(value="新增商品信息", notes="传入商品信息model新增商品信息")
	@ApiImplicitParam(dataType = "Spxx", name = "model", value = "商品信息", required = true)
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void add(@RequestBody Spxx model) {
		service.AddModel(model);
	}
	
}

