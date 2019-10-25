package org.hupbd.androidmultichoicesquiz.Interface;

import org.hupbd.androidmultichoicesquiz.Model.CurrentQuestion;

public interface IQuestion {

    CurrentQuestion getSelectedAnswer(); // Get Selected  Answer from user select
    void showCorrectAnswer(); //Bold Correct Answer text
    void disableAnswer (); //Disable all check box
    void resetQuestion(); //Reset all function on question
}
