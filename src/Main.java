import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        StringBuilderWithUndoMethod sb = new StringBuilderWithUndoMethod();
        sb.append("Кастомный StringBuilder ");
        System.out.println(sb);
        sb.append("Умеет откатывать состояние ");
        System.out.println(sb);
        sb.append("Хранит в себе стек всех предыдущих состояний");
        System.out.println(sb);
        sb.undo();
        System.out.println(sb);
        sb.undo();
        System.out.println(sb);
        sb.undo();
        System.out.println(sb);
    }
}

interface Memento {
    String getStage();
}

class Snapshot implements Memento {

    private final String state;

    public Snapshot(String state) {
        this.state = state;
    }

    @Override
    public String getStage() {
        return state;
    }
}

class StringBuilderWithUndoMethod {

    private StringBuilder stringBuilder;
    private Stack<Memento> history;

    public StringBuilderWithUndoMethod (){
        this.stringBuilder = new StringBuilder();
        this.history = new Stack<>();
    }

    private void saveState() {
        history.push(new Snapshot(stringBuilder.toString()));
    }

    public StringBuilderWithUndoMethod append(String s) {
        saveState();
        stringBuilder.append(s);
        return this;
    }

    public StringBuilderWithUndoMethod insert(int offset, String s) {
        saveState();
        stringBuilder.insert(offset, s);
        return this;
    }

    public StringBuilderWithUndoMethod delete(int start, int end) {
        saveState();
        stringBuilder.delete(start, end);
        return this;
    }

    public StringBuilderWithUndoMethod replace(int start, int end, String s){
        saveState();
        stringBuilder.replace(start, end, s);
        return this;
    }

    public void undo(){
        if (!history.isEmpty()) {
            Memento memento = history.pop();
            stringBuilder = new StringBuilder(memento.getStage());
        }
    }
    @Override
    public String toString() {
        return stringBuilder.toString();
    }
}