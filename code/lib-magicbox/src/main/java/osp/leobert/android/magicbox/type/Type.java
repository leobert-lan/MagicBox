package osp.leobert.android.magicbox.type;


import osp.leobert.android.magicbox.operators.BundleReader;
import osp.leobert.android.magicbox.operators.BundleWriter;
import osp.leobert.android.magicbox.operators.readers.BooleanArrayReader;
import osp.leobert.android.magicbox.operators.readers.BooleanReader;
import osp.leobert.android.magicbox.operators.readers.ByteArrayReader;
import osp.leobert.android.magicbox.operators.readers.ByteReader;
import osp.leobert.android.magicbox.operators.readers.CharArrayReader;
import osp.leobert.android.magicbox.operators.readers.CharReader;
import osp.leobert.android.magicbox.operators.readers.DefaultReader;
import osp.leobert.android.magicbox.operators.readers.DoubleArrayReader;
import osp.leobert.android.magicbox.operators.readers.FloatArrayReader;
import osp.leobert.android.magicbox.operators.readers.FloatReader;
import osp.leobert.android.magicbox.operators.readers.IntArrayReader;
import osp.leobert.android.magicbox.operators.readers.IntReader;
import osp.leobert.android.magicbox.operators.readers.LongArrayReader;
import osp.leobert.android.magicbox.operators.readers.LongReader;
import osp.leobert.android.magicbox.operators.readers.ParcelableArrayReader;
import osp.leobert.android.magicbox.operators.readers.ParcelableReader;
import osp.leobert.android.magicbox.operators.readers.SerializableReader;
import osp.leobert.android.magicbox.operators.readers.ShortArrayReader;
import osp.leobert.android.magicbox.operators.readers.ShortReader;
import osp.leobert.android.magicbox.operators.readers.SizeFReader;
import osp.leobert.android.magicbox.operators.readers.SizeReader;
import osp.leobert.android.magicbox.operators.readers.StringArrayReader;
import osp.leobert.android.magicbox.operators.readers.StringReader;
import osp.leobert.android.magicbox.operators.writers.BooleanArrayWriter;
import osp.leobert.android.magicbox.operators.writers.BooleanWriter;
import osp.leobert.android.magicbox.operators.writers.ByteArrayWriter;
import osp.leobert.android.magicbox.operators.writers.ByteWriter;
import osp.leobert.android.magicbox.operators.writers.CharArrayWriter;
import osp.leobert.android.magicbox.operators.writers.CharWriter;
import osp.leobert.android.magicbox.operators.writers.DefaultWriter;
import osp.leobert.android.magicbox.operators.writers.DoubleArrayWriter;
import osp.leobert.android.magicbox.operators.writers.DoubleWriter;
import osp.leobert.android.magicbox.operators.writers.FloatArrayWriter;
import osp.leobert.android.magicbox.operators.writers.FloatWriter;
import osp.leobert.android.magicbox.operators.writers.IntArrayWriter;
import osp.leobert.android.magicbox.operators.writers.IntWriter;
import osp.leobert.android.magicbox.operators.writers.LongArrayWriter;
import osp.leobert.android.magicbox.operators.writers.LongWriter;
import osp.leobert.android.magicbox.operators.writers.ParcelableArrayWriter;
import osp.leobert.android.magicbox.operators.writers.ParcelableWriter;
import osp.leobert.android.magicbox.operators.writers.SerializableWriter;
import osp.leobert.android.magicbox.operators.writers.ShortArrayWriter;
import osp.leobert.android.magicbox.operators.writers.ShortWriter;
import osp.leobert.android.magicbox.operators.writers.SizeFWriter;
import osp.leobert.android.magicbox.operators.writers.SizeWriter;
import osp.leobert.android.magicbox.operators.writers.StringArrayWriter;
import osp.leobert.android.magicbox.operators.writers.StringWriter;
import osp.leobert.android.magicbox.type.infer.InferType;
import osp.leobert.android.magicbox.type.infer.impl.Implementation;
import osp.leobert.android.magicbox.type.infer.impl.ImplementationArray;
import osp.leobert.android.magicbox.type.infer.impl.PrimitiveTypeArray;
import osp.leobert.android.magicbox.type.infer.impl.PrimitiveType;
import osp.leobert.android.magicbox.type.infer.impl.SimpleType;
import osp.leobert.android.magicbox.type.infer.impl.SimpleTypeArray;
import osp.leobert.android.magicbox.type.infer.impl.NegativeInfer;

