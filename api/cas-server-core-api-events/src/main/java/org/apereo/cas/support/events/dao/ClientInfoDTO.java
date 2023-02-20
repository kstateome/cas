package org.apereo.cas.support.events.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang.StringUtils;
import org.apereo.inspektr.common.web.ClientInfoHolder;
import org.apereo.inspektr.common.web.ClientInfo;
@Getter
@AllArgsConstructor
public class ClientInfoDTO implements java.io.Serializable{
    private static final long serialVersionUID = -4206712375316423417L;

    private final String clientIpAddress;
    private final String serverIpAddress;
    private final String geoLocation;
    private final String userAgent;
    private final boolean initialized;

   public ClientInfoDTO(ClientInfo clientInfo){
       if(clientInfo != null && StringUtils.isNotEmpty(clientInfo.getClientIpAddress())){
           this.clientIpAddress = clientInfo.getClientIpAddress();
           this.serverIpAddress = clientInfo.getServerIpAddress();
           this.userAgent = clientInfo.getUserAgent();
           this.geoLocation = clientInfo.getGeoLocation();
           this.initialized = true;
       }else {
           this.clientIpAddress = null;
           this.serverIpAddress = null;
           this.userAgent = null;
           this.geoLocation = null;
           this.initialized = false;
       }
   }
}
