/*
 * Licensed to Elasticsearch under one or more contributor
 * license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright
 * ownership. Elasticsearch licenses this file to you under
 * the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.elasticsearch.painless;

import org.elasticsearch.painless.Definition.Cast;
import org.elasticsearch.painless.Definition.Sort;
import org.elasticsearch.painless.Definition.Type;

/**
 * Used during the analysis phase to collect legal type casts and promotions
 * for type-checking and later to write necessary casts in the bytecode.
 */
public final class AnalyzerCaster {

    public static Cast getLegalCast(final Definition definition, final String location, final Type actual,
                                    final Type expected, final boolean explicit, final boolean internal) {
        if (actual.equals(expected)) {
            return null;
        }

        switch (actual.sort) {
            case BOOL:
                switch (expected.sort) {
                    case DEF:
                        return new Cast(actual, definition.getType("def"), explicit, false, false, true, false);
                    case OBJECT:
                    case BOOL_OBJ:
                        if (internal)
                            return new Cast(actual, actual, explicit, false, false, false, true);
                }

                break;
            case BYTE:
                switch (expected.sort) {
                    case SHORT:
                    case INT:
                    case LONG:
                    case FLOAT:
                    case DOUBLE:
                        return new Cast(actual, expected, explicit);
                    case CHAR:
                        if (explicit)
                            return new Cast(actual, expected, true);

                        break;
                    case DEF:
                        return new Cast(actual, definition.getType("def"), explicit, false, false, true, false);
                    case OBJECT:
                    case NUMBER:
                    case BYTE_OBJ:
                        if (internal)
                            return new Cast(actual, actual, explicit, false, false, false, true);

                        break;
                    case SHORT_OBJ:
                        if (internal)
                            return new Cast(actual, definition.getType("short"), explicit, false, false, false, true);

                        break;
                    case INT_OBJ:
                        if (internal)
                            return new Cast(actual, definition.getType("int"), explicit, false, false, false, true);

                        break;
                    case LONG_OBJ:
                        if (internal)
                            return new Cast(actual, definition.getType("long"), explicit, false, false, false, true);

                        break;
                    case FLOAT_OBJ:
                        if (internal)
                            return new Cast(actual, definition.getType("float"), explicit, false, false, false, true);

                        break;
                    case DOUBLE_OBJ:
                        if (internal)
                            return new Cast(actual, definition.getType("double"), explicit, false, false, false, true);

                        break;
                    case CHAR_OBJ:
                        if (explicit && internal)
                            return new Cast(actual, definition.getType("char"), explicit, false, false, false, true);

                        break;
                }

                break;
            case SHORT:
                switch (expected.sort) {
                    case INT:
                    case LONG:
                    case FLOAT:
                    case DOUBLE:
                        return new Cast(actual, expected, explicit);
                    case BYTE:
                    case CHAR:
                        if (explicit)
                            return new Cast(actual, expected, true);

                        break;
                    case DEF:
                        return new Cast(actual, definition.getType("def"), explicit, false, false, true, false);
                    case OBJECT:
                    case NUMBER:
                    case SHORT_OBJ:
                        if (internal)
                            return new Cast(actual, actual, explicit, false, false, false, true);

                        break;
                    case INT_OBJ:
                        if (internal)
                            return new Cast(actual, definition.getType("int"), explicit, false, false, false, true);

                        break;
                    case LONG_OBJ:
                        if (internal)
                            return new Cast(actual, definition.getType("long"), explicit, false, false, false, true);

                        break;
                    case FLOAT_OBJ:
                        if (internal)
                            return new Cast(actual, definition.getType("float"), explicit, false, false, false, true);

                        break;
                    case DOUBLE_OBJ:
                        if (internal)
                            return new Cast(actual, definition.getType("double"), explicit, false, false, false, true);

                        break;
                    case BYTE_OBJ:
                        if (explicit && internal)
                            return new Cast(actual, definition.getType("byte"), true, false, false, false, true);

                        break;
                    case CHAR_OBJ:
                        if (explicit && internal)
                            return new Cast(actual, definition.getType("char"), true, false, false, false, true);

                        break;
                }

                break;
            case CHAR:
                switch (expected.sort) {
                    case INT:
                    case LONG:
                    case FLOAT:
                    case DOUBLE:
                        return new Cast(actual, expected, explicit);
                    case BYTE:
                    case SHORT:
                        if (explicit)
                            return new Cast(actual, expected, true);

                        break;
                    case DEF:
                        return new Cast(actual, definition.getType("def"), explicit, false, false, true, false);
                    case OBJECT:
                    case NUMBER:
                    case CHAR_OBJ:
                        if (internal)
                            return new Cast(actual, actual, explicit, false, false, false, true);

                        break;
                    case STRING:
                        return new Cast(actual, definition.getType("String"), explicit, false, false, false, false);
                    case INT_OBJ:
                        if (internal)
                            return new Cast(actual, definition.getType("int"), explicit, false, false, false, true);

                        break;
                    case LONG_OBJ:
                        if (internal)
                            return new Cast(actual, definition.getType("long"), explicit, false, false, false, true);

                        break;
                    case FLOAT_OBJ:
                        if (internal)
                            return new Cast(actual, definition.getType("float"), explicit, false, false, false, true);

                        break;
                    case DOUBLE_OBJ:
                        if (internal)
                            return new Cast(actual, definition.getType("double"), explicit, false, false, false, true);

                        break;
                    case BYTE_OBJ:
                        if (explicit && internal)
                            return new Cast(actual, definition.getType("byte"), true, false, false, false, true);

                        break;
                    case SHORT_OBJ:
                        if (explicit && internal)
                            return new Cast(actual, definition.getType("short"), true, false, false, false, true);

                        break;
                }

                break;
            case INT:
                switch (expected.sort) {
                    case LONG:
                    case FLOAT:
                    case DOUBLE:
                        return new Cast(actual, expected, explicit);
                    case BYTE:
                    case SHORT:
                    case CHAR:
                        if (explicit)
                            return new Cast(actual, expected, true);

                        break;
                    case DEF:
                        return new Cast(actual, definition.getType("def"), explicit, false, false, true, false);
                    case OBJECT:
                    case NUMBER:
                    case INT_OBJ:
                        if (internal)
                            return new Cast(actual, actual, explicit, false, false, false, true);

                        break;
                    case LONG_OBJ:
                        if (internal)
                            return new Cast(actual, definition.getType("long"), explicit, false, false, false, true);

                        break;
                    case FLOAT_OBJ:
                        if (internal)
                            return new Cast(actual, definition.getType("float"), explicit, false, false, false, true);

                        break;
                    case DOUBLE_OBJ:
                        if (internal)
                            return new Cast(actual, definition.getType("double"), explicit, false, false, false, true);

                        break;
                    case BYTE_OBJ:
                        if (explicit && internal)
                            return new Cast(actual, definition.getType("byte"), true, false, false, false, true);

                        break;
                    case SHORT_OBJ:
                        if (explicit && internal)
                            return new Cast(actual, definition.getType("short"), true, false, false, false, true);

                        break;
                    case CHAR_OBJ:
                        if (explicit && internal)
                            return new Cast(actual, definition.getType("char"), true, false, false, false, true);

                        break;
                }

                break;
            case LONG:
                switch (expected.sort) {
                    case FLOAT:
                    case DOUBLE:
                        return new Cast(actual, expected, explicit);
                    case BYTE:
                    case SHORT:
                    case CHAR:
                    case INT:
                        if (explicit)
                            return new Cast(actual, expected, true);

                        break;
                    case DEF:
                        return new Cast(actual, definition.getType("def"), explicit, false, false, true, false);
                    case OBJECT:
                    case NUMBER:
                    case LONG_OBJ:
                        if (internal)
                            return new Cast(actual, actual, explicit, false, false, false, true);

                        break;
                    case FLOAT_OBJ:
                        if (internal)
                            return new Cast(actual, definition.getType("float"), explicit, false, false, false, true);

                        break;
                    case DOUBLE_OBJ:
                        if (internal)
                            return new Cast(actual, definition.getType("double"), explicit, false, false, false, true);

                        break;
                    case BYTE_OBJ:
                        if (explicit && internal)
                            return new Cast(actual, definition.getType("byte"), true, false, false, false, true);

                        break;
                    case SHORT_OBJ:
                        if (explicit && internal)
                            return new Cast(actual, definition.getType("short"), true, false, false, false, true);

                        break;
                    case CHAR_OBJ:
                        if (explicit && internal)
                            return new Cast(actual, definition.getType("char"), true, false, false, false, true);

                        break;
                    case INT_OBJ:
                        if (explicit && internal)
                            return new Cast(actual, definition.getType("int"), true, false, false, false, true);

                        break;
                }

                break;
            case FLOAT:
                switch (expected.sort) {
                    case DOUBLE:
                        return new Cast(actual, expected, explicit);
                    case BYTE:
                    case SHORT:
                    case CHAR:
                    case INT:
                    case FLOAT:
                        if (explicit)
                            return new Cast(actual, expected, true);

                        break;
                    case DEF:
                        return new Cast(actual, definition.getType("def"), explicit, false, false, true, false);
                    case OBJECT:
                    case NUMBER:
                    case FLOAT_OBJ:
                        if (internal)
                            return new Cast(actual, actual, explicit, false, false, false, true);

                        break;
                    case DOUBLE_OBJ:
                        if (internal)
                            return new Cast(actual, definition.getType("double"), explicit, false, false, false, true);

                        break;
                    case BYTE_OBJ:
                        if (explicit && internal)
                            return new Cast(actual, definition.getType("byte"), true, false, false, false, true);

                        break;
                    case SHORT_OBJ:
                        if (explicit && internal)
                            return new Cast(actual, definition.getType("short"), true, false, false, false, true);

                        break;
                    case CHAR_OBJ:
                        if (explicit && internal)
                            return new Cast(actual, definition.getType("char"), true, false, false, false, true);

                        break;
                    case INT_OBJ:
                        if (explicit && internal)
                            return new Cast(actual, definition.getType("int"), true, false, false, false, true);

                        break;
                    case LONG_OBJ:
                        if (explicit && internal)
                            return new Cast(actual, definition.getType("long"), true, false, false, false, true);

                        break;
                }

                break;
            case DOUBLE:
                switch (expected.sort) {
                    case BYTE:
                    case SHORT:
                    case CHAR:
                    case INT:
                    case FLOAT:
                        if (explicit)
                            return new Cast(actual, expected, true);

                        break;
                    case DEF:
                        return new Cast(actual, definition.getType("def"), explicit, false, false, true, false);
                    case OBJECT:
                    case NUMBER:
                    case DOUBLE_OBJ:
                        if (internal)
                            return new Cast(actual, actual, explicit, false, false, false, true);

                        break;
                    case BYTE_OBJ:
                        if (explicit && internal)
                            return new Cast(actual, definition.getType("byte"), true, false, false, false, true);

                        break;
                    case SHORT_OBJ:
                        if (explicit && internal)
                            return new Cast(actual, definition.getType("short"), true, false, false, false, true);

                        break;
                    case CHAR_OBJ:
                        if (explicit && internal)
                            return new Cast(actual, definition.getType("char"), true, false, false, false, true);

                        break;
                    case INT_OBJ:
                        if (explicit && internal)
                            return new Cast(actual, definition.getType("int"), true, false, false, false, true);

                        break;
                    case LONG_OBJ:
                        if (explicit && internal)
                            return new Cast(actual, definition.getType("long"), true, false, false, false, true);

                        break;
                    case FLOAT_OBJ:
                        if (explicit && internal)
                            return new Cast(actual, definition.getType("float"), true, false, false, false, true);

                        break;
                }

                break;
            case OBJECT:
            case NUMBER:
                switch (expected.sort) {
                    case BYTE:
                        if (internal && explicit)
                            return new Cast(actual, definition.getType("Byte"), true, false, true, false, false);

                        break;
                    case SHORT:
                        if (internal && explicit)
                            return new Cast(actual, definition.getType("Short"), true, false, true, false, false);

                        break;
                    case CHAR:
                        if (internal && explicit)
                            return new Cast(actual, definition.getType("Character"), true, false, true, false, false);

                        break;
                    case INT:
                        if (internal && explicit)
                            return new Cast(actual, definition.getType("Integer"), true, false, true, false, false);

                        break;
                    case LONG:
                        if (internal && explicit)
                            return new Cast(actual, definition.getType("Long"), true, false, true, false, false);

                        break;
                    case FLOAT:
                        if (internal && explicit)
                            return new Cast(actual, definition.getType("Float"), true, false, true, false, false);

                        break;
                    case DOUBLE:
                        if (internal && explicit)
                            return new Cast(actual, definition.getType("Double"), true, false, true, false, false);

                        break;
                }

                break;
            case BOOL_OBJ:
                switch (expected.sort) {
                    case BOOL:
                        if (internal)
                            return new Cast(actual, expected, explicit, true, false, false, false);

                        break;
                }

                break;
            case BYTE_OBJ:
                switch (expected.sort) {
                    case BYTE:
                    case SHORT:
                    case INT:
                    case LONG:
                    case FLOAT:
                    case DOUBLE:
                        if (internal)
                            return new Cast(actual, expected, explicit, true, false, false, false);

                        break;
                    case CHAR:
                        if (internal && explicit)
                            return new Cast(actual, expected, true, true, false, false, false);

                        break;
                }

                break;
            case SHORT_OBJ:
                switch (expected.sort) {
                    case SHORT:
                    case INT:
                    case LONG:
                    case FLOAT:
                    case DOUBLE:
                        if (internal)
                            return new Cast(actual, expected, explicit, true, false, false, false);

                        break;
                    case BYTE:
                    case CHAR:
                        if (internal && explicit)
                            return new Cast(actual, expected, true, true, false, false, false);

                        break;
                }

                break;
            case CHAR_OBJ:
                switch (expected.sort) {
                    case CHAR:
                    case INT:
                    case LONG:
                    case FLOAT:
                    case DOUBLE:
                        if (internal)
                            return new Cast(actual, expected, explicit, true, false, false, false);

                        break;
                    case BYTE:
                    case SHORT:
                        if (internal && explicit)
                            return new Cast(actual, expected, true, true, false, false, false);

                        break;
                }

                break;
            case INT_OBJ:
                switch (expected.sort) {
                    case INT:
                    case LONG:
                    case FLOAT:
                    case DOUBLE:
                        if (internal)
                            return new Cast(actual, expected, explicit, true, false, false, false);

                        break;
                    case BYTE:
                    case SHORT:
                    case CHAR:
                        if (internal && explicit)
                            return new Cast(actual, expected, true, true, false, false, false);

                        break;
                }

                break;
            case LONG_OBJ:
                switch (expected.sort) {
                    case LONG:
                    case FLOAT:
                    case DOUBLE:
                        if (internal)
                            return new Cast(actual, expected, explicit, true, false, false, false);

                        break;
                    case BYTE:
                    case SHORT:
                    case CHAR:
                    case INT:
                        if (internal && explicit)
                            return new Cast(actual, expected, true, true, false, false, false);

                        break;
                }

                break;
            case FLOAT_OBJ:
                switch (expected.sort) {
                    case FLOAT:
                    case DOUBLE:
                        if (internal)
                            return new Cast(actual, expected, explicit, true, false, false, false);

                        break;
                    case BYTE:
                    case SHORT:
                    case CHAR:
                    case INT:
                    case LONG:
                        if (internal && explicit)
                            return new Cast(actual, expected, true, true, false, false, false);

                        break;
                }

                break;
            case DOUBLE_OBJ:
                switch (expected.sort) {
                    case FLOAT:
                    case DOUBLE:
                        if (internal)
                            return new Cast(actual, expected, explicit, true, false, false, false);

                        break;
                    case BYTE:
                    case SHORT:
                    case CHAR:
                    case INT:
                    case LONG:
                        if (internal && explicit)
                            return new Cast(actual, expected, true, true, false, false, false);

                        break;
                }

                break;
            case DEF:
                switch (expected.sort) {
                    case BOOL:
                    case BYTE:
                    case SHORT:
                    case CHAR:
                    case INT:
                    case LONG:
                    case FLOAT:
                    case DOUBLE:
                            return new Cast(actual, expected, true, true, false, false, false);
                }

                break;
            case STRING:
                switch (expected.sort) {
                    case CHAR:
                        if (explicit)
                            return new Cast(actual, expected, true, false, false, false, false);

                        break;
                }

                break;
        }

        if (expected.clazz.isAssignableFrom(actual.clazz) ||
            ((explicit || expected.sort == Sort.DEF) && actual.clazz.isAssignableFrom(expected.clazz))) {
            return new Cast(actual, expected, explicit);
        } else {
            throw new ClassCastException("Error" + location + ": Cannot cast from [" + actual.name + "] to [" + expected.name + "].");
        }
    }

