package ui;

import javax.swing.*;
import java.awt.*;

public class AddRemoveDanceClass {

    private JButton addButton;
    private JButton removeButton;

    public JPanel setUpButtons() {
        addButton = new JButton("Add Dance Class");
        removeButton = new JButton("Remove Dance Class");

        JPanel buttonArea = new JPanel();
        buttonArea.setLayout(new GridBagLayout());
        buttonArea.add(addButton);
        buttonArea.add(removeButton);

        return buttonArea;
    }


}
