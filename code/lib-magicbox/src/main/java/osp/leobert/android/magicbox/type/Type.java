package osp.leobert.android.magicbox.type;


import osp.leobert.android.magicbox.operators.BundleReader;
import osp.leobert.android.magicbox.operators.BundleWriter;
import osp.leobert.android.magicbox.operators.readers.BooleanReader;
import osp.leobert.android.magicbox.operators.writers.BooleanWriter;
import osp.leobert.android.magicbox.type.infer.InferType;
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
 * <p><b>Classname:</b> Types </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/11/15.
 */

public enum Type {
    Infer,

    Boolean(new PrimitiveOrItsBoxedType(java.lang.Boolean.class.getName(),
            boolean.class.getName()),
            BooleanWriter.getInstance(),
            BooleanReader.getInstance()),

    Byte(new PrimitiveOrItsBoxedType(java.lang.Byte.class.getName(), byte.class.getName())),
    Char(new PrimitiveOrItsBoxedType(Character.class.getName(), char.class.getName())),
    Short(new PrimitiveOrItsBoxedType(java.lang.Short.class.getName(), short.class.getName())),
    Int(new PrimitiveOrItsBoxedType(Integer.class.getName(), int.class.getName())),
    Long(new PrimitiveOrItsBoxedType(java.lang.Long.class.getName(), long.class.getName())),
    Float(new PrimitiveOrItsBoxedType(java.lang.Float.class.getName(), float.class.getName())),
    Double(new PrimitiveOrItsBoxedType(java.lang.Double.class.getName(), double.class.getName())),

    String(new SimpleType(java.lang.String.class.getName())),
    CharSequence(new Implementation(java.lang.CharSequence.class)),
    Parcelable(new Implementation(android.os.Parcelable.class)),
    Size(new SimpleType(android.util.Size.class.getName())),
    SizeF(new SimpleType(android.util.SizeF.class.getName())),
    Serializable(new Implementation(java.io.Serializable.class)),

    BooleanArray(new PrimitiveOrItsBoxedTypeArray(byte.class, java.lang.Byte.class)),

    ByteArray(new PrimitiveOrItsBoxedTypeArray(byte.class, java.lang.Byte.class)),
    CharArray(new PrimitiveOrItsBoxedTypeArray(char.class,Character.class)),
    ShortArray(new PrimitiveOrItsBoxedTypeArray(short.class, java.lang.Short.class)),
    IntArray(new PrimitiveOrItsBoxedTypeArray(int.class,Integer.class)),
    LongArray(new PrimitiveOrItsBoxedTypeArray(long.class,java.lang.Long.class)),
    FloatArray(new PrimitiveOrItsBoxedTypeArray(float.class,java.lang.Float.class)),
    DoubleArray(new PrimitiveOrItsBoxedTypeArray(double.class,java.lang.Double.class)),

    StringArray(new SimpleTypeArray(java.lang.String.class)),

    CharSequenceArray(new ImplementationArray(java.lang.CharSequence.class)),
    ParcelableArray(new ImplementationArray(android.os.Parcelable.class)),

    SparseParcelableArray(new UnSupportInfer()),

    ParcelableArrayList(new UnSupportInfer()),
    IntegerArrayList(new UnSupportInfer()),
    StringArrayList(new UnSupportInfer()),
    CharSequenceArrayList(new UnSupportInfer()),

    Null,
    Object;

    private InferType inferType;

    private BundleWriter bundleWriter;

    private BundleReader bundleReader;

    Type() {
    }

    Type(InferType inferType) {
        this.inferType = inferType;
    }

    Type(InferType inferType, BundleWriter bundleWriter, BundleReader bundleReader) {
        this.inferType = inferType;
        this.bundleWriter = bundleWriter;
        this.bundleReader = bundleReader;
    }

    public boolean check(Class clz) {
        if (clz == null)
            return Null.equals(this);

        return inferType.infer(clz);
    }

    public BundleWriter getBundleWriter() {
        return bundleWriter;
    }

    public BundleReader getBundleReader() {
        return bundleReader;
    }
}
