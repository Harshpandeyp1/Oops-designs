package factory;

import product.Button;
import product.CheckBox;

import java.nio.Buffer;

public interface UIFactory {
    Button createButton();
    CheckBox createCheckBox();
}
