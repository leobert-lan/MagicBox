/*
 * MIT License
 *
 * Copyright (c) 2017 leobert-lan
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package osp.leobert.android.magicbox.type;

//import android.annotation.TargetApi;
import android.os.Build;
//import android.support.annotation.RequiresApi;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;

/**
 * <p><b>Package:</b> osp.leobert.android.savedstate.type </p>
 * <p><b>Project:</b> MyJava </p>
 * <p><b>Classname:</b> TypeInterUtils </p>
 * <p><b>Description:</b> TypeInterUtils </p>
 * Created by leobert on 2017/11/15.
 */

public class TypeInferUtils {
    public static Type infer(Field field) {
        if (field == null)
            return Type.Null;

        boolean isArray = field.getType().isArray();
//        System.out.println(field.getType());
        boolean isParameterized = field.getGenericType() instanceof ParameterizedType;
        Type ret = Type.Infer;
        if (isParameterized) { //may be some custom like: Foo<Bar>,be attention
            ret = inferCollection(field);
        }
        if (!ret.equals(Type.Infer))
            return ret;


        if (isArray) {
            return inferArray(field);
        } else {
            return inferObject(field);
        }
    }

    private static Type[] primitiveTypes = new Type[]{
            Type.Boolean,
            Type.Byte,
            Type.Char,
            Type.Short,
            Type.Int,
            Type.Long,
            Type.Float,
            Type.Double,
    };

//    @RequiresApi(21)
    private static Type[] simpleTypes = new Type[]{
            Type.String,
//            Type.Size,
//            Type.SizeF,
            Type.Bundle
    };

    private static Type[] simpleTypesV14 = new Type[]{
            Type.String,
            Type.Bundle
    };

    private static Type[] implTypes = new Type[]{
            Type.CharSequence,
            Type.Parcelable,
            Type.Serializable
    };

    private static Type[] primitiveTypeArrays = new Type[]{
            Type.BooleanArray,
            Type.ByteArray,
            Type.CharArray,
            Type.ShortArray,
            Type.IntArray,
            Type.LongArray,
            Type.FloatArray,
            Type.DoubleArray,
    };

    private static Type[] simpleTypeArrays = new Type[]{
            Type.StringArray
    };

    private static Type[] implTypeArrays = new Type[]{
            Type.CharSequenceArray,
            Type.ParcelableArray
    };

    private static Type[] genericCollections = new Type[]{
            Type.SparseParcelableArray,
            Type.ParcelableArrayList,
            Type.IntegerArrayList,
            Type.StringArrayList,
            Type.CharSequenceArrayList
    };

    ///////////////////////////////////////////////////////////////////////////
    // infer collections
    ///////////////////////////////////////////////////////////////////////////

    private static Type inferCollection(Field field) {
        if (field == null)
            return Type.Null;
        for (Type type : genericCollections) {
            try {
                if (type.check(field))
                    return type;
            } catch (ClassCastException e) {
                e.printStackTrace();
            }
        }
        return Type.Infer;
    }


    private static Type inferArray(Field field) {
        Type type = inferPrimitiveTypeArray(field);

        if (type.equals(Type.Infer)) {
            type = inferSimpleTypeArray(field);
        }

        if (type.equals(Type.Infer)) {
            type = inferImplArray(field);
        }

        return type;
    }

    private static Type inferPrimitiveTypeArray(Field field) {
        if (field == null)
            return Type.Null;
        for (Type type : primitiveTypeArrays) {
            if (type.check(field))
                return type;
        }
        return Type.Infer;
    }

    private static Type inferSimpleTypeArray(Field field) {
        if (field == null)
            return Type.Null;
        for (Type type : simpleTypeArrays) {
            if (type.check(field))
                return type;
        }
        return Type.Infer;
    }

    private static Type inferImplArray(Field field) {
        if (field == null)
            return Type.Null;
        for (Type type : implTypeArrays) {
            if (type.check(field))
                return type;
        }
        return Type.Infer;
    }


    ///////////////////////////////////////////////////////////////////////////
    // check something not array
    ///////////////////////////////////////////////////////////////////////////


    private static Type inferObject(Field field) {
        //boxed type will be inferred to be impl of Serializable
        Type type = inferPrimitiveTypes(field);

        if (type.equals(Type.Infer)) {
            type = inferSimpleType(field);
        }

        if (type.equals(Type.Infer)) {
            type = inferImpl(field);
        }

        return type;
    }


    private static Type inferPrimitiveTypes(Field field) {
        if (field == null)
            return Type.Null;
        for (Type type : primitiveTypes) {
            if (type.check(field))
                return type;
        }
        return Type.Infer;
    }

    private static Type inferSimpleTypeV14(Field field) {
        if (field == null)
            return Type.Null;
        for (Type type : simpleTypesV14) {
            if (type.check(field))
                return type;
        }
        return Type.Infer;
    }

    private static Type inferSimpleType(Field field) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            return inferSimpleTypeV14(field);
        }
        return inferSimpleTypeV21(field);
    }

//    @TargetApi(21)
    private static Type inferSimpleTypeV21(Field field) {
        if (field == null)
            return Type.Null;
        for (Type type : simpleTypes) {
            if (type.check(field))
                return type;
        }
        return Type.Infer;
    }

    private static Type inferImpl(Field field) {
        if (field == null)
            return Type.Null;
        for (Type type : implTypes) {
            if (type.check(field))
                return type;
        }
        return Type.Infer;
    }
}
