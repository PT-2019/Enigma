package editor.Entity.Interface;

public interface Lockable {
    public void lock();
    public void unlock();
    public boolean isLocked();
}
