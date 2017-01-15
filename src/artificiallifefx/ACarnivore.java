/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artificiallifefx;

/**
 * Inherited from ALifeform, this class is represented as a Bee and has different pack behaviour to herbivores. 
 * @author James
 */
public class ACarnivore extends ALifeform{

    /**
     * Default constructor
     * @param species species of entity
     * @param symbol symbol of entity
     * @param xposition xpos of entity
     * @param yposition ypos of entity
     * @param energy energy of entity
     * @param ID ID of entity
     * @param world instance of world
     */
    ACarnivore(String species, char symbol, int xposition, int yposition, int energy, int ID, AWorld world) {
        super(species, symbol, xposition, yposition,  energy, ID, world);
        canMove = true;
    }
    
    /**
     * not yet implemented
     */
    public void packCheck(){
    
    }


}
