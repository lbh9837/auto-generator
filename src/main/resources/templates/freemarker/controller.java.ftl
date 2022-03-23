package ${package.Controller};

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

/**
 * <p>
 * ${table.comment!} 控制器
 * </p>
 *
 * @author ${author}
 * @date ${.now}
 */
<#if swagger2>
@Api(value = "${table.comment!}服务接口")
</#if>
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("/${cfg.mapperUrl}")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private ${table.serviceName} ${table.serviceName?uncap_first};

    @PostMapping("/add")
    @ApiOperation(value = "新增${table.comment!}")
    public Result<${entity}> add(@RequestBody ${entity} ${entity?uncap_first}) {
        ${table.serviceName?uncap_first}.save(${entity?uncap_first});
        return Result.success(${entity?uncap_first});
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除${table.comment!}")
    public Result delete(@RequestParam Long id) {
        ${table.serviceName?uncap_first}.removeById(id);
        return Result.success();
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改${table.comment!}")
    public Result<${entity}> update(@ApiParam(required = true) ${entity} ${entity?uncap_first}) {
        AssertUtil.notNull(${entity?uncap_first}.getId(), ResultCode.UPDATE_MUST_HAS_ID);
        ${table.serviceName?uncap_first}.updateById(${entity?uncap_first});
        ${entity} model = ${table.serviceName?uncap_first}.getById(${entity?uncap_first}.getId());
        return Result.success(model);
    }

    @GetMapping("/detail")
    @ApiOperation(value = "查询${table.comment!}详情")
    public Result<${entity}> detail(@RequestParam Long id) {
        ${entity} ${entity?uncap_first} = ${table.serviceName?uncap_first}.getById(id);
        return Result.success(${entity?uncap_first});
    }

    @PostMapping("/search")
    @ApiOperation(value = "根据条件查询${table.comment!}分页列表")
    public Result<List<${entity}>> search(@ApiParam(value = "查询参数") ${entity} ${entity?uncap_first},
    @RequestParam(defaultValue = "1") Integer page,
    @RequestParam(defaultValue = "10") Integer size) {
        PageHelper.startPage(page, size);
        List<${entity}> list = ${table.serviceName?uncap_first}.list(${entity?uncap_first});
        return Result.success(list);
    }
}
</#if>
