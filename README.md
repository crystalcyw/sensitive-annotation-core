# sensitive-annotation-core
自定义数据脱敏注解

## 使用方法:
  ##### 1、引入依赖
         <dependency>
            <groupId>com.zzyk</groupId>
            <artifactId>sensitive-annotation-core</artifactId>
            <version>1.0.0</version>
        </dependency>
  ##### 2、@SpringBootApplication(baseScanPackages="com.zzyk.sensitive")
  ##### 3、在需要脱敏的实体类字段上标注@SensitiveField(value=Sensitivetype.MOBILE_PHONE)
  ##### 4、在controller的方法或者类上标注@Sensitive
