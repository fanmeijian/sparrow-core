package cn.sparrow.permission.mgt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cn.sparrow.permission.mgt.api.WorkspaceService;
import cn.sparrow.permission.mgt.service.repository.AppRepository;
import cn.sparrow.permission.mgt.service.repository.WorkspaceRepository;
import cn.sparrow.permission.model.resource.App;
import cn.sparrow.permission.model.resource.Workspace;

@Service
public class WorkspaceServiceImpl implements WorkspaceService {

    @Autowired
    private WorkspaceRepository workspaceRepository;
    @Autowired
    private AppRepository appRepository;

    @Override
    public String create(Workspace workspace) {
        return this.workspaceRepository.save(workspace).getId();
    }

    @Override
    public Workspace update(String workspaceId, Workspace workspace) {
        return this.workspaceRepository.save(workspace);
    }

    @Override
    public Workspace get(String workspaceId) {
        return this.workspaceRepository.findById(workspaceId).get();
    }

    @Override
    public void delete(List<String> workspaceIds) {
        this.workspaceRepository.deleteAllById(workspaceIds);
    }

    @Override
    public Page<Workspace> list(Pageable pageable) {
        return this.workspaceRepository.findAll(pageable);
    }

    @Override
    public Page<App> listApps(String workspaceId, Pageable pageable) {
        return this.appRepository.findByWorkspaceId(workspaceId, pageable);
    }

    @Override
    public void addApps(String workspaceId, List<String> appIds) {
        appIds.forEach(f -> {
            App app = this.appRepository.findById(f).get();
            app.setWorkspaceId(workspaceId);
            this.appRepository.save(app);
        });
    }

    @Override
    public void removeApps(String workspaceId, List<String> appIds) {
        appIds.forEach(f -> {
            App app = this.appRepository.findById(f).get();
            app.setWorkspaceId(null);
            this.appRepository.save(app);
        });
    }

}
