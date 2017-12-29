# MagicBox
使用magicbox可以简化在android中对状态保存和恢复的编码，让程序员从无聊的编码和取key名中摆脱出来。

## 为什么使用magicbox
在安卓开发中，我们会面临一些比较糟糕的情况，例如：低内存时处于后台的活动被系统回收了，这些情况下我们需要对活动的状态进行保存以期在下次页面重建时恢复这些状态。

但是，定义一系列的不重复的字符串常量作为bundle存储的key是一件麻烦且无聊的事情。

**使用MagicBox，您只需要使用注解进行标注，甚至忽略onSaveInstanceState and onRestoreInstanceState方法的重载**

## 集成

gradle 插件版本 3.0.0及其以上

```
implementation 'osp.leobert.android:lib-magicbox:1.1.0'
```

3.0.0以下

```
compile 'osp.leobert.android:lib-magicbox:1.1.0'
```

## 使用

### 启用log输出

```
 MagicBox.setLogEnable(true);
```

### 启用global delegate mode
通常来说，我们需要处理Activity和Fragment的状态保存（他们的一些依赖其实也需要处理），（本库还没有考虑是否要针对View进行一定的处理），而且保存和恢复的起点是从Activity开始的，或者说Instrumentation更为准确。

全局委托模式通过hook`Instrumentation`达到目的（让我们忽略onSaveInstanceState and onRestoreInstanceState方法的重载）

```
MagicBox.getInstance().globalDelegateMode();
```

但是，请注意！我仅仅是使用了Instrumentation的一个子类实例，（替换了ActivityThread中的成员变量），如果你的项目中也有类似的行为，这会导致bug。如果这个功能带来了相当多的麻烦，我会将其改变为静态代理的模式。


### KeepState注解

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

使用这个注解标注需要存储和恢复的field，
**例子 [here](https://github.com/leobert-lan/MagicBox/blob/master/code/app/src/main/java/osp/leobert/android/magicboxsample/SampleActivity.java)**

#### 注明类型或者利用推断
如果你关心效率问题，您可以直接注明类型，例如:

```
@KeepState(type = Type.BooleanArray)
boolean[] booleans1;
```

如果您注明的类型时错误的，就像下面的例子。错误信息会进行输出（开启日志功能时）

```
@KeepState(type = Type.Boolean)
Boolean[] booleans3;//an error type given
```

我还是推荐使用类型推测

#### 自定义的io组件
如果您有一个pojo类，您无法修改他的源码。您可以使用Gson之类的json库去序列化它。这是一个折中的办法，但并不太推荐，只有实在没辙的时候才可以用。

定义BoxReader:

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

定义BoxWriter:

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

定义 Component:

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

这样使用:

```
@KeepState(type = Type.Object,io = GsonBoxIOComponent.class)
TestObject testObject;
```

**注意：type仅可以申明为：Type.Object**

#### 需要穿透处理的情况

例如（包含但不限于）:

* fragment
* mvp模式中的presenter
* 一个不想实现Serializable or Parcelable接口的pojo.

```
 //    @KeepState(type = Type.Object,io = BoxIOComponent.DelegateIOComponent.class) or more simple：
    @KeepState(type = Type.Delegate)
    private Bar bar = testCase == CASE_NORMAL ? new Bar() : null;
```

**注意: Type.Delegate 是不可以被推断的，您必须注明**

##### 使用BeanFactory注解处理未初始化的问题
某些情况下，一些引用类型的成员变量是被初始化为null的，然后在某些情况下被重新实例化、指向另一个实例化过的引用等等。当save时，它不是null，那么就需要被恢复，这时候我们需要将其实例化。**注意，这些情况当前只会在Type.Delegate情况下出现。而且Activity的实例化已经由framework层处理了**。

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

**注意，Factory必须使用默认构造器，我知道这很粗暴。我计划添加一个配合Dagger2使用的组件来解决这些问题。**

### KeepSuperState 注解
在父类中具有一些需要保存的field是很常见的事情。

例如:

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

再如：

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

在类头上使用KeepSuperState注解声明父类中使用了KeepState注解标注的Field也是需要处理的。

在上面的例子中，FooBar类中的foobar就会被忽略，因为Foo并没与使用KeepSuperState来声明他的父类中的属性也需要处理。

--

尽情享用吧😁

**如果您喜欢MagicBox项目，欢迎star，提issue，甚至Fork它并为他提供支持**

# Licenese
[MIT](https://github.com/leobert-lan/MagicBox/blob/master/LICENSE)
