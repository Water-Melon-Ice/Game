package io.github.minetrinity.game.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class PropertiesIO extends ResourceIO<Properties> {
    @Override
    public Properties read(InputStream in) {
        Properties prop = new Properties();
        try {
            prop.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

    @Override
    public void write(OutputStream out, Properties obj) {
        try {
            obj.store(out, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isReadable(String format) {
        return format.equalsIgnoreCase("properties");
    }

    @Override
    public boolean isWriteable() {
        return true;
    }
}
