package tutorial.beans.propertyeditors;

import tutorial.beans.Pet;
import tutorial.beans.Species;

import java.beans.PropertyEditorSupport;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * @author HeCG
 * @description xxx
 * @date 2023/2/1 13:44
 * @since 1.0
 */
public class OwnerEditor extends PropertyEditorSupport {

    public void setAsText(String text) throws IllegalArgumentException {
        System.out.println(">>>>>>>>>>>>>>");
        // text contains a sequence of petname/age/species, separated by comma’s
        ArrayList pets = new ArrayList();
        StringTokenizer t = new StringTokenizer(text, ",");
        while (t.hasMoreTokens()) {
            String name_owner_species = t.nextToken();
            StringTokenizer nos = new StringTokenizer(name_owner_species, "/");
            Pet pet = new Pet();
            pet.setName(nos.nextToken());
            try {
                pet.setAge(Integer.parseInt(nos.nextToken()));
            } catch(NumberFormatException e) {
                pet.setAge(0);
            }
            pet.setSpecies(new Species(nos.nextToken()));
            pets.add(pet);
        }
        setValue(pets);
    }

}
