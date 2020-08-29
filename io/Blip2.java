package io;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import static io.Blips.print;

class Blip2 implements Externalizable {
    Blip2() {
        print("Blip2 Constructor");
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        print("Blip2.writeExternal");
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        print("Blip2.readExternal");
    }
}
