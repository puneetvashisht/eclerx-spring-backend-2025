package com.pv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransferService {
    
    @Autowired
    TransferRepository transferRepository;

    public TransferService(TransferRepository transferRepository) {
        this.transferRepository = transferRepository;
    }

    public void execute() {
        transferRepository.execute();
        System.out.println("Transfer executed...");
    }
}
