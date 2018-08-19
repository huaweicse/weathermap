package com.service.weathermap.web.server;

import io.vertx.ext.web.handler.impl.StaticHandlerImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.nio.charset.Charset;
import java.util.List;

@Component("/*")
public class ViewsHandler extends StaticHandlerImpl {

    @Value("#{'${app.web.roots:views,weathermapweb/views}'.split(',')}")
    List<String> wwwRoots;

    @PostConstruct
    public void init(){
        setDefaultContentEncoding("UTF-8");
        for(int i=0;i<wwwRoots.size();i++){
            String webRoot = wwwRoots.get(i);
            if(new File(webRoot).exists()){
                setWebRoot(webRoot);
                return;
            }
        }
        throw new IllegalArgumentException("Web roots ["+ StringUtils.join(wwwRoots,", ")+"] not exist!");
    }

}
