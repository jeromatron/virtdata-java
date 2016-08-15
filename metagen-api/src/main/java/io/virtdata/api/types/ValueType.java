package io.virtdata.api.types;/*
*   Copyright 2016 jshook
*   Licensed under the Apache License, Version 2.0 (the "License");
*   you may not use this file except in compliance with the License.
*   You may obtain a copy of the License at
*
*       http://www.apache.org/licenses/LICENSE-2.0
*
*   Unless required by applicable law or agreed to in writing, software
*   distributed under the License is distributed on an "AS IS" BASIS,
*   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*   See the License for the specific language governing permissions and
*   limitations under the License.
*/

import java.util.Comparator;

/**
 * Capture preference for types, favoring more efficient types for generation over others.
 */
public enum ValueType implements Comparator<ValueType> {
    LONG(long.class, 1),
    INT(int.class, 2),
    FLOAT(float.class, 3),
    DOUBLE(double.class, 4),
    BOOLEAN(boolean.class, 5),
    BYTE(byte.class, 6),
    STRING(String.class, 7),
    OBJECT(Object.class, 8);

    private final Class<?> clazz;
    private int precedence;

    ValueType(Class<?> clazz, int precedence) {
        this.clazz = clazz;
        this.precedence = precedence;
    }

    public static ValueType valueOf(Class<?> clazz) {
        for (ValueType valueType : ValueType.values()) {
            if (valueType.clazz.isAssignableFrom(clazz)) {
                return valueType;
            }
        }
        throw new RuntimeException("Unable to find a matching value type for " + clazz);
    }

    @Override
    public int compare(ValueType o1, ValueType o2) {
        return Integer.compare(o1.precedence, o2.precedence);
    }
}
