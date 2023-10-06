## Leanback页面构建主要类
- `BaseGridView`：继承自`RecyclerView`，重写所有焦点逻辑，Leanback页面根布局容器
- `HorizontalGridView`：继承自`BaseGridView`，提供水平布局能力
- `VerticalGridView`：继承自`BaseGridView`，提供垂直布局能力
- `ArrayObjectAdapter`：继承自`ObjectAdapter`，内部可包含数据和视图结构内容信息
- `ItemBridgeAdapter`：填充至`BaseGridView`的适配器，继承`RecyclerView.Adapter`
- `PresenterSelector`：根据不同的Item类型提供不同的布局视图创建和数据绑定
- `Presenter`：提供视图创建及数据绑定，类似`RecyclerView.Adapter`的功能
## RecyclerView对比HorizontalGridView
RecyclerView和HorizontalGridView都是Android平台上的列表控件，它们的区别在于HorizontalGridView是RecyclerView的子类，它是针对TV端再次封装的RecyclerView。HorizontalGridView提供了一些TV端特有的属性和方法，例如focusOutFront、focusOutEnd、setFocusScrollStrategy等，用来处理焦点切换和滚动效果HorizontalGridView使用Presenter来绑定和展示子Item，而RecyclerView使用Adapter来实现这个功能。Presenter和Adapter有相似的方法，但Presenter将数据刷新的功能抽象到了ObjectAdapter中。
![HorizontalGridView](https://cdn.nlark.com/yuque/0/2023/svg/28049321/1696583217004-7788b25a-384c-4706-8c70-12e5389e234a.svg#clientId=u8d67f31d-b7c0-4&from=ui&id=u408b5d02&originHeight=451&originWidth=331&originalType=binary&ratio=2&rotation=0&showTitle=true&size=4264&status=done&style=none&taskId=u56d9b52b-53c8-4b24-b121-251175a4045&title=HorizontalGridView "HorizontalGridView")
Presenter中定义了ViewHolder以及onCreateViewHolder、onBindViewHolder等方法，可以通过自定义PresenterSelector来实现针对不同类型的Item采用不同布局的效果。在使用RecyclerView时我们把数据列表传入Adapter中，但是在这里需要把数据列表传给ArrayObjectAdapter，有时候我们还需要在ArrayObjectAdapter嵌套ListRow等。
![RecyclerView](https://cdn.nlark.com/yuque/0/2023/svg/28049321/1696583216970-a4125f29-3712-4f3b-915f-2df084bee826.svg#clientId=u8d67f31d-b7c0-4&from=ui&id=u244dac82&originHeight=211&originWidth=331&originalType=binary&ratio=2&rotation=0&showTitle=true&size=2986&status=done&style=none&taskId=u2d986d55-34b7-456a-937c-b5231b9547b&title=RecyclerView "RecyclerView")
## LeanbackDemo
本项目主要功能有切换不同城市，显示该城市未来15天逐小时的天气预报，用到了Leanback、Retrofit、Gson等库，项目地址：[https://github.com/IF-Ding/LeanbackDemo](https://github.com/IF-Ding/LeanbackDemo)
![1.jpg](https://cdn.nlark.com/yuque/0/2023/jpeg/28049321/1696598931563-79a45d64-465b-4ee0-9433-bb428bf23d6b.jpeg#averageHue=%23a8a8a8&clientId=u8d67f31d-b7c0-4&from=ui&id=ua2f92221&originHeight=1906&originWidth=3000&originalType=binary&ratio=2&rotation=0&showTitle=false&size=158319&status=done&style=none&taskId=u7d6f9ea3-326e-4f6c-a3ce-1ee920981db&title=)![2.jpg](https://cdn.nlark.com/yuque/0/2023/jpeg/28049321/1696598931597-8d601d6a-76a1-41da-97c8-f59fdc30e5d2.jpeg#averageHue=%23ababab&clientId=u8d67f31d-b7c0-4&from=ui&id=u4f3a6932&originHeight=1906&originWidth=3000&originalType=binary&ratio=2&rotation=0&showTitle=false&size=159696&status=done&style=none&taskId=u1a07a5f8-ea1a-496b-8744-c84ccc21410&title=)
顶部的城市选择栏由HorizontalGridView实现，下方的天气信息使用VerticalGridView，其中每一行都是一个ListRow，ListRow包含一个日期Item和若干温度Item。
可以通过`android:nextFocusUp`、`android:nextFocusDown`等属性设置焦点的切换规则，在不同组件间切换焦点时需要将`app:focusOutEnd`和`app:focusOutFront`设置为true。
