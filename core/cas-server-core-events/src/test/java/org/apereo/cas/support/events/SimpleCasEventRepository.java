package org.apereo.cas.support.events;

import org.apereo.cas.support.events.dao.AbstractCasEventRepository;
import org.apereo.cas.support.events.dao.CasEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Stream;

/**
 * A simple repostiory to facitilate testing.
 * @author  David Malia
 * @since 6.5
 */
public class SimpleCasEventRepository extends AbstractCasEventRepository {
    private final Map<String, CasEvent> events = new HashMap<>();

    public SimpleCasEventRepository(final CasEventRepositoryFilter eventRepositoryFilter) {
        super(eventRepositoryFilter);
    }

    @Override
    public CasEvent saveInternal(final CasEvent event) {
        events.put(UUID.randomUUID().toString(), event);
        return event;
    }

    @Override
    public Stream<CasEvent> load() {
        return events.values().stream();
    }

    /**
     * remove events from event repository.
     */
    public void clearEvents(){
        events.clear();
    }

}
