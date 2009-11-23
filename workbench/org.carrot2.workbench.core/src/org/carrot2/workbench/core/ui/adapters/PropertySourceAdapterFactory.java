
/*
 * Carrot2 project.
 *
 * Copyright (C) 2002-2009, Dawid Weiss, Stanisław Osiński.
 * All rights reserved.
 *
 * Refer to the full license file "carrot2.LICENSE"
 * in the root folder of the repository checkout or at:
 * http://www.carrot2.org/carrot2.LICENSE
 */

package org.carrot2.workbench.core.ui.adapters;


import java.util.Arrays;

import org.carrot2.core.*;
import org.carrot2.workbench.core.ui.SearchEditor;
import org.carrot2.workbench.core.ui.SearchResult;
import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.core.runtime.IAdapterManager;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.IPropertySource2;


/**
 * Adapters for transforming {@link SearchResult} into other classes.
 */
@SuppressWarnings("unchecked")
public final class PropertySourceAdapterFactory implements IAdapterFactory
{
    private final static Class [] adapted = new Class [] { 
        IPropertySource2.class, IPropertySource.class }; 

    /*
     * 
     */
    public Object getAdapter(Object adaptableObject, Class adapterType)
    {
        if (adaptableObject == null)
        {
            return null;
        }

        if (Arrays.asList(adapted).contains(adapterType))
        {
            if (adaptableObject instanceof SearchEditor)
            {
                adaptableObject = ((SearchEditor) adaptableObject).getSearchResult();
                if (adaptableObject == null) return null;
            }

            if (adaptableObject instanceof SearchResult)
            {
                return new SearchResultPropertySource((SearchResult) adaptableObject);
            }
            
            if (adaptableObject instanceof Cluster)
            {
                return new ClusterPropertySource((Cluster) adaptableObject);
            }
            
            if (adaptableObject instanceof Document)
            {
                return new DocumentPropertySource((Document) adaptableObject);
            }
        }

        return null;
    }

    /*
     * 
     */
    public Class [] getAdapterList()
    {
        return adapted;
    }

    /**
     * Register 
     */
    public static void register(IAdapterManager adapterManager)
    {
        final PropertySourceAdapterFactory factory = new PropertySourceAdapterFactory();
        adapterManager.registerAdapters(factory, SearchEditor.class);
        adapterManager.registerAdapters(factory, SearchResult.class);
        adapterManager.registerAdapters(factory, Cluster.class);
        adapterManager.registerAdapters(factory, Document.class);
    }
}
