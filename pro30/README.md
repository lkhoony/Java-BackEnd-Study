# 스프링으로 답변형 게시판 만들기
## 개요
- 회원 관리 기능과 답변형 게시판 기능을 추가한 웹 애플리케이션 구현

## 1. Web Application Context(Container)
> Web Application에서 사용되는 Container에 대한 범위(scope)와 특징들에 대해서 알아보자

### (1) Application Context
- Web Application 최상단에 위치하고 있는 Context

- Spring에서 ApplicationContext란 BeanFactory를 상속받고 있는 Context

- Spring에서 root-context.xml, applicationContext.xml 파일은 ApplicationContext 생성 시 필요한 설정정보를 담은 파일 (Bean 선언 등..)

- Spring에서 생성되는 Bean에 대한 IoC Container (또는 Bean Container)

- 특정 Servlet설정과 관계 없는 설정을 한다 (@Service, @Repository, @Configuration, @Component)

- 서로 다른 여러 Servlet에서 공통적으로 공유해서 사용할 수 있는 Bean을 선언한다.

- __Application Context에 정의된 Bean은 Servlet Context에 정의 된 Bean을 사용할 수 없다.__

### (2) Servlet-Context (servlet-context.xml)

- Servlet 단위로 생성되는 context

- Spring에서 servlet-context.xml 파일은 DispatcherServlet 생성 시에 필요한 설정 정보를 담은 파일 __(Interceptor, Bean생성, ViewResolver등..)__

- URL설정이 있는 Bean을 생성 (@Controller, Interceptor)

- Application Context를 자신의 부모 Context로 사용한다.

- Application Context와 Servlet Context에 같은 id로 된 Bean이 등록 되는 경우, Servlet Context에 선언된 Bean을 사용한다.
- Bean 찾는 순서
	- Servlet Context에서 먼저 찾는다.
	- 만약 Servlet Context에서 bean을 못찾는 경우 Application Context에 정의된 bean을 찾는다.

- Servlet Context에 정의된 Bean은 Application Context의 Bean을 사용할 수 있다.

### (3) Context를 나누는 기준

#### Application Context

- 공통 기능을 할 수 있는 Bean설정 (Datasource, Service 등..)

- 각 Servlet에서 공유할 수 있는 Bean

#### Servlet Context

- Servlet 구성에 필요한 Bean 설정 (Controller, Interceptor, MappingHandler등..)

## 2. web.xml 설정
> Web Application Deployment Description에 어떤 내용이 들어가는지 살펴보자

- WAS 구동 시 /WEB-INF 디렉토리에 존재하는 web.xml을 읽어 웹 어플리케이션의 설정을 구성

```
<?xml version="1.0" encoding="UTF-8"?>
<web-app version="2.5" xmlns="http://java.sun.com/xml/ns/javaee"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://java.sun.com/xml/ns/javaee https://java.sun.com/xml/ns/javaee/web-app_2_5.xsd">

	<!-- ------------------------------------------------------------------------------------ -->
	<!-- The definition of the Root Spring Container shared by all Servlets and Filters -->
	<!-- action-mybatis.xml에서 설정한 Bean은 모든 서블릿, 필터에서 공유-->
	<context-param>
		<param-name>contextConfigLocation</param-name>
		<param-value>/WEB-INF/spring/action-mybatis.xml</param-value>
	</context-param>
	
	<!-- ------------------------------------------------------------------------------------ -->
	<!-- Creates the Spring Container shared by all Servlets and Filters -->
	<!-- ContextLoader : Controller가 공유하는 Bean들을 포함하는 Spring Container를 생성 -->
	<listener>
		<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
	</listener>

	<!-- ------------------------------------------------------------------------------------ -->
	<!-- Processes application requests -->
	<!-- DispatcherServlet에서만 참고하는 Spring Container 생성 -->
	<servlet>
		<servlet-name>appServlet</servlet-name>
		<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
		<init-param>
			<param-name>contextConfigLocation</param-name>
			<param-value>/WEB-INF/spring/appServlet/servlet-context.xml</param-value>
		</init-param>
		<load-on-startup>1</load-on-startup>
	</servlet>
		
	<servlet-mapping>
		<servlet-name>appServlet</servlet-name>
		<url-pattern>/</url-pattern>
	</servlet-mapping>

</web-app>
```

### (1) action-mybatis.xml

- Application Context설정을 위한 설정 파일로써 Database 연결에 필요한 정보와 Bean들을 설정(jdbc.properties, sqlSessionFactory, sqlSession 등)

- mybatis를 사용하기 위해 설정되는 modelConfig.xml과 mapper파일 (board.xml, member.xml 등)이 입력됨

