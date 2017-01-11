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
public class AFruit extends AFood{

    AFruit(String speciesIn, char symbolIn, int xPosIn, int yPosIn, int IDIn, AWorld iWorld) {
        super(speciesIn, symbolIn, xPosIn, yPosIn, IDIn, iWorld);
        canMove = false;
    }
    
}
