package fr.pay.scim.server.service.implementation.memory;

import org.springframework.stereotype.Service;

import fr.pay.scim.server.service.ProviderConfigService;

@Service
public class ProviderConfigServiceImplMemory implements ProviderConfigService {

    @Override
    public Boolean patchSupported() {
        return Boolean.FALSE;
    }

    @Override
    public Boolean bulkSupported() {
        return Boolean.FALSE;
    }

    @Override
    public Long bulkMaxOperations() {
       return null;
    }

    @Override
    public Long bulkMaxPayloadSize() {
        return null;
    }

    @Override
    public Boolean filterSupported() {
        return Boolean.FALSE;
    }

    @Override
    public Long filterMaxResults() {
        return null;
    }

    @Override
    public Boolean changePassword() {
        return Boolean.TRUE;
    }

    @Override
    public Boolean sortSupported() {
        return Boolean.FALSE;
    }

    @Override
    public Boolean etagSupported() {
        return Boolean.FALSE;
    }

}
