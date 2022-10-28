package cn.sparrow.permission.portal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cn.sparrow.permission.model.resource.App;
import cn.sparrow.permission.portal.repository.AppRepository;
import cn.sparrow.permission.portal.service.AppService;

//@Service
public class AppServiceImpl implements AppService {

    @Autowired
    private AppRepository appRepository;

    @Override
    public String create(App app) {
        return this.appRepository.save(app).getId();
    }

    @Override
    public App update(String appId, App app) {
        App app_ = this.appRepository.findById(appId).get();
        app_.setName(app.getName());
        return app_;
    }

    @Override
    public App get(String appId) {
        return this.appRepository.findById(appId).get();
    }

    @Override
    public void delete(List<String> appIds) {
        this.appRepository.deleteAllById(appIds);
    }

    @Override
    public Page<App> list(Pageable pageable) {
        return this.appRepository.findAll(pageable);
    }

}
