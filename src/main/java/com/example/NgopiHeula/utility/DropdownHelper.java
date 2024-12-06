package com.example.NgopiHeula.utility;

import com.example.NgopiHeula.model.dto.dropdown.DropdownDTO;

import java.util.LinkedList;
import java.util.List;

public class DropdownHelper {
    public static List<DropdownDTO> roles(){
        List<DropdownDTO> result = new LinkedList<>();
        result.add(new DropdownDTO("admin","Admin"));
        result.add(new DropdownDTO("cashier","Cashier"));
        return result;
    }
    public static List<DropdownDTO> isAvailable(){
        List<DropdownDTO> result = new LinkedList<>();
        result.add(new DropdownDTO("True","Yes"));
        result.add(new DropdownDTO("False","No"));
        return result;
    }
    public static List<DropdownDTO> Bulan(){
        List<DropdownDTO> result = new LinkedList<>();
        result.add(new DropdownDTO("1","January"));
        result.add(new DropdownDTO("2","February"));
        result.add(new DropdownDTO("3","March"));
        result.add(new DropdownDTO("4","April"));
        result.add(new DropdownDTO("5","May"));
        result.add(new DropdownDTO("6","June"));
        result.add(new DropdownDTO("7","July"));
        result.add(new DropdownDTO("8","August"));
        result.add(new DropdownDTO("9","September"));
        result.add(new DropdownDTO("10","October"));
        result.add(new DropdownDTO("11","November"));
        result.add(new DropdownDTO("12","December"));
        return result;
    }
}
