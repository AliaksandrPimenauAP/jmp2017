package com.epam.jmp2017.model.impl.action;

import com.epam.jmp2017.model.Action;
import com.epam.jmp2017.model.Data;
import com.epam.jmp2017.model.impl.data.Dog;

//Solid
public class PrintIfBlackDog implements Action {
    @Override
    public boolean check(Data data) {
        return data != null &&
                data instanceof Dog &&
                "black".equalsIgnoreCase(((Dog)data).getColor());
    }

    @Override
    public String perform(Data data) {
        if(data == null) {
            return "";
        }
        return data.print();
    }
}
