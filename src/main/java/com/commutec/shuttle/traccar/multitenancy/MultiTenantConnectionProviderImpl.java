package com.commutec.shuttle.traccar.multitenancy;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.commutec.shuttle.traccar.exception.InvalidTenantException;

import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * MultiTenantConnectionProviderImpl
 */
@Component
public class MultiTenantConnectionProviderImpl implements MultiTenantConnectionProvider {

    private static final long serialVersionUID = 1L;

    @Value("${default.tenant.id}")
    private String defaultTenantId;
    
    @Autowired
    private DataSource dataSource;

    @Override
    public Connection getAnyConnection() throws SQLException {
        return dataSource.getConnection();
    }

    @Override
    public void releaseAnyConnection(Connection connection) throws SQLException {
        connection.close();
    }

    @Override
    public Connection getConnection(String tenantIdentifie) throws SQLException {
        String tenantIdentifier = TenantContext.getCurrentTenant();
        final Connection connection = getAnyConnection();
        try {
            if (tenantIdentifier != null) {
                connection.createStatement().execute("USE " + tenantIdentifier);
            } else {
                connection.createStatement().execute("USE " + this.defaultTenantId);
            }
        } catch (SQLException e) {
            throw new InvalidTenantException("No DB found with name " + tenantIdentifier);
            // throw new HibernateException("Problem setting schema to " + tenantIdentifier, e);
        }
        return connection;
    }

    @Override
    public void releaseConnection(String tenantIdentifier, Connection connection) throws SQLException {
        try {
            connection.createStatement().execute("USE " + this.defaultTenantId);
        } catch (SQLException e) {
            throw new InvalidTenantException("No DB found with name " + tenantIdentifier);
            // throw new HibernateException("Problem setting schema to " + tenantIdentifier, e);
        }
        connection.close();
    }

    @SuppressWarnings("rawtypes")
    @Override
    public boolean isUnwrappableAs(Class unwrapType) {
        return false;
    }

    @Override
    public <T> T unwrap(Class<T> unwrapType) {
        return null;
    }

    @Override
    public boolean supportsAggressiveRelease() {
        return true;
    }
}