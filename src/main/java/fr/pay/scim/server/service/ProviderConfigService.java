package fr.pay.scim.server.service;

public interface ProviderConfigService {

    public Boolean patchSupported();

    public Boolean bulkSupported();
    public Long bulkMaxOperations();
    public Long bulkMaxPayloadSize();

    public Boolean filterSupported();
    public Long filterMaxResults();

    public Boolean changePassword();

    public Boolean sortSupported();

    public Boolean etagSupported();

}
