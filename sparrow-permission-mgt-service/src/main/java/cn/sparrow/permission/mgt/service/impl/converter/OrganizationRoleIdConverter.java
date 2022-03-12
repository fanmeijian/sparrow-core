package cn.sparrow.permission.mgt.service.impl.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import cn.sparrow.permission.model.organization.OrganizationRolePK;

@Component
public class OrganizationRoleIdConverter implements Converter<String, OrganizationRolePK> {

    @Override
    public OrganizationRolePK convert(String source) {
        String[] parts = source.split("_");
        OrganizationRolePK pk = new OrganizationRolePK();
        pk.setOrganizationId(parts[0]);
        pk.setRoleId(parts[1]);

        return pk;
    }
}