/**
 * <p><b>Package:</b> osp.leobert.android.savedstate.type </p>
 * <p><b>Project:</b> MyJava </p>
 * <p><b>Classname:</b> Types </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/11/15.
 */

public enum Type {
    Infer(new NegativeInfer(),
            DefaultWriter.getInstance(),
            DefaultReader.getInstance()),

    Boolean(new PrimitiveType(boolean.class),
            BooleanWriter.getInstance(),
            BooleanReader.getInstance()),

    Byte(new PrimitiveType(byte.class),
            ByteWriter.getInstance(),
            ByteReader.getInstance()),

    Char(new PrimitiveType(char.class),
            CharWriter.getInstance(),
            CharReader.getInstance()),

    Short(new PrimitiveType(short.class),
            ShortWriter.getInstance(),
            ShortReader.getInstance()),

    Int(new PrimitiveType(int.class),
            IntWriter.getInstance(),
            IntReader.getInstance()),

    Long(new PrimitiveType(long.class),
            LongWriter.getInstance(),
            LongReader.getInstance()),

    Float(new PrimitiveType(float.class),
            FloatWriter.getInstance(),
            FloatReader.getInstance()),

    Double(new PrimitiveType(double.class),
            DoubleWriter.getInstance(),
            DefaultReader.getInstance()),

    String(new SimpleType(java.lang.String.class),
            StringWriter.getInstance(),
            StringReader.getInstance()),

    CharSequence(new Implementation(java.lang.CharSequence.class),
            CharWriter.getInstance(),
            CharReader.getInstance()),

    Parcelable(new Implementation(android.os.Parcelable.class),
            ParcelableWriter.getInstance(),
            ParcelableReader.getInstance()),

    Size(new SimpleType(android.util.Size.class),
            SizeWriter.getInstance(),
            SizeReader.getInstance()),

    SizeF(new SimpleType(android.util.SizeF.class),
            SizeFWriter.getInstance(),
            SizeFReader.getInstance()),

    Serializable(new Implementation(java.io.Serializable.class),
            SerializableWriter.getInstance(),
            SerializableReader.getInstance()),

    BooleanArray(new PrimitiveTypeArray(boolean.class),
            BooleanArrayWriter.getInstance(),
            BooleanArrayReader.getInstance()),

    ByteArray(new PrimitiveTypeArray(byte.class),
            ByteArrayWriter.getInstance(),
            ByteArrayReader.getInstance()),

    CharArray(new PrimitiveTypeArray(char.class),
            CharArrayWriter.getInstance(),
            CharArrayReader.getInstance()),

    ShortArray(new PrimitiveTypeArray(short.class),
            ShortArrayWriter.getInstance(),
            ShortArrayReader.getInstance()),
    IntArray(new PrimitiveTypeArray(int.class),
            IntArrayWriter.getInstance(),
            IntArrayReader.getInstance()),

    LongArray(new PrimitiveTypeArray(long.class),
            LongArrayWriter.getInstance(),
            LongArrayReader.getInstance()),

    FloatArray(new PrimitiveTypeArray(float.class),
            FloatArrayWriter.getInstance(),
            FloatArrayReader.getInstance()),

    DoubleArray(new PrimitiveTypeArray(double.class),
            DoubleArrayWriter.getInstance(),
            DoubleArrayReader.getInstance()),

    StringArray(new SimpleTypeArray(java.lang.String.class),
            StringArrayWriter.getInstance(),
            StringArrayReader.getInstance()),

    CharSequenceArray(new ImplementationArray(java.lang.CharSequence.class),
            CharArrayWriter.getInstance(),
            CharArrayReader.getInstance()),
    ParcelableArray(new ImplementationArray(android.os.Parcelable.class),
            ParcelableArrayWriter.getInstance(),
            ParcelableArrayReader.getInstance()),

    SparseParcelableArray(new NegativeInfer()),

    ParcelableArrayList(new NegativeInfer()),
    IntegerArrayList(new NegativeInfer()),
    StringArrayList(new NegativeInfer()),
    CharSequenceArrayList(new NegativeInfer()),

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

    public boolean canBeChecked() {
        return !(inferType instanceof NegativeInfer);
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