    public static Object constCast(final String location, final Object constant, final Cast cast) {
        final Sort fsort = cast.from.sort;
        final Sort tsort = cast.to.sort;

        if (fsort == tsort) {
            return constant;
        } else if (fsort == Sort.STRING && tsort == Sort.CHAR) {
            return Utility.StringTochar((String)constant);
        } else if (fsort == Sort.CHAR && tsort == Sort.STRING) {
            return Utility.charToString((char)constant);
        } else if (fsort.numeric && tsort.numeric) {
            final Number number;

            if (fsort == Sort.CHAR) {
                number = (int)(char)constant;
            } else {
                number = (Number)constant;
            }

            switch (tsort) {
                case BYTE:   return number.byteValue();
                case SHORT:  return number.shortValue();
                case CHAR:   return (char)number.intValue();
                case INT:    return number.intValue();
                case LONG:   return number.longValue();
                case FLOAT:  return number.floatValue();
                case DOUBLE: return number.doubleValue();
                default:
                    throw new IllegalStateException("Error" + location + ": Cannot cast from " +
                        "[" + cast.from.clazz.getCanonicalName() + "] to [" + cast.to.clazz.getCanonicalName() + "].");
            }
        } else {
            throw new IllegalStateException("Error" + location + ": Cannot cast from " +
                "[" + cast.from.clazz.getCanonicalName() + "] to [" + cast.to.clazz.getCanonicalName() + "].");
        }
    }

