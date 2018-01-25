package org.openstack4j.api.network;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.util.List;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.network.ext.Ethertype;
import org.openstack4j.model.network.ext.FlowClassifier;
import org.openstack4j.model.network.ext.PortChain;
import org.testng.annotations.Test;

@Test(suiteName = "ServiceFunctionChain")
public class SFCTests extends AbstractTest {
    private static final String JSON_FLOW_CLASSIFIERS = "/network/sfc/flow_classifiers.json";
    private static final String JSON_FLOW_CLASSIFIER = "/network/sfc/flow_classifier.json";
    private static final String JSON_PORT_CHAINS = "/network/sfc/port_chains.json";
    private static final String JSON_PORT_CHAIN = "/network/sfc/port_chain.json";

    private static final String TEST_PROJECT_ID = "12345678909876543210abcdefabcdef";
    private static final String TEST_PARAM_KEY = "aparam";
    private static final String TEST_FC_NAME2 = "FC2";
    private static final String TEST_FC_NAME1 = "FC1";
    private static final String TEST_PC_NAME2 = "PC2";
    private static final String TEST_PC_NAME1 = "PC1";

    @Test
    public void testFlowClassifiersList() throws Exception {
        respondWith(JSON_FLOW_CLASSIFIERS);
        List<? extends FlowClassifier> flowClassifiers = osv3().sfc().flowclassifiers().list();
        server.takeRequest();
        assertNotNull(flowClassifiers);
        assertEquals(2, flowClassifiers.size());
        assertEquals(flowClassifiers.get(0).getName(), TEST_FC_NAME1);
        assertEquals(flowClassifiers.get(1).getName(), TEST_FC_NAME2);
        assertNotNull(flowClassifiers.get(0).getEthertype());
        assertEquals(flowClassifiers.get(0).getEthertype(), Ethertype.IPv4);
        assertNotNull(flowClassifiers.get(0).getL7Parameters());
        assertNotNull(flowClassifiers.get(0).getL7Parameters().get(TEST_PARAM_KEY));
        assertEquals(flowClassifiers.get(0).getL7Parameters().get(TEST_PARAM_KEY), "A");
    }

    @Test
    public void testGetFlowClassifier() throws Exception {
        respondWith(JSON_FLOW_CLASSIFIER);
        FlowClassifier flowClassifier = osv3().sfc().flowclassifiers().get("abcdef");
        server.takeRequest();
        assertNotNull(flowClassifier);
        assertEquals(flowClassifier.getName(), TEST_FC_NAME1);
        assertNotNull(flowClassifier.getEthertype());
        assertEquals(flowClassifier.getEthertype(), Ethertype.IPv4);
        assertNotNull(flowClassifier.getL7Parameters());
        assertNotNull(flowClassifier.getL7Parameters().get(TEST_PARAM_KEY));
        assertEquals(flowClassifier.getL7Parameters().get(TEST_PARAM_KEY), "A");
        assertEquals(flowClassifier.getTenantId(), TEST_PROJECT_ID);
    }

    @Test
    public void testPortChainsList() throws Exception {
        respondWith(JSON_PORT_CHAINS);
        List<? extends PortChain> portChain = osv3().sfc().portchains().list();
        server.takeRequest();
        assertNotNull(portChain);
        assertEquals(2, portChain.size());
        assertEquals(portChain.get(0).getName(), TEST_PC_NAME1);
        assertEquals(portChain.get(1).getName(), TEST_PC_NAME2);
    }

    @Test
    public void testGetPortChain() throws Exception {
        respondWith(JSON_PORT_CHAIN);
        PortChain portChain = osv3().sfc().portchains().get("0abcdef");
        server.takeRequest();
        assertNotNull(portChain);
        assertEquals(portChain.getName(), TEST_PC_NAME1);
    }

    @Override
    protected Service service() {
        return Service.NETWORK;
    }
}
