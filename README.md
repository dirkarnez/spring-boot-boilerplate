# spring-boot-boilerplate

Reference: https://juejin.im/post/5aa3917af265da239c7af858
```
keytool -genkey -keyalg RSA -alias selfsigned -keystore src/main/resources/keystore.jks -storepass xxx -validity 360 -keysize 2048
```

## Contains
- [x] Tomcat swapped for Undertow
- [x] HTTP/2, https enabled
- [x] Server-push enabled (requires servlet version 4 and undertow2)
