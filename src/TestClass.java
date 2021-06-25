import java.util.ArrayList;
import java.util.List;

public class TestClass {
    public static void main(String[] args) {
        Board board = new SquareBoard(4);
        Game game2048 = new Game2048();
        System.out.println(game2048.canMove());
        MyClass<Object> my = new MyClass<>();
        my.setT("string");
        System.out.println(my.getT());
        MyClass<Object> my2 = new MyClass<>();
        my2.setT(new Object());
        System.out.println(my2.getT());
    }

    static private class MyClass<T>{
        private T t;

        T getT() {
            return t;
        }

        void setT(T t) {
            this.t = t;
        }
    }
}
