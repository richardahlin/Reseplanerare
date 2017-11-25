package reseplanerare;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TestAPrioMap {
    
    static int nopdone = 0;
    static int lastnopdone = -1;
    static StringBuilder log = new StringBuilder();

    static void showException(Exception e, StringBuilder log) {
        System.out.println("The operation on the last line of the following code causes an exception:\n");
        System.out.print(log.toString());
        System.out.println("\nThe exception is: " + e.toString());
        System.exit(1);
    }

    static Integer readInteger(Scanner in) {
        String s = in.next();
        if (s.equals("null")) {
            return null;
        } else {
            return Integer.parseInt(s);
        }
    }

    static Pair<String, Integer> readStringIntegerPair(Scanner in) {
        String s = in.next();
        if (s.equals("null")) {
            return null;
        } else {
            int i = in.nextInt();
            return new Pair<>(s, i);
        }
    }

    static <E> String show(E o) {
        if (o == null) {
            return "null";
        } else {
            return o.toString();
        }
    }
    
    static <E> boolean equal(E o1, E o2) {
        if (o1 == null) {
            return o2 == null;
        } else {
            return o1.equals(o2);
        }
    }
    
    static void test(Scanner in) {
        log = new StringBuilder();

        log.append("APrioMap<String> pm = new APrioMap<>();\n");
        APrioMap<String, Integer> pm = new APrioMap<>();

        for (;;) {
            String s = in.next();
            if (s.equals("end")) break;
            
            if (s.equals("put")) {
                String k = in.next();
                int prio = in.nextInt();
                log.append("pm.put(\"" + k + "\", " + prio + ");\n");
                try {
                    pm.put(k, prio);
                } catch (Exception e) {
                    showException(e, log);
                }
            } else if (s.equals("get")) {
                String k = in.next();
                Integer expres = readInteger(in);
                log.append("pm.get(\"" + k + "\");");
                Integer res = null;
                try {
                    res = pm.get(k);
                } catch (Exception e) {
                    showException(e, log);
                }
                log.append("  // result: " + show(res) + "\n");
                if (!equal(res, expres)) {
                    System.out.println("The result on the last line of the following code is incorrect:\n");
                    System.out.print(log.toString());
                    System.out.println("The result should be: " + show(expres));
                    System.exit(1);
                }
            } else if (s.equals("peek")) {
                Pair<String, Integer> expres = readStringIntegerPair(in);
                log.append("pm.peek();");
                Pair<String, Integer> res = null;
                try {
                    res = pm.peek();
                } catch (Exception e) {
                    showException(e, log);
                }
                log.append("  // result: " + show(res) + "\n");
                if (!equal(res, expres)) {
                    System.out.println("The result on the last line of the following code is incorrect:\n");
                    System.out.print(log.toString());
                    System.out.println("The result should be: " + show(expres));
                    System.exit(1);
                }
            } else if (s.equals("poll")) {
                Pair<String, Integer> expres = readStringIntegerPair(in);
                log.append("pm.poll();");
                Pair<String, Integer> res = null;
                try {
                    res = pm.poll();
                } catch (Exception e) {
                    showException(e, log);
                }
                log.append("  // result: " + show(res) + "\n");
                if (!equal(res, expres)) {
                    System.out.println("The result on the last line of the following code is incorrect:\n");
                    System.out.print(log.toString());
                    System.out.println("The result should be: " + show(expres));
                    System.exit(1);
                }
            } else {
                System.err.println("invalid command: " + s);
                System.exit(1);
            }
            nopdone++;
        }
    }

    private static class Monitor implements Runnable {
        @Override
        public void run() {
            for (;;) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    return;
                }
                if (nopdone == lastnopdone) {
                    System.out.println("No new operation completed during the last second.");
                    System.out.println("Your program seems to loop (or be very slow) at the operation on the last line of the following code:\n");
                    System.out.print(log.toString());
                    System.exit(1);
                }
                lastnopdone = nopdone;
                System.out.println(nopdone + " operations executed. (No bugs found so far.)");
            }
        }
        
    }

    public static void main(String[] args) {
        String filename = "apriomaptestcases.txt";
        new Thread(new Monitor()).start();

        Scanner in = null;
        try {
            in = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            System.out.println("The file " + filename + " is not found.");
        }

        for (;;) {
            test(in);
            if (!in.hasNext()) break;
        }

        System.out.println("Ran " + nopdone + " operations. No bugs found.");
        System.exit(0);
    }    
}
