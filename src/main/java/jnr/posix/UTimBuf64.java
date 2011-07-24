package jnr.posix;

import com.kenai.jaffl.struct.Struct;

public final class UTimBuf64 extends Struct {
    public final Signed64 actime = new Signed64();
    public final Signed64 modtime = new Signed64();

    public UTimBuf64(long actime, long modtime) {
        this.actime.set(actime);
        this.modtime.set(modtime);
    }
}
