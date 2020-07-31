package leetcode.multithreading;

class Foo {
    private volatile boolean finishFirst, finishSecond;

    public Foo() {
    }

    public void first(Runnable printFirst) throws InterruptedException {
        synchronized (this) {
            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            finishFirst = true;

            // Must wake up all sleeping threads.
            notifyAll();
        }
    }

    public void second(Runnable printSecond) throws InterruptedException {
        synchronized (this) {
            while (!finishFirst) {
                wait();
            }

            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();
            finishSecond = true;
            notifyAll();
        }
    }

    public void third(Runnable printThird) throws InterruptedException {
        synchronized (this) {
            while (!finishSecond) {
                wait();
            }

            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();
        }
    }
}