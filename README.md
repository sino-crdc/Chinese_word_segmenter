### 中文分词器

主要用于中法创研中心的“文本情感分析器——多功能应用接口”

该中文分词器基于word分词器

#### 用途特点
1. 中文分词
2. 可排除停止词
3. 多种分词算法
4. 支持分布式
5. 词性标注
6. 同义、反义、拼音标注
7. 词语境计算
8. 相关词
9. 词频统计
10. 文本相似度(10种算法)
11. 判定句子是有意义的人话的可能性
12. 支持应用统计语言模型(即隐含马尔可夫)(二元模型、三元模型)

#### 部分文件功能

1. word分词器的API可打开 word-1.3 API.html查看
2. 运行demo-word查看分词效果
3. 运行evaluation对分词效果进行评估(评估结果位于target/evaluation目录)
4. 通过计算词的语境来获得相关词
    - 用word分词内置语料库：运行word分词项目根目录下的脚本 demo-word-vector-corpus
    - 使用自己的文本内容：运行word分词项目根目录下的脚本 
   demo-word-vector-file
5. 运行sentence-identify判定句子是有意义的人话的可能性
6. pom.xml: Project Object Model, 内含项目依赖版本
7. 项目部署(Build)后，会出现data文件夹，内可放置分词器所需的外部数据源/目标; logs为项目运行的日志

#### 编辑方法
1. 将项目clone到自己的项目中
2. 修改项目classpath，添加：
    - target/word-1.3.jar;
    - target/dependency/slf4j-api-1.6.4.jar;
    - target/dependency/logback-classic-0.9.28.jar;
    - target/dependency/logback-core-0.9.28.jar
3. 修改项目的运行参数虚拟机选项: 在JVM Options添加-Xms1200m -Xmx1200m
4. 修改src中的代码，对word分词器进行操作
5. 需要对word分词器进行修改时候：target
    - 一般不修改依赖，生成文件和maven的配置
    - 方法一(不推荐): 反编译修改classes或者word-1.3.jar,再编译覆盖
    - 方法二: 修改word-1.3-sources.jar然后编译覆盖上面的文件
    
#### 详细
详见链接 [ysc/word: Java分布式中文分词组件 - word分词](https://github.com/ysc/word)