```
<?xml version="1.0" encoding="UTF-8" ?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:p="http://www.springframework.org/schema/p"
	xmlns:aop="http://www.springframework.org/schema/aop"
	xmlns:context="http://www.springframework.org/schema/context"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans   
http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
http://www.springframework.org/schema/aop
http://www.springframework.org/schema/aop/spring-aop-3.0.xsd
http://www.springframework.org/schema/context
http://www.springframework.org/schema/context/spring-context-3.0.xsd">

  <!-- PropertyPlaceholderConfig : 외부의 프로퍼티에 저장된 정보를 스프링 설정파일에서 사용할 수 있음 -->
  <bean id="propertyPlaceholderConfigurer" class="org.springframework.beans.factory.config.PropertyPlaceholderConfigurer">
    <property name="location">
      <value>/WEB-INF/config/jdbc/jdbc.properties</value>
    </property>
  </bean>
  
  <bean id="dataSource" class="org.apache.ibatis.datasource.pooled.PooledDataSource">
    <property name="driver" value="${jdbc.driverClassName}"></property>
    <property name="url" value="${jdbc.url}"></property>
    <property name="username" value="${jdbc.username}"></property>
    <property name="password" value="${jdbc.password}"></property>
  </bean>
  
  <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
    <property name="dataSource" ref="dataSource"></property>
    <property name="configLocation" value="classpath:mybatis/model/modelConfig.xml"></property>
    <property name="mapperLocations" value="classpath:mybatis/mappers/*.xml"></property>
  </bean>
  
  <bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate">
    <constructor-arg index="0" ref="sqlSessionFactory"></constructor-arg>
  </bean>
  
</beans>
```

### (2) servlet-context.xml

- Servlet Context를 위한 설정파일로 View Resolver, Tiles, Interceptors 등을 설정
```
<?xml version="1.0" encoding="UTF-8"?>
<beans:beans xmlns="http://www.springframework.org/schema/mvc"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:beans="http://www.springframework.org/schema/beans"
	xmlns:mvc="http://www.springframework.org/schema/mvc"
	xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="http://www.springframework.org/schema/mvc https://www.springframework.org/schema/mvc/spring-mvc.xsd
		http://www.springframework.org/schema/beans https://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd">

	<!-- DispatcherServlet Context: defines this servlet's request-processing infrastructure -->
	
	<!-- Enables the Spring MVC @Controller programming model -->
	<annotation-driven />

	<!-- Handles HTTP GET requests for /resources/** by efficiently serving up static resources in the ${webappRoot}/resources directory -->
	<resources mapping="/resources/**" location="/resources/" />

	<!-- Resolves views selected for rendering by @Controllers to .jsp resources in the /WEB-INF/views directory -->
	<!-- 
	<beans:bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
		<beans:property name="prefix" value="/WEB-INF/views/" />
		<beans:property name="suffix" value=".jsp" />
	</beans:bean>
	 -->
	 
	<!-- tiles configure -->
	 <beans:bean id="tilesConfigure" class="org.springframework.web.servlet.view.tiles2.TilesConfigurer">
	 	<beans:property name="definitions">
	 		<beans:list>
	 			<beans:value>classpath:tiles/*.xml</beans:value>
	 		</beans:list>
	 	</beans:property>
	 	<beans:property name="preparerFactoryClass" value="org.springframework.web.servlet.view.tiles2.SpringBeanPreparerFactory">
	 	</beans:property>
	 </beans:bean>
	 
	 <!-- view resolver -->
	 <beans:bean id="viewResolver" class="org.springframework.web.servlet.view.UrlBasedViewResolver">
	 	<beans:property name="viewClass" value="org.springframework.web.servlet.view.tiles2.TilesView"></beans:property>
	 </beans:bean>
	 
	 <!-- interceptor -->
	 <mvc:interceptors>
	 	<mvc:interceptor>
	 		<mvc:mapping path="/*/*.do"/>
	 		<beans:bean class="com.myspring.pro30.common.interceptor.ViewNameInterceptor"></beans:bean>
	 	</mvc:interceptor>
	 </mvc:interceptors>
	 
	 <context:component-scan base-package="com.myspring.pro30" />
	 
</beans:beans>
```

### (3) component-scan

- base-package에 입력된 대상에서 어노테이션(@Controller, @Component 등)으로 설정된 클래스들을 Bean으로 등록해주는 Filter 속성

```
<!-- com.myspring.pro30에 있는 모든 어노테이션으로 설정된 클래스들을 Bean으로 등록 -->
<context:component-scan base-package="com.myspring.pro30" />
```
