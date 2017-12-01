package osp.leobert.android.magicbox.type;

import osp.leobert.android.magicbox.type.infer.impl.Implementation;
import osp.leobert.android.magicbox.type.infer.impl.ImplementationArray;
import osp.leobert.android.magicbox.type.infer.impl.PrimitiveOrItsBoxedType;
import osp.leobert.android.magicbox.type.infer.impl.PrimitiveOrItsBoxedTypeArray;
import osp.leobert.android.magicbox.type.infer.impl.SimpleType;
import osp.leobert.android.magicbox.type.infer.impl.SimpleTypeArray;
import osp.leobert.android.magicbox.type.infer.impl.UnSupportInfer;

/**
 * <p><b>Package:</b> osp.leobert.android.savedstate.type </p>
 * <p><b>Project:</b> MyJava </p>
 * <p><b>Classname:</b> TypeInterUtils </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/11/15.
 */

public class TypeInferUtils {
    public static Types infer(Object obj) {
        if (obj == null)
            return Types.Null;

        boolean isArray = obj.getClass().isArray();

        if (isArray) {
            return inferArray(obj);
        } else {
            return inferObject(obj);
        }
    }

    private static Types[] primitiveOrItsBoxedTypes = new Types[]{
            Types.Boolean,
            Types.Byte,
            Types.Char,
            Types.Short,
            Types.Int,
            Types.Long,
            Types.Float,
            Types.Double,
    };

    private static Types[] simpleTypes = new Types[]{
            Types.String,
            Types.Size,
            Types.SizeF
    };

    private static Types[] implTypes = new Types[]{
            Types.CharSequence,
            Types.Parcelable,
            Types.Serializable
    };

    private static Types[] primitiveOrItsBoxedTypeArrays = new Types[]{
            Types.BooleanArray,
            Types.ByteArray,
            Types.CharArray,
            Types.ShortArray,
            Types.IntArray,
            Types.LongArray,
            Types.FloatArray,
            Types.DoubleArray,
    };

    private static Types[] simpleTypeArrays = new Types[]{
            Types.StringArray
    };

    private static Types[] implTypeArrays = new Types[]{
            Types.CharSequenceArray,
            Types.ParcelableArray
//            Types.SerializableArray
    };


//    Types.SparseParcelableArray,
//
//    Types.ParcelableArrayList,
//    Types.IntegerArrayList,
//    Types.StringArrayList,
//    Types.CharSequenceArrayList,

    private static Types inferArray(Object obj) {
        Types type = inferPrimitiveOrItsBoxedTypeArray(obj);

        if (type.equals(Types.Infer)) {
            type = inferSimpleTypeArray(obj);
        }

        if (type.equals(Types.Infer)) {
            type = inferImplArray(obj);
        }

        return type;
    }

    private static Types inferPrimitiveOrItsBoxedTypeArray(Object obj) {
        if (obj == null)
            return Types.Null;
        for (Types type : primitiveOrItsBoxedTypeArrays) {
            if (type.check(obj.getClass()))
                return type;
        }
        return Types.Infer;
    }

    private static Types inferSimpleTypeArray(Object obj) {
        if (obj == null)
            return Types.Null;
        for (Types type : simpleTypeArrays) {
            if (type.check(obj.getClass()))
                return type;
        }
        return Types.Infer;
    }

    private static Types inferImplArray(Object obj) {
        if (obj == null)
            return Types.Null;
        for (Types type : implTypeArrays) {
            if (type.check(obj.getClass()))
                return type;
        }
        return Types.Infer;
    }


    ///////////////////////////////////////////////////////////////////////////
    // infer something not array
    ///////////////////////////////////////////////////////////////////////////


    private static Types inferObject(Object obj) {
        Types type = inferPrimitiveOrItsBoxedTypes(obj);

        if (type.equals(Types.Infer)) {
            type = inferSimpleType(obj);
        }

        if (type.equals(Types.Infer)) {
            type = inferImpl(obj);
        }

        return type;
    }


    private static Types inferPrimitiveOrItsBoxedTypes(Object obj) {
        if (obj == null)
            return Types.Null;
        for (Types type : primitiveOrItsBoxedTypes) {
            if (type.check(obj.getClass()))
                return type;
        }
        return Types.Infer;
    }

    private static Types inferSimpleType(Object obj) {
        if (obj == null)
            return Types.Null;
        for (Types type : simpleTypes) {
            if (type.check(obj.getClass()))
                return type;
        }
        return Types.Infer;
    }

    private static Types inferImpl(Object obj) {
        if (obj == null)
            return Types.Null;
        for (Types type : implTypes) {
            if (type.check(obj.getClass()))
                return type;
        }
        return Types.Infer;
    }
}
