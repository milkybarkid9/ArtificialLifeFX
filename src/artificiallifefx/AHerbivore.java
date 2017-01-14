/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artificiallifefx;

/**
 *
 * @author James
 */
public class AHerbivore extends ALifeform{

    AHerbivore(String species, char symbol, int xposition, int yposition, int energy, int entityStack, AWorld world) {
        super(species, symbol, xposition, yposition,  energy, entityStack, world);
        canMove = true;
    }
    
    public void packCheck(){
    
    }

}