    public static Type promoteNumeric(final Definition definition, final Type from, final boolean decimal) {
        final Sort sort = from.sort;

        if (sort == Sort.DEF) {
            return definition.getType("def");
        } else if ((sort == Sort.DOUBLE) && decimal) {
            return definition.getType("double");
        } else if ((sort == Sort.FLOAT) && decimal) {
            return  definition.getType("float");
        } else if (sort == Sort.LONG) {
            return definition.getType("long");
        } else if (sort == Sort.INT || sort == Sort.CHAR || sort == Sort.SHORT || sort == Sort.BYTE) {
            return definition.getType("int");
        }

        return null;
    }

    public static Type promoteNumeric(final Definition definition, final Type from0, final Type from1, final boolean decimal) {
        final Sort sort0 = from0.sort;
        final Sort sort1 = from1.sort;

        if (sort0 == Sort.DEF || sort1 == Sort.DEF) {
            return definition.getType("def");
        }

        if (decimal) {
            if (sort0 == Sort.DOUBLE || sort1 == Sort.DOUBLE) {
                return definition.getType("double");
            } else if (sort0 == Sort.FLOAT || sort1 == Sort.FLOAT) {
                return definition.getType("float");
            }
        }

        if (sort0 == Sort.LONG || sort1 == Sort.LONG) {
            return definition.getType("long");
        } else if (sort0 == Sort.INT   || sort1 == Sort.INT   ||
                   sort0 == Sort.CHAR  || sort1 == Sort.CHAR  ||
                   sort0 == Sort.SHORT || sort1 == Sort.SHORT ||
                   sort0 == Sort.BYTE  || sort1 == Sort.BYTE) {
            return definition.getType("int");
        }

        return null;
    }

