/*
 * <Alice LiveMan>
 * Copyright (C) <2018>  <NekoSunflower>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package site.alice.liveman.model;

import com.alibaba.fastjson.annotation.JSONField;
import site.alice.liveman.dataobject.ExternalAppSecretDO;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.concurrent.CopyOnWriteArraySet;

public class LiveManSetting {
    private String[]                                 bannedYoutubeChannel;
    private String[]                                 bannedKeywords;
    private String                                   tempPath;
    private String                                   ffmpegPath;
    private String                                   defaultResolution;
    private String                                   baseUrl;
    private String                                   oneDriveClientId;
    private String                                   oneDriveClientSecret;
    private String                                   oneDriveToken;
    private CopyOnWriteArraySet<AccountInfo>         accounts;
    private CopyOnWriteArraySet<ChannelInfo>         channels;
    private CopyOnWriteArraySet<ServerInfo>          servers;
    private CopyOnWriteArraySet<ExternalAppSecretDO> externalAppSecretDOS;
    private String                                   encodeKey;
    private String                                   apShopUrl;
    private int[]                                    serverPoints = {0, 8, 30};
    private Boolean                                  preReEncode  = false;
    private Proxy                                    proxy;

    public String[] getBannedYoutubeChannel() {
        return bannedYoutubeChannel;
    }

    public void setBannedYoutubeChannel(String[] bannedYoutubeChannel) {
        this.bannedYoutubeChannel = bannedYoutubeChannel;
    }

    public String[] getBannedKeywords() {
        return bannedKeywords;
    }

    public void setBannedKeywords(String[] bannedKeywords) {
        this.bannedKeywords = bannedKeywords;
    }

    public String getTempPath() {
        return tempPath;
    }

    public void setTempPath(String tempPath) {
        this.tempPath = tempPath;
    }

    public String getFfmpegPath() {
        return ffmpegPath;
    }

    public void setFfmpegPath(String ffmpegPath) {
        this.ffmpegPath = ffmpegPath;
    }

    public String getDefaultResolution() {
        return defaultResolution;
    }

    public void setDefaultResolution(String defaultResolution) {
        this.defaultResolution = defaultResolution;
    }

    public CopyOnWriteArraySet<AccountInfo> getAccounts() {
        return accounts;
    }

    public void setAccounts(CopyOnWriteArraySet<AccountInfo> accounts) {
        this.accounts = accounts;
    }

    public CopyOnWriteArraySet<ChannelInfo> getChannels() {
        return channels;
    }

    public void setChannels(CopyOnWriteArraySet<ChannelInfo> channels) {
        this.channels = channels;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getOneDriveClientId() {
        return oneDriveClientId;
    }

    public void setOneDriveClientId(String oneDriveClientId) {
        this.oneDriveClientId = oneDriveClientId;
    }

    public String getOneDriveClientSecret() {
        return oneDriveClientSecret;
    }

    public void setOneDriveClientSecret(String oneDriveClientSecret) {
        this.oneDriveClientSecret = oneDriveClientSecret;
    }

    public int[] getServerPoints() {
        return serverPoints;
    }

    public void setServerPoints(int[] serverPoints) {
        this.serverPoints = serverPoints;
    }

    public String getOneDriveToken() {
        return oneDriveToken;
    }

    public void setOneDriveToken(String oneDriveToken) {
        this.oneDriveToken = oneDriveToken;
    }

    public Boolean getPreReEncode() {
        return preReEncode;
    }

    public void setPreReEncode(Boolean preReEncode) {
        this.preReEncode = preReEncode;
    }

    public String getEncodeKey() {
        return encodeKey;
    }

    public void setEncodeKey(String encodeKey) {
        this.encodeKey = encodeKey;
    }

    @JSONField(serialize = false)
    public Proxy getProxy() {
        String useSystemProxies = System.getProperty("admin.proxy.socks", "FALSE");     
        if (proxy == null && useSystemProxies != "FALSE") { 
            return new Proxy(Proxy.Type.SOCKS, new InetSocketAddress(System.getProperty("admin.proxy.address", "127.0.0.1"), Integer.parseInt(System.getProperty("admin.proxy.port", "7890"))));
        }
        return proxy;
    }

    public ProxyInfo getProxyInfo() {
        if (proxy != null) {
            ProxyInfo proxyInfo = new ProxyInfo();
            InetSocketAddress address = (InetSocketAddress) proxy.address();
            proxyInfo.setHost(address.getHostName());
            proxyInfo.setPort(address.getPort());
            proxyInfo.setType(proxy.type());
            return proxyInfo;
        }
        return null;
    }

    public void setProxyInfo(ProxyInfo proxyInfo) {
        this.proxy = new Proxy(proxyInfo.getType(), new InetSocketAddress(proxyInfo.getHost(), proxyInfo.getPort()));
    }

    public AccountInfo findByAccountId(String accountId) {
        if (accountId == null) {
            return null;
        }
        for (AccountInfo account : accounts) {
            if (account.getAccountId().equals(accountId)) {
                return account;
            }
        }
        return null;
    }

    public AccountInfo findByRoomId(String roomId) {
        if (roomId == null) {
            return null;
        }
        for (AccountInfo account : accounts) {
            if (roomId.equals(account.getRoomId())) {
                return account;
            }
        }
        return null;
    }

    public String getApShopUrl() {
        return apShopUrl;
    }

    public void setApShopUrl(String apShopUrl) {
        this.apShopUrl = apShopUrl;
    }

    public CopyOnWriteArraySet<ServerInfo> getServers() {
        return servers;
    }

    public void setServers(CopyOnWriteArraySet<ServerInfo> servers) {
        this.servers = servers;
    }

    public CopyOnWriteArraySet<ExternalAppSecretDO> getExternalAppSecretDOS() {
        return externalAppSecretDOS;
    }

    public void setExternalAppSecretDOS(CopyOnWriteArraySet<ExternalAppSecretDO> externalAppSecretDOS) {
        this.externalAppSecretDOS = externalAppSecretDOS;
    }
}
