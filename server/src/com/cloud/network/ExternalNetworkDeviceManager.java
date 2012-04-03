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
// Automatically generated by addcopyright.py at 04/02/2012
package com.cloud.network;

import java.util.ArrayList;
import java.util.List;
import com.cloud.api.commands.AddNetworkDeviceCmd;
import com.cloud.api.commands.DeleteNetworkDeviceCmd;
import com.cloud.api.commands.ListNetworkDeviceCmd;
import com.cloud.host.Host;
import com.cloud.server.api.response.NetworkDeviceResponse;
import com.cloud.utils.component.Manager;

public interface ExternalNetworkDeviceManager extends Manager {
    
    public static class NetworkDevice {
        private String _name;
        private String _provider;
        private static List<NetworkDevice> supportedNetworkDevices = new ArrayList<NetworkDevice>();

        public static final NetworkDevice ExternalDhcp = new NetworkDevice("ExternalDhcp", null);
        public static final NetworkDevice PxeServer = new NetworkDevice("PxeServer", null);
        public static final NetworkDevice NetscalerMPXLoadBalancer = new NetworkDevice("NetscalerMPXLoadBalancer", Network.Provider.Netscaler.getName());
        public static final NetworkDevice NetscalerVPXLoadBalancer = new NetworkDevice("NetscalerVPXLoadBalancer", Network.Provider.Netscaler.getName());
        public static final NetworkDevice NetscalerSDXLoadBalancer = new NetworkDevice("NetscalerSDXLoadBalancer", Network.Provider.Netscaler.getName());
        public static final NetworkDevice F5BigIpLoadBalancer = new NetworkDevice("F5BigIpLoadBalancer", Network.Provider.F5BigIp.getName());
        public static final NetworkDevice JuniperSRXFirewall = new NetworkDevice("JuniperSRXFirewall", Network.Provider.JuniperSRX.getName());
        
        public NetworkDevice(String deviceName, String ntwkServiceprovider) {
            _name = deviceName;
            _provider = ntwkServiceprovider;
            supportedNetworkDevices.add(this);
        }
        
        public String getName() {
            return _name;
        }

        public String getNetworkServiceProvder() {
            return _provider;
        }

        public static NetworkDevice getNetworkDevice(String devicerName) {
            for (NetworkDevice device : supportedNetworkDevices) {
                if (device.getName().equalsIgnoreCase(devicerName)) {
                    return device;
                }
            }
            return null;
        }
    }

    public Host addNetworkDevice(AddNetworkDeviceCmd cmd);
    
    public NetworkDeviceResponse getApiResponse(Host device);
    
    public List<Host> listNetworkDevice(ListNetworkDeviceCmd cmd);
    
    public boolean deleteNetworkDevice(DeleteNetworkDeviceCmd cmd);
    
}