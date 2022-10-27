package cn.sparrow.permission.mgt.api;

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
import cn.sparrow.permission.model.resource.Workspace;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "workspace", description = "工作台服务")
@RequestMapping(value = "workspaces")
public interface WorkspaceService {

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ResponseStatus(code = HttpStatus.CREATED)
    public String create(@RequestBody Workspace workspace);

    @PatchMapping(value = "/{workspaceId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Workspace update(@PathVariable String workspaceId,@RequestBody Workspace workspace);

    @GetMapping(value = "/{workspaceId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Workspace get(String workspaceId);

    @PostMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@RequestBody List<String> workspaceIds);

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Page<Workspace> list(Pageable pageable);

    @GetMapping(value = "/{workspaceId}/apps", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Page<App> listApps(@PathVariable String workspaceId,Pageable pageable);

    @PostMapping(value = "/{workspaceId}/apps/add", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void addApps(@PathVariable String workspaceId,@RequestBody List<String> appIds);

    @PostMapping(value = "/{workspaceId}/apps/remove", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void removeApps(@PathVariable String workspaceId,@RequestBody List<String> appIds);
}
