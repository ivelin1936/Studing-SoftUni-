package models.behavors;

import interfaces.Attack;
import interfaces.Behavior;
import models.Blob;
import observers.Subject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class InflatedTest {

    private final String DEFAULT_NAME = "Boko";
    private final int DEFAULT_DAMAGE = 50;
    private final int DEFAULT_HEALTH = 20;
    private final int AGGRESSIVE_DAMAGE_DECREMENT = 5;

    private Behavior inflated;
    private Blob blob;

    @Before
    public void init() throws Exception {
        this.inflated = new Inflated();
        Attack attack = Mockito.mock(Attack.class);
        Subject subject = Mockito.mock(Subject.class);

        this.blob = new Blob(
                DEFAULT_NAME,
                DEFAULT_HEALTH,
                DEFAULT_DAMAGE,
                this.inflated,
                attack,
                subject);
    }

    @Test
    public void trigger() {
        //TODO...
    }

    @Test
    public void applyRecurrentEffect() {
        //TODO...
    }
}