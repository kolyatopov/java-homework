package edu.phystech.hw4.stepper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author kzlv4natoly
 */

public class Stepper {

    public enum Side {
        LEFT, RIGHT
    }

    private final List<Side> history = new ArrayList<>();
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition mayStep = lock.newCondition();
    private boolean isLeftTurn = true;

    public void leftStep() throws InterruptedException {
        lock.lock();
        try {
            while (!isLeftTurn) {
                mayStep.await();
            }
            history.add(Side.LEFT);
            isLeftTurn = false;
            mayStep.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void rightStep() throws InterruptedException {
        lock.lock();
        try {
            while (isLeftTurn) {
                mayStep.await();
            }
            history.add(Side.RIGHT);
            isLeftTurn = true;
            mayStep.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public List<Side> getHistory() {
        lock.lock();
        try {
            return new ArrayList<>(history);
        } finally {
            lock.unlock();
        }
    }
}
