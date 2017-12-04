package osp.leobert.android.magicbox.type;

import java.lang.reflect.Field;

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
        System.out.println(field.getType());

        if (isArray) {
            return inferArray(field.getType());
        } else {
            return inferObject(field.getType());
        }
    }

    private static Type[] primitiveOrItsBoxedTypes = new Type[]{
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

    private static Type[] primitiveOrItsBoxedTypeArrays = new Type[]{
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
//            Types.SerializableArray
    };


//    Types.SparseParcelableArray,
//
//    Types.ParcelableArrayList,
//    Types.IntegerArrayList,
//    Types.StringArrayList,
//    Types.CharSequenceArrayList,

    private static Type inferArray(Class<?> fieldClz) {
        Type type = inferPrimitiveOrItsBoxedTypeArray(fieldClz);

        if (type.equals(Type.Infer)) {
            type = inferSimpleTypeArray(fieldClz);
        }

        if (type.equals(Type.Infer)) {
            type = inferImplArray(fieldClz);
        }

        return type;
    }

    private static Type inferPrimitiveOrItsBoxedTypeArray(Class<?> clz) {
        if (clz == null)
            return Type.Null;
        for (Type type : primitiveOrItsBoxedTypeArrays) {
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
    // infer something not array
    ///////////////////////////////////////////////////////////////////////////


    private static Type inferObject(Class<?> fieldClz) {
        Type type = inferPrimitiveOrItsBoxedTypes(fieldClz);

        if (type.equals(Type.Infer)) {
            type = inferSimpleType(fieldClz);
        }

        if (type.equals(Type.Infer)) {
            type = inferImpl(fieldClz);
        }

        return type;
    }


    private static Type inferPrimitiveOrItsBoxedTypes(Class<?> clz) {
        if (clz == null)
            return Type.Null;
        for (Type type : primitiveOrItsBoxedTypes) {
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
