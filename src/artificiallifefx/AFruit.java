/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artificiallifefx;

/**
 * Inherited from AFood, this class creates consumable entities (fruit)
 * @author James
 */
public class AFruit extends AFood{
    /**
     * Default constructor 
     * @param speciesIn species of entity
     * @param symbolIn symbol of entity
     * @param xPosIn xpos of entity
     * @param yPosIn ypos of entity
     * @param IDIn id of entity
     * @param iWorld world entity is in
     */
    AFruit(String speciesIn, char symbolIn, int xPosIn, int yPosIn, int IDIn, AWorld iWorld) {
        super(speciesIn, symbolIn, xPosIn, yPosIn, IDIn, iWorld);
        canMove = false;
    }

    
    
}
