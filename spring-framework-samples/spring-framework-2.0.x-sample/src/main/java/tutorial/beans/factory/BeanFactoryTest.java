package tutorial.beans.factory;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;
import tutorial.beans.IPet;
import tutorial.beans.ISpecies;
import tutorial.beans.Owner;

import java.util.ListIterator;

/**
 * @author HeCG
 * @description xxx
 * @date 2023/2/1 14:48
 * @since 1.0
 */
public class BeanFactoryTest {

    public void useFactory(String file) {
        XmlBeanFactory fac = new XmlBeanFactory(new FileSystemResource(file), null);
        // Find bean isabelle
        Owner isabelle = (Owner)fac.getBean("isabelle");
        System.out.println("Found bean: " + isabelle.getName() + " with pets: ");
        ListIterator iter = isabelle.getPets().listIterator();
        while (iter.hasNext()) {
            IPet pet = (IPet)iter.next();
            ISpecies sp = pet.getSpecies();
            System.out.println(pet.getName() + " of species " + sp.getName());
        }
    }

    public static void main(String[] args) {

        BeanFactoryTest demo = new BeanFactoryTest();
        demo.useFactory("E:\\obank\\code\\github\\HeCG95\\thinking-in-spring-boot-samples\\spring-framework-samples\\spring-framework-2.0.x-sample\\src\\main\\java\\tutorial\\beans\\factory\\pets.xml");

    }

}
