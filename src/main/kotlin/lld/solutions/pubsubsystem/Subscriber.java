package lld.solutions.pubsubsystem;

public interface Subscriber {
    void onMessage(Message message);
}
