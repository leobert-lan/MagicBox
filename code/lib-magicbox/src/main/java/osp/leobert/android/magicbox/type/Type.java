package osp.leobert.android.magicbox.type;


import osp.leobert.android.magicbox.BoxIOComponent;
import osp.leobert.android.magicbox.io.BoxReader;
import osp.leobert.android.magicbox.io.BoxWriter;
import osp.leobert.android.magicbox.io.readers.BooleanArrayReader;
import osp.leobert.android.magicbox.io.readers.BooleanReader;
import osp.leobert.android.magicbox.io.readers.ByteArrayReader;
import osp.leobert.android.magicbox.io.readers.ByteReader;
import osp.leobert.android.magicbox.io.readers.CharArrayReader;
import osp.leobert.android.magicbox.io.readers.CharReader;
import osp.leobert.android.magicbox.io.readers.CharSequenceArrayListReader;
import osp.leobert.android.magicbox.io.readers.DefaultReader;
import osp.leobert.android.magicbox.io.readers.DoubleArrayReader;
import osp.leobert.android.magicbox.io.readers.FloatArrayReader;
import osp.leobert.android.magicbox.io.readers.FloatReader;
import osp.leobert.android.magicbox.io.readers.IntArrayReader;
import osp.leobert.android.magicbox.io.readers.IntReader;
import osp.leobert.android.magicbox.io.readers.IntegerArrayListReader;
import osp.leobert.android.magicbox.io.readers.LongArrayReader;
import osp.leobert.android.magicbox.io.readers.LongReader;
import osp.leobert.android.magicbox.io.readers.ParcelableArrayListReader;
import osp.leobert.android.magicbox.io.readers.ParcelableArrayReader;
import osp.leobert.android.magicbox.io.readers.ParcelableReader;
import osp.leobert.android.magicbox.io.readers.SerializableReader;
import osp.leobert.android.magicbox.io.readers.ShortArrayReader;
import osp.leobert.android.magicbox.io.readers.ShortReader;
import osp.leobert.android.magicbox.io.readers.SizeFReader;
import osp.leobert.android.magicbox.io.readers.SizeReader;
import osp.leobert.android.magicbox.io.readers.SparseParcelableArrayReader;
import osp.leobert.android.magicbox.io.readers.StringArrayListReader;
import osp.leobert.android.magicbox.io.readers.StringArrayReader;
import osp.leobert.android.magicbox.io.readers.StringReader;
import osp.leobert.android.magicbox.io.writers.BooleanArrayWriter;
import osp.leobert.android.magicbox.io.writers.BooleanWriter;
import osp.leobert.android.magicbox.io.writers.ByteArrayWriter;
import osp.leobert.android.magicbox.io.writers.ByteWriter;
import osp.leobert.android.magicbox.io.writers.CharArrayWriter;
import osp.leobert.android.magicbox.io.writers.CharSequenceArrayListWriter;
import osp.leobert.android.magicbox.io.writers.CharWriter;
import osp.leobert.android.magicbox.io.writers.DefaultWriter;
import osp.leobert.android.magicbox.io.writers.DoubleArrayWriter;
import osp.leobert.android.magicbox.io.writers.DoubleWriter;
import osp.leobert.android.magicbox.io.writers.FloatArrayWriter;
import osp.leobert.android.magicbox.io.writers.FloatWriter;
import osp.leobert.android.magicbox.io.writers.IntArrayWriter;
import osp.leobert.android.magicbox.io.writers.IntWriter;
import osp.leobert.android.magicbox.io.writers.IntegerArrayListWriter;
import osp.leobert.android.magicbox.io.writers.LongArrayWriter;
import osp.leobert.android.magicbox.io.writers.LongWriter;
import osp.leobert.android.magicbox.io.writers.ParcelableArrayListWriter;
import osp.leobert.android.magicbox.io.writers.ParcelableArrayWriter;
import osp.leobert.android.magicbox.io.writers.ParcelableWriter;
import osp.leobert.android.magicbox.io.writers.SerializableWriter;
import osp.leobert.android.magicbox.io.writers.ShortArrayWriter;
import osp.leobert.android.magicbox.io.writers.ShortWriter;
import osp.leobert.android.magicbox.io.writers.SizeFWriter;
import osp.leobert.android.magicbox.io.writers.SizeWriter;
import osp.leobert.android.magicbox.io.writers.SparseParcelableArrayWriter;
import osp.leobert.android.magicbox.io.writers.StringArrayListWriter;
import osp.leobert.android.magicbox.io.writers.StringArrayWriter;
import osp.leobert.android.magicbox.io.writers.StringWriter;
import osp.leobert.android.magicbox.type.infer.InferType;
import osp.leobert.android.magicbox.type.infer.impl.Implementation;
import osp.leobert.android.magicbox.type.infer.impl.ImplementationArray;
import osp.leobert.android.magicbox.type.infer.impl.NegativeInfer;
import osp.leobert.android.magicbox.type.infer.impl.PrimitiveType;
import osp.leobert.android.magicbox.type.infer.impl.PrimitiveTypeArray;
import osp.leobert.android.magicbox.type.infer.impl.SimpleType;
import osp.leobert.android.magicbox.type.infer.impl.SimpleTypeArray;

/**
 * <p><b>Package:</b> osp.leobert.android.savedstate.type </p>
 * <p><b>Project:</b> MyJava </p>
 * <p><b>Classname:</b> Types </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/11/15.
 */

public enum Type implements BoxIOComponent, ITypeCheck {
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

    SparseParcelableArray(new NegativeInfer(),
            SparseParcelableArrayWriter.getInstance(),
            SparseParcelableArrayReader.getInstance()),

    ParcelableArrayList(new NegativeInfer(),
            ParcelableArrayListWriter.getInstance(),
            ParcelableArrayListReader.getInstance()),

    IntegerArrayList(new NegativeInfer(),
            IntegerArrayListWriter.getInstance(),
            IntegerArrayListReader.getInstance()),

    StringArrayList(new NegativeInfer(),
            StringArrayListWriter.getInstance(),
            StringArrayListReader.getInstance()),

    CharSequenceArrayList(new NegativeInfer(),
            CharSequenceArrayListWriter.getInstance(),
            CharSequenceArrayListReader.getInstance()),

    Null(new NegativeInfer(),
            DefaultWriter.getInstance(),
            DefaultReader.getInstance()),

    Object(new InferType() {
        @Override
        public boolean infer(Class<?> clz) {
            return true;
        }
    }, DefaultWriter.getInstance(),
            DefaultReader.getInstance());


    private final InferType inferType;

    private final BoxWriter boxWriter;

    private final BoxReader boxReader;

    Type(InferType inferType, BoxWriter boxWriter, BoxReader boxReader) {
        this.inferType = inferType;
        this.boxWriter = boxWriter;
        this.boxReader = boxReader;
    }

    @Override
    public boolean canBeChecked() {
        return !(inferType instanceof NegativeInfer);
    }

    @Override
    public boolean check(Class clz) {
        if (clz == null)
            return Null.equals(this);

        return inferType.infer(clz);
    }

    @Override
    public BoxWriter getBoxWriter() {
        return boxWriter;
    }

    @Override
    public BoxReader getBoxReader() {
        return boxReader;
    }
}
