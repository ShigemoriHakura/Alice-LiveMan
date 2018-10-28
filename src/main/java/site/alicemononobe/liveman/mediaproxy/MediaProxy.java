package site.alicemononobe.liveman.mediaproxy;

import site.alicemononobe.liveman.mediaproxy.proxytask.MediaProxyTask;

import java.io.IOException;
import java.net.Proxy;
import java.net.URI;

public interface MediaProxy {

    boolean isMatch(URI url, String requestFormat);

    MediaProxyTask createProxyTask(String videoId, URI sourceUrl, Proxy proxy) throws IOException;

    Object requestHandler(String videoId) throws Exception;
}
