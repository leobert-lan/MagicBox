package osp.leobert.android.magicbox.type;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;

/**
 * <p><b>Package:</b> osp.leobert.android.savedstate.type </p>
 * <p><b>Project:</b> MyJava </p>
 * <p><b>Classname:</b> TypeInterUtils </p>
 * <p><b>Description:</b> TODO </p>
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
            return inferArray(field.getType());
        } else {
            return inferObject(field.getType());
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

    private static Type[] simpleTypes = new Type[]{
            Type.String,
            Type.Size,
            Type.SizeF
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
                if (type.check((Class<?>) field.getGenericType()))
                    return type;
            } catch (ClassCastException e) {
                e.printStackTrace();
            }
        }
        return Type.Infer;
    }


    private static Type inferArray(Class<?> fieldClz) {
        Type type = inferPrimitiveTypeArray(fieldClz);

        if (type.equals(Type.Infer)) {
            type = inferSimpleTypeArray(fieldClz);
        }

        if (type.equals(Type.Infer)) {
            type = inferImplArray(fieldClz);
        }

        return type;
    }

    private static Type inferPrimitiveTypeArray(Class<?> clz) {
        if (clz == null)
            return Type.Null;
        for (Type type : primitiveTypeArrays) {
            if (type.check(clz))
                return type;
        }
        return Type.Infer;
    }

    private static Type inferSimpleTypeArray(Class<?> clz) {
        if (clz == null)
            return Type.Null;
        for (Type type : simpleTypeArrays) {
            if (type.check(clz))
                return type;
        }
        return Type.Infer;
    }

    private static Type inferImplArray(Class<?> clz) {
        if (clz == null)
            return Type.Null;
        for (Type type : implTypeArrays) {
            if (type.check(clz.getClass()))
                return type;
        }
        return Type.Infer;
    }


    ///////////////////////////////////////////////////////////////////////////
    // check something not array
    ///////////////////////////////////////////////////////////////////////////


    private static Type inferObject(Class<?> fieldClz) {
        //boxed type will be inferred to be impl of Serializable
        Type type = inferPrimitiveTypes(fieldClz);

        if (type.equals(Type.Infer)) {
            type = inferSimpleType(fieldClz);
        }

        if (type.equals(Type.Infer)) {
            type = inferImpl(fieldClz);
        }

        return type;
    }


    private static Type inferPrimitiveTypes(Class<?> clz) {
        if (clz == null)
            return Type.Null;
        for (Type type : primitiveTypes) {
            if (type.check(clz))
                return type;
        }
        return Type.Infer;
    }

    private static Type inferSimpleType(Class<?> clz) {
        if (clz == null)
            return Type.Null;
        for (Type type : simpleTypes) {
            if (type.check(clz))
                return type;
        }
        return Type.Infer;
    }

    private static Type inferImpl(Class<?> clz) {
        if (clz == null)
            return Type.Null;
        for (Type type : implTypes) {
            if (type.check(clz))
                return type;
        }
        return Type.Infer;
    }
}
