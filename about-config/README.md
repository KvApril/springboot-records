#### 说明
Spring Boot使用application.properties作为全局的配置文件。  
application.properties路径: src/main/resources  
在此全局配置文件中可以对一些默认的配置值进行修改

#### 自定义属性
1. application.properties提供了对自定义属性的支持，可以在此文件定义一些需要的常量数据。  
```
com.kv.name="喝着火车开可乐的炮姐"
com.kv.say="hola! hola! 这个世界太可怕"
```
2. 在需要使用的地方可以通过注解@Value(value="#{com.kv.name}"),绑定该值到你定义的属性上面
```
@RestController
public class HelloController {

    //通过注解注入属性值
    @Value("${com.kv.name}")
    private String name;

    @Value("${com.kv.say}")
    private String say;

    @RequestMapping("/hello")
    public String sayWhat(){
        return name+" say: " + say;
    }
}
```
如果你遇到了返回乱码：http://note.youdao.com/noteshare?id=6922f8e5d672ac43b1861b6dc81176d5

3.启动项目,访问http://localhost:8080/hello  
可以看到打印出了："kv-开车可乐喝火车" say: "hola! hola! 这个世界太可怕!"  


#### 通过配置类绑定自定义属性
有时候，你可能会觉得属性太多，通过@Value注解添加会不好管理，这时候可以创建一个bean对象，来进行绑定。  

1. application.properties提供了对自定义属性的支持，可以在此文件定义一些需要的常量数据。  
```
com.kv.name="喝着火车开可乐的炮姐"
com.kv.say="hola! hola! 这个世界太可怕"
```

2. 创建一个ConfigBean.java类，定义一些属性  
@Configuration: 声明改类是一个配置类  
@ConfigurationProperties(prefix="com.kv"): 使用前缀说明使用的值范围 
```
@Configuration
@ConfigurationProperties(prefix = "com.kv")
public class configBean {
    private String name;
    private String say;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSay() {
        return say;
    }

    public void setSay(String say) {
        this.say = say;
    }
}
```

3.在Controller中引入configBean
``` 
@RestController
public class HelloTwoController {
    @Autowired
    ConfigBean configBean;

    @RequestMapping("/hello_two")
    public String helloTwo(){
        return configBean.getName()+" say: "+configBean.getSay();
    }
}
```
4.启动应用，访问：http://localhost:8080/hello_two


#### 使用自定义配置文件
如果要定义的值比较多，可以创建一个新的properties文件，来单独管理。  
文件路径和application.properties相同： src/main/resources

1. 新建一个values.properties文件
``` 
com.kving.name="kv-开车可乐喝火车"
com.kving.say="hola! hola! 你个小机灵鬼儿!"
```
2. 新建一个bean类:ConfigValueBean，来映射新生命的常量值
@Configuration: 声明改类是一个配置类  
@ConfigurationProperties(prefix="com.kv"): 使用前缀说明使用的值范围  
@PropertySource("classpath:values.properties"): 指明配置文件的位置

``` 
@Configuration
@ConfigurationProperties(prefix = "com.kving")
@PropertySource("classpath:values.properties")
public class ConfigValueBean {
    private String name;
    private String say;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSay() {
        return say;
    }

    public void setSay(String say) {
        this.say = say;
    }
}
```
3. 在controller中引入ConfigValueBean
``` 

@RestController
public class HelloThreeController {
    @Autowired
    ConfigValueBean configValueBean;

    @RequestMapping("/hello_three")
    public String helloThree(){
        return configValueBean.getName()+" say: "+configValueBean.getSay();
    }
}
```
4. 启动项目，访问: http://localhost:8080/hello_three


#### 配置随机值
在配置文件中通过${random}可以生成不同类型的随机值。
1. 创建一个random.properties,文件路径：src/main/resources
``` 
kv.random.age=${random.int}
kv.random.uuid=${random.uuid}
kv.random.range=${random.int[100,200]}
kv.random.less=${random.int(100)}
```
2. 新建一个bean类:ConfigRandomBean
@Configuration: 声明改类是一个配置类  
@ConfigurationProperties(prefix="kv.random"): 使用前缀说明使用的值范围  
@PropertySource("classpath:random.properties"): 指明配置文件的位置

``` 
@Configuration
@ConfigurationProperties(prefix = "kv.random")
@PropertySource("classpath:random.properties")
public class ConfigRandomBean {
    private Integer age;
    private UUID uuid;
    private Integer range;
    private Integer less;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Integer getRange() {
        return range;
    }

    public void setRange(Integer range) {
        this.range = range;
    }

    public Integer getLess() {
        return less;
    }

    public void setLess(Integer less) {
        this.less = less;
    }

    @Override
    public String toString() {
        return "ConfigRandomBean{" +
                "age=" + age +
                ", uuid=" + uuid +
                ", range=" + range +
                ", less=" + less +
                '}';
    }
}

```
3. 在controller中引入ConfigValueBean
``` 

@RestController
public class HelloFourController {
    @Autowired
    ConfigRandomBean configRandomBean;

    @RequestMapping("/hello_four")
    public String helloFour(){
        return configRandomBean.toString();
    }
}

```
4. 启动项目，访问: http://localhost:8080/hello_four
 