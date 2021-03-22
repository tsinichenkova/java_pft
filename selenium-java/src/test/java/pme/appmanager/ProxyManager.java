package pme.appmanager;

import net.lightbody.bmp.BrowserMobProxyServer;

public class ProxyManager {

    public ProxyManager() {
    }

    public BrowserMobProxyServer setUpProxy(){
        BrowserMobProxyServer proxy = new BrowserMobProxyServer();
        proxy.start(0);

        String authorizationKey = "x_remote_user";
        String value = "IrIlina";
        proxy.addRequestFilter((request, content, messageInfo)->{
            if (request.headers().contains(authorizationKey)) {
                request.headers().remove(authorizationKey);
            }
            request.headers().add(authorizationKey,value);
            return null;
        });
        return proxy;
    }
}
