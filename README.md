# sensitive-annotation-core
自定义数据脱敏注解

## 使用方法:
  ##### 1、@SpringBootApplication(baseScanPackages="com.zzyk.sensitive")
  ##### 2、在需要脱敏的实体类字段上标注@SensitiveField(value=Sensitivetype.MOBILE_PHONE)
  ##### 3、在controller的方法或者类上标注@Sensitive
