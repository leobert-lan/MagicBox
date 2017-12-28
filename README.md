# MagicBox
MagicBox is one library helps to keep and restore instances' state in android., which can help develop get out of the hell writing boring and mechanical codes.

## Why we use magicbox
In android developing, we need to save/restore the instances' states to handle some bad issues like: background activity destoryed when low memory.

It is boring to define a series of string constants working as the key in bundle for each field that needs saveing/restoring.

**With MagicBox, only you need to do is notating the fields with notaions, even ignoring the onSaveInstanceState and onRestoreInstanceState!**

## Integration

gradle plugin 3.0.0 and upper

```
implementation 'osp.leobert.android:lib-magicbox:1.1.0'
```

else

```
compile 'osp.leobert.android:lib-magicbox:1.1.0'
```

## usage

### enable log output

```
 MagicBox.setLogEnable(true);
```

### global delegate mode
Normally, we need to handle the states' saving and restoring in Activity or Fragment,(haven't desided to handle custom view or not), and the begining is activity or said `Instrumentation`.

use global delegate mode can hook the `Instrumentation`

```
MagicBox.getInstance().globalDelegateMode();
```

But with caution! I just using one instance of the Instrumentation's sub-class, if you have some work with Instrumentation, **bug will occur**. If we have much issues with this, i will change it to static proxy pattern in the future.



### notation KeepState

```
@KeepState
```

```
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface KeepState {

    Type type() default Type.Infer;

    /**
     * @return a class implement {@link BoxIOComponent} with a public empty constructor
     */
    Class<? extends BoxIOComponent> io() default BoxIOComponent.DefaultComponent.class;
}
```

use this notation to notate the fields that need to be save/restore,
**see sample at [here](https://github.com/leobert-lan/MagicBox/blob/master/code/app/src/main/java/osp/leobert/android/magicboxsample/SampleActivity.java)**

#### use type or just infer
If you are caring about the efficiency,you can given it's type, e.g.:

```
@KeepState(type = Type.BooleanArray)
boolean[] booleans1;
```

and, if you given an error type as follow, the log will output the error info

```
@KeepState(type = Type.Boolean)
Boolean[] booleans3;//an error type given
```

However, I like the type infer feature.

#### use custom IO component
If you have one POJO in library that nothing you can do with it, you can use some libraries like Gson to serialise it to json string. This is one way to handle the trouble but not strongly suggested.

Define your custom Reader:

```

public class CustomBoxReader extends BaseCustomBoxReader {
    private static CustomBoxReader instance = null;

    private CustomBoxReader() {
        // single
    }

    public static CustomBoxReader getInstance() {
        if (instance == null)
            instance = new CustomBoxReader();
        return instance;
    }

    @Override
    protected Object fetchAndParse(Bundle bundle, String bundleKey, Class<?> type) {
        String s = bundle.getString(bundleKey);
        Gson gson = new Gson();

        return gson.fromJson(s,type);
    }

}

```

And Writer:

```
public class CustomBoxWriter extends BaseCustomBoxWriter {
    private static CustomBoxWriter instance = null;

    private CustomBoxWriter() {
        // single
    }

    public static CustomBoxWriter getInstance() {
        if (instance == null)
            instance = new CustomBoxWriter();
        return instance;
    }

    @Override
    protected void parseAndSave(Bundle bundle, String bundleKey, Object o) {
        Gson gson = new Gson();
        String _s = gson.toJson(o);

        bundle.putString(bundleKey, _s);
    }
}
```

then Component:

```

public class GsonBoxIOComponent implements BoxIOComponent {
    @NonNull
    @Override
    public BoxWriter getBoxWriter() {
        return CustomBoxWriter.getInstance();
    }

    @NonNull
    @Override
    public BoxReader getBoxReader() {
        return CustomBoxReader.getInstance();
    }
}
```

use it:

```
@KeepState(type = Type.Object,io = GsonBoxIOComponent.class)
TestObject testObject;
```

**remember to declare the type as Type.Object**

#### Something need to be penetrated

e.g.:

* fragment
* presenter in mvp structure
* one pojo but you don't want to implement Serializable or Parcelable interface.

```
 //    @KeepState(type = Type.Object,io = BoxIOComponent.DelegateIOComponent.class) or more simple：
    @KeepState(type = Type.Delegate)
    private Bar bar = testCase == CASE_NORMAL ? new Bar() : null;
```

**remember: Type.Delegate cannot be infered!**

##### use BeanFactory notation to solve null
In some cases, the variable field is initialized as null, and changes to something then. If it is not null when saving, the field will be restored, so we need to instantiate it.

e.g.:

```
@BeanFactory(factory = Bar.BarFactory.class)
public class Bar extends Foo {
    @KeepState
    private String bar;

    public String getBar() {
        return bar;
    }

    public void setBar(String bar) {
        this.bar = bar;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    public static final class BarFactory extends Factory<Bar> {

        @Override
        public Bar getBean() {
            return new Bar();
        }
    }
}
```

**With caution**: the Factory must have default constructor. I know it is crude. I plan to add one component to using Dagger2 in the future.

### notation KeepSuperState
It is normal to have some field in the super class.

e.g.:

```
@KeepSuperState
public class NestedDemo1Activity extends ParentActivity {
//...
}

public abstract class ParentActivity extends PP {
    @KeepState
    private String foo;

    // super foofoo will be ignored because not annotated with KeepSuperState yet.

    public String getFoo() {
        return foo;
    }

    public void setFoo(String foo) {
        this.foo = foo;
    }
}

```

or

```
@KeepSuperState
@BeanFactory(factory = Bar.BarFactory.class)
public class Bar extends Foo {
    @KeepState
    private String bar;

    public String getBar() {
        return bar;
    }

    public void setBar(String bar) {
        this.bar = bar;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    public static final class BarFactory extends Factory<Bar> {

        @Override
        public Bar getBean() {
            return new Bar();
        }
    }
}

public class Foo extends FooBar{
    @KeepState
    private String foo;

    public String getFoo() {
        return foo;
    }

    public void setFoo(String foo) {
        this.foo = foo;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}

public class FooBar {
    @KeepState
    private String foobar;

    public String getFoobar() {
        return foobar;
    }

    public void setFoobar(String foobar) {
        this.foobar = foobar;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
```

use KeepSuperState to declare fields with KeepState notation in super class is needed to be handle.

In the upper case, foobar in FooBar will be ignored because Foo hasn't be notated with KeepSuperState.


--

Just enjoy it😁

**If you love MagicBox, giving a star to support! Noting issues and pull-request are absolutely welcome！**
# Licenese
[MIT](https://github.com/leobert-lan/MagicBox/blob/master/LICENSE)
