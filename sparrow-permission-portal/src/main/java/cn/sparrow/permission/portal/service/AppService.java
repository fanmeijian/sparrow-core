package cn.sparrow.permission.portal.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import cn.sparrow.permission.model.resource.App;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "应用")
@RequestMapping(value = "apps")
public interface AppService {
    
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ResponseStatus(code = HttpStatus.CREATED)
    public String create(@RequestBody App app);

    @PatchMapping(value = "/{appId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public App update(@PathVariable String appId, @RequestBody App app);

    @GetMapping(value = "/{appId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public App get(@PathVariable String appId);

    @PostMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@RequestBody List<String> appIds);

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Page<App> list(Pageable pageable);
}
