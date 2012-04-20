// Copyright 2012 Citrix Systems, Inc. Licensed under the
// Apache License, Version 2.0 (the "License"); you may not use this
// file except in compliance with the License.  Citrix Systems, Inc.
// reserves all rights not expressly granted by the License.
// You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
// 
// Automatically generated by addcopyright.py at 04/03/2012
package com.cloud.network.element;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import org.apache.log4j.Logger;

import com.cloud.agent.AgentManager;
import com.cloud.api.commands.AddCiscoNexusVSMCmd;
import com.cloud.api.commands.DeleteCiscoNexusVSMCmd;
import com.cloud.api.commands.ListCiscoNexusVSMCmd;
import com.cloud.api.commands.ListCiscoNexusVSMNetworksCmd;
import com.cloud.api.response.CiscoNexusVSMResponse;
import com.cloud.configuration.ConfigurationManager;
import com.cloud.configuration.dao.ConfigurationDao;
import com.cloud.dc.dao.DataCenterDao;
import com.cloud.deploy.DeployDestination;
import com.cloud.exception.ConcurrentOperationException;
import com.cloud.exception.InsufficientCapacityException;
import com.cloud.exception.ResourceUnavailableException;
import com.cloud.host.dao.HostDao;
import com.cloud.host.dao.HostDetailsDao;
import com.cloud.network.CiscoNexusVSMDeviceVO;
import com.cloud.network.CiscoNexusVSMDeviceManagerImpl;
import com.cloud.network.Network;
import com.cloud.network.NetworkManager;
import com.cloud.network.PhysicalNetworkServiceProvider;
import com.cloud.network.Network.Capability;
import com.cloud.network.Network.Provider;
import com.cloud.network.Network.Service;
import com.cloud.network.dao.NetworkDao;
import com.cloud.network.dao.NetworkServiceMapDao;
import com.cloud.network.dao.PhysicalNetworkDao;
import com.cloud.network.resource.CiscoNexusVSMResource;
import com.cloud.resource.ServerResource;
import com.cloud.utils.component.Inject;
import com.cloud.vm.NicProfile;
import com.cloud.vm.ReservationContext;
import com.cloud.vm.VirtualMachine;
import com.cloud.vm.VirtualMachineProfile;
import com.cloud.network.PortProfile;
import com.cloud.network.element.NetworkElement;
import com.cloud.offering.NetworkOffering;
import com.cloud.utils.component.Manager;

@Local(value = NetworkElement.class)
public class CiscoNexusVSMElement extends CiscoNexusVSMDeviceManagerImpl implements CiscoNexusVSMElementService, NetworkElement, Manager {

    private static final Logger s_logger = Logger.getLogger(CiscoNexusVSMElement.class);

    @Inject
    NetworkManager _networkManager;
    @Inject
    ConfigurationManager _configMgr;
    @Inject
    NetworkServiceMapDao _ntwkSrvcDao;
    @Inject
    AgentManager _agentMgr;
    @Inject
    NetworkManager _networkMgr;
    @Inject
    HostDao _hostDao;
    @Inject
    DataCenterDao _dcDao;
    @Inject
    HostDetailsDao _hostDetailDao;    
    @Inject
    PhysicalNetworkDao _physicalNetworkDao;
    @Inject
    NetworkDao _networkDao;
    @Inject
    HostDetailsDao _detailsDao;
    @Inject
    ConfigurationDao _configDao;
    

    @Override
    public Map<Service, Map<Capability, String>> getCapabilities() {
    	return null;
    }
    
    @Override
    public Provider getProvider() {
        return null;
    }
    
    @Override
    public boolean implement(Network network, NetworkOffering offering,
            DeployDestination dest, ReservationContext context)
            throws ConcurrentOperationException, ResourceUnavailableException,
            InsufficientCapacityException {
        return true;
    }
    
    @Override
    public boolean prepare(Network network, NicProfile nic,
            VirtualMachineProfile<? extends VirtualMachine> vm,
            DeployDestination dest, ReservationContext context)
            throws ConcurrentOperationException, ResourceUnavailableException,
            InsufficientCapacityException {
        return true;
    }
    
