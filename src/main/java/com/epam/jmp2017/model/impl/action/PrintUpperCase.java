package com.epam.jmp2017.model.impl.action;

import com.epam.jmp2017.model.Action;
import com.epam.jmp2017.model.Data;

//Solid
public class PrintUpperCase implements Action {
    @Override
    public boolean check(Data data) {
        return data != null;
    }

    @Override
    public String perform(Data data) {
        if(data == null) {
            return "";
        }
        return data.print().toUpperCase();
    }
}
