package org.example.lab1.model.exceptions;

import org.aspectj.weaver.ast.Not;

public class NotAvailableCopiesOfThisBook extends RuntimeException{
    public NotAvailableCopiesOfThisBook(){
        super("Not AvailableCopiesOfThisBook");
    }
}