    public static Type promoteAdd(final Definition definition, final Type from0, final Type from1) {
        final Sort sort0 = from0.sort;
        final Sort sort1 = from1.sort;

        if (sort0 == Sort.STRING || sort1 == Sort.STRING) {
            return definition.getType("String");
        }

        return promoteNumeric(definition, from0, from1, true);
    }

    public static Type promoteXor(final Definition definition, final Type from0, final Type from1) {
        final Sort sort0 = from0.sort;
        final Sort sort1 = from1.sort;

        if (sort0.bool || sort1.bool) {
            return definition.getType("boolean");
        }

        return promoteNumeric(definition, from0, from1, false);
    }

    public static Type promoteEquality(final Definition definition, final Type from0, final Type from1) {
        final Sort sort0 = from0.sort;
        final Sort sort1 = from1.sort;

        if (sort0 == Sort.DEF || sort1 == Sort.DEF) {
            return definition.getType("def");
        }

        if (sort0.primitive && sort1.primitive) {
            if (sort0.bool && sort1.bool) {
                return definition.getType("boolean");
            }

            if (sort0.numeric && sort1.numeric) {
                return promoteNumeric(definition, from0, from1, true);
            }
        }

        return definition.getType("Object");
    }

    public static Type promoteConditional(final Definition definition,
                                          final Type from0, final Type from1, final Object const0, final Object const1) {
        if (from0.equals(from1)) {
            return from0;
        }

        final Sort sort0 = from0.sort;
        final Sort sort1 = from1.sort;

        if (sort0 == Sort.DEF || sort1 == Sort.DEF) {
            return definition.getType("def");
        }

        if (sort0.primitive && sort1.primitive) {
            if (sort0.bool && sort1.bool) {
                return definition.getType("boolean");
            }

            if (sort0 == Sort.DOUBLE || sort1 == Sort.DOUBLE) {
                return definition.getType("double");
            } else if (sort0 == Sort.FLOAT || sort1 == Sort.FLOAT) {
                return definition.getType("float");
            } else if (sort0 == Sort.LONG || sort1 == Sort.LONG) {
                definition.getType("long");
            } else {
                if (sort0 == Sort.BYTE) {
                    if (sort1 == Sort.BYTE) {
                        return definition.getType("byte");
                    } else if (sort1 == Sort.SHORT) {
                        if (const1 != null) {
                            final short constant = (short)const1;

                            if (constant <= Byte.MAX_VALUE && constant >= Byte.MIN_VALUE) {
                                return definition.getType("byte");
                            }
                        }

                        return definition.getType("short");
                    } else if (sort1 == Sort.CHAR) {
                        return definition.getType("int");
                    } else if (sort1 == Sort.INT) {
                        if (const1 != null) {
                            final int constant = (int)const1;

                            if (constant <= Byte.MAX_VALUE && constant >= Byte.MIN_VALUE) {
                                return definition.getType("byte");
                            }
                        }

                        return definition.getType("int");
                    }
                } else if (sort0 == Sort.SHORT) {
                    if (sort1 == Sort.BYTE) {
                        if (const0 != null) {
                            final short constant = (short)const0;

                            if (constant <= Byte.MAX_VALUE && constant >= Byte.MIN_VALUE) {
                                return definition.getType("byte");
                            }
                        }

                        return definition.getType("short");
                    } else if (sort1 == Sort.SHORT) {
                        return definition.getType("short");
                    } else if (sort1 == Sort.CHAR) {
                        return definition.getType("int");
                    } else if (sort1 == Sort.INT) {
                        if (const1 != null) {
                            final int constant = (int)const1;

                            if (constant <= Short.MAX_VALUE && constant >= Short.MIN_VALUE) {
                                return definition.getType("short");
                            }
                        }

                        return definition.getType("int");
                    }
                } else if (sort0 == Sort.CHAR) {
                    if (sort1 == Sort.BYTE) {
                        return definition.getType("int");
                    } else if (sort1 == Sort.SHORT) {
                        return definition.getType("int");
                    } else if (sort1 == Sort.CHAR) {
                        return definition.getType("char");
                    } else if (sort1 == Sort.INT) {
                        if (const1 != null) {
                            final int constant = (int)const1;

                            if (constant <= Character.MAX_VALUE && constant >= Character.MIN_VALUE) {
                                return definition.getType("byte");
                            }
                        }

                        return definition.getType("int");
                    }
                } else if (sort0 == Sort.INT) {
                    if (sort1 == Sort.BYTE) {
                        if (const0 != null) {
                            final int constant = (int)const0;

                            if (constant <= Byte.MAX_VALUE && constant >= Byte.MIN_VALUE) {
                                return definition.getType("byte");
                            }
                        }

                        return definition.getType("int");
                    } else if (sort1 == Sort.SHORT) {
                        if (const0 != null) {
                            final int constant = (int)const0;

                            if (constant <= Short.MAX_VALUE && constant >= Short.MIN_VALUE) {
                                return definition.getType("byte");
                            }
                        }

                        return definition.getType("int");
                    } else if (sort1 == Sort.CHAR) {
                        if (const0 != null) {
                            final int constant = (int)const0;

                            if (constant <= Character.MAX_VALUE && constant >= Character.MIN_VALUE) {
                                return definition.getType("byte");
                            }
                        }

                        return definition.getType("int");
                    } else if (sort1 == Sort.INT) {
                        return definition.getType("int");
                    }
                }
            }
        }

        // TODO: In the rare case we still haven't reached a correct promotion we need
        //       to calculate the highest upper bound for the two types and return that.
        //       However, for now we just return objectType that may require an extra cast.

        return definition.getType("Object");
    }

    private AnalyzerCaster() {}
}
