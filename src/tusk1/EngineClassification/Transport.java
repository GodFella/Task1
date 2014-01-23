/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tusk1.EngineClassification;

/**
 *
 * @author serg
 */
public interface Transport {
    void takePeopleOnBoard(int amount);
    int getCapacity();
    int getCurrentLoad();
}
