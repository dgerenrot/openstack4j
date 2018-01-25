package org.openstack4j.openstack.networking.internal.ext;

import org.openstack4j.api.Apis;
import org.openstack4j.api.networking.ext.FlowClassifierService;
import org.openstack4j.api.networking.ext.PortChainService;
import org.openstack4j.api.networking.ext.SFCService;
import org.openstack4j.openstack.networking.internal.BaseNetworkingServices;

/**
 * {@inheritDoc}
 */
public class SFCServiceImpl extends BaseNetworkingServices implements SFCService {

    /**
     * {@inheritDoc}
     */
    @Override
    public FlowClassifierService flowclassifiers() {
        return Apis.get(FlowClassifierService.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PortChainService portchains() {
        return Apis.get(PortChainService.class);
    }
}
