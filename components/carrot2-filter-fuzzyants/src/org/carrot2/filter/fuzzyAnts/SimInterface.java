
/*
 * Carrot2 project.
 *
 * Copyright (C) 2002-2007, Dawid Weiss, Stanisław Osiński.
 * Portions (C) Contributors listed in "carrot2.CONTRIBUTORS" file.
 * All rights reserved.
 *
 * Refer to the full license file "carrot2.LICENSE"
 * in the root folder of the repository checkout or at:
 * http://www.carrot2.org/carrot2.LICENSE
 */

package org.carrot2.filter.fuzzyAnts;


import java.util.*;


/**
 * Possible extensions include "DocumentSet"
 *
 * @author Steven Schockaert
 */
public interface SimInterface
{
    public double similarity(int i1, int i2);


    public double leadervalue(int i);


    public Set getIndices();


    public int getNumber();
}