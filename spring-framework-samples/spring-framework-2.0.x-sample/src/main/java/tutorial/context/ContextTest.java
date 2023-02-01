package tutorial.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import tutorial.beans.IOwner;
import tutorial.beans.IPet;
import tutorial.beans.propertyeditors.OwnerEditor;

import java.beans.PropertyEditorManager;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * @author HeCG
 * @description xxx
 * @date 2023/2/1 15:56
 * @since 1.0
 */
public class ContextTest {

    private ApplicationContext ctx;

    public ContextTest(){
        PropertyEditorManager.registerEditor(List.class, OwnerEditor.class);
        String sep = System.getProperty("file.separator");
        // The parent context
        String buff1 = "E:\\obank\\code\\github\\HeCG95\\thinking-in-spring-boot-samples\\spring-framework-samples\\spring-framework-2.0.x-sample\\src\\main\\java\\tutorial\\context\\appContext.xml";

        // The child context
        String buff2 = "E:\\obank\\code\\github\\HeCG95\\thinking-in-spring-boot-samples\\spring-framework-samples\\spring-framework-2.0.x-sample\\src\\main\\java\\tutorial\\context\\babyContext.xml";
        try {
            ctx = new FileSystemXmlApplicationContext(new String[]
                    {buff1, buff2});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listBeans() {
        try {
            IPet pet = (IPet)ctx.getBean("bodo");
            if (null != pet)
                System.out.println("Found " + pet.getName() + " of species " +
                        pet.getSpecies().getName());
            pet = (IPet)ctx.getBean("raphael");
            if (null != pet)
                System.out.println("Found " + pet.getName() + " of species " +
                        pet.getSpecies().getName());
            pet = (IPet)ctx.getBean("pixel");
            if (null != pet)
                System.out.println("Found " + pet.getName() + " of species " +
                        pet.getSpecies().getName());
            ApplicationContext parent = ctx.getParent();
            if (null != parent) {
                IOwner o = (IOwner)parent.getBean("isabelle");
                if (null != o)
                    System.out.println("Found " + o.getName());
            }
        } catch (BeansException e) {
            e.printStackTrace();
        }
    }

    public void testSharedObject() {
        /*class Foo {
            void foo() {
                System.out.println("I am a Foo shared object");
            }
        }
        ctx.shareObject("foo", new Foo());
        Foo foo = (Foo)ctx.sharedObject("foo");
        if (null != foo)
            foo.foo();*/
    }

    public void testMessages() {
        System.out.println(ctx.getMessage("tutorial.context.defaultmsg",
                null, Locale.getDefault()));
        Object[] arguments = {new Date(System.currentTimeMillis())};
        System.out.println(ctx.getMessage("tutorial.context.othermsg",
                arguments, Locale.getDefault()));
    }

    public static void main(String[] args) {

        ContextTest demo = new ContextTest();
//        demo.listBeans();
//        demo.testSharedObject();
        demo.testMessages();

    }

}
