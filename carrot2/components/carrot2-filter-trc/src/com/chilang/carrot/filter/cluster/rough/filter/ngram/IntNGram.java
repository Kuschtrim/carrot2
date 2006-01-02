package com.chilang.carrot.filter.cluster.rough.filter.ngram;

import com.chilang.util.ArrayUtils;

public class IntNGram {
    protected int[] grams;

    protected int lazyHashCode = 17;
    protected boolean hashCalculated = false;
    public IntNGram(int[] source, int from, int length) {
        grams = new int[length];
        System.arraycopy(source,from, grams, 0, length);
    }

    public int[] getGrams() {
        return grams;
    }

    public String toString() {
        return ArrayUtils.toString(grams);
    }

    public boolean equals(Object object) {
        if (!(object instanceof IntNGram))
            return false;
        IntNGram o = (IntNGram)object;
        if (grams.length != o.grams.length)
            return false;
        for (int i=0; i<grams.length;i++) {
            if (grams[i] != o.grams[i])
                return false;
        }
        return true;
    }


    public int hashCode() {
        //calculate hash code as suggested in Effective Java [Bloch]
        if (hashCalculated)
            return lazyHashCode;
        for (int i=0; i < grams.length; i++) {
            lazyHashCode = 37 * lazyHashCode + grams[i];
        }
        hashCalculated = true;
        return lazyHashCode;
    }
}
