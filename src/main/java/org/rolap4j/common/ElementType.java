package org.rolap4j.common;

/**
 * Created by andriantomanga on 09/05/16.
 */
public enum ElementType {

    /**
     *
     */
    MEASURE(1),


    /**
     *
     */
    CUBE(2),


    /**
     *
     */
    DIMENSION(3),


    /**
     *
     */
    LEVEL(4),


    /**
     *
     */
    PROPERTY(5),

    /**
     *
     */
    UNKNOWN(10);

   
    private int value;

    private ElementType(int value) {
        this.value = value;
    }

}
