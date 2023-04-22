package org.lab_5;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class MockConsole implements Console {
    private List<String> input;
    private List<String> output;

    public MockConsole(List<String> input) {
        this.input = input;
        this.output = new ArrayList<>();
    }

    public String readLine() {
        if (input.isEmpty()) {
            throw new NoSuchElementException("No more input!");
        }
        String line = input.remove(0);
        output.add(line);
        return line;
    }

    public void print(String s) {
        output.add(s);
    }

    public List<String> getOutput() {
        return output;
    }
}
