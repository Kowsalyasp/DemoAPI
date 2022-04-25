package com.tlc.crm.crud;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * Activator for the crud module.
 *
 * @author KowsalyaSP
 */
public class Activator implements BundleActivator {

    /**
     * Starts the bundle.
     *
     * @param context
     */
    public void start(BundleContext context) {
        System.out.println("crud");
    }

    /**
     * Stops the bundle.
     *
     * @param context
     */
    public void stop(BundleContext context) {
        System.out.println("Stopping the bundle");
    }
}


