package cn.sparrow.permission.mgt.service.impl.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import cn.sparrow.permission.model.organization.OrganizationPositionLevelPK;

@Component
public class OrganizationLevelIdConverter implements Converter<String, OrganizationPositionLevelPK> {

    @Override
    public OrganizationPositionLevelPK convert(String source) {
        String[] parts = source.split("_");
        OrganizationPositionLevelPK pk = new OrganizationPositionLevelPK();
        pk.setOrganizationId(parts[0]);
        pk.setPositionLevelId(parts[1]);

        return pk;
    }
}
