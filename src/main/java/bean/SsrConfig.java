/**
 * Copyright 2019 bejson.com
 */
package bean;

/**
 * Auto-generated: 2019-06-08 19:16:38
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class SsrConfig {

    private String server;
    private String server_ipv6;
    private int server_port;
    private String local_address;
    private int local_port;
    private String password;
    private int timeout;
    private String method;
    private String protocol;
    private String protocol_param;
    private String obfs;
    private String obfs_param;
    private String redirect;
    private boolean dns_ipv6;
    private boolean fast_open;
    private int workers;

    public void setServer(String server) {
        this.server = server;
    }

    public String getServer() {
        return server;
    }

    public void setServer_ipv6(String server_ipv6) {
        this.server_ipv6 = server_ipv6;
    }

    public String getServer_ipv6() {
        return server_ipv6;
    }

    public void setServer_port(int server_port) {
        this.server_port = server_port;
    }

    public int getServer_port() {
        return server_port;
    }

    public void setLocal_address(String local_address) {
        this.local_address = local_address;
    }

    public String getLocal_address() {
        return local_address;
    }

    public void setLocal_port(int local_port) {
        this.local_port = local_port;
    }

    public int getLocal_port() {
        return local_port;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getMethod() {
        return method;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol_param(String protocol_param) {
        this.protocol_param = protocol_param;
    }

    public String getProtocol_param() {
        return protocol_param;
    }

    public void setObfs(String obfs) {
        this.obfs = obfs;
    }

    public String getObfs() {
        return obfs;
    }

    public void setObfs_param(String obfs_param) {
        this.obfs_param = obfs_param;
    }

    public String getObfs_param() {
        return obfs_param;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

    public String getRedirect() {
        return redirect;
    }

    public void setDns_ipv6(boolean dns_ipv6) {
        this.dns_ipv6 = dns_ipv6;
    }

    public boolean getDns_ipv6() {
        return dns_ipv6;
    }

    public void setFast_open(boolean fast_open) {
        this.fast_open = fast_open;
    }

    public boolean getFast_open() {
        return fast_open;
    }

    public void setWorkers(int workers) {
        this.workers = workers;
    }

    public int getWorkers() {
        return workers;
    }

}