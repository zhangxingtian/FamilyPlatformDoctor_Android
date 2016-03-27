package com.zhonghong.doctor.net;


public class ServerConfig {

    /**
     * 设置是运营服务器，测试服务器，还是局域网服务器
     */

    private static Server server = Server.official;

    /**
     * 获取IP域名地址
     *
     * @return 返回域名
     */
    public static String getIpAddress() {
        String ip = null;
        switch (server) {
            case official:
                ip = "server_domain";
                break;
            case test:
                ip = "test_server_domain";
                break;
            case local:
                ip = "local_server_domain";
                break;
        }
        return MetaUtil.getInstance().getMetaString(ip);
    }

    /**
     * 获取请求的服务器地址
     *
     * @return 返回服务器地址
     */
    public static String getServerAddress() {
        return getIpAddress() + MetaUtil.getInstance().getMetaString("server_method");
    }

    public static boolean isTest() {
        return server == Server.test;
    }

    private enum Server {
        official,
        test,
        local
    }

}
