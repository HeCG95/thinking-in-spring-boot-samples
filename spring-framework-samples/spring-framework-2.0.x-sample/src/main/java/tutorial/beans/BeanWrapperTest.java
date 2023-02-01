package tutorial.beans;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import tutorial.beans.propertyeditors.OwnerEditor;

import java.beans.PropertyEditorManager;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * @author HeCG
 * @description xxx
 * @date 2023/2/1 11:37
 * @since 1.0
 */
public class BeanWrapperTest {

    public void simpleTest(){
        Species cat = new Species();
        Species dog = new Species();
        Owner owner = new Owner();

        Pet bodo = new Pet();
        Pet pixel = new Pet();
        ArrayList list = new ArrayList();
        list.add(bodo);
        list.add(pixel);

        BeanWrapper bwbodo = new BeanWrapperImpl(bodo);
        BeanWrapper bwpixel = new BeanWrapperImpl(pixel);
        BeanWrapper bwo = new BeanWrapperImpl(owner);
        try {
            bwbodo.setPropertyValue("name", "Bodo");
            bwbodo.setPropertyValue("age", new Integer(4));
            bwbodo.setPropertyValue("species", cat);
            bwbodo.setPropertyValue("species.name", "Cat");

            bwpixel.setPropertyValue("name", "Pixel");
            bwpixel.setPropertyValue("age", new Integer(11));
            bwpixel.setPropertyValue("species", dog);
            bwpixel.setPropertyValue("species.name", "Dog");

            bwo.setPropertyValue("name", "Isabelle");
            bwo.setPropertyValue("pets", list);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String isabelle = (String)bwo.getPropertyValue("name");
        System.out.println("Bodo’s owner is " + isabelle);

        List pets = (List)bwo.getPropertyValue("pets");
        System.out.println(isabelle + "’s pets are: ");
        ListIterator iter = pets.listIterator();
        while (iter.hasNext()) {
            IPet pet = (IPet)iter.next();
            ISpecies sp = pet.getSpecies();
            System.out.println(pet.getName() + " of species " + sp.getName());
        }
    }

    public void editorTest(){
        PropertyEditorManager.registerEditor(List.class, OwnerEditor.class);
        Owner isabelle = new Owner();
        BeanWrapper bw = new BeanWrapperImpl(isabelle);

        try{
            bw.setPropertyValue("name", "Isabelle");
            bw.setPropertyValue("pets", "Bodo/4/Cat,Pixel/11/Dog,Raphael/6/Cat");
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("Property editor : " + isabelle.getName() + "’s pets are: ");
        ListIterator iter = isabelle.getPets().listIterator();
        while (iter.hasNext()) {
//            System.out.println("iter.next(): "+iter.next());
            IPet pet = (IPet)iter.next();
            ISpecies sp = pet.getSpecies();
            System.out.println(pet.getName() + " of species " + sp.getName());
        }
    }

    public static void main(String[] args) {

        BeanWrapperTest demo = new BeanWrapperTest();
//        demo.simpleTest();
        demo.editorTest();

        /*OwnerEditor editor = new OwnerEditor();
        editor.setAsText("Bodo/4/Cat,Pixel/11/Dog,Raphael/6/Cat");
        Object editorValue = editor.getValue();
        System.out.println("editorValue: "+editorValue);*/

    }

}