    @Override
    public boolean release(Network network, NicProfile nic,
            VirtualMachineProfile<? extends VirtualMachine> vm,
            ReservationContext context) throws ConcurrentOperationException,
            ResourceUnavailableException {
        return true;
    }
    
    @Override
    public boolean shutdown(Network network, ReservationContext context,
    		boolean cleanup) throws ConcurrentOperationException,
    		ResourceUnavailableException {
        return true;
    }

    @Override
    public boolean destroy(Network network)
            throws ConcurrentOperationException, ResourceUnavailableException {
        return true;
    }
    
    @Override
    public boolean isReady(PhysicalNetworkServiceProvider provider) {
        return true;
    }

    @Override
    public boolean shutdownProviderInstances(PhysicalNetworkServiceProvider provider,
    		ReservationContext context) throws ConcurrentOperationException,
    		ResourceUnavailableException {
    	return true;
    }
    
    @Override
    public boolean canEnableIndividualServices() {
    	return true;
    }
    
    @Override
    public boolean verifyServicesCombination(List<String> services) {
    	return true;
    }
    
    @Override
    public CiscoNexusVSMDeviceVO addCiscoNexusVSM(AddCiscoNexusVSMCmd cmd) {
    
    	// This function essentially prepares all the parameters we need to send
    	// to the addCiscoNexusVSM() function defined in CiscoNexusVSMDeviceManagerImpl.java.
    	
    	// We have this separation of functionality between CiscoNexusVSMElement.java
    	// to address multiple versions of Cisco Nexus Switches in future. This is the
    	// function/layer that will parse multiple versions and accordingly prepare
    	// different parameters to pass to to the CiscoNexusVSMDeviceManagerImpl functions
    	// which will in turn talk to the right resource via the appropriate manager.

        String vsmipaddress = cmd.getIpAddr();
        String vsmusername = cmd.getUsername();
        String vsmpassword = cmd.getPassword();
        String vsmName = cmd.getVSMName();
        long zoneId = cmd.getZoneId();
        
        // Invoke the addCiscoNexusVSM() function defined in the upper layer (DeviceMgrImpl).
        // The  upper layer function will create a resource of type "host" to represent this VSM.
        // It will add this VSM to the db.
        CiscoNexusVSMDeviceVO vsmDeviceVO = addCiscoNexusVSM(zoneId, vsmipaddress, vsmusername, vsmpassword, (ServerResource) new CiscoNexusVSMResource(), vsmName);
        return vsmDeviceVO;
    }

    @Override
    public boolean deleteCiscoNexusVSM(DeleteCiscoNexusVSMCmd cmd) {
    	return true;
    }
    

    @Override
    public List<? extends PortProfile> listNetworks(ListCiscoNexusVSMNetworksCmd cmd) {
    
    	/***
    	// Get the device id of the VSM from the database.
    	Long vsmDeviceId = cmd.getvsmDeviceId();
    	
    	// Get all details of the VSM by firing a dao find (querying the db). 
    	CiscoNexusVSMDeviceVO vsmDeviceVo = _lbDeviceDao.findById(vsmDeviceId);
    	
        List<PortProfileVO> portProfileMap = _networkLBDao.listByLoadBalancerDeviceId(lbDeviceId);
        if (networkLbMaps != null && !networkLbMaps.isEmpty()) {
            for (NetworkExternalLoadBalancerVO networkLbMap : networkLbMaps) {
                NetworkVO network = _networkDao.findById(networkLbMap.getNetworkId());
                networks.add(network);
            }
        }
        
        return networks;
        ***/
    	return null;
    }

    @Override
    public List<CiscoNexusVSMDeviceVO> listCiscoNexusVSMs(ListCiscoNexusVSMCmd cmd) {
    	return null;
    	
    }

    @Override
    public CiscoNexusVSMResponse createCiscoNexusVSMResponse(CiscoNexusVSMDeviceVO vsmDeviceVO) {    		
            CiscoNexusVSMResponse response = new CiscoNexusVSMResponse();
            response.setId(vsmDeviceVO.getId());
            response.setMgmtIpAddress(vsmDeviceVO.getMgmtIpAddr());
            return response;
        }

    @Override
    public String getPropertiesFile() {
    	return "cisconexusvsm_commands.properties";
    }
}