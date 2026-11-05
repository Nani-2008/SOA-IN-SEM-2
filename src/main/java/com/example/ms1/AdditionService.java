package com.example.ms1;

import org.springframework.stereotype.Service;

@Service
public class AdditionService {
    public long add(int a, int b) {
        return (long) a + b;
    }
}
