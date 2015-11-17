package com.registeredreviews.web.listener;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.registeredreviews.mobile.Constants;

import fiftyone.mobile.detection.Factory;
import fiftyone.mobile.detection.webapp.Provider;
 
public class MobileListener extends Factory implements ServletContextListener{


    final private static Logger _logger = LoggerFactory.getLogger(MobileListener.class);
    private ThreadPoolExecutor _threadPool;
    
    @Override
    public void contextInitialized(final ServletContextEvent contextEvent) {
        try{    // first try to get premium data
            _threadPool = (ThreadPoolExecutor)Executors.newCachedThreadPool();
            
        initialize(
            contextEvent.getServletContext().getRealPath(Constants.DEFAULT_DATA_FILE_PATH),
            (String)null // instead of null, put your licence key there.
            ,_threadPool
                );  
        }
        catch(Exception ex) // failed, now get lite data
        {
            initialize();
        }
        contextEvent.getServletContext().setAttribute(Constants.FACTORY_KEY, this);
        _logger.info("51Degrees.mobi Listener initialised using key: "+Constants.FACTORY_KEY);        
    }
    /**
     * Calls the base factory to stop the auto update timer and destroy the
     * current provider.
     * @param contextEvent 
     */
    @Override
    public void contextDestroyed(final ServletContextEvent contextEvent) {
        _logger.info("Destroyed 51Degrees.mobi Listener");
        try {
            _threadPool.shutdown();
            super.destroy();
        } catch (Throwable ex) {
            _logger.warn(
                    "Exception destroying factory",
                    ex);
        }
    }
    /**
     * Creates a new version of the webapp provider with support for getting
     * results from HttpServletRequests.
     * @return 
     */
    @Override
    protected Provider createProvider() {
        return new Provider();
    }
    /**
     * Returns a provider of type webapp.Provider.
     * @return the active provider.
     */
    @Override
    public Provider getProvider() {
        return (Provider)super.getProvider();
    }
}