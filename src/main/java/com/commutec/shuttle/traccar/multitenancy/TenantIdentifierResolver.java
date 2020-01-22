package com.commutec.shuttle.traccar.multitenancy;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * TenantIdentifierResolver
 */
@Component
public class TenantIdentifierResolver implements CurrentTenantIdentifierResolver {

    @Value("${default.tenant.id}")
    private String defaultTenantId;
    
    @Override
    public String resolveCurrentTenantIdentifier() {
        String tenantId = TenantContext.getCurrentTenant();
        if (tenantId != null) {
            return tenantId;
        }
        return this.defaultTenantId;
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }  
}