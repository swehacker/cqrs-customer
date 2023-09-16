package swehacker.demo.cqrs.entities;

import swehacker.demo.cqrs.events.BaseEvent;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class AggregateRoot<K> {
    protected K id;
    private int version = -1;

    private final List<BaseEvent<K>> changes = new ArrayList<>();
    private final Logger logger = Logger.getLogger(AggregateRoot.class.getName());

    public K getId() {
        return this.id;
    }

    public int getVersion() {
        return this.version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public List<BaseEvent<K>> getUncommittedChanges() {
        return this.changes;
    }

    public void markChangesAsCommitted() {
        this.changes.clear();
    }

    protected void applyChange(BaseEvent<K> event, Boolean isNewEvent) {
        try {
            var method = getClass().getDeclaredMethod("apply", event.getClass());
            method.setAccessible(true);
            method.invoke(this, event);
        } catch (NoSuchMethodException e) {
            logger.log(Level.WARNING, MessageFormat.format("The apply method was not found in the aggregate for {0}", event.getClass().getName()));
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error applying event to aggregate", e);
        } finally {
            if (Boolean.TRUE.equals(isNewEvent)) {
                changes.add(event);
            }
        }
    }

    public void raiseEvent(BaseEvent<K> event) {
        applyChange(event, true);
    }

    public void replayEvents(Iterable<BaseEvent<K>> events) {
        events.forEach(event -> applyChange(event, false));
    }
}
