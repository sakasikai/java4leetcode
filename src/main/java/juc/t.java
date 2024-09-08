package juc;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author maiqi
 * @title t
 * @description TODO
 * @create 2024/1/27 22:42
 */
public class t {
    ConcurrentHashMap chm = new ConcurrentHashMap<>();

    ReentrantLock lock = new ReentrantLock();
}
