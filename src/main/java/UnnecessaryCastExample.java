import java.nio.*;

public class UnnecessaryCastExample {

  public byte[] foo() {
    ByteBuffer bb = ByteBuffer.allocate(5 * 4);
    // this cast is necessary for Java 8, but not anymore for Java 9+
    return (byte[])bb.flip().array();
  }
}
