/**
 * 1.Пять безмолвных философов сидят вокруг круглого стола, перед каждым философом стоит тарелка спагетти.
 * 2.Вилки лежат на столе между каждой парой ближайших философов.
 * 3.Каждый философ может либо есть, либо размышлять.
 * 4.Философ может есть только тогда, когда держит две вилки — взятую справа и слева.
 * 5.Философ не может есть два раза подряд, не прервавшись на размышления
 * 6.Можно брать только две вилки одновременно
 *
 *
 * Описать в виде кода такую ситуацию. Каждый философ должен поесть три раза
 */
package own;


public class Main {
    public static void main(String[] args) {
//        long start  = System.currentTimeMillis();
        int countPhilosophers = 5;
        int howManyTimesShouldIEat = 3;
        Philosopher[] philosophers = new Philosopher[countPhilosophers];
        Fork[] forks = new Fork[countPhilosophers];

        Thread[] threads = new Thread[countPhilosophers];
        for (int i = 0; i < countPhilosophers; i++) {
            forks[i] = new Fork(i);
        }

        for (int i = 0; i < countPhilosophers; i++) {
            Fork leftFork = forks[i];
            Fork rightFork = forks[(i + 1) % countPhilosophers];
            philosophers[i] = new Philosopher(i, leftFork, rightFork);
            threads[i] = new Thread(philosophers[i]);
            threads[i].start();
        }


        try {
            for (int i = 0; i < countPhilosophers; i++) {
                threads[i].join();
                if (!threads[i].isAlive()) {
                    System.out.println("\n Философ " + i + " покушал " + howManyTimesShouldIEat + " раз(а) и наелся !) \n");
                }
            }
            System.out.println("\n Все Философы поели!) \n");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        long stop  = System.currentTimeMillis();
//        System.out.println(stop-start);//время выполнения нелинейно от объема задачи


    }
}
