# Book Scroller

Book Scroller 是一个适用于 Minecraft 1.20.1 Forge 的纯客户端模组，为原版书本阅读界面增加鼠标滚轮翻页功能。

## 功能

- 阅读手持书本或讲台上的书时，滚轮向上翻到上一页，向下翻到下一页。
- 按住 Shift 后滚轮翻页使用独立的加速页数。
- 普通滚轮和 Shift 滚轮的翻页数均可设置为 1-10，默认值分别为 1 和 3。
- 到达第一页或最后一页时会自动停在边界，不会产生越界错误。
- 配置入口位于 Forge 的“模组”列表中，保存后立即生效。

## 构建

项目使用 Gradle Wrapper。使用 JDK 17（开发环境可使用 JDK 21）执行：

```text
gradlew build
```

构建产物位于 `build/libs/`。

## 开发说明

模组 ID 为 `bksr`，Java 包名为 `com.hjsmc.bksr`。本项目不需要服务端安装。

代码由 GPT-5.6 Sol 协助完成。
