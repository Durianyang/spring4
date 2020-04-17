package test;

import org.junit.Test;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import top.ywlog.bean.Car;
import top.ywlog.bean.Mobile;
import top.ywlog.extension.MyBeanPostProcessor;
import top.ywlog.extension.MyInstantiationAwareBeanPostProcessor;

/**
 * @Author: Durian
 * @Date: 2020/4/4 22:08
 * @Description: bean生命周期测试
 * 总结：
 * 虽然Car类的生命周期有了更详细的控制，但是因为实现了Spring的接口，使得bean与Spring框架紧紧关联在一起
 * 实际使用时，1、InitializingBean接口可以不使用，而使用init-method指定方法替代InitializingBean的afterPropertiesSet()方法
 * 2、DisposableBean不使用，使用destroy-method指定方法替代DisposableBean的destroy()方法
 * 3、对于BeanFactoryAware和BeanNameAware接口，前者使得bean可以感知容器，后者获得bean在配置文件中的id，一般情况下不使用，
 * 如果需要bean获取容器中其他bean时，也可以将bean作为属性注入到该bean中，而beanName也可以作为属性简单注入
 * (注解方式使用init-method和destroy-method)
 * 4、此外，Spring的后处理InitDestroyAnnotationBeanPostProcessor对标注了@PostConstruc和@PreDestroy的bean处理
 * 在bean初始化和销毁前执行相应逻辑(ApplicationContext默认装配该类)
 * 注解@PostConstruc和@PreDestroy其实就是init-method和destroy-method的作用
 * 也就是说Car类实现的四个接口的功能都可以被替代，从而减少与Spring框架的耦合性
 *
 * 构造器注入属性时与set注入属性的差别:
 * set方式注入属性需要bean有无参构造，且在bean实例化时步骤调用无参构造实例化bean，在属性设置时调用set方法注入
 * 构造器注入，不必有无参构造和set方法，在bean实例化时可以直接通过有参构造完成注入属性，之后不再设置属性
 *
 * 当bean是多例时，在BeanPostProcessor后处理器的after方法将bean返回给调用者，不再管理bean的生命周期
 * 当bean是单例时，在BeanPostProcessor后处理器的after方法将bean放入缓存池，并将bean引用返回给调用者，Spring继续管理bean生命周期
 *
 * 生命周期中主要分为四类方法：
 * 1、bean自身的方法，如构造方法(实例化时调用)，set方法(设置属性调用)，bean中的init-method和destroy-method
 *
 * 2、bean级生命周期接口方法(Spring 检测到 bean 实现 Aware 接口，则会为其注入相应的依赖, 使bean可以获得相应的 Spring 容器资源
 *  Aware类接口在Bean设置属性之后执行方法
 *   BeanFactory类型容器:
 *      BeanNameAware：注入当前 bean 对应 beanName；
 *      BeanClassLoaderAware：注入加载当前 bean 的 ClassLoader；
 *      BeanFactoryAware：注入 当前BeanFactory容器 的引用。
 *   ApplicationContext:对于 ApplicationContext 类型的容器，也提供了 Aware 接口，只不过这些 Aware 接口的注入实现，
 *   是通过 BeanPostProcessor 的方式注入的，但其作用仍是注入依赖。
 *      EnvironmentAware：注入 Environment，一般用于获取配置属性；
 *      ServletContextAware:在一个MVC应用中可以获取ServletContext对象，可以读取context中的参数。
 *      ServletConfigAware:在一个MVC应用中可以获取ServletConfig对象，可以读取config中的参数。
 *      EmbeddedValueResolverAware：注入 EmbeddedValueResolver（Spring EL解析器），一般用于参数解析；
 *      ApplicationContextAware（ResourceLoader、ApplicationEventPublisherAware、MessageSourceAware）：注入 ApplicationContext 容器本身。
 * BeanNameAware，BeanFactoryAware，InitializingBean和DisposableBean等，这些接口由bean直接实现
 *      - BeanNameAware和BeanFactoryAware接口的方法在bean设置属性值后调用，可以在属性注入中方式替代这两个接口方法
 *
 * 3、容器级生命周期接口方法：InstantiationAwareBeanPostProcessor和BeanPostProcessor接口，一般称他们的实现类为“后处理器”
 * 他们独立于bean，以容器附加形式注册到容器中，并通过接口反射为Spring容器扫描识别。Spring每次创建bean时，这些后处理器都会起作用
 *
 * 4、工厂后处理器接口方法：BeanFactoryPostProcessor，AspectJWeavingEnabler,CustomAutowireConfigurer等，工厂级也是容器级别的，
 * 在应用上下文装配配置文件后立即调用（只会调用一次）。注意实现多个工厂后处理器时，尽量使用Order指定它们的调用顺序！
 *
 */
public class BeanLifeCycleTest
{
    @Test
    public void beanFactoryLifeCycleTest()
    {
        // 装载配置文件,启动容器
        Resource resource = new ClassPathResource("applicationContext.xml");
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions(resource);
        // 然后分别注册两个post后置处理器
        ((ConfigurableBeanFactory) beanFactory).addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        ((ConfigurableBeanFactory) beanFactory).addBeanPostProcessor(new MyBeanPostProcessor());


        // 从容器中获取car，将触发 实例化 bean，
        Car car = beanFactory.getBean("car", Car.class);
        System.out.println(car);
        car.setColor("红色");
        // 因为是singlet bean，第二次将从缓存获取
        Car car1 = beanFactory.getBean("car", Car.class);
        System.out.println(car1);
        System.out.println("car == car1 = " + (car == car1));
        Mobile mobile = beanFactory.getBean("mobile", Mobile.class);
        System.out.println(mobile);
        Mobile mobile1 = beanFactory.getBean("mobile", Mobile.class);
        System.out.println(mobile1);
        // 关闭容器
        beanFactory.destroySingletons();
    }

    @Test
    public void applicationContextBeanLifeCycleTest()
    {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Car car = context.getBean("car", Car.class);
        System.out.println(car);
        Car car1 = context.getBean("car", Car.class);
        System.out.println(car1);
        System.out.println("car == car1 = " + (car1 == car));
        context.close();
    }

    @Test
    public void scopeTest()
    {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Car car = context.getBean("car", Car.class);
        System.out.println(car);
        System.out.println();
        // 单例模式的bean只会执行一次生命周期中的各种方法,然后放入缓存池供使用，最后跟随容器销毁
        Car car1 = context.getBean("car", Car.class);
        System.out.println(car1);
        context.close();
    }
}
