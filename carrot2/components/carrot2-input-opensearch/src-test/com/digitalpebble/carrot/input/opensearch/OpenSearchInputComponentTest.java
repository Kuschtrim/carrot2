
/*
 * Carrot2 project.
 *
 * Copyright (C) 2002-2006, Dawid Weiss, Stanisław Osiński.
 * Portions (C) Contributors listed in "carrot2.CONTRIBUTORS" file.
 * All rights reserved.
 *
 * Refer to the full license file "carrot2.LICENSE"
 * in the root folder of the repository checkout or at:
 * http://www.carrot2.org/carrot2.LICENSE
 */

package com.digitalpebble.carrot.input.opensearch;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.dawidweiss.carrot.core.local.*;
import com.dawidweiss.carrot.core.local.impl.DocumentsConsumerOutputComponent;

/**
 * A test case for OpenSearch input component.
 * 
 * @author Dawid Weiss
 */
public class OpenSearchInputComponentTest extends junit.framework.TestCase {
    private final static Logger log = Logger.getLogger(OpenSearchInputComponentTest.class);

    public OpenSearchInputComponentTest(String s) {
        super(s);
    }

    /**
     * Sends a sample query to icerocket's OpenSearch feed.
     */
    public void testIceRocket() throws Exception {
        final LocalComponentFactory inputFactory = new LocalComponentFactory() {
            public LocalComponent getInstance() {
                return new OpenSearchInputComponent("http://blogs.icerocket.com/search?q={searchTerms}&rss=1&os=1&p={startPage}&n={count}");
            }
        };

        final LocalControllerBase controller = setUpController(inputFactory);
        final String query = "blog";
        final long start = System.currentTimeMillis();
        final HashMap params = new HashMap();
        params.put(LocalInputComponent.PARAM_REQUESTED_RESULTS, new Integer(50));
        final List results = (List) controller.query(
                "testprocess", query, params).getQueryResult();
        final long end = System.currentTimeMillis();
        log.info("Open Search query time: " + (end - start) + " ms.");

        // the results should contain some documents.
        assertEquals("Results acquired > 0?: " + results.size(), 50, results.size());
    }

	private LocalControllerBase setUpController(LocalComponentFactory inputFactory) throws Exception {
		LocalControllerBase controller;
		
        // Some output component
        LocalComponentFactory outputFactory = new LocalComponentFactory() {
            public LocalComponent getInstance() {
                return new DocumentsConsumerOutputComponent();
            }
        };

        // Register with the controller
        controller = new LocalControllerBase();
        controller.addLocalComponentFactory("output", outputFactory);
        controller.addLocalComponentFactory("input", inputFactory);

        // Create and register the process
        LocalProcessBase process = new LocalProcessBase();
        process.setInput("input");
        process.setOutput("output");
        controller.addProcess("testprocess", process);

        return controller;
	}
}